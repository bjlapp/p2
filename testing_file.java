package p2;

public class testing_file{
	public static void main (String[] args){
		DLinkedList<Integer> list = new DLinkedList<Integer>();
		
		list.add(10);
		list.add(5);
		list.add(1);
		
		System.out.println(list.get(0));
	}
}