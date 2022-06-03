package logic;

public class IDcontroller {
	
	private int id = 1;
	
	/**
	 * Setzt die ID-Variable auf einen bestimmten Wert
	 * @param id Wert
	 */
	public void setID(int id) {
		this.id = id;
	}
	
	/**
	 * Gibt die ID-Variable zur�ck
	 * @return ID-Variable
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Erh�ht die ID-Variable um 1
	 */
	public void increaseID() {
		this.id = this.id + 1;
	}

}
