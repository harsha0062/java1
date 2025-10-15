public class String_java {
    public static void main(String[] args) {
        String str = "Hello, World!";
        String str1="Hello, World!";
        String str2="Hello";
        System.out.println(str);
        System.out.println("Length: " + str.length());
        System.out.println(str.charAt(4));    
        
        System.out.println(str==str1);

        System.out.println(str1==str2);
        System.out.println(str1.equals(str2));

    }
}
