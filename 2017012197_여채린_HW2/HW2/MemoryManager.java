
import hw2.DLinkedList;

/* Block will be used as a type argument */
class Block {
    public int size;
    public int start;
    public int end;

    public Block(int size, int start, int end) {
        this.size = size;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + size +", " + start + ", " + end + ")";
    }
}

public class MemoryManager {

    private DLinkedList<Block> heap = new DLinkedList<>();
    //추가

    public MemoryManager(int capacity) {
    		Block freeblock1 = new Block(capacity, 0, capacity-1);
        heap.addFirst(new Node<Block>(freeblock1,null,null));
        //엣지케이스 구현!! 
    }

    public Block malloc(int size) {
    	/*
    		if(freeblock.size < size) {
    			throw new OutOfMemoryException("size lack");
    		}
    		Block newblock = new Block(size, freeblock.start, freeblock.start+size-1);
    		freeblock = new Block(freeblock.size-size, freeblock.start+size, freeblock.end);
    		return newblock;
    		*/
    		
    		Node<Block> x = heap.getFirst();
        Block curr = x.getItem();
        for(; x != null; x = x.getNext()){
            curr = x.getItem();
            if(size <= curr.size) break;
        }
        if(x == null){
            throw new OutOfMemoryException("size lack");
        }
        Block rBlock = new Block(size,curr.start,curr.start+size);
        Block nBlock = new Block(curr.size-size, curr.start+size, curr.end);
        Node<Block> nNode = new Node<Block>(nBlock,x.getPrev(),x.getNext());
        x.getPrev().setNext(nNode);
        x.getNext().setPrev(nNode);
        
        return rBlock;
        /**/
    }

    public void free(Block block) {
    		Node<Block> x = heap.getFirst();
        Block curr = x.getItem();
        for(; x != null; x = x.getNext()) {
            curr = x.getItem();
            if(block.end < curr.start){
                break;
            }
        }
        if(block.end+1 == curr.start){
            Block nBlock = new Block(curr.size+block.size, block.start, curr.end);
            Node<Block> nNode = new Node<Block>(nBlock,x.getPrev(),x.getNext());
            x.getPrev().setNext(nNode);
            x.getNext().setPrev(nNode);
        }else {
            Node<Block> nNode = new Node<Block>(block,x.getPrev(),x);
            x.getPrev().setNext(nNode);
            x.setPrev(nNode);
        }
    }

    // for debugging purpose only
    public DLinkedList<Block> getHeap() {
        return heap;
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}


