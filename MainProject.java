import java.util.*;

class Cab {
    private int cabId;
    private String driverName;
    private boolean isAvailable;

    Cab(int cabId, String driverName) {
        this.cabId = cabId;
        this.driverName = driverName;
        this.isAvailable = true;
    }

    synchronized boolean book() {
        if (this.isAvailable) {
            this.isAvailable = false;
            return true;
        }
        return false;
    }

    synchronized boolean release() {
        if (!this.isAvailable) {
            this.isAvailable = true;
            return true;
        }
        return false;
    }

    int getCabId() {
        return this.cabId;
    }

    boolean getIsAvailable() {
        return this.isAvailable;
    }

    String getDriverName() {
        return this.driverName;
    }
}

class CabBookingSystem {
    private List<Cab> cabs = new ArrayList<>();
    private int bookingId = 0;

    void addACab(Cab cab) {
        this.cabs.add(cab);
        System.out.println("Cab has been added with CabId " + cab.getCabId());
    }

    synchronized void bookACab() {
        for (Cab cab : cabs) {
            if (cab.getIsAvailable() && cab.book()) {
                this.bookingId++;
                System.out.println("CabBooking is done with BookingId " + this.bookingId +
                        " CabId of " + cab.getCabId());
                return;
            }
        }
        System.out.println("No cabs Available");
    }

    synchronized void releaseCab(int cabId) {
        for (Cab cab : cabs) {
            if (cab.getCabId() == cabId && !cab.getIsAvailable()) {
                if (cab.release()) {
                    System.out.println("Cab " + cabId + " released.");
                }
                return;
            }
        }
        System.out.println("No such booked cab found.");
    }

    void displayCabs() {
        System.out.printf("%-10s%-15s%-10s\n", "CabId", "Driver", "Available");
        for (Cab cab : cabs) {
            System.out.printf("%-10d%-15s%-10s\n", cab.getCabId(), cab.getDriverName(),
                    cab.getIsAvailable() ? "Yes" : "No");
        }
    }
}

class UserThread extends Thread {
    CabBookingSystem system;
    String username;

    UserThread(String username, CabBookingSystem system) {
        this.username = username;
        this.system = system;
    }

    public void run() {
        system.bookACab();
    }
}

public class MainProject {
    public static void main(String[] args) {
        CabBookingSystem system = new CabBookingSystem();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Cab Booking System Menu:\n1. Add a Cab\n2. Book a Cab\n3. Release a Cab\n4. Display list of Cabs\nEnter the choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) {
                case 1: {
                    System.out.print("Enter number of cabs to add: ");
                    int cabCount = sc.nextInt();
                    sc.nextLine(); // consume newline
                    for (int i = 1; i <= cabCount; i++) {
                        System.out.print("Enter CabId: ");
                        int cabId = sc.nextInt();
                        sc.nextLine(); // consume newline
                        System.out.print("Enter Driver Name: ");
                        String driverName = sc.nextLine();
                        Cab cab = new Cab(cabId, driverName);
                        system.addACab(cab);
                    }
                    break;
                }
                case 2: {
                    System.out.print("Number of Users: ");
                    int n = sc.nextInt();
                    sc.nextLine(); // consume newline
                    UserThread[] threads = new UserThread[n];
                    for (int i = 0; i < n; i++) {
                        System.out.print("Enter username " + (i + 1) + ": ");
                        String username = sc.nextLine();
                        threads[i] = new UserThread(username, system);
                    }
                    for (UserThread t : threads) {
                        t.start();
                    }
                    break;
                }
                case 3: {
                    System.out.print("Enter CabId to release: ");
                    int cabId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    system.releaseCab(cabId);
                    break;
                }
                case 4: {
                    system.displayCabs();
                    break;
                }
                default:
                    System.out.println("Invalid choice");
            }
            System.out.print("Do you want to continue? (yes/no): ");
            String cont = sc.next();
            if (cont.equalsIgnoreCase("no")) {
                break;
            }
            sc.nextLine(); // consume newline
        }
        sc.close();
    }
}
