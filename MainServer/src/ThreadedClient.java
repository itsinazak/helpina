import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ThreadedClient implements Runnable{

	private Socket s;
	Worker[] workers = new Worker[50];

	public ThreadedClient(Socket sock){
		workers[0] = null;
		s = sock;
		Thread t = new Thread(this);
		t.start();
	}

	public void run(){

		try {

			DataInputStream din=new DataInputStream(s.getInputStream());  
			//DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
			String id, temp, pulse, acc, TF, status, location;
			int idI, pulseI, statusI, tempI;
			boolean tfb, accb;
			while(true){
				
				String str=din.readUTF();
				System.out.println(str);
				StringTokenizer token = new StringTokenizer(str," ");
				
				id = token.nextToken();
				idI = Integer.parseInt(id);
				
				
				temp= token.nextToken();
				tempI = Integer.parseInt(temp);
				
				pulse = token.nextToken();
				pulseI = Integer.parseInt(pulse);
				
				TF = token.nextToken();
			
				if(TF.equals('t'))
					tfb = true;
				else
					tfb = false;
				
				acc = token.nextToken();
				
				if(acc.equals('t'))
					accb = true;
				else
					accb = false;
				

				
				status = token.nextToken();
				statusI = Integer.parseInt(status);
				
				location = token.nextToken();
				
				if(workers[idI] == null)
					workers[idI] = new Worker(location, idI, pulseI, statusI, tempI, accb, tfb);
				else
					workers[idI].update(location, idI, pulseI, statusI, tempI, accb, tfb);
				
				
				if(status.equals("0")){
					System.out.println("Send help to Grid: " + location + ", Belt ID: " + id + ", Temp: " + temp + ", pulse: " + pulse );
				}
				
			}   
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			din.close();  
//			s.close(); 
			e.printStackTrace();
		}  
	}
	
}