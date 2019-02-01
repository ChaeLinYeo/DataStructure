package hw4;
import java.util.Stack;
import org.junit.Test;

public class Quiz {
	boolean Solve(int start, int[] boxes) {
		Stack<Integer> IndexStack = new Stack<Integer>();
		Stack<Integer> ValueStack = new Stack<Integer>();
		Stack<Boolean> DirectionStack = new Stack<Boolean>();
		IndexStack.push(start);
		ValueStack.push(boxes[start]);
		DirectionStack.push(true);
		while(ValueStack.peek() != 0 && !IndexStack.isEmpty()) {
			if(DirectionStack.peek() && CheckLength(boxes.length, IndexStack.peek()+ValueStack.peek())){
				
				MoveMarker(boxes, IndexStack, ValueStack ,DirectionStack.peek());
				DirectionStack.push(true);
			}
			else if(!DirectionStack.peek() && CheckLength(boxes.length, IndexStack.peek()-ValueStack.peek())){
				
				MoveMarker(boxes, IndexStack, ValueStack ,DirectionStack.peek());
				DirectionStack.push(true);
			}
			else if(DirectionStack.peek() && !CheckLength(boxes.length, IndexStack.peek()+ValueStack.peek())) {
				
				DirectionStack.pop();
				DirectionStack.push(false);
			}
			else if(!DirectionStack.peek() && !CheckLength(boxes.length, IndexStack.peek()-ValueStack.peek())) {
				IndexStack.pop();
				ValueStack.pop();
				DirectionStack.pop();
				if(!IndexStack.isEmpty()) {
					DirectionStack.pop();
					DirectionStack.push(false);
				}
	
				
			}
			if(IndexStack.isEmpty() ||  ValueStack.peek() == 0)
				break;
			Stack<Integer> CopyIndexStack = new Stack<Integer>();
			CopyIndexStack.addAll(IndexStack);
			
			if(CheckVisted(CopyIndexStack ,IndexStack.peek())) {

				IndexStack.pop();
				ValueStack.pop();
				DirectionStack.pop();
				while(!DirectionStack.peek()) {

					IndexStack.pop();
					ValueStack.pop();
					DirectionStack.pop();
				}
				DirectionStack.pop();
				DirectionStack.push(false);
			}
		}
		if(IndexStack.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	void MoveMarker(int[] boxes, Stack<Integer> IndexStack, Stack<Integer> ValueStack , boolean Direction) {
		if(Direction) {
			IndexStack.push(IndexStack.peek() + ValueStack.peek());
			ValueStack.push(boxes[IndexStack.peek()]);
			
		}
		else {
			IndexStack.push(IndexStack.peek() - ValueStack.peek());
			ValueStack.push(boxes[IndexStack.peek()]);
		}
	}
	boolean CheckLength(int Length ,int Index) {
		if(0 <= Index && Index< Length) {
			return true;
		}
		else {
			return false;
		}
		
	} 
	boolean CheckVisted(Stack<Integer> CopyIndexStack ,int TopIndexStack) {	
		CopyIndexStack.pop();
		while(!CopyIndexStack.isEmpty()) {
			if(TopIndexStack == CopyIndexStack.pop()) {
				return true;
			}
		}
		return false;
	}
	void PrintStack(Stack<Integer> IndexStack) {	
		while(!IndexStack.isEmpty()) {
			System.out.println(IndexStack.pop());
		}
	}
}