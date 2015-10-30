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
		tree.insert(19);
		tree.insert(24);
		
		tree.insert(10);
		tree.insert(210);
		tree.insert(17);
		tree.insert(20);
	return;
	}
}
class IntegerBTree extends btree<Integer, Integer> {
	public void insert(int key) {
		this.insert(key, key);
	}
}