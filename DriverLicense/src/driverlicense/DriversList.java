package driverlicense;
public class DriversList {
	private ListNode list;

	public DriversList()
	{
		list = null;
	}

	public ListNode getList()
	{
		return list;
	}

	public void setList(ListNode newList)
	{
		list = newList;
	}
	
	public void add(DriverInfo driver)
	{
		ListNode node = new ListNode (driver, null);
		ListNode current;

		if (list == null)
		{
			list = node;
		}
		else
		{
			current = list;
			while (current.getNext() != null)
			{
				current = current.getNext();
			}
			current.setNext(node);
		}
	}
	
	public void insert(DriverInfo driver, int index)
	{
		ListNode current = list;
		int count = 0;

		if (index == 0)
		{
			list = new ListNode(driver, list);
		}
		else
		{
			while (count < index - 1)
			{
				current = current.getNext();
				count++;
			}

			ListNode newReminder = new ListNode(driver, current.getNext());
			current.setNext(newReminder);
		}
	}

	public DriverInfo searchName(String name)
	{
		ListNode current = list;
		DriverInfo driver;
		String driverName;

		while (current != null)
		{
			driver = ((DriverInfo) current.getValue());
			driverName = driver.getName();

			if (driverName.toLowerCase().contains(name.toLowerCase()))
			{
				return (DriverInfo)current.getValue();
			}

			current = current.getNext();
		}

		return null;		
	}
	
	public DriverInfo searchDlNum(int num)
	{
		ListNode current = list;
		DriverInfo driver;
		int dlNum;

		while (current != null)
		{
			driver = ((DriverInfo) current.getValue());
			dlNum = driver.getDlnum();

			if (dlNum == num)
			{
				return (DriverInfo)current.getValue();
			}

			current = current.getNext();
		}

		return null;		
	}
	
//	------------------------------------------
	
	public static ListNode nameMergeSort(ListNode a, ListNode b) 
    { 
		ListNode result = null;
		
        // Base cases
        if (a == null) 
            return b; 
        if (b == null)
            return a;
  
        // Recursively find the smaller value between a and b
        if (((DriverInfo)(a.getValue())).getName().compareTo(((DriverInfo)(b.getValue())).getName()) <= 1)
        { 
            result = a;
            result.setNext(nameMergeSort(a.getNext(), b)); 
        } 
        else { 
            result = b; 
            result.setNext(nameMergeSort(a, b.getNext())); 
        } 
        return result; 
    }
	
	public static ListNode weightMergeSort(ListNode a, ListNode b) 
    { 
		ListNode result = null;
		
        // Base cases
        if (a == null) 
            return b; 
        if (b == null)
            return a;
  
        // Recursively find the smaller value between a and b
        if (((DriverInfo)(a.getValue())).getWeight() <= ((DriverInfo)(b.getValue())).getWeight())
        { 
            result = a;
            result.setNext(weightMergeSort(a.getNext(), b)); 
        } 
        else { 
            result = b; 
            result.setNext(weightMergeSort(a, b.getNext())); 
        } 
        return result; 
    }
	
	public static ListNode dlNumMergeSort(ListNode a, ListNode b) 
    { 
		ListNode result = null;
		
        // Base cases
        if (a == null) 
            return b; 
        if (b == null)
            return a;
  
        // Recursively find the smaller value between a and b
        if (((DriverInfo)(a.getValue())).getDlnum() <= ((DriverInfo)(b.getValue())).getDlnum())
        { 
            result = a;
            result.setNext(dlNumMergeSort(a.getNext(), b)); 
        } 
        else { 
            result = b; 
            result.setNext(dlNumMergeSort(a, b.getNext())); 
        } 
        return result; 
    }
	
	public static ListNode genderMergeSort(ListNode a, ListNode b) 
    { 
		ListNode result = null;
		
        // Base cases
        if (a == null) 
            return b; 
        if (b == null)
            return a;
  
        // Recursively find the smaller value between a and b
        if (((DriverInfo)(a.getValue())).getGender().compareTo(((DriverInfo)(b.getValue())).getGender()) <= 1)
        { 
            result = a;
            result.setNext(genderMergeSort(a.getNext(), b)); 
        } 
        else { 
            result = b; 
            result.setNext(genderMergeSort(a, b.getNext())); 
        } 
        return result; 
    }
  
	public static ListNode sortByName(ListNode head) 
    { 
		// Base case if head is null
        if (head == null || head.getNext() == null) 
            return head;
        
        // Get the middle of the list 
        ListNode middle = getMiddle(head); 
        ListNode nextofmiddle = middle.getNext(); 
  
        // Set the next of middle node to null so left list won't past the middle mark
        middle.setNext(null); 
  
        // Apply mergeSort on left list 
        ListNode left = sortByName(head); 
  
        // Apply mergeSort on right list 
        ListNode right = sortByName(nextofmiddle); 
  
        // Merge the left and right lists 
        ListNode sortedlist = nameMergeSort(left, right); 
        return sortedlist; 
    }
	
	public static ListNode sortByWeight(ListNode head)
    { 
		// Base case if head is null 
        if (head == null || head.getNext() == null) 
            return head;
        
        // Get the middle of the list 
        ListNode middle = getMiddle(head); 
        ListNode nextofmiddle = middle.getNext(); 
  
        // Set the next of middle node to null so left list won't past the middle mark
        middle.setNext(null); 
  
        // Apply mergeSort on left list 
        ListNode left = sortByWeight(head); 
  
        // Apply mergeSort on right list 
        ListNode right = sortByWeight(nextofmiddle); 
  
        // Merge the left and right lists 
        ListNode sortedlist = weightMergeSort(left, right); 
        return sortedlist;
    }
	
	public static ListNode sortByDlNum(ListNode head) 
    { 
        // Base case if head is null 
        if (head == null || head.getNext() == null) 
            return head;
        
        // Get the middle of the list 
        ListNode middle = getMiddle(head); 
        ListNode nextofmiddle = middle.getNext(); 
  
        // Set the next of middle node to null so left list won't past the middle mark
        middle.setNext(null); 
  
        // Apply mergeSort on left list 
        ListNode left = sortByDlNum(head); 
  
        // Apply mergeSort on right list 
        ListNode right = sortByDlNum(nextofmiddle); 
  
        // Merge the left and right lists 
        ListNode sortedlist = dlNumMergeSort(left, right); 
        return sortedlist;
    }
	
	public static ListNode sortByGender(ListNode head) 
    { 
		// Base case if head is null 
        if (head == null || head.getNext() == null) 
            return head;
        
        // Get the middle of the list 
        ListNode middle = getMiddle(head); 
        ListNode nextofmiddle = middle.getNext(); 
  
        // Set the next of middle node to null so left list won't past the middle mark
        middle.setNext(null); 
  
        // Apply mergeSort on left list 
        ListNode left = sortByGender(head); 
  
        // Apply mergeSort on right list 
        ListNode right = sortByGender(nextofmiddle); 
  
        // Merge the left and right lists 
        ListNode sortedlist = genderMergeSort(left, right); 
        return sortedlist;
    }
  
    // Utility function to get the middle node of a linked list 
    public static ListNode getMiddle(ListNode head) 
    { 
        if (head == null)
            return head; 
  
        ListNode turtle = head, hare = head; 
  
        while (hare.getNext() != null && hare.getNext().getNext() != null)
        { 
        	turtle = turtle.getNext(); 
        	hare = hare.getNext().getNext(); 
        } 
        return turtle; 
    }
}
