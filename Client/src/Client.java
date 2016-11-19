import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import jssc.SerialPort;
import jssc.SerialPortException;


public class Client implements Runnable {
	String location;

	Client (String s)
	{
		location = s;
	}
	@Override
	public void run() {

		Socket s;
		try {
			s = new Socket("10.25.33.172",3333);

			DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
			SerialPort serialPort = new SerialPort("COM3");
			serialPort.openPort();//Open serial port
			serialPort.setParams(9600, 8, 1, 0);
			//BufferedReader br = new BuefferedReader (new InputStreamReader(serialPort.ge))
			while(true)
			{
//				System.out.println("ok");
//				byte[] id = serialPort.readBytes(2);
//				byte[] temp = serialPort.readBytes(2);
//				byte[] pulse = serialPort.readBytes(3);
//				byte[] acc = serialPort.readBytes(4);
//				byte[] TF = serialPort.readBytes(1);
//				byte[] status = serialPort.readBytes(1);
//				String str = serialPort.readString(10);
//				String id = serialPort.readString(2);
//				String temp = serialPort.readString(2);
//				String pulse = serialPort.readString(3);
//				String acc = serialPort.readString(4);
//				String TF = serialPort.readString(1);
//				String status = serialPort.readString(1);
				
//				//String str = new String ("0127552479800");
//				System.out.println("str");
//				dout.writeUTF(str);
//				dout.flush(); 
//				String sendout = new String (id + " "+ temp + " "+ pulse + " "+ acc + " "+ TF + " "+ status + " "+ location);
				//byte[] b = serialPort.readBytes(15);
				String str =serialPort.readString(15);
				String sendout = new String (str.substring(0, 14));
				//int i = Integer.parseInt(str);
				//String str = serialPort.readString(2);
				//serialPort.readIntArray()
//				int xd = Integer.parseInt(str);
				//char xe = str.charAt(0);
				//int z = (int)xe;
				System.out.println(sendout + " " + location);
				
				
				dout.writeUTF(sendout + " " + location);
				Thread.sleep(100);
				dout.flush(); 
				
			}	

			//serialPort.closePort();//Close serial port
			//dout.close();  
			//s.close();  
		}	 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}
}