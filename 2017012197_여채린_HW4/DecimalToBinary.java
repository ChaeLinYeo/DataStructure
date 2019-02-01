package hw4;
import java.util.Stack;
import org.junit.Test;
public class DecimalToBinary {
	
	public void deciTobin(int n) {
			Stack<Integer> N = new Stack<Integer>();
			while(n>0){
				N.push(n%2);
				n/=2;
			}
			if(N.isEmpty()) {
				System.out.println(0);
			}
			while(!N.isEmpty()) {
				System.out.print(N.pop());
			}
			
	}
	public void deciTobinRec(int n) {
		if(n == 1 ) {
			System.out.print(1);
		}
		else if(n==0) {
			System.out.print(0);
		}
		else {
			deciTobinRec(n/2);
			System.out.print(n%2);
		}
	}
}