/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.nio.ByteBuffer;
import jpcap.*;
import jpcap.packet.*;
import jpcap.packet.IPPacket;
/**
 *
 * @author Gopani
 */
public class Example2 
{
public static void main(String args[]) throws IOException, NullPointerException{
    NetworkInterface[] devices = JpcapCaptor.getDeviceList();
    JpcapCaptor captor=JpcapCaptor.openDevice(devices[3], 4000, false, 20);
    int h[]=new int[100];
    String s;
    try
    {
        
 
 
  //IPPacket po = 
for(int i=0;i<10;i++){
  //capture a single packet
  Packet p=captor.getPacket();
  //System.out.println(p);
  IPPacket ip = (IPPacket)captor.getPacket();
  //captor.setFilter("ip", true);
  //save it into the opened fi
  //s= new String(p.);
  if(ip.protocol==6)
  {
  /*for(int j=0;j<p.header.length;j++)
  {
      h[j]=p.header[j];
      System.out.print(h[j]);
  }*/
  //h = new String(p.header);
  System.out.println();
  //System.out.println(p.header);
    
  TCPPacket ud1 = (TCPPacket)ip;
  System.out.print(ud1.hop_limit+"-");
  System.out.print(ud1.len+"-");
  System.out.print(ud1.src_ip.getAddress()+"-");
//System.out.println(ud1.dst_port);
  }
  }
    }
  catch(NullPointerException na){};
  
}
}

class PacketPrinter implements PacketReceiver {
  //this method is called every time Jpcap captures a packet
  public void receivePacket(Packet packet) {
    //just print out a captured packeet
      UDPPacket ud2 = (UDPPacket)packet;
      System.out.println(ud2.hop_limit);
      System.out.println(ud2.protocol);
      
  }
}
