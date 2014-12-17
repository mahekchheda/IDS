/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ids;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author Gopani
 */
public class Analyzer extends JFrame implements ActionListener
{
    JTextField usern;
    JPasswordField passw;
    static String username1;
    String password1;
    JFrame frame1;
    
Analyzer()
{
initComponents();    
}   

private void initComponents()
{
frame1 = new JFrame("Cloud Intrusion Detection System Version 1.0");
JLabel user = new JLabel();
JLabel aboutus = new JLabel();
aboutus.setText("<html>Developed by- "
+ "Sunny Gopani<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
+ "&nbsp;&nbsp;&nbsp;&nbsp; Mahek Cheda<br>&nbsp;&nbsp;&nbsp;"
+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
+ "&nbsp;&nbsp;&nbsp; Siddharth Gala</html>");
JPanel panel = new JPanel();
panel.setLayout(null);
usern = new JTextField();
passw = new JPasswordField();
JSeparator sep = new JSeparator();
ImageIcon  usernam = new ImageIcon("D:/IDS images/lock.png");
ImageIcon passwd = new ImageIcon("D:/IDS images/key.png");
ImageIcon coverp = new ImageIcon("D:/IDS images/cover1.png");
JLabel cover = new JLabel();
cover.setIcon(coverp);
JLabel pass = new JLabel();
pass.setText("Enter Password");
user.setIcon(usernam);
pass.setIcon(passwd);
JButton next = new JButton("Login");
JButton reset = new JButton("Reset");
cover.setBounds(5,5,200,160);
aboutus.setBounds(250,5,250,100);
sep.setBounds(5,155,450,2);
user.setBounds(60,160,70,70);
pass.setBounds(60,250,70,70);
usern.setBounds(150,190,120,30);
//passwd.setBounds(20,250,80,30);
passw.setBounds(150,260,120,30);
next.setBounds(60,340,80,40);
reset.setBounds(190,340,80,40);
panel.add(cover);
panel.add(user);
panel.add(usern);
panel.add(pass);
panel.add(passw);
panel.add(next);
panel.add(sep);
panel.add(reset);
panel.add(aboutus);
frame1.add(panel);
frame1.setIconImage(Toolkit.getDefaultToolkit().getImage("D:/IDS images/title1.png"));
frame1.setResizable(false);
frame1.setSize(450,450);
frame1.setVisible(true);

next.addActionListener(this);


}
public void actionPerformed(ActionEvent ae)
{
    String username2=null;;
    String password2=null;;
    Connection con = null;
 try
 {
     username1 = usern.getText(); 
char passwd1[] = passw.getPassword();
String password1="";    
for(int i=0;i<passwd1.length;i++)
{
password1= password1+passwd1[i];    
}
    //System.out.println(password1);
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ids","root","gopani");
    PreparedStatement stmt = null;
    stmt=con.prepareStatement("select * from credentials where username=?");
    stmt.setString(1,username1);
    ResultSet rs = stmt.executeQuery();
    if(rs.next())
    {
        username2=rs.getString(1);
        password2=rs.getString(2);
    }
    if(username1!=null && username1.equals(username2)&&password1!=null && password1.equals(password2))
    {
    frame1.setVisible(false);
    CDashBoard1 cdb = new CDashBoard1(5);    
    }
    else
    {
    JOptionPane.showMessageDialog(frame1,"You have enter Wrong Username or Password \n Please Check again");    
    }
 }
 catch(Exception ex)
 {
}

}
public static void main(String args[])
{
 Analyzer al = new Analyzer(); 
 

}

}