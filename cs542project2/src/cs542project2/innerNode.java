package cs542project2;

public class innerNode <TKey extends Comparable<TKey>> extends btreeNode<TKey>{

	protected Object[] children;
	
	public innerNode() {
		this.keys = new Object[MaxEntries];
		this.children = new Object[MaxEntries+1];
	}
	
	@SuppressWarnings("unchecked")
	public btreeNode<TKey> getChild(int index) {
		return (btreeNode<TKey>)this.children[index];
	}

	public void setChild(int index, btreeNode<TKey> child) {
		this.children[index] = child;
		if (child != null)
			child.setParent(this);
	}
	
	@Override
	public TreeNodeType getNodeType() {
		return TreeNodeType.InnerNode;
	}
	
	@Override
	public boolean isFull() {
		return this.getKeyCount() == NumOfEntries;
	}
	
	//use binary search to find the key in a inner node
	//return index of the next inner node
	private int searchKey(TKey key, int j, int r){
		if(j==r) {if(this.getKey(j).compareTo(key)<0) return j; else return j+1;}
		int m=(j+r)/2;
		while(this.getKey(m).compareTo(null)==0) m++;
		if(this.getKey(m).compareTo(key)>0) return searchKey(key, m+1, r);
		return searchKey(key, j, m);	
	}
	
	@Override
	public int search(TKey key) {
		return searchKey(key,0,NumOfEntries);
	}
	
	//the insert function do not check overflow
	public void insertAt(int index, TKey key,btreeNode<TKey> leftChild, btreeNode<TKey> rightChild) {
		// move space for the new key
		for (int i = this.getKeyCount() + 1; i > index; --i) {
			this.setChild(i, this.getChild(i - 1));
		}
		for (int i = this.getKeyCount(); i > index; --i) {
			this.setKey(i, this.getKey(i - 1));
		}
		
		// insert the new key
		this.setKey(index, key);
		this.setChild(index, leftChild);
		this.setChild(index + 1, rightChild);
		this.keyCount += 1;
	}
	
	//note the difference between split a leaf node and an inner node
	public btreeNode<TKey> split(TKey key, int midIndex){
		innerNode<TKey> newRNode = new innerNode<TKey>();
		for (int i = midIndex; i < MaxEntries; i++) {
			newRNode.setKey(i - midIndex, this.getKey(i+1));
			newRNode.setChild(i - midIndex, this.getChild(i+1));
			this.setKey(i, null);
			this.setChild(i+1, null);
			if(this.getKey(i).compareTo(null)!=0) 
				newRNode.keyCount++;
		};
		this.keyCount = this.getKeyCount()-newRNode.keyCount;
		if (this.getParent() == null) {
			this.setParent(new innerNode<TKey>());
		}
		innerNode<TKey> theParent=(innerNode<TKey>) this.getParent();
		newRNode.setParent(theParent);
		theParent.insertAt(theParent.search(key),key,this,newRNode);
		//parent might be overflowed here
		return theParent;
	}
	
}
