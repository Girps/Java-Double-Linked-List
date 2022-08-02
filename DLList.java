

/*DDList class takes a genric*/ 
public class DLList<E> 
{
   //------- data
	protected DLLNode<E> head;
	protected DLLNode<E> tail;
        
        
        //------- "inner classes"
	//==============================
	class DLLNode<E>
	{
            DLLNode<E> prev; 
            DLLNode<E> next; 
            E data;
            // Constructor our node 
		public DLLNode(E data)
                {
                    prev = null; 
                    next = null;
                    this.data = data;
                }
               
                @Override 
                public String toString()
                {
                    String result = "";
                    result = result.concat(this.data.toString());
                    return result; 
                }
	}	
	//------- constructors
	public DLList()
	{
            head = null; 
            tail = null; 
	}

	//------- methods
	//addFirst - adds an element to the front of the list
	public void addFirst(E theData)
        {
            // Case if empty
            if(head == null)
            {
                // Assign head refecne to address of new node
                head = new DLLNode(theData);
                tail = head; 
            } // Not empty
            else
            {
                // Replace old head 
                DLLNode temp = new DLLNode(theData);
                temp.next = head; 
                head.prev = temp; 
                head = temp; // temp node becomes new node 
            }
        }

	//addLast - adds an element to the end of the list
	public void addLast(E theData)
	{
		if(head == null)
                {
                    addFirst(theData); 
                }
                else
                {
                    // If not empty  
                    tail.next = new DLLNode(theData);
                    tail.next.prev = tail; 
                    tail = tail.next; // New node is address held by tail
                }
	}

	//removeFirst - removes and returns the first element
	public E removeFirst()
	{  
            // Check if list is empty
            if(head == null)
            {
                throw new RuntimeException("List is empty!"); 
            }
            else if (tail == head) // head and tail point to same node
            {
               E data = head.data;
               head = head.next; // move head forward 
               tail = head;
               return data; 
            }
            else // Otherwise remove node between head and tails 
            {
               E data = head.data;
               head = head.next; // move head forward 
               head.prev = null;
               return data; 
            }
	}

	//removeLast - removes and returns the last element
	public E removeLast()
	{
            // Check if list is empty
		if(head == null)
                {
                     throw new RuntimeException("List is empty!"); 
                }
                else if(tail == head)
                {
                    return removeFirst(); 
                }
                else
                {
                    E data = tail.data; 
                    tail = tail.prev; // assing tail backwards
                    tail.next.prev = null; // old tail point to nothing
                    tail.next = null; // make sure not point to old tail
                    return data; 
                }
                
        }

	//contains - returns true if the list contains what is received
	public boolean contains(Object obj)
	{
            // Refecnce to node in the list
            DLLNode cursor = head; 
            
            // Iterate over till end or object is found
            while(cursor != null && cursor.data.equals(obj) == false )
            {
                cursor = cursor.next; 
            }
            // Return true if object found not false
            return (cursor != null && cursor.data.equals(obj) == true) ? true : false; 
        }

	//remove - removes what is received from the list.  Returns true if it
	//                was actually found and removed
	public boolean remove(Object obj)
	{        
            boolean result = true; 
            // Point to first node in the list
            DLLNode cursor = head; 
            // Iterate till end or found the object
            while(cursor != null && cursor.data.equals(obj) == false)
            {
                cursor = cursor.next; 
            }
            
            // Make sure not null
            if(cursor != null)
            {
            // Check if node is in last
            if(cursor == head && (cursor.data.equals(obj) == true))
            {
                removeFirst(); 
               return true; 
            } // Check if its tail 
            else if(cursor == tail && (cursor.data.equals(obj ) == true))
            {
                removeLast(); 
                return true; 
            }
            }
            
            // Neither check inbetween
            if(cursor != null && cursor.data.equals(obj) == true )
            {
                //Now remove the node
                cursor.prev.next = cursor.next; // assign piror node to node after target
                cursor.next.prev = cursor.prev; 
                cursor.prev = null; //sep target node
                cursor.next = null; // sep target node
                return true;   //found it; links have been changed
            }
            else 
            {
                return false; 
            }
        }
        
	/*Add data by zero index*/
	public void add(int index, E elt)
	{
            // Check index is valid
		if(index < 0 || (index > 0 && index > size()) )
                {
                    throw new RuntimeException("Invalid index"); 
                } // Otherwise add it 
                else if(index == 0)
                {
                    // Add it in the first element of list
                    addFirst(elt);
                } // Addign to max size addLast
                else if(index == size())
                {
                    addLast(elt); 
                }
                else
                {
                    // find node piror to location
                    int count = index;
                    --count; 
                    DLLNode cursor = head;
                    while(count > 0 )
                    {
                        // Move to next node 
                        cursor = cursor.next; 
                        --count; 
                    }
                   // found cursor now add new node
                   DLLNode temp = new DLLNode(elt);
                   // get temp to point to prior nodes next 
                   temp.next = cursor.next; 
                   temp.prev = cursor;
                   temp.next.prev = temp; 
                   cursor.next = temp;
                }
	}

	//getFirst - returns the data from the head
	public E getFirst()
	{
            return this.head.data;
	}

	//getLast - returns the data from the tail
	public E getLast()
	{
            return this.tail.data; 
	}

	//isEmpty - returns true if the list is empty
	public boolean isEmpty()
	{
            return (head == null) ? (true): (false); 
	}

	//size - returns its size
	public int size()
	{
		// Get size of the list
            DLLNode cursor = head; 
            int count = 0; 
            for(;cursor != null;++count)
            {
                // Move to next node
                cursor = cursor.next; 
            }
            // Return amount of elements in linked list
            return count; 
	}

	//toString - returns its representation as a String'
        @Override
	public String toString()
	{
		String result = ""; 
                DLLNode cursor = head; 
                // Iterate till end of list reached
                for(;cursor != null;cursor = cursor.next)
                {
                    result = result.concat(cursor.toString() + "--->");
                }
                
                return result; 
	}
    
    
}
