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
public class AiS 
{


    AiS() throws IOException
{
  encode();
}

void encode() throws IOException
{
try{

//int counter=0;

NetworkInterface[] devices = JpcapCaptor.getDeviceList();
JpcapCaptor captor=JpcapCaptor.openDevice(devices[3], 65535, false, 20);
Class.forName("com.mysql.jdbc.Driver").newInstance();

captor.loopPacket(-1,new PacketPrinter());
/*counter++;
System.out.println(counter);*/
}
catch(Exception se){System.out.println(se);};
}
    

public static void main(String args[])throws IOException
{
 AiS ae = new AiS(); 
}
        
}

class PacketPrinter implements PacketReceiver {
  //this method is called every time Jpcap captures a packet
PreparedStatement stmt2 = null;    
PreparedStatement stmt3 = null;
Connection con2 = null;
Connection con3 = null;  
PreparedStatement stmt = null;    
Connection con = null;
public void receivePacket(Packet packet) {
try
{  
int re=0;

String self2[] = new String[100];
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ids","root","gopani");

Class.forName("com.mysql.jdbc.Driver").newInstance();
con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/ids","root","gopani");
con3=DriverManager.getConnection("jdbc:mysql://localhost:3306/ids","root","gopani");
//PreparedStatement stmt2 = null;    
//Connection con5 = null;
//Connection con6=null;
ResultSet rst2=null;
    //just print out a captured packeet
//AiS ae1 = new AiS();
String antibody,sourc,dest;
int srcport1=0,destport1=0,payload=0;
long timestm=0;
int id=0,idd=0;
int srcip[],destip[];
//boolean self[] = new boolean[7];
int re2=0;
int urgp,win,i=0;
long acn,seq1,tdiff=0;
boolean fy,sy,ack;
long t1[] = new long[2];
int id2[]=new int[2];
int length1=0;
boolean dfrag,mfrag;
String data2;
int offs;
IPPacket ip = (IPPacket)packet;
//System.out.println("Hi");
stmt2=con2.prepareStatement("insert into timestamm values(?,?,?,?)");
stmt2.setNull(1,java.sql.Types.INTEGER);
stmt2.setLong(2,ip.sec);
stmt2.setLong(3,ip.usec);
stmt2.setInt(4, ip.ident);
re2=stmt2.executeUpdate();

stmt2=con2.prepareStatement("select * from timestamm where usecc=?");
stmt2.setLong(1,ip.usec);
rst2 = stmt2.executeQuery();
while(rst2.next())
{
idd=rst2.getInt(1);
id=rst2.getInt(4);
}
if(ip.protocol==17)
{
//System.out.println(idd-1+"\t"+idd);
stmt2=con2.prepareStatement("select * from timestamm where identi>=? and identi<=?");
stmt2.setInt(1,idd-1);
stmt2.setInt(2,idd);
rst2 = stmt2.executeQuery();
while(rst2.next())
{
t1[i]=rst2.getLong(2);
id2[i]=rst2.getInt(4);
i++;
}
tdiff=t1[1]-t1[0];
id=id2[1]-id2[0];
UDPPacket ip2 = (UDPPacket)packet;

sourc=ip2.src_ip.toString();
dest=ip2.dst_ip.toString();
srcport1=ip2.src_port;
destport1=ip2.dst_port;
payload = ip2.data.length;
timestm=ip2.sec;
data2 = new String(ip2.data);
dfrag = ip2.d_flag;
mfrag = ip2.more_frag;
offs = ip2.offset;
length1=ip2.length;
Random rad2 = new Random();
int strti=0,etrti=0;
strti=rad2.nextInt(12);
//System.out.println(strti);
etrti=rad2.nextInt(12);
        //ipp1=Integer.parseInt(ip1.src_ip.toString());
srcip=stringcon(ip2.src_ip.toString());
destip=stringcon(ip2.dst_ip.toString());
antibody=encode1(srcport1,destport1,payload,srcip,destip,tdiff,id,data2,dfrag,mfrag,offs,length1);
//System.out.println(antibody);
for(i=0;i<3;i++)
{
//self2[i] =randomgen();
if(strti<=etrti)    
{
    String temp1=randomgen(strti);
    String temp2=randomgen(12-etrti);
    self2[i]=temp1+antibody.substring(strti,etrti)+temp2;
}
else
{
    String temp1=randomgen(etrti);
    String temp2=randomgen(12-strti);
    self2[i]=temp1+antibody.substring(etrti,strti)+temp2;
}  
System.out.println(self2[i]);
stmt=con.prepareStatement("insert into self(randomstring) values(?)");
stmt.setString(1,self2[i]);
re=stmt.executeUpdate();
}
stmt2 = con2.prepareStatement("select * from self where randomstring=?");
stmt2.setString(1, antibody);
rst2 = stmt2.executeQuery();
rst2.first();
if(rst2.first())
{
//System.out.println("SS");
stmt3 = con3.prepareStatement("delete from self where randomstring=?");
stmt3.setString(1,rst2.getString(1));
stmt3.execute();
}
//System.out.println(encode1(srcport1,destport1,payload,srcip,destip,tdiff,id,data2,dfrag,mfrag,offs,length1));
System.out.println(ip2.protocol);
//System.out.println(timestm);
//System.out.println(ip1.src_ip.toString());
//System.out.println(randomgen());
}
else if(ip.protocol==6)
{
TCPPacket ip3 = (TCPPacket)packet;
sourc=ip3.src_ip.toString();
dest=ip3.dst_ip.toString();
srcport1=ip3.src_port;
destport1=ip3.dst_port;
payload = ip3.data.length;
timestm=ip3.usec;
id=ip3.ident;
acn=ip3.ack_num;
win=ip3.window;
urgp=ip3.urgent_pointer;
seq1=ip3.sequence;
fy=ip3.fin;
sy=ip3.syn;
ack=ip3.ack;
//ipp1=Integer.parseInt(ip1.src_ip.toString());
srcip=stringcon(ip3.src_ip.toString());
destip=stringcon(ip3.dst_ip.toString());
System.out.println(ip3.protocol);
/*antibody=encode1(srcport1,destport1,payload,srcip,destip);
stmt2 = con2.prepareStatement("delete from self where randomstring=?");
stmt2.setString(1, antibody);
re2=stmt2.executeUpdate();
//Syste2.protocol);m.out.println(encode1(srcport1,destport1,payload,srcip,destip));
//System.out.println(ip1.src_ip.toString());
//System.out.println(randomgen());  

*/ }
else
{
System.out.println("Cannot detect other protocols");    
}

}
catch(Exception ee){System.out.println(ee);}
  }   
 String encode1(int srcport1,int destport1,int payload,int[] srcip,int[] destip,long tdiff,int id,String data2,boolean dfrag,boolean mfrag,int offs,int length1)
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
 if(tdiff>5000)
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
String randomgen(int l)
{
Random self = new Random();
int rando[] = new int[l];
String randome="";
for(int i=0;i<l;i++)
{
rando[i]=self.nextInt(2);
randome=randome+rando[i];
}
    return randome;
    }
int[] stringcon(String input)
{

int temp2[]=new int[4];

String temp=input.substring(1);
String delimiter="\\.";
//System.out.println(temp[0]);
//System.out.println(delimiter);
String temp1[] = temp.split(delimiter);
//System.out.println(input);
for(int i=0;i<temp1.length;i++)
{
temp2[i]=Integer.parseInt(temp1[i]);    
//System.out.print(temp2[i]+"  ");
//return temp2;    
}
//System.out.println();
return temp2;
   
}


}