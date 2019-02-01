package hw2;
import org.junit.Test;
import hw2.*;
import hw2.MemoryManager;
public class ListTest {

	@Test
	public void Linked() {
		DLinkedList<Integer> input = new DLinkedList<Integer>();
		for(int i=0;i<10;i++) {
			Node<Integer> InputNode = new Node<>(i,null,null);
			input.addFirst(InputNode);
		}
			input.remove(input.getLast());
		System.out.println(input.toString());
		}
	@Test
	public void Manager() {
//		System.out.println("hello");
		MemoryManager manager = new MemoryManager(1000);
//		System.out.println("hello");
		Block b1 = manager.malloc(150);
//		System.out.println("hello");
		Block b2 = manager.malloc(150);
//		System.out.println("hello");
		Block b3 = manager.malloc(200);
		manager.free(b1);
		manager.free(b2);
		manager.free(b3);
//		System.out.println("hello");
		System.out.println(manager.toString());
//		System.out.println("hello");
		
		
		
	}

}