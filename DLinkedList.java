///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AmazonStore.java
// File:             DlinkedList.java
// Semester:         CS302 Spring 2015
//
// Author:           Adam Converse
// Email:            acconverse@wisc.edu
// CS Login:         adam
// Lecturer's Name:  Jim Skrentny
// Lab Section:      105
//
// Pair Partner:     Brandon Lapp
// Email:            blapp@wisc.edu
// CS Login:         brendon
// Lecturer's Name:  Jim Skrentny
// Lab Section:      105

//package p2;

/**
 * Creates and maintains a chain of single linked nodes.  Includes a head and 
 * tail reference.
 */
public class DLinkedList<E> implements ListADT<E>{

	private Listnode<E> head;
	private Listnode<E> tail;
	private int numItems;
 	
	/**
	 * Constructs a DLinkedList
	 */
	public DLinkedList(){
		this.head = new Listnode<E>(null);
		this.tail = new Listnode<E>(null);
		numItems = 0;
		//
	}
	/**
	 * Adds an item to the end of a chain of nodes.
	 * @param item the item being added to the chain of nodes
	 * @return nothing
	 */
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

	/**
	 * Adds an item to a chain of nodes at a position
	 * @param pos position the item should be added at
	 * @param item the item being added to the chain of nodes
	 * @return nothing
	 */
	@Override
	public void add(int pos, E item) {
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

	/**
	 * Search through chain and returns true if the item was found in the array.
	 * False if it was not
	 * @param item the item to check if it is in the array
	 * @return true if item found, false if not
	 */
	@Override
	public boolean contains(E item) {
		Listnode<E> curr = head;
			if(item.equals(head.getData())){
				return true;
			}else if(head.equals(tail)){
				return false;
			}
		for(int i = 1; i < numItems; i++){
			curr = curr.getNext();
			if(curr.getData().equals(item)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Get item at position
	 * @param pos position of item to get
	 * @return item at position specified
	 */
	@Override
	public E get(int pos) {
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

	/**
	 * Return true if list is empty false if not
	 * @return true if list is empty false if not
	 */
	@Override
	public boolean isEmpty() {
		return (numItems==0);
	}

	/**
	 * Remove item at position
	 * @param pos position of item to be removed
	 * @return return item removed
	 */
	@Override
	public E remove(int pos) {
		 Listnode<E> curr = head;
		 if(pos==0){
			 head = head.getNext();
			 head.setPrev(null);
			 numItems--;
			 return curr.getData();
		 }
		 for(int i = 0; i < pos-1; i++){
			 curr = curr.getNext();
		 }
		 E remove = curr.getNext().getData();
		 curr.setNext(curr.getNext().getNext());
		 curr.getNext().setPrev(curr);
		 numItems--;
		 return remove;
	}

	/**
	 * Return size of list
	 * @return size of list
	 */
	@Override
	public int size() {
		
		return numItems;
	}




}
