import java.io.*;
import java.net.*;

class Sender implements Runnable
{
   Thread t;
   PrintWriter pw;
   
   Sender(PrintWriter out)
   {
      pw = out;
      t = new Thread(this,"ClientSender");
      t.start();
   }
   
   public void run()
   {
      String I;
      BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
      try
      {
        I = br.readLine();
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
     catch (IOException ioe)
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
      t = new Thread(this,"ClientReceiver");
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
          System.out.println("RECVD MSG:" + I);
          if(I.compareTo("quit") ==0)
          {
             break;
          }
          I = br.readLine();
        }
        
      }
      catch (IOException ioe)
      {
      }
      catch (NullPointerException nle)
      {
      }
      
   }
}

public class Nclient
{
   public static void main(String args[])
   {
     try
     {
     Socket c1 = new Socket(InetAddress.getLocalHost(),2001);
     Socket c2 = new Socket(InetAddress.getLocalHost(),2002);
     PrintWriter pw = new PrintWriter(c1.getOutputStream(),true);
     BufferedReader br = new BufferedReader(new InputStreamReader(c2.getInputStream()));
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
