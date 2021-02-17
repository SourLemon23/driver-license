package driverlicense;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import java.awt.Container;
import java.awt.Font;

public class GUI extends JFrame
{
	static DriverInfo license1 = new DriverInfo(1);
	static DriverInfo license2 = new DriverInfo(2);
	static DriverInfo license3 = new DriverInfo(3);

	static DriversList suspendedLicenses = new DriversList();

	static DriversList driversList = new DriversList();
	static DriversList originalList = new DriversList();
	static ListNode sortedList;

	static DriversList expirationList = new DriversList();
	static DriversList renewalList = new DriversList();

	static boolean firstSort = true;


	public static void main(String[] args)
	{
		driversList.add(license1);
		driversList.add(license2);
		driversList.add(license3);

		originalList.add(license1);
		originalList.add(license2);
		originalList.add(license3);

		//		------------------------------------------
		//		Suspended Licenses
		//		------------------------------------------

		if (license1.getIsSuspended() == true)
			suspendedLicenses.add(license1);
		if (license2.getIsSuspended() == true)
			suspendedLicenses.add(license2);
		if (license3.getIsSuspended() == true)
			suspendedLicenses.add(license3);

		//		------------------------------------------
		//		Expiration and Renewal Lists
		//		------------------------------------------

		if (license1.getIsExpired() == true)
			renewalList.add(license1);
		else
			expirationList.add(license1);
		if (license2.getIsExpired() == true)
			renewalList.add(license2);
		else
			expirationList.add(license2);
		if (license3.getIsExpired() == true)
			renewalList.add(license3);
		else
			expirationList.add(license3);

		displayMainMenu();
	}

	public static void displayMainMenu()
	{
		JFrame frame = new JFrame("Chat Frame");
		frame.setTitle("California DMV");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container jp = frame.getContentPane();
		jp.setLayout(null);

		JLabel header = new JLabel("California DMV");
		header.setBounds(285, 10, 300, 100);
		header.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		JLabel nameSearchLbl = new JLabel("Search Name:");
		nameSearchLbl.setBounds(130, 90, 100, 100);
		JTextField nameSearch = new JTextField();
		nameSearch.setBounds(130, 150, 150, 40);
		JLabel dlNumSearchLbl = new JLabel("Search Driver License #:");
		dlNumSearchLbl.setBounds(490, 90, 150, 100);
		JTextField dlNumSearch = new JTextField();
		dlNumSearch.setBounds(490, 150, 150, 40);

		JButton nameSortButton = new JButton("Sort by Name");
		nameSortButton.setBounds(100, 280, 150, 100);
		JButton dlNumSortButton = new JButton("Sort by DL #");
		dlNumSortButton.setBounds(250, 280, 150, 100);
		JButton weightSortButton = new JButton("Sort by Weight");
		weightSortButton.setBounds(400, 280, 150, 100);
		JButton genderSortButton = new JButton("Sort by Gender");
		genderSortButton.setBounds(550, 280, 150, 100);

		JButton expirationButton = new JButton("Print Expiration List");
		expirationButton.setBounds(100, 400, 200, 100);
		JButton renewalButton = new JButton("Print Renewal List");
		renewalButton.setBounds(300, 400, 200, 100);
		JButton suspendedButton = new JButton("Print Suspended Licenses");
		suspendedButton.setBounds(500, 400, 200, 100);

		//		------------------------------------------
		//		Searching and Display
		//		------------------------------------------

		nameSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				String name = nameSearch.getText();
				DriverInfo driver = originalList.searchName(name);
				if (driver != null)
					driver.display();
				else
					System.out.println("\n\n------------------------------------------\n\nDriver not found.");
			}
		});

		dlNumSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				String dlNum= dlNumSearch.getText();
				DriverInfo driver = originalList.searchDlNum(Integer.parseInt(dlNum));
				if (driver != null)
					driver.display();
				else
					System.out.println("\n\n------------------------------------------\nDriver not found.");
			}
		});

		//		------------------------------------------
		//		Suspended Licenses
		//		------------------------------------------

		suspendedButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n------------------------------------------");

				System.out.println("\nSuspended Licenses:");
				ListNode.printList(suspendedLicenses.getList());
			}          
		});

		//		------------------------------------------
		//		Expiration and Renewal Lists
		//		------------------------------------------

		expirationButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n------------------------------------------");

				System.out.println("\nExpiration List (licenses have not yet expired):");
				ListNode.printList(expirationList.getList());
			}          
		});

		renewalButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n------------------------------------------");

				System.out.println("\nRenewal List (licenses need a renewal):");
				ListNode.printList(renewalList.getList());
			}          
		});

		//		------------------------------------------
		//		Sorting
		//		------------------------------------------

		nameSortButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n------------------------------------------");

				System.out.println("\nThe original linked list:");
				ListNode.printList(originalList.getList());

				System.out.println("\n\nSorted by driver name:");

				if (firstSort)
					sortedList = DriversList.sortByName(driversList.getList());
				else
					sortedList = DriversList.sortByName(sortedList);

				ListNode.printList(sortedList);

				firstSort = false;
			}          
		});

		dlNumSortButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n------------------------------------------");

				System.out.println("\nThe original linked list:");
				ListNode.printList(originalList.getList());

				System.out.println("\n\nSorted by driver license #:");

				if (firstSort)
					sortedList = DriversList.sortByDlNum(driversList.getList());
				else
					sortedList = DriversList.sortByDlNum(sortedList);

				ListNode.printList(sortedList);

				firstSort = false;
			}          
		});

		weightSortButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n------------------------------------------");

				System.out.println("\nThe original linked list:");
				ListNode.printList(originalList.getList());

				System.out.println("\n\nSorted by driver weight:");

				if (firstSort)
					sortedList = DriversList.sortByWeight(driversList.getList());
				else
					sortedList = DriversList.sortByWeight(sortedList);

				ListNode.printList(sortedList);

				firstSort = false;
			}          
		});

		genderSortButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n------------------------------------------");

				System.out.println("\nThe original linked list:");
				ListNode.printList(originalList.getList());

				System.out.println("\n\nSorted by driver gender:");

				if (firstSort)
					sortedList = DriversList.sortByGender(driversList.getList());
				else
					sortedList = DriversList.sortByGender(sortedList);

				ListNode.printList(sortedList);

				firstSort = false;
			}          
		});


		jp.add(header);

		jp.add(nameSearchLbl);
		jp.add(nameSearch);
		jp.add(dlNumSearchLbl);
		jp.add(dlNumSearch);

		jp.add(nameSortButton);
		jp.add(dlNumSortButton);
		jp.add(weightSortButton);
		jp.add(genderSortButton);

		jp.add(expirationButton);
		jp.add(renewalButton);
		jp.add(suspendedButton);

		frame.setVisible(true);
	}
}