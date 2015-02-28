package p2;

public class testing_file{
	public static void main (String[] args){
		DLinkedList<Integer> list = new DLinkedList<Integer>();
		
		list.add(10);
		list.add(5);
		list.add(1);
		
		list.add(1, 7);
		
		for(int i=0; i<list.size()-1; i++){
			System.out.println(list.get(i));
		}
	}
}