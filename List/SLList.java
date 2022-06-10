import edu.princeton.cs.algs4.In;

import java.util.Iterator;

/** An SLList is a list of integers, which hides the terrible truth
 * of the nakedness within. */
public class SLList<Item> implements List<Item>{
	private class IntNode {
		public Item item;
		public IntNode next;

		public IntNode(Item i, IntNode n) {
			item = i;
			next = n;
		}
	}

	public Iterator<Item> iterator(){
		return new SLListIterator();
	}

	private class SLListIterator implements Iterator<Item>{
		private int idx;

		public SLListIterator(){
			idx = 0;
		}

		@Override
		public Item next(){
			Item temp = get(idx);
			idx++;
			return temp;
		}

		@Override
		public boolean hasNext(){
			return idx < size;
		}
	}

	/* The first item (if it exists) is at sentinel.next. */
	private IntNode sentinel;
	private int size;

	/** Creates an empty SLList. */
	public SLList() {
		sentinel = new IntNode(null, null);
		size = 0;
	}

	public SLList(Item x) {
		sentinel = new IntNode(null, null);
		sentinel.next = new IntNode(x, null);
		size = 1;
	}

	/** Adds x to the front of the list. */
	public void addFirst(Item x) {
		sentinel.next = new IntNode(x, sentinel.next);
		size = size + 1;
	}

	/** Returns the first item in the list. */
	public Item getFirst() {
		return sentinel.next.item;
	}

	/** Adds x to the end of the list. */
	public void addLast(Item x) {
		size = size + 1;

		IntNode p = sentinel;

		/* Advance p to the end of the list. */
		while (p.next != null) {
			p = p.next;
		}

		p.next = new IntNode(x, null);
	}

	/** returns last item in the list */
	public Item getLast() {
		size = size + 1;

		IntNode p = sentinel;

		/* Advance p to the end of the list. */
		while (p.next != null) {
			p = p.next;
		}

		return p.item;
	}


		/** Returns the size of the list. */
	public int size() {
		return size;
	}

	@Override
	public Item get(int index) {
		int counter = -1; // index starts from 0
		IntNode temp = sentinel;
		while(counter < index) {
			temp = temp.next;
			counter++;
		}

		return temp.item;
	}

	public static void main(String[] args) {
		/* Creates a list of one integer, namely 10 */
		SLList L = new SLList();
		L.addLast(20);
		System.out.println(L.size());
	}
}