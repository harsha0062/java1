import java.util.*;
import java.util.stream.*;
public class stream_lab
{
	public static void main(String[] args) {
		List<Integer> nums=Arrays.asList(1,2,3,4,5);
		List<Integer> out=nums.stream()
		.map(n->n*2)
		.filter(num->num%4==0)
		.collect(Collectors.toList());
		System.out.println(out);
		
	}
}
