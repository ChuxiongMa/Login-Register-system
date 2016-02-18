import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Register {
	Connection con;
	Statement st;
	ResultSet rs;
	
	JFrame frame = new JFrame("User Register");
	JLabel name = new JLabel("Username");
	JLabel pass = new JLabel("Password"); 
	JTextField userField = new JTextField(10);
	JPasswordField passField = new JPasswordField(10);
	JButton register = new JButton("Register");
	
	
	public Register(){
		connect();
		frame();
		
	}
	
	public void connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","qazZAQ360921,.");
			st = con.createStatement();
			
		}catch(Exception e){
			System.out.print("Connection Failed");
		}
		
	}
	
	public void frame(){
		frame.setSize(400,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.add(name);
		panel.add(userField);
		panel.add(pass);
		panel.add(passField);
		panel.add(register);
		
		frame.add(panel);
		passField.setEchoChar('*');
		
		
		register.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
				        
					 String username = userField.getText().trim();
					 String password = new String(passField.getPassword());
					    
					 String sql = "select username, password from log where username = '"+username+"'";
				     rs = st.executeQuery(sql);
				     
				     int count = 0;
				     while(rs.next()){
				    	 count++; 
				     }
				     
				     if(count == 1){
				    	 
				    	 JOptionPane.showMessageDialog(null, "Registered Username, please modify the name");
				    	 
				     }
				     else if(password.equals("") || username.equals("")){
				    	 
				    	 JOptionPane.showMessageDialog(null, "No Empty username or password!");
				     }
				     else if (count == 0){
				    	 String insert ="INSERT INTO log(username,password) VALUES(?,?)";
					     PreparedStatement statement = (PreparedStatement) con.prepareStatement(insert);
					     statement.setString(1, username);
					     statement.setString(2, password);
					     statement.executeUpdate();

					     JOptionPane.showMessageDialog(null, "Resgister successful, You can log in right now!");
					     frame.dispose();
				     }
					 
				}catch(Exception ex){
					System.out.print("Register Failed\n");
				}
			}
		});
	
		     
		}
	
}