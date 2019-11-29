package lampa;



public class Lamp {
	
	private boolean Turn;
	private boolean Electric;
    private String Status;
	
	
	public Lamp () {
	
	
	this.Turn=false;
	this.Electric=false;
	this.Status="o";
	}
	public boolean isTurn() {
		return Turn;
		}
		
		public void setTurn(boolean Turn) {
		this.Turn= Turn;

		}

		public boolean isElectric() {
		return Electric;
		}

		public void setElectric(boolean Electric) {
		this.Electric = Electric;
		}

		public String getStatus() {
		return Status;
		}

		public void setStatus(String Status) {
		this.Status = Status;
		}

	
	
	public void turnOff() {
	  	Turn=false;
	  	setStatus("o");
	}
	  	public void turnOn() {
		  	if(Turn=true);
		  	setStatus("*");
	}
	  	
	  	public void electricalOff() {
		  	Electric=false;
		  	setStatus("o");
		}
		  	public void electricalOn() {
			  	if(Electric=true);
			  	setStatus("*");
		}
		  	
	public void Turnoffon() {
		
		System.out.println(Status);
		
		System.out.println("Svetlo je " + Turn+" "+ "elektrina je " + Electric);
	}
		
		public void toggler() {
			if( Electric == true){
			this.turnOff();
			}
			else {
			this.turnOn();
			}
			
		}


		
}
	


	


