package p2;

public class DLinkedList<E> implements ListADT<E>{

	private Listnode<E> head = null;
	private Listnode<E> tail = null;
 	
	@Override
	public void add(E item) {
		Listnode<E> temp;
		// TODO Auto-generated method stub
		if(head.equals(null)){
			new Listnode<E>(item);
			head.equals(item);
			tail.equals(item);
		}else{
			temp = new Listnode<E>(item, null, tail);
			tail.setNext(temp);
			tail = temp;
		}
		
	}

	@Override
	public void add(int pos, E item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(E item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove(int pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		
		return 0;
	}



}
