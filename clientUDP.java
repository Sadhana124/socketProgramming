import java.io.*;
import java.net.*;

class UDPSender implements Runnable
{
  Thread t;
  DatagramSocket ds;
    
  UDPSender(DatagramSocket s)
  {
    ds = s;
    t = new Thread(this,"UDPClientSender");
    t.start();
  }
  
  public void run()
  {
    byte buffer[] = new byte[1024];
    int c;
    int pos=0;
    boolean done = false;
    try
    {
      while(!done)
      {
        c = System.in.read();
        switch(c)
        {
           case '-' : done = true;
                      break;
           case '\n' : ds.send(new DatagramPacket(buffer,pos,InetAddress.getLocalHost(),2080));
                       pos = 0; 
                       break;
           default : buffer[pos++]= (byte) c;
        }
      }
    }
    catch(IOException e)
    {
    }
  }
}

class UDPReceiver implements Runnable
{
  Thread t;
  DatagramSocket ds;
    
  UDPReceiver(DatagramSocket s)
  {
    ds = s;
    t = new Thread(this,"UDPClientReceiver");
    t.start();
  }
  
  public void run()
  {
    byte buffer[] = new byte[1024];
    DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
    String s;
    boolean done = false;
    try
    {
      while(!done)
      {
        ds.receive(dp);
        s = new String(dp.getData(),0,dp.getLength());
        System.out.println("RCD MSG:" + s);
        if(s == "-")
          done = true;
      }
    }
    catch(IOException e)
    {
    }
  }
}

public class clientUDP
{
  public static void main(String args[])
  {
    try
    {
      DatagramSocket d1 = new DatagramSocket(2071);
      DatagramSocket d2 = new DatagramSocket(2081);
      UDPSender s = new UDPSender(d1);
      UDPReceiver r = new UDPReceiver(d2);
      s.t.join();
      r.t.join();
      d1.close();
      d2.close();
    }
    catch(Exception e)
    {
    }
  } 
}
