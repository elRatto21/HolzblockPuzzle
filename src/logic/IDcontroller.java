package logic;

public class IDcontroller {
	
	private int id = 1;
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public void increaseID() {
		this.id = this.id + 1;
	}

}
