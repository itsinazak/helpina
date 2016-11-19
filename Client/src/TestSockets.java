public class TestSockets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Client client = new Client("1A");
		//Server server = new Server();
		Thread clientThread =  new Thread(client,"Client");
		//Thread serverThread =  new Thread(server,"Server");
		clientThread.start();
		//serverThread.start();


	}

}