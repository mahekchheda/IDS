/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ids;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import jpcap.*;

/**
 *
 * @author Mahek Chheda
 */
public class CDashBoard extends JFrame
{
NetworkInterface[] devices = JpcapCaptor.getDeviceList();   
String data[][]= new String[devices.length][5];
String cols[]={"Adapter","Data-Link","IP Address","SubnetMask"};
JFrame frame2;
JTable table;
JLabel label3;
String selectedNIC;
int col=0,row=0;
CDashBoard() throws IOException
{
initComponents2();    
}

private void initComponents2() throws IOException
{
JLabel label1 = new JLabel();
label1.setText("Cuurent Network Interface:");
JLabel label2 = new JLabel();
label2.setText("You can select a network adapter below, and click OK button:");
label3 = new JLabel();
label3.setText(null);
JPanel panel2 = new JPanel();
JButton button1 = new JButton("OK");
JButton button2= new JButton("Cancle");
for(int i=0;i<devices.length;i++)
{
data[i][0]=devices[i].description+"";
data[i][1]=devices[i].datalink_name+"";

for(NetworkInterfaceAddress a:devices[i].addresses)
{
data[i][2]=a.address+"";
data[i][3]=a.subnet+"";
} 
}
panel2.setLayout(null);
table = new JTable(data,cols){
public boolean isCellEditable(int rowIndex, int colIndex)
{
return false;    
}
};
table.setAutoResizeMode(0);
JScrollPane scrollpane1 = new JScrollPane(table);
table.setShowGrid(false);
table.getColumnModel().getColumn(0).setPreferredWidth(250);
table.getColumnModel().getColumn(1).setPreferredWidth(100);
table.getColumnModel().getColumn(2).setPreferredWidth(100);
table.getColumnModel().getColumn(3).setPreferredWidth(100);
table.getTableHeader().setReorderingAllowed(false);
table.setDragEnabled(false);
table.getTableHeader().setResizingAllowed(false);
label1.setBounds(20, 20, 300, 30);
label3.setBounds(20, 40, 300, 30);
label2.setBounds(20, 80, 400, 30);
scrollpane1.setBounds(20, 120,350, 100);
button1.setBounds(100, 300, 80,20 );
button2.setBounds(200, 300, 80,20);
panel2.add(label1);
panel2.add(label2);
panel2.add(label3);
panel2.add(button1);
panel2.add(button2);
panel2.add(scrollpane1);
//table.setCellSelectionEnabled(true);

table.addMouseListener(new MouseAdapter()   
{  
public void mouseClicked(MouseEvent evt)  
{  
col = table.getSelectedColumn();  
row = table.getSelectedRow();  
label3.setText((String)table.getModel().getValueAt(row, col));  
}  
});  

button1.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae){
selectedNIC = (String)table.getModel().getValueAt(row, col);
//row=table.getSelectedRow();
frame2.setVisible(false);
    try {
        CDashBoard1 cdb = new CDashBoard1(row);
    } catch (IOException ex) {
        Logger.getLogger(CDashBoard.class.getName()).log(Level.SEVERE, null, ex);
    }
}        
});

button2.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae){
frame2.setVisible(false);
}        
});


frame2 = new JFrame("Select a Network Adapter for sniffing");

frame2.add(panel2);
frame2.setIconImage(Toolkit.getDefaultToolkit().getImage("D:/IDS images/title1.png"));
frame2.setResizable(false);
frame2.setSize(400,400);
frame2.setVisible(true);




}

public static void main(String args[]) throws IOException
{
   // CDashBoard cbd = new CDashBoard();
}

}

