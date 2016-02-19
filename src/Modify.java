import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Modify {
	Connection con;
	Statement st;
	ResultSet rs;
	
	
	JFrame frame = new JFrame("Modify Password");
	JLabel name = new JLabel("Username");
	JLabel oldpass = new JLabel("Old Password");
	JLabel newpass = new JLabel("New Password"); 
	JTextField userField = new JTextField(10);
	JPasswordField oldpassField = new JPasswordField(10);
	JPasswordField newpassField = new JPasswordField(10);
	JButton save = new JButton("Save");
	
	
	public Modify(){
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
		frame.setSize(400,200);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		JPanel userpanel = new JPanel();
		JPanel oldpasswordpanel = new JPanel();
		JPanel newpasswordpanel = new JPanel();
		
		userpanel.add(name);
		userpanel.add(userField);
		
		oldpasswordpanel.add(oldpass);
		oldpasswordpanel.add(oldpassField);
		
		newpasswordpanel.add(newpass);
		newpasswordpanel.add(newpassField);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(userpanel);
		panel.add(oldpasswordpanel);
		panel.add(newpasswordpanel);
		panel.add(save);
		
		frame.add(panel);
		oldpassField.setEchoChar('*');
		newpassField.setEchoChar('*');
		
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
				        
					 String username = userField.getText().trim();
					 String oldpassword = new String(oldpassField.getPassword());
					 String newpassword = new String(newpassField.getPassword());
					 
					 String findUser = "select username from log where username = '" + username + "' AND password = '" + oldpassword +"'";
                     rs = st.executeQuery(findUser);
				     
				     int count = 0;
				     while(rs.next()){
				    	 count++;
				    	 
				     }
				     
				     if(count == 0){
				    	 
				    	 JOptionPane.showMessageDialog(null, "Wrong Username or Old Password!");
				    	 
				     }
				     else if(newpassword.equals("")){
				    	 JOptionPane.showMessageDialog(null, "No Empty password!");
				    	 
				     }
				     else if (count == 1 ){
				    	 String sql = "update log set password = '"+ newpassword +"' where username = '" + username + "'";
					     st = con.createStatement();
					     st.executeUpdate(sql);

						 JOptionPane.showMessageDialog(null, "Password changed!");
						 frame.dispose();
						 
				     }
					    
					
				}catch(Exception ex){
					System.out.print("Register Failed\n");
				}
			}
		});
		
		     
		}
	
}
