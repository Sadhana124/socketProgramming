import java.io.*;
import java.net.*;

class Sender implements Runnable
{
  Thread t;
  PrintWriter pw;
  Sender(PrintWriter out)
  {
     pw = out;
     t = new Thread(this,"ServerSender");
     t.start();
  }
  
  public void run()
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String I;
    try
    {
      I=br.readLine();
      while(true)
      {
        pw.println(I);
        if(I.compareTo("quit")==0)
        {
          break;
        }
        I = br.readLine();
      }
      br.close();
    }
    catch(IOException ioe)
    {
    }  
  }
}

class Receiver implements Runnable
{
  Thread t;
  BufferedReader br;
  
  Receiver(BufferedReader in)
  {
     br = in;   
     t = new Thread(this,"ServerReceiver");
     t.start();
  }
  
  public void run()
  {
    String I;
    try
    {
      I = br.readLine();
      while(true)
      {
         System.out.println("RECD MSG:" + I);
         if(I.compareTo("quit")==0)
         {
           break;
         }
         I = br.readLine();
         
      }
    }
    catch(IOException ioe)
    {
    }
    catch(NullPointerException nle)
    {
    }
  }
}

public class NServer
{
   public static void main(String args[])
   { 
      try
      {
         ServerSocket s1 = new ServerSocket(2001);
         ServerSocket s2 = new ServerSocket(2002);
         Socket c1 = s1.accept();
         Socket c2 = s2.accept();
         PrintWriter pw = new PrintWriter(c2.getOutputStream(),true);
         BufferedReader br = new BufferedReader(new InputStreamReader(c1.getInputStream()));
         Sender st = new Sender(pw);
         Receiver rt = new Receiver(br);
         st.t.join();
         rt.t.join();
         pw.close();
         br.close();
         c1.close();
         c2.close();
         
      }
      catch(Exception e)
      {
        
      }
   }
}
