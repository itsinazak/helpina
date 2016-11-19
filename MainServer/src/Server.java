
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class Server implements Runnable{

	@Override
	public void run() {

		try {
			ServerSocket ss=new ServerSocket(3333, 100);  
			while(true){
				
				Socket s=ss.accept();  
				System.out.println("connection made");
				//ThreadedClient client = new ThreadedClient(s);
				EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                JFrame jf = new WorkersFrame(s);
		                jf.setVisible(true);
		                jf.setTitle("Factory Workers Status");
		                jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		                jf.setUndecorated(true);
		                jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		                jf.setResizable(true);
		                jf.setTitle("Worker's Status");
		                jf.pack();
		        }
				
			});
			}	
			//ss.close();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  



	}
}
