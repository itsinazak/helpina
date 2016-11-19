import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   
		//Client client = new Client();
		Server server = new Server();
		//Thread clientThread =  new Thread(client,"Client");
		Thread serverThread =  new Thread(server,"Server");
		//clientThread.start();
		serverThread.start();
		/*
				EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                JFrame jf = new WorkersFrame();
	                jf.setVisible(true);
	                jf.setTitle("Factory Workers Status");
	                jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	                jf.setUndecorated(true);
	                jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	                jf.setResizable(true);
	                jf.setTitle("Worker's Status");
	                jf.pack();
	           }*/
	}

}



