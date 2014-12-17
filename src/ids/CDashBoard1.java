/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ids;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import jpcap.*;
import jpcap.packet.Packet;

/**nuBar
 *
 * @author Gopani
 */
public class CDashBoard1 extends JFrame {
NetworkInterface[] devices2 = JpcapCaptor.getDeviceList();     
JMenuBar idsmenu;
JMenu file;
JMenu view;
JMenu help;
JTable table2;
JFrame frame3;
static JTextArea dis;
JpcapCaptor captor;
CDashBoard cdb2;
static int selectedNIC=0;
CDashBoard1(int rown) throws IOException
{
selectedNIC=rown;    
initComponents2();    
}

    
private void initComponents2() throws IOException
{
frame3= new JFrame("Cloud Intrusion Detection System Version 1.0");
JPanel panel3 = new JPanel();
panel3.setLayout(null);
idsmenu = new JMenuBar();
file = new JMenu();
view = new JMenu();
help = new JMenu();
file.setText("File");
view.setText("View");
help.setText("Help");
JMenuItem new1 = new JMenuItem("New");
JMenuItem load1 = new JMenuItem("Load");
JMenuItem toolbar = new JMenuItem("Hide Toolbar");
JMenuItem help2 = new JMenuItem("About us");

file.add(new1);
file.add(load1);
view.add(toolbar);
help.add(help2);
idsmenu.add(file);
idsmenu.add(view);
idsmenu.add(help);
JToolBar toolbar1 = new JToolBar();
toolbar1.setFloatable(false);
toolbar1.setBounds(0,0 ,500, 40);
JButton play1 = new JButton();
JButton stop1 = new JButton();
JButton nadap = new JButton();
JButton logout = new JButton();
JLabel cone = new JLabel();
cone.setText("Connections:");
dis = new JTextArea();
JScrollPane pane2 = new JScrollPane(dis);
ImageIcon play12 = new ImageIcon("D:/IDS images/play.jpg");
ImageIcon stop12 = new ImageIcon("D:/IDS images/stop.jpg");
ImageIcon adap = new ImageIcon("D:/IDS images/nad2.png");
ImageIcon logt = new ImageIcon("D:/IDS images/logout.png");
logout.setIcon(logt);
play1.setIcon(play12);
stop1.setIcon(stop12);
nadap.setIcon(adap);
logout.setBounds(450,5,30,30);
play1.setBounds(10, 10, 20, 20);
stop1.setBounds(35,10,20,20);
nadap.setBounds(60, 10,20,20);
cone.setBounds(10, 20, 100,100);
dis.setBounds(10,100,400,300);
dis.setLineWrap(true);
toolbar1.add(play1);
toolbar1.add(stop1);
toolbar1.add(nadap);
panel3.add(cone);
panel3.add(play1);
panel3.add(stop1);
panel3.add(nadap);
panel3.add(dis);
panel3.add(logout);
frame3.add(panel3);
panel3.add(toolbar1);
frame3.setJMenuBar(idsmenu);
frame3.setIconImage(Toolkit.getDefaultToolkit().getImage("D:/IDS images/title1.png"));
frame3.setResizable(false);
frame3.setSize(500,500);
frame3.setVisible(true);


logout.addActionListener(new ActionListener()
        {
        public void actionPerformed(ActionEvent aee)
        {
           Analyzer aln = new Analyzer();
           frame3.setVisible(false);
        }
        } 
        );


nadap.addActionListener(new ActionListener()
        {
        public void actionPerformed(ActionEvent aee)
        {
            try {
                cdb2 = new CDashBoard();                
                System.out.println(selectedNIC);
                frame3.setVisible(false);
            } catch (IOException ex) {
                Logger.getLogger(CDashBoard1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        } 
        );


captor=JpcapCaptor.openDevice(devices2[selectedNIC],65535, false, 20);
stop1.addActionListener(new ActionListener()
        {
        public void actionPerformed(ActionEvent aee)
        {
        
        }
        } 
        );


play1.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent aee)
        {
           
        for(int i=0;i<10;i++)
        {
            if(captor.getPacket()!=null)
            {
            dis.append(captor.getPacket().toString());
            dis.append("\n");
            }
        }
            //captor.processPacket(100, new PacketPrinter());
        }
        } 
        );



}
/*
public static void main(String args[]) throws IOException
        
{
 CDashBoard1 cdb2 = new CDashBoard1(); 
 

}
*/

}
/*
class PacketPrinter implements PacketReceiver {
  //this method is called every time Jpcap captures a packet
  
    public void receivePacket(Packet packet) {
       
            //just print out a captured packet
   
          CDashBoard1.dis.append(packet.toString());
       
        
  }
}
*/