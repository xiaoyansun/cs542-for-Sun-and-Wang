package cs542project2;

enum TreeNodeType {
	InnerNode,
	LeafNode
}

abstract class btreeNode<TKey extends Comparable<TKey>> {
	protected final static int NumOfEntries=5;
	protected final static int MaxEntries = NumOfEntries+1;
	protected Object[] keys;
	protected int keyCount;
	protected btreeNode<TKey> parentNode;

	protected btreeNode() {
		this.keyCount = 0;
		this.parentNode = null;
	}

	//number of keys stored in current node
	public int getKeyCount() {
		return this.keyCount;
	}
	
	//get a certain key in current node
	@SuppressWarnings("unchecked")
	public TKey getKey(int index) {
		return (TKey)this.keys[index];
	}

	public void setKey(int index, TKey key) {
		this.keys[index] = key;
	}

	public btreeNode<TKey> getParent() {
		return this.parentNode;
	}

	public void setParent(btreeNode<TKey> parent) {
		this.parentNode = parent;
	}	
	
	public abstract TreeNodeType getNodeType();
	public abstract boolean hasChild();
	
	
	/**
	 * Search a key on current node, if found the key then return its position,
	 * otherwise return -1 for a leaf node, 
	 * return the child node index which should contain the key for a internal node.
	 */
	public abstract int search(TKey key);
	
	public abstract boolean isFull();
	
	public boolean isOverflow() {
		return this.getKeyCount() == this.keys.length;
	}
	
	public abstract btreeNode<TKey> split(TKey key, int midIndex);

	public btreeNode<TKey> furtherHandle() {
		if(!this.isOverflow()){
			return this;
		}
		int midIndex = MaxEntries / 2;
		while(this.getKey(midIndex).compareTo(null)==0)
			midIndex++;
		TKey upKey = this.getKey(midIndex);
			
		btreeNode<TKey> newNode = this.split(upKey, midIndex);
		newNode = this.split(upKey, midIndex);
		
		return newNode.furtherHandle();
	}
	
	abstract public void printNode();

}
