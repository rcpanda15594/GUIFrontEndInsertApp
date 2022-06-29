package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public  class GUIFrontEndInsertApp extends JFrame implements ActionListener {

	private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT1 VALUES(?,?,?)";
		private JLabel lno,lname,laddrs,lres;
		private JTextField tno,tname,taddrs;
		private JButton bt1;
		Connection con;
		PreparedStatement ps;
		
		
//Frame Settings
	public GUIFrontEndInsertApp() {
	setTitle("GUIFrontEndInsertApp");
	setBackground(Color.BLUE);
	setLayout(new FlowLayout());
	setSize(300,300);
		
//Add Comps
	lno=new JLabel("Student No:");
	add(lno);
	tno=new JTextField(10);
	add(tno);
	
	lname=new JLabel("Student Name:");
	add(lname);
	tname=new JTextField(10);
	add(tname);
	
	laddrs=new JLabel("Student Address::");
	add(laddrs);
	taddrs=new JTextField(10);
	add(taddrs);
	
	bt1=new JButton("Register");
	bt1.addActionListener(this);
	add(bt1);
	
	
	lres=new JLabel("");
	lres.setForeground(Color.RED);
	add(lres);

//set Visiablity on
	setVisible(true);
	
	//Terminate App when close button is clicked
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	initilize();
	
	
	}//constructor
	
	
	private void initilize() {
	try {
		 //register jdbc driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","tiger");
		 //create PreparedStatement object
		 ps=con.prepareStatement(STUDENT_INSERT_QUERY);

	}
	 catch(SQLException se){
		 se.printStackTrace();
	 }
	 catch(ClassNotFoundException cnf){
		 cnf.printStackTrace();
	 }
	 catch(Exception e) {
		e.printStackTrace();
	  }
 }//initilize method
	
	
	public static void main(String[] args) {
				
		 new GUIFrontEndInsertApp();

		
	}//method


	@Override
	public void actionPerformed(ActionEvent ae) {
		int sno=0;
		String sname=null,saddr=null;
		int count=0;
		try {
	//read value from the text box
			sno=Integer.parseInt(tno.getText());
			sname=tname.getText();
			saddr=taddrs.getText();
			
   //set value to query param
			ps.setInt(1, sno);
			ps.setString(2,sname);
			ps.setString(3,saddr);
			
//execute the  query
			count=ps.executeUpdate();
			if(count!=0)
				lres.setText("Record Inserted");
			else
				lres.setText("Record  not Inserted");
		
		}//try
		catch(SQLException se){
			se.printStackTrace();
			lres.setText("Record  not Inserted -->"+se.getMessage());
		}
		catch(Exception e) {
           e.printStackTrace();
           lres.setText("Record not Inserted  -->"+e.getMessage());
		}
	}//method

}//class
