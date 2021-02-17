package driverlicense;
public class ListNode
{
	private Object value;
	private ListNode next;

	//-----------------------------------------------------------------
	//  Initializes this node.
	//-----------------------------------------------------------------
	public ListNode(Object initValue, ListNode initNext)
	{
		value = initValue;
		next = initNext;
	}

	//-----------------------------------------------------------------
	//  Returns the value of this node.
	//-----------------------------------------------------------------
	public Object getValue()
	{
		return value;
	}

	//-----------------------------------------------------------------
	//  Returns the next reference in this node.
	//-----------------------------------------------------------------
	public ListNode getNext()
	{
		return next;
	}

	//-----------------------------------------------------------------
	//  Sets the value of this node.
	//-----------------------------------------------------------------
	public void setValue(Object theNewValue)
	{
		value = theNewValue;
	}

	//-----------------------------------------------------------------
	//  Sets the next reference in this node.
	//-----------------------------------------------------------------
	public void setNext(ListNode theNewNext)
	{
		next = theNewNext;
	}

	// Prints contents of linked list
	public static void printList(ListNode list) 
	{ 
		int reminderCount = 1;
		while (list != null) 
		{ 
			System.out.print("\n" + reminderCount + ") " + list.getValue()); 
			list = list.getNext(); 
			reminderCount++;
		} 
	}
}
