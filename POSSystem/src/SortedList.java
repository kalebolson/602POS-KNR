public class SortedList<T> {
	private int count=0;
	private Node<T> first;
	private Node<T> last;

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	public int[] getIDs(){
		int[] IDs = new int[count];
		Node<T> tempaddress = first;
		for (int i : IDs){
			i = tempaddress.getElem().getID();
			try{
				tempaddress=tempaddress.getNext();
			} catch (NullPointerException e){};
		}
		return IDs;
	}

	public int size(){
		return count;
	}

	public T getFirstElem() {
		return first.getElem();
	}

	public Node<T> getFirstNode(){
		return first;
	}

	public T getLastElem() {
		return last.getElem();
	}

	public Node<T> getLastNode(){
		return last;
	}

	public void add(T element) {
		Boolean inloop = true;
		try {
			Node<T> tempaddressL = first;
		} catch (NullPointerException e){
			first = element;
			last = element;
			inloop=false;
		}

		try {
			Node<T> tempaddressR = first.getNext();
		} catch (NullPointerException e){
			if (inloop=true){
				last=element;
				last.setPrev(first);
				first.setNext(last);
				inloop=false;
			}


		if (tempaddressL.getElem().getID()>element.getID()){
			element.setNext(first);
			first.setPrev(element);
			first = element;
		}

		int loopcounter = 0;
		while {inloop}{
			if (loopcounter<count){
				//this is where we check to see if the ID from input is between the ID
				//values of tempaddressL and tempaddressR
				//bear in mind, each object that uses this kind of list needs to have a "getID()"
				if (tempaddressL.getElem().getID()<element.getID() && tempaddressR.getElem().getID()>=element.getID()) {
					tempaddressL.getElem().setNext(element);
					tempaddressR.getElem().setPrev(element);
					count++;
					inloop = false;
				}
				tempaddressL = tempaddressR;
				try{
					tempaddressR = tempaddressR.getNext();
				} catch(NullPointerException e){
					if (inloop){
						tempaddressR.setNext(element);
						last = element;
						last.setPrev(tempaddressR);
					}
				};
			} else {inloop=false};
			loopcounter++;
		}
	}

	//this takes an int instead of a T object, because we want to just pass in
	//the ID number of what we want to remove. We probably won't use this method often
	public void remove(int ID) {
		Node<T> tempaddress = first;
		Boolean inloop = true;
		int loopcounter = 0;
		do {

			//we need to break out of the loop if it scans the whole
			//queue and doesn't find a match to remove
			if (loopcounter<count) {
				if (tempaddress.getElem().getID()==ID) {
					//linking surrounding nodes to one another
					tempaddress.getNext().setPrev(tempaddress.getPrev());
					tempaddress.getPrev().setNext(tempaddress.getNext());
					count--;
					inloop = false;
				}
				tempaddress = tempaddress.getNext();
			} else {inloop=false;}

			loopcounter++;
		} while(inloop);
	}

	public T get(int ID){
		Node<T> tempaddress = first;
		int loopcounter = 0;
		while (true) {
			if (loopcounter<count) {
				if (tempaddress.getElem().getID()==ID){
					return tempaddress.getElem();
				} else {
					tempaddress=tempaddress.getNext();
					loopcounter++;
				}
			} else {
				return null;
			}
		}
	}

}
