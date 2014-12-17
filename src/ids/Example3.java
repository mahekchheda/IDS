/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ids;

import java.io.IOException;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import jpcap.packet.UDPPacket;
import jpcap.*;
import jpcap.packet.IPPacket;

/**
 *
 * @author Mahek Chheda
 */
public class Example3 {
    public static void main(String args[]) throws IOException
    {
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
     JpcapCaptor captor=JpcapCaptor.openDevice(devices[3], 65535, false, 20);

     captor.loopPacket(-1,new PacketPrinter1());   
    }
}

 class PacketPrinter1 implements PacketReceiver {
  //this method is called every time Jpcap captures a packet
  public void receivePacket(Packet packet) {
    //just print out a captured packeet
      UDPPacket ud2 = (UDPPacket)packet;
      System.out.print(ud2.sec);
      //System.out.print(ud2.usec);
      System.out.println();
      
  }
}