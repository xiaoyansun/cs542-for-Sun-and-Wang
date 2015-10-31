package cs542project2;

class leafNode<TKey extends Comparable<TKey>, TValue> extends btreeNode<TKey> {

	private Object[] values;
	protected btreeNode<TKey> nextLeaf;
	
	public leafNode() {
		//one more for the entry of the next leaf
		this.keys = new Object[MaxEntries];  
		this.values = new Object[MaxEntries];
		this.nextLeaf=null;
	}

	@SuppressWarnings("unchecked")
	public TValue getValue(int index) {
		return (TValue)this.values[index];
	}

	public void setValue(int index, TValue value) {
		this.values[index] = value;
	}
	
	@Override
	public TreeNodeType getNodeType() {
		return TreeNodeType.LeafNode;
	}
	
	@Override
	public boolean isFull() {
		return this.getKeyCount() == NumOfEntries;
	}
	
	public boolean hasChild(){
		return false;
	}
	
	@Override
	public int search(TKey key) {
		return searchKey(key,0,NumOfEntries);
	}
	
	//use binary search to find the key in a leaf node
	//return index of value
	private int searchKey(TKey key, int j, int r){
		if(j==r) {
			if(this.getKey(j).compareTo(key)==0) 
				return j;
			else System.out.println("Key cannot be found!");
		} 
		int m=(j+r)/2;
		if(this.getKey(m).compareTo(null)==0) m++;
		if(this.getKey(m).compareTo(key)>0) return searchKey(key, m+1, r);
		return searchKey(key, j, m);	
	}
	
	public void insertKey(TKey key, TValue value) {
		int index = 0;
		while (this.getKey(index)!= null && 
		index < MaxEntries && this.getKey(index).compareTo(key) < 0)
			++index;
		this.insertAt(index, key, value);
	}
	
	private void insertAt(int index, TKey key, TValue value) {
		//if the value at index-1 is null, insert it there without moving other keys
		if(index>1 && this.getKey(index-1)==null){
			this.setKey(index-1, key);
			this.setValue(index-1, value);
		}		
		// move space for the new key
		if(this.getKeyCount()>1){
			for (int i = this.getKeyCount() - 1; i >= index; --i) {
				this.setKey(i + 1, this.getKey(i));
				this.setValue(i + 1, this.getValue(i));
			}
		}
		// insert new key and value
		this.setKey(index, key);
		this.setValue(index, value);
		++this.keyCount;
	}
	
	public btreeNode<TKey> handleOverflow(TKey key, TValue value) {

		//insert the new key in, overflow at this moment
		this.insertKey(key, value);
		
		int midIndex = MaxEntries / 2;
		while(this.getKey(midIndex)==null)
			midIndex++;
		TKey upKey = this.getKey(midIndex);
		//System.out.println(upKey);
		btreeNode<TKey> newNode = this.split(upKey, midIndex);
		if(newNode.isOverflow())
			newNode= newNode.furtherHandle();
		
		return newNode;

	}
	
	//Split the leaf node into two
	//return the parent
	public btreeNode<TKey> split(TKey key, int midIndex){
		leafNode<TKey, TValue> newRNode = new leafNode<TKey, TValue>();
		for (int i = midIndex; i < NumOfEntries+1; i++) {
			newRNode.setKey(i - midIndex, this.getKey(i));
			newRNode.setValue(i - midIndex, this.getValue(i));
			this.setKey(i, null);
			this.setValue(i, null);
			if(this.getKey(i)==null) newRNode.keyCount++;
		}
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
	
	public void printNode(){
		for(int i=0;i<MaxEntries;i++){
			System.out.println((Integer)this.getKey(i)+"  "+(Integer)this.getValue(i));
			//System.out.println((Integer)this.getValue(i));
		}
	}
	
}
