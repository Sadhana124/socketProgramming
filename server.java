import java.io.*;
import java.net.*;
public class server
{
public static void main(String args[])throws IOException
{
ServerSocket s=new ServerSocket(3002);
Socket c=s.accept();
PrintWriter out=new PrintWriter(c.getOutputStream(),true);
BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
String I;
I=(String)(in.readLine());
while(!I.equals("quit"))
{
out.println(I);
I=(in.readLine());

}
in.close();
out.close();
s.close();
c.close();
}
}



