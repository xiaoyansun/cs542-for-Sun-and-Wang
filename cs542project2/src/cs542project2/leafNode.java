package cs542project2;


import java.util.*;


class leafNode<TKey extends Comparable<TKey>, TValue> extends btreeNode<TKey> {
	private Object[] values;
	protected btreeNode<TKey> nextLeaf;
	
	public leafNode() {
		//one more for the entry of the next leaf
		//LinkedList[]valuelist = new LinkedList[MaxEntries];
		this.keys = new Object[MaxEntries];  
		this.values = new Object[MaxEntries];
		this.nextLeaf=null;
		
		for (int i=0;i<MaxEntries;i++)
	        values[i] = new LinkedList<TValue>();
	}


	@SuppressWarnings("unchecked")
	public void setValue(int index, TValue value) {
		//System.out.println(index);
		((LinkedList<TValue>) this.values[index]).add(value);
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
		for(int i=0; i<NumOfEntries; i++){
			//System.out.println("+++++++++++++++"+this.getKey(i));
			if(this.getKey(i)==null) continue;
			if(this.getKey(i).compareTo(key)==0) return i; 
		}
		//System.out.println("Key "+ key +" Not Found");
		return -1;
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
		if(!this.isOverflow()){
			//find the next empty spot
			int i=index;
			while(this.getKey(i)!=null) i++;
			//allow key to be inserted at MaxEntries-1 position, for the purpose of split
			//however, split when total number of key reaches MaxEntries
			if(i<MaxEntries)     
			{
				for (int j = i - 1; j >= index; --j) {
					this.setKey(j + 1, this.getKey(j));
					//this.swaplist(values[j+1], values[j]);
					Object c= new Object();
					c= values[j+1];
					values[j+1]=values[j];
					values[j]=c;
				}
			}
			else{
				//when positions after index are all occupied
				//search before index
				System.out.println("Insert Fail...");
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
		//System.out.println(key);
		
		int midIndex = MaxEntries / 2;
		while(this.getKey(midIndex)==null)
			midIndex++;
		TKey upKey = this.getKey(midIndex);
		//System.out.println(upKey+" "+ midIndex);
		btreeNode<TKey> newNode = this.split(upKey, midIndex);
		//System.out.println("---------------");
		//newNode.printNode();
		//System.out.println("---------------"+newNode.keyCount);
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
			//System.out.println("here");
			//newRNode.setValuelist(i - midIndex, this.getValue(i));
			Object c = new Object();
			c=newRNode.values[i-midIndex];
			newRNode.values[i-midIndex]=this.values[i];
			this.values[i]=c;
			this.setKey(i, null);
			////////////////////////////////////////////////////////////////////////
			//this.setValue(i, null);
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
	
	@SuppressWarnings("unchecked")
	public LinkedList<TValue> getValue(int index) {
		return (LinkedList<TValue>) this.values[index];
	}
	public void printNode(){
		for(int i=0;i<MaxEntries;i++){
			System.out.println((Integer)this.getKey(i));
			for(int j=0;j<this.getValue(i).size();j++){
				System.out.println(this.getValue(i).get(j));
			}
			//System.out.println((Integer)this.getValue(i));
		}
	}
	
	public int deleteNode(int index, TKey key){
		this.setKey(index,null);
		this.keyCount--;
		this.values[index]=null;
		if(this.keyCount==0){
			//delete the leaf, then change its parent
			
			innerNode<TKey> theParent= new innerNode<TKey>();
			theParent=(innerNode<TKey>) this.getParent();
			//return -1 when tree is null
			return theParent.delete(key);
			
		}
		return 1;
	}
}

