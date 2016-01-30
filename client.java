import java.io.*;
import java.net.*;
public class client
{
public static void main(String args[])throws IOException
{
Socket c=new Socket("127.0.0.1",3002);

BufferedReader in=new BufferedReader(new InputStreamReader(c.getInputStream()));
String I=in.readLine();
while(!I.equals("quit"))
{
System.out.println(I);
I=(String)in.readLine();
}
c.close();
in.close();
}
}
