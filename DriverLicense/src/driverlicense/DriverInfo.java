package driverlicense;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.border.EmptyBorder;

import java.awt.Container;
import java.awt.Font;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class DriverInfo extends JFrame
{
	public static String DATA_FILE = "REPLACE W/ THE FILE PATH FOR YOUR CSV FILE THAT CONATINS ALL THE DRIVER INFO";
	public final int VIOLATION_THRESHOLD = 15;
	
	String name, dob, address, city, state, restrictions, height, sex, eyes, expDate, photoFP;
	String strDlnum, strZipCode, strWeight, strViolationPoints;
	int dlNum, zipCode, weight, violationPoints;
	boolean isExpired = false, isSuspended = false;

	public DriverInfo(int csvRowNum)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE)))
		{
			String line;
			int row = 0;
			while ((line = br.readLine()) != null)
			{
				if (row == csvRowNum)
				{
					String[] data = line.split(",");
					name = data[0];
					strDlnum = data[1];
					dob = data[2];
					address = data[3];
					city = data[4];
					strZipCode = data[5];
					state = data[6];
					restrictions = data[7];
					height = data[8];
					strWeight = data[9];
					sex = data[10];
					eyes = data[11];
					strViolationPoints = data[12];
					expDate = data[13];
					photoFP = data[14];

					break;
				}

				row++;
			}
		}
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}

		// Strings cannot be cast to ints inside while reading files
		dlNum = Integer.parseInt(strDlnum);
		zipCode = Integer.parseInt(strZipCode);
		weight = Integer.parseInt(strWeight);
		violationPoints = Integer.parseInt(strViolationPoints);
		
		ZoneId z = ZoneId.of("America/Los_Angeles");
		LocalDate today = LocalDate.now(z);
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");
		LocalDate expDate = LocalDate.parse(this.expDate, f);
	
		if (expDate.isBefore(today))
			isExpired = true;

		if (violationPoints >= VIOLATION_THRESHOLD)
			isSuspended = true;
	}

	public void display()
	{
		setTitle("DMV");
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		Container jp = getContentPane();
		jp.setLayout(null);
		
		JLabel img = new JLabel("");
		JLabel dlNum = new JLabel("DL  " + this.dlNum);
		JLabel expDate = new JLabel("EXP   " + this.expDate);
		JLabel dob = new JLabel("DOB   " + this.dob);
		JLabel name = new JLabel("" + this.name);
		
		JLabel address = new JLabel("" + this.address);
		JLabel location = new JLabel(city + ", " + state + " " + zipCode);
		
		JLabel restrictions = new JLabel("RSTR   " + this.restrictions);
		JLabel sex = new JLabel("SEX      " + this.sex);
		JLabel height = new JLabel("HGT     " + this.height);
		JLabel eyes = new JLabel("EYES    " + this.eyes);
		
		img.setBounds(50, 50, 320, 450);
		dlNum.setBounds(400, 30, 200, 100);
		expDate.setBounds(400, 60, 200, 100);
		dob.setBounds(400, 90, 200, 100);
		name.setBounds(400, 135, 300, 100);
		
		address.setBounds(400, 170, 200, 100);
		location.setBounds(400, 190, 300, 100);
		
		restrictions.setBounds(420, 250, 300, 100);
		sex.setBounds(420, 270, 200, 100);
		height.setBounds(420, 290, 200, 100);
		eyes.setBounds(420, 310, 200, 100);
		
		dlNum.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		expDate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		dob.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		name.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		
		address.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		location.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		restrictions.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		sex.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		height.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		eyes.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		
		img.setIcon(new ImageIcon(this.photoFP));
		
		jp.add(img);
		jp.add(dlNum);
		jp.add(expDate);
		jp.add(dob);
		jp.add(name);
		jp.add(address);
		jp.add(location);
		jp.add(restrictions);
		jp.add(sex);
		jp.add(height);
		jp.add(eyes);
		
		setVisible(true);
	}

	public String getName()
	{
		return this.name;
	}

	public int getDlnum()
	{
		return this.dlNum;
	}

	public int getWeight()
	{
		return this.weight;
	}
	
	public String getGender()
	{
		return this.sex;
	}
	
	public boolean getIsSuspended()
	{
		return this.isSuspended;
	}
	
	public boolean getIsExpired()
	{
		return this.isExpired;
	}
	
	public String toString()
    {
        return this.name;
    }
}
