import javax.swing.*;

import java.awt.event.*;
import java.sql.*;

import javax.swing.JPasswordField;

public class Login {
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	JFrame frame = new JFrame("User Login");
	JLabel name = new JLabel("Username");
	JLabel pass = new JLabel("Password"); 
	JTextField userField = new JTextField(10);
	JPasswordField passField = new JPasswordField(10);
	JButton login = new JButton("Log In");
	JButton register = new JButton("Sign Up");
	JButton modify = new JButton("Modify Password");
	
	
	public Login(){
		connect();
		frame();
		
	}
	
	public void connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","password");
			st = con.createStatement();
			
		}catch(Exception e){
			System.out.print("Connection Failed");
		}
		
	}
	
	public void frame(){
		
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.add(name);
		panel.add(userField);
		panel.add(pass);
		panel.add(passField);
		panel.add(login);
		panel.add(register);
		panel.add(modify);
		
		frame.add(panel);
		passField.setEchoChar('*');
		
		login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
				     String username = userField.getText().trim();
				     String password = new String(passField.getPassword());
				     String sql = "select username, password from log where username = '"+username+"' AND password = '"+password+"'";
				     rs = st.executeQuery(sql);
				     
				     int count = 0;
				     while(rs.next()){
				    	 count++;
				    	 
				     }
				     
				     if(count == 1){
				    	 
				    	 JOptionPane.showMessageDialog(null, "User found, log in successful!");
				    	 
				     }
				     
				     else if(count > 1){
				    	 
				    	 JOptionPane.showMessageDialog(null, "Duplicated User");
				    	 
				     }
				     
				     else if(count == 0){
				    	 
				    	 JOptionPane.showMessageDialog(null, "No Found");
				    	 
				     }


				}catch(Exception ex){
					
				}
			}
		});
		
		register.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Register register_window = new Register();
			}
			});
		
		modify.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Modify register_window = new Modify();
			}
			});
	}

	public static void main(String[] args) {
	
        Login s = new Login();
	}

}
