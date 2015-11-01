package cs542project2;

public class test1 {
	public static void main(String args[]){
		IntegerBTree tree = new IntegerBTree();
		
		// test for the btree on assignment4
		tree.insert(1);
		tree.insert(78);
		tree.insert(37);
		tree.insert(150);
		tree.insert(35);
		
		tree.insert(145);
		//tree.printTree();
		tree.insert(19);
		tree.insert(24);
		tree.insert(10);
		tree.insert(210);
		
		tree.insert(2);
		tree.insert(18);
		tree.insert(20);
		tree.insert(22);
		tree.insert(216);
		
		tree.insert(79);
		tree.insert(90);
		tree.insert(102);
		tree.insert(17);
		tree.insert(20);
		tree.insert(95);
		//tree.insert(21);
		//tree.insert(5);
		//tree.insert(4);
		
		//tree.printTree();
		System.out.println(tree.search(79));
		System.out.println(tree.search(90));
		System.out.println(tree.search(2));
		System.out.println(tree.search(20));
		System.out.println(tree.search(18));
		System.out.println(tree.search(50));
		
	return;
	}
}
class IntegerBTree extends btree<Integer, Integer> {
	public void insert(int key) {
		this.insert(key, key);
	}
}