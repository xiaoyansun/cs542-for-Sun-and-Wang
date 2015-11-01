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
	/*
	private int searchKey(TKey key, int j, int r){
		if(j==r) {
			if(j==0) return 0;
			if(this.getKey(j).compareTo(key)<0) return j; else return j+1;}
		int m=(j+r)/2;
		System.out.println("first "+m+" "+j+" "+r);
		while(this.getKey(m)==null && m<r) m++;
			if(m==r) return searchKey(key, j,(j+r)/2);
		if(this.getKey(m).compareTo(key)>0) 
			return searchKey(key, m+1, r);
		System.out.println(key+" "+j +" "+m);
		return searchKey(key, j, m);	
	}*/
	
	@Override
	public int search(TKey key) {
		//return searchKey(key,0,NumOfEntries);
		int flag=0;
		for(int i=0; i<MaxEntries; i++){
			//System.out.println("+++++++++++++++"+this.getKey(i));
			if(this.getKey(i)==null) continue;
			if(this.getKey(i).compareTo(key)>0 && i==0) return 0;
			if(this.getKey(i).compareTo(key)<0) {flag=i+1;continue;}
			if(this.getKey(i).compareTo(key)>0) return i;  
		}
		//System.out.println("error: innerNode no Key"); 
		return flag;
		
	}
	
	
	//the insert function do not check overflow
	public void insertAt(int index, TKey key,btreeNode<TKey> leftChild, btreeNode<TKey> rightChild) {
		//if(index!=0) index++;
		//System.out.println(index);
		if(this.getKey(index)==null){
			this.setKey(index, key);
			this.setChild(index, leftChild);
			this.setChild(index+1, rightChild);
		}
		else{
			TKey k1=this.getKey(index);
			btreeNode <TKey> rightC= this.getChild(index+1);
			this.setKey(index, key);
			this.setChild(index, leftChild);
			this.setChild(index+1, rightChild);
			index++;
			//System.out.println("++++++"+index);
			while(index<NumOfEntries && this.getKey(index)!=null){
				TKey k2=this.getKey(index);
				btreeNode <TKey> rightC2= this.getChild(index+1);
				this.setKey(index, k1);
				this.setChild(index+1, rightC);
				k1=k2;
				rightC=rightC2;
				index++;
			}
			//System.out.println("++++++++$#$"+index);
			this.setKey(index, k1);
			
			this.setChild(index+1, rightC);

		}
		this.keyCount += 1;
	}
	
	//note the difference between split a leaf node and an inner node
	public btreeNode<TKey> split(TKey key, int midIndex){
		innerNode<TKey> newRNode = new innerNode<TKey>();
		for (int i = midIndex; i < MaxEntries-1; i++) {
			newRNode.setKey(i - midIndex, this.getKey(i+1));
			newRNode.setChild(i - midIndex, this.getChild(i+1));
			this.setKey(i, null);
			this.setChild(i+1, null);
			if(this.getKey(i)!=null) 
				newRNode.keyCount++;
		}
		this.setKey(NumOfEntries, null);
		this.setChild(NumOfEntries+1, null);
		
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
		for(int i=0;i<MaxEntries;i++)
			System.out.println((Integer)this.getKey(i));
			//System.out.println((String)this.getKey(i));
	}

	@Override
	public boolean hasChild() {
		
		for(int i=0;i<NumOfEntries;i++){
			if(this.getChild(i)!=null) {
					this.getChild(i).printNode();
					
					System.out.println("one more");
					//return true;
				
			}
		}
		return false;
	}

}
