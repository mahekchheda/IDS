/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ids;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;
import jpcap.*;
import jpcap.packet.*;
import jpcap.packet.Packet;
import jpcap.packet.IPPacket;
import jpcap.packet.UDPPacket;
import jpcap.packet.TCPPacket;
import java.sql.*; 
  /*      *
 * @author Gopani
 */
public class AiS2 
{

PreparedStatement stmtt = null;    
Connection conn = null;
    AiS2() throws IOException
{
  encode2();
}

void encode2() throws IOException
{
try{
//int re=0;
NetworkInterface[] devices2 = JpcapCaptor.getDeviceList();
JpcapCaptor captor=JpcapCaptor.openDevice(devices2[3], 65535, false, 20);
Class.forName("com.mysql.jdbc.Driver").newInstance();
conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ids","root","gopani");
captor.setFilter("udp and ip", true);
captor.loopPacket(-1,new PacketPrinter2());
/*counter++;
System.out.println(counter);*/
}
catch(Exception se){System.out.println(se);};
}
    

public static void main(String args[])throws IOException
{
 AiS2 ae2 = new AiS2();
 
}
    
    
}

class PacketPrinter2 implements PacketReceiver {
  //this method is called every time Jpcap captures a packet
PreparedStatement stmtt2 = null;    
PreparedStatement stmtt3= null;
Connection conn2 = null;
ResultSet rs;
public void receivePacket(Packet packet) {
try
{
Class.forName("com.mysql.jdbc.Driver").newInstance();
conn2=DriverManager.getConnection("jdbc:mysql://localhost:3306/ids","root","gopani");
    //just print out a captured packeet
//AiS ae1 = new AiS();
String antibody,sourc,dest;
int srcport1=0,destport1=0,payload=0;
long timestm=0,tdiff=0;
int id=0,idd=0,i=0,th=0;
int srcip[],destip[];
long t1[] = new long[2];
int length1=0;
boolean dfrag,mfrag;
String data2;
int offs;
//boolean self[] = new boolean[7];
int re2=0;
IPPacket ipp = (IPPacket)packet;
UDPPacket ip1 = (UDPPacket)ipp;
srcport1=ip1.src_port;
destport1=ip1.dst_port;
payload = ip1.data.length;
timestm=ip1.usec;
id=ip1.ident;
//ipp1=Integer.parseInt(ip1.src_ip.toString());
srcip=stringcon2(ip1.src_ip.toString());
destip=stringcon2(ip1.dst_ip.toString());
data2 = new String(ip1.data);
dfrag = ip1.d_flag;
mfrag = ip1.more_frag;
offs = ip1.offset;
length1=ip1.length;
int id2[]=new int[2];
int count=0;
stmtt2=conn2.prepareStatement("insert into timestamm values(?,?,?,?)");
stmtt2.setNull(1,java.sql.Types.INTEGER);
stmtt2.setLong(2,ip1.sec);
stmtt2.setLong(3,ip1.usec);
stmtt2.setInt(4, ip1.ident);
re2=stmtt2.executeUpdate();
stmtt2=conn2.prepareStatement("select * from timestamm where usecc=?");
stmtt2.setLong(1,ip1.usec);

rs = stmtt2.executeQuery();
while(rs.next())
{
idd=rs.getInt(1);
id=rs.getInt(4);
}
stmtt2=conn2.prepareStatement("select * from timestamm where identi>=? and identi<=?");
stmtt2.setInt(1,idd-1);
stmtt2.setInt(2,idd);
rs = stmtt2.executeQuery();
while(rs.next())
{
t1[i]=rs.getLong(2);
id2[i]=rs.getInt(4);
i++;
}
tdiff=t1[1]-t1[0];
id=id2[1]-id2[0];
String temp3;
antibody=encode3(srcport1,destport1,payload,srcip,destip,tdiff,id,data2,dfrag,mfrag,offs,length1);
//System.out.println(antibody);
stmtt2 = conn2.prepareStatement("select * from self");
rs=stmtt2.executeQuery();

//re2=stmtt2.executeUpdate();
//System.out.println(re2+"Hi");
while(rs.next())
{
//System.out.println("Hello");
temp3=rs.getString(1);
th=hamming(antibody,temp3);
if(th>5)    
{
stmtt3=conn2.prepareStatement("update self set stimulation=? where uid=?");
stmtt3.setInt(1,count++);
stmtt3.setInt(2, rs.getInt(4));
re2=stmtt3.executeUpdate();
if(rs.getInt(4)>100)
System.out.println("Alert Attack!!");
}
}

//System.out.println(ip1.protocol);
}
catch(Exception ee){System.out.println(ee);}
  }   
 String encode3(int srcport1,int destport1,int payload,int[] srcip,int[] destip,long tdiff,int id,String data2,boolean dfrag,boolean mfrag,int offs,int length1)
 {
 String antibody="";
 if(srcport1>0 && srcport1<1024)
 antibody+="0";
 else
 antibody+="1";
 if(destport1>0 && destport1<1024)
 antibody+="0";
 else
 antibody+="1";
 if(payload>64)
 antibody+="0";
 else
 antibody+="1";
 if(srcip[0]==192 && srcip[1]==168 && srcip[2]>=0 && srcip[3]>=0 && srcip[2]<1 &&srcip[3]<30)
 antibody+="0";
else
antibody+="1";
 if(destip[0]==192 && destip[1]==168 && destip[2]>=0 && destip[3]>=0 && destip[2]<1 &&destip[3]<30)
 antibody+="0";
else
antibody+="1";
 if(tdiff>5)
     antibody+="0";
else
antibody+="1";
 if(id==1 && tdiff>3)
     antibody+="0";
 else 
     antibody+="1";
 if(data2.matches("exe$"))
     antibody+="1";
 else
     antibody+="0";
 if(dfrag==true)
     antibody+="0";
 else
     antibody+="1";
 if(mfrag==true)
     antibody+="1";
 else antibody+="0";
 if(offs==0)
     antibody+="0";
 else
     antibody+="1";
 if(length1>50 && length1<150)
     antibody+="0";
 else
     antibody+="1";
 return antibody;
 
}
String randomgen2()
{
Random self = new Random();
int rando[] = new int[5];
String randome="";
for(int i=0;i<5;i++)
{
rando[i]=self.nextInt(2);
randome=randome+rando[i];
}
    return randome;
    }
int[] stringcon2(String input)
{

int temp2[]=new int[4];

String temp=input.substring(1);
String delimiter="\\.";
String temp1[] = temp.split(delimiter);
for(int i=0;i<temp1.length;i++)
{
temp2[i]=Integer.parseInt(temp1[i]);        
}
//System.out.println();
return temp2;
   
}
int hamming(String str1,String str2)
{
int j=0,threshold=0;
char s,d;
for(j=0;j<12;j++)
{
 
s=str1.charAt(j);

//System.out.println(s);
d=str2.charAt(j);
// System.out.println(d);
if(s==d)
{
threshold++;    
}
}   
return threshold;
}

}