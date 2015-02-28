package p2;

public class DLinkedList<E> implements ListADT<E>{

	private Listnode<E> head;
	private Listnode<E> tail;
	private int numItems;
 	
	
	public DLinkedList(){
		this.head = new Listnode<E>(null);
		this.tail = new Listnode<E>(null);
		numItems = 0;
		//
	}
	
	@Override
	public void add(E item) {
		
		if(item == null){
			throw new IllegalArgumentException();
		}
		
		Listnode<E> newnode = new Listnode<E>(item);
		if(numItems==0){
			head = newnode;
			tail = newnode;
			numItems++;
			return;
		}
		
		tail.setNext(newnode);
		newnode.setPrev(tail);
		tail = newnode;
		numItems++;
		
		//Listnode<E> curr = head;
		/*while(curr.getNext()!=null){
			curr = curr.getNext();
		}*/

		//curr.setNext(newnode);
		
		
		}

	@Override
	public void add(int pos, E item) {
		// TODO Auto-generated method stub
		//Listnode<E> newnode = new Listnode<E>(item);
		Listnode<E> curr = head;
		if(pos == 0){
			head = new Listnode<E>(item,curr,null);
			numItems++;
			return;
		}
	
		for(int i = 0; i < pos-1; i++){
			curr = curr.getNext();
		}
		
		Listnode<E> newnode = new Listnode<E>(item,curr.getNext(),curr);
		curr.setNext(newnode);
		newnode.getNext().setPrev(newnode);
		numItems++;
	}

	@Override
	public boolean contains(E item) {
		// TODO Auto-generated method stub
		Listnode<E> curr = head;
			if(item==head){
				return true;
			}else if(head==tail){
				return false;
			}
		for(int i = 1; i < numItems; i++){
			curr = curr.getNext();
			if(curr==item){
				return true;
			}
		}
		return false;
	}

	@Override
	public E get(int pos) {
		// TODO Auto-generated method stub
		Listnode<E> curr = head;
		/*
		if(pos==0){
			return curr.getData();
		}
		*/
		for(int i = 0; i < pos; i++){
			curr = curr.getNext();
		}
		return curr.getData();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (numItems==0);
	}

	@Override
	public E remove(int pos) {
		// TODO Auto-generated method stub
		 Listnode<E> curr = head;
		 if(pos==0){
			 head = head.getNext();
			 head.setPrev(null);
			 return curr.getData();
		 }
		 for(int i = 0; i < pos-1; i++){
			 curr = curr.getNext();
		 }
		 E remove = curr.getNext().getData();
		 curr.setNext(curr.getNext().getNext());
		 curr.getNext().setPrev(curr);
		 return remove;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		
		return numItems;
	}




}
