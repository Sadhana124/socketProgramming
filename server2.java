import java.io.*;

import java.net.*;

public class server2


{

public static void main(String args[])throws IOException

{

int Serverport=3001,clientport=3000;

DatagramSocket ds;

DatagramPacket p;

byte buffer[]=new byte[100];

ds=new DatagramSocket(Serverport);

p=new DatagramPacket(buffer,buffer.length);

BufferedReader dis=new BufferedReader(new InputStreamReader(System.in));

System.out.println("server started");

System.out.println("Type\"end\"to exit");

InetAddress ia=InetAddress.getByName("localhost");

while(true)

{

String str=dis.readLine();

if(str==null||str.equals("end"))

{

buffer=str.getBytes();

ds.send(new DatagramPacket(buffer,str.length(),ia,clientport));

break;

}

buffer=str.getBytes();

ds.send(new DatagramPacket(buffer,str.length(),ia,clientport));

ds.receive(p);

String s=new String(p.getData(),0,p.getLength());

if(s.equals("end"))

break;

else

System.out.println(s);

}

}

}
