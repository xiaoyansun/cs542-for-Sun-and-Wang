package cs542project2;

import java.util.LinkedList;

public class btree<TKey extends Comparable<TKey>, TValue> {
	private btreeNode<TKey> root;
	
	public btree() {
		this.root = new leafNode<TKey, TValue>();
	}
	
	public LinkedList<TValue> search(TKey key) {
		leafNode<TKey, TValue> leaf = this.findTheLeaf(key);
		int index = leaf.search(key);
		return (index == -1) ? null : leaf.getValue(index);
	}
	public void searchPrint(TKey key){
		LinkedList<TValue> results= new LinkedList<TValue>();
		results= this.search(key);
		//System.out.println(results.size());
		for(int i=0;i<results.size();i++)
			System.out.println((String)results.get(i));
	}
	
	//returns a leaf node where the key should be hold in, or in the range
	@SuppressWarnings("unchecked")
	private leafNode<TKey, TValue> findTheLeaf(TKey key) {
		btreeNode<TKey> node = this.root;
		while (node.getNodeType() == TreeNodeType.InnerNode) {
			node = ((innerNode<TKey>)node).getChild(node.search(key));
		}
		return (leafNode<TKey, TValue>)node;
	}
	
	/**
	 * Insert a new key and its associated value into the B+ tree.
	 */
	public void insert(TKey key, TValue value) {
		leafNode<TKey, TValue> leaf = this.findTheLeaf(key);
		int index=leaf.search(key);
		if(index!=-1)
			leaf.setValue(index, value);
		else{
			if (leaf.isFull()) {
			btreeNode<TKey> n = leaf.handleOverflow(key,value);
			if (n.getParent()== null)
				this.root = n; 
		}
		else 
			leaf.insertKey(key, value);
		}
	}
	
	public void printTree(){
		this.root.printNode();
		//btreeNode<TKey> newNode= new btreeNode<TKey>() ;
		
			this.root.hasChild();		
			
		
	}
}
