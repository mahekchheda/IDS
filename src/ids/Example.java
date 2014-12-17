/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ids;
import java.io.IOException;
import jpcap.*;
import java.net.InetAddress;
/**
 *
 * @author Mahek Chheda
 */
public class Example {
    //Obtain the list of network interfaces
    public static void main(String args[]) throws IOException
    {
        
NetworkInterface[] devices = JpcapCaptor.getDeviceList();
String name;
int count=0;
//for each network interface
for (int i = 0; i < devices.length; i++) {
  //print out its name and description
    name=devices[i].name;
    System.out.println(name);
  System.out.println(i+": "+devices[i].name + "(" + devices[i].description+")");

  //print out its datalink name and description
  System.out.println(" datalink: "+devices[i].datalink_name + "(" + devices[i].datalink_description+")");

  //print out its MAC address
  System.out.print(" MAC address:");
  for (byte b : devices[i].mac_address)
    System.out.print(Integer.toHexString(b&0xff) + ":");
  System.out.println();
//NetworkInterfaceAddress bss;
//String id1 = bss.address+"";
//print out its IP address, subnet mask and broadcast address
  for (NetworkInterfaceAddress a : devices[i].addresses)
  {
      name=a.address+"";
      System.out.println(name);
    System.out.println(" address:"+a.address );
  }
  }

for(int i=0;i<100;i++){
  //capture a single packet and print it out
  System.out.println();
}


    }
}
