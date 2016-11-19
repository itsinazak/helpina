
public class Worker {
	String location;
	int id, pulse, status, temp;
	boolean tf, acc;
	
	
	public Worker() {
		super();
		this.location = "00";
		this.id = 0;
		this.pulse = 0;
		this.status = 0;
		this.temp = 0;
		this.acc = false;
		this.tf = true;
	}
	
	public Worker(String location, int id, int pulse, int status, int temp,
			boolean acc, boolean tf) {
		super();
		this.location = location;
		this.id = id;
		this.pulse = pulse;
		this.status = status;
		this.temp = temp;
		this.acc = acc;
		this.tf = tf;
	}
	public void update(String location, int id, int pulse, int status, int temp,
			boolean acc, boolean tf){
		this.location = location;
		this.id = id;
		this.pulse = pulse;
		this.status = status;
		this.temp = temp;
		this.acc = acc;
		this.tf = tf;
	}
	
	
}
