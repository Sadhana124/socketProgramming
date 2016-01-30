import java.io.*;

import java.net.*;

public class client2

{

public static void main(String args[])throws IOException

{

int Serverport=3001;

DatagramSocket ds;

DatagramPacket p;

byte buffer[]=new byte[100];

int clientport=3000;

String str;

ds=new DatagramSocket(clientport);

p=new DatagramPacket(buffer,buffer.length);

BufferedReader dis=new BufferedReader(new InputStreamReader(System.in));

System.out.println("Client is waiting");

System.out.println("Type\"end\"to exit");

InetAddress ia=InetAddress.getByName("localhost");

while(true)

{

ds.receive(p);

str=new String(p.getData(),0,p.getLength());

if(str.equals("end"))

break;

else

System.out.println(str);

String st=dis.readLine();

if(st==null||st.equals("end"))

{

buffer=st.getBytes();

ds.send(new DatagramPacket(buffer,st.length(),ia,Serverport));

break;

}

buffer=st.getBytes();

ds.send(new DatagramPacket(buffer,st.length(),ia,Serverport));

}

}

}
