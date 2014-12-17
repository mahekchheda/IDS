/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ids;

import jpcap.*;
import java.io.*;
import java.util.*;
import jpcap.packet.*;
import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;
import jpcap.packet.Packet;
/**
 *
 * @author Gopani
 */
public class Analyze {

    public static void main(String args[]) throws IOException
    {
    int choice=0;
    int proto=0;
    int count=0;
    String data1;
    Scanner sc = new Scanner(System.in);
    try
    {
    NetworkInterface[] devices = JpcapCaptor.getDeviceList();
    System.out.println("Select Network Interface");
    choice=sc.nextInt();
    if(choice==3)
    {   
    JpcapCaptor captor=JpcapCaptor.openDevice(devices[choice], 4000, false, 20);
    //for(int i=0;i<100;i++)3
    captor.setNonBlockingMode(true);
     System.out.println(captor.isNonBlockinMode());
    while(captor.isNonBlockinMode())
    {
        System.out.println(captor.isNonBlockinMode());
        count++;
        System.out.println("2");
    IPPacket ipp = (IPPacket)captor.getPacket();
  
   
    //ICMPPacket ic = (ICMPPacket)ipp;
  
    proto = ipp.protocol;
            System.out.println(proto);
    //PacketPrinter pr;
    if(proto==17)
    {
     UDPPacket ud = (UDPPacket)ipp;
    data1 = new String(ud.data);
    System.out.print(ud.caplen+"       "+ud.d_flag+"     "+data1+"       "+ud.src_port+"         "+ud.dst_ip+"       "+ud.dont_frag+"        "+ud.flow_label+"       "+ud.hop_limit+"        "+ud.ident+"        "+ud.more_frag+"         "+ud.sec+"      "+ud.src_ip.toString()+"        "+ud.dst_ip.toString()+"        "+ud.version);
    System.out.println();
    
    }    
            
        
   // System.out.println("CAPLEN       DFLAG      DATA       SOURCEPORT         DEST-PORt       DONT_FRAG       FLOW LABEL      HOPLIMIT    IDENTITY        MORE-FRAG       SEC         SOURCE-IP       DEST-IP         VERSION          ACK      ACKNUM      FYN         PUSHFLAG       SYN         URG         URG POINTER");    
    
    //IPPacket ipp = (IPPacket)captor.getPacket();
    
    
    else if(proto==6)
    {    
     TCPPacket tc = (TCPPacket)ipp;
    data1 = new String(tc.data);
    System.out.print(tc.caplen+"       "+tc.d_flag+"     "+data1+"       "+tc.src_port+"         "+tc.dst_ip+"       "+tc.dont_frag+"        "+tc.flow_label+"       "+tc.hop_limit+"        "+tc.ident+"        "+tc.more_frag+"         "+tc.sec+"      "+tc.src_ip.toString()+"        "+tc.dst_ip.toString()+"        "+tc.version+"        "+tc.ack+"       "+tc.ack_num+"      "+tc.fin+"         "+tc.psh+"       "+tc.syn+"         "+tc.urg+"       "+tc.urgent_pointer);
    System.out.println();    
    }
    else break;
    }
    
    System.out.println(count);
    
    
    }
    else
        System.out.println("Wrong choice");
    }
    catch(Exception e){
        System.out.println("\n\n\n\n"+count);
    System.out.println(e);
    };
    
}
public static int[] bytetoint(int le, byte inp[])
{
    int h[] = new int[1000]; 
    for(int j=0;j<le;j++)
  {
      h[j]=inp[j];
      
  }
return h;
}
}
/*
class PacketPrinter implements PacketReceiver {
  //this method is called every time Jpcap captures a packet
  public void receivePacket(Packet packet) {
    //just print out a captured packeet
      /*System.out.println(packet.hop_limit);
      System.out.println(packet.ident);
      System.out.println(packet.length);/
  }
}*/