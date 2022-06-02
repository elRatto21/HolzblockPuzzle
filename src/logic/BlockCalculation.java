package logic;

import java.util.ArrayList;

public class BlockCalculation {
	
	private String blockCords[][] = new String[9][9];
	private boolean blockStatus[][] = new boolean[9][9];
	private int blockIDs[][] = new int[9][9];
	private ArrayList<String> cP = new ArrayList<String>(); // cp = calculatedParts
	private int part = -1;
	
	private IDcontroller ic = new IDcontroller();
	
	public BlockCalculation() {
		setupCords();
		setupStatus();
		setupIDs();
	}
	
	private void setupCords() {
		int i = 107;
		for(int a = 0; a < 9; a++) {
			int j = 58;
			for(int b = 0; b < 9; b++) {
				blockCords[a][b] = j + " " + i;
				j = j + 55;
			}
			i = i + 55;
		}
	}
	
	private void setupStatus() {
		for(int a = 0; a < 9; a++) {
			for(int b = 0; b < 9; b++) {
				blockStatus[a][b] = false;
			}
		}
	}
	
	private void setupIDs() {
		for(int a = 0; a < 9; a++) {
			for(int b = 0; b < 9; b++) {
				blockIDs[a][b] = ic.getID();
				ic.increaseID();
			}
		}
		ic.increaseID();
		ic.increaseID();
		ic.increaseID();
	}
	
	/**
	 * Gibt die X Koordinate für ein bestimmtes Feld zurück
	 * @param a Y-Feld vom Spielfeld
	 * @param b X-Feld vom Spielfeld
	 * @return X Koordinate für ein bestimmtes Feld
	 */
	public int getX(int a, int b) {
		String arr[] = blockCords[a][b].split(" ");
		return Integer.parseInt(arr[0]);
	}
	
	/**
	 * Gibt die Y Koordinate für ein bestimmtes Feld zurück
	 * @param a Y-Feld vom Spielfeld
	 * @param b X-Feld vom Spielfeld
	 * @return Y Koordinate für ein bestimmtes Feld
	 */
	public int getY(int a, int b) {
		String arr[] = blockCords[a][b].split(" ");
		return Integer.parseInt(arr[1]);
	}
	
	/**
	 * Setzt die Part-Nummer (1 bis 3)
	 * @param x Die Part-Nummer
	 */
	public void setPart(int x) {
		part = x;
	}
	
	/**
	 * Gibt die Part-Nummer zurück
	 * @return Part-Nummer
	 */
	public int getPart() {
		return part;
	}
	
	/**
	 * Gibt die ID von einem bestimmten Feld zurück
	 * @param a Die Y-Koordinate des Feldes (0 bis 8)
	 * @param b Die X-Koordinate des Feldes (0 bis 8)
	 * @return Die ID des Feldes
	 */
	public int checkID(int a, int b) {
		return blockIDs[a][b];
	}
	
	public ArrayList<String> calcBlocks(String cords, String[] Parts) {
		cP = new ArrayList<String>();
		String selectedPart = Parts[getPart()-1];
		int selectedA = -1;
		int selectedB = -1;
		for(int b = 0; b < 9; b++) {
			for(int a = 0; a < 9; a++) {
				System.out.println("Arr: " + blockCords[a][b] + " Cords: " + cords);
				if(blockCords[a][b].equals(cords)) {
					selectedA = a;
					selectedB = b;
					cP.add(selectedA + " " + selectedB + " " + checkID(selectedA, selectedB));
					System.out.println("A (Y): " + selectedA + " | B (X): " + selectedB);
					break;
				}
			}
			if(selectedA != -1) {
				break;
			}
		}
		switch(selectedPart) {
		case "dickesDach":
			cP.add(selectedA + " " + (selectedB+1) + " " + checkID(selectedA, (selectedB+1)));
			cP.add(selectedA + " " + (selectedB+2) + " " + checkID(selectedA, (selectedB+2)));
			cP.add((selectedA+1) + " " + selectedB + " " + checkID((selectedA+1), selectedB));
			cP.add((selectedA+2) + " " + selectedB + " " + checkID((selectedA+2), selectedB));
			break;
		case "dickesDachReversed":
			cP.add((selectedA) + " " + (selectedB-1) + " " + checkID((selectedA), (selectedB-1)));
			cP.add((selectedA) + " " + (selectedB-2) + " " + checkID((selectedA), (selectedB-2)));
			cP.add((selectedA+1) + " " + (selectedB) + " " + checkID((selectedA+1), (selectedB)));
			cP.add((selectedA+2) + " " + (selectedB) + " " + checkID((selectedA+2), (selectedB)));
			break;
		case "dickesL":
			cP.add((selectedA-1) + " " + (selectedB) + " " + checkID((selectedA-1), (selectedB)));
			cP.add((selectedA-2) + " " + (selectedB) + " " + checkID((selectedA-2), (selectedB)));
			cP.add((selectedA) + " " + (selectedB+1) + " " + checkID((selectedA), (selectedB+1)));
			cP.add((selectedA) + " " + (selectedB+2) + " " + checkID((selectedA), (selectedB+2)));
			break;
		case "dickesLreversed":
			cP.add((selectedA) + " " + (selectedB-1) + " " + checkID((selectedA), (selectedB-1)));
			cP.add((selectedA) + " " + (selectedB-2) + " " + checkID((selectedA), (selectedB-2)));
			cP.add((selectedA-1) + " " + (selectedB) + " " + checkID((selectedA-1), (selectedB)));
			cP.add((selectedA-2) + " " + (selectedB) + " " + checkID((selectedA-2), (selectedB)));
			break;
		case "kleinesSenkrecht":
			cP.add((selectedA+1) + " " + (selectedB) + " " + checkID((selectedA+1), (selectedB)));
			break;
		case "kleinesWaagerecht":
			cP.add((selectedA) + " " + (selectedB+1) + " " + checkID((selectedA), (selectedB+1)));
			break;
		case "kreuz":
			cP.add((selectedA+1) + " " + (selectedB) + " " + checkID((selectedA+1), (selectedB)));
			cP.add((selectedA-1) + " " + (selectedB) + " " + checkID((selectedA-1), (selectedB)));
			cP.add((selectedA) + " " + (selectedB+1) + " " + checkID((selectedA), (selectedB+1)));
			cP.add((selectedA) + " " + (selectedB-1) + " " + checkID((selectedA), (selectedB-1)));
			break;
		case "l":
			cP.add((selectedA-1) + " " + (selectedB) + " " + checkID((selectedA-1), (selectedB)));
			cP.add((selectedA-2) + " " + (selectedB) + " " + checkID((selectedA-2), (selectedB)));
			cP.add((selectedA) + " " + (selectedB+1) + " " + checkID((selectedA), (selectedB+1)));
			break;
		case "lreversed":
			cP.add((selectedA-1) + " " + (selectedB) + " " + checkID((selectedA-1), (selectedB)));
			cP.add((selectedA-2) + " " + (selectedB) + " " + checkID((selectedA-2), (selectedB)));
			cP.add((selectedA) + " " + (selectedB-1) + " " + checkID((selectedA), (selectedB-1)));
			break;
		case "mitteBlock":
			break;
		case "quadrat":
			cP.add((selectedA+1) + " " + (selectedB) + " " + checkID((selectedA+1), (selectedB)));
			cP.add((selectedA) + " " + (selectedB-1) + " " + checkID((selectedA), (selectedB-1)));
			cP.add((selectedA+1) + " " + (selectedB-1) + " " + checkID((selectedA+1), (selectedB-1)));
			break;
		case "senkrecht":
			cP.add((selectedA-1) + " " + (selectedB) + " " + checkID((selectedA-1), (selectedB)));
			cP.add((selectedA+1) + " " + (selectedB) + " " + checkID((selectedA+1), (selectedB)));
			break;
		case "waagerecht":
			cP.add((selectedA) + " " + (selectedB-1) + " " + checkID((selectedA), (selectedB-1)));
			cP.add((selectedA) + " " + (selectedB+1) + " " + checkID((selectedA), (selectedB+1)));
			break;
		}
		
		
		return cP;
	}
	
	public void setStatus(ArrayList<String> blocks) {
		for(int i = 0; i < blocks.size(); i++) {
			String tempArr[] = blocks.get(i).split(" ");
			int a = Integer.parseInt(tempArr[0]);
			int b = Integer.parseInt(tempArr[1]);
			blockStatus[a][b] = true;
		}
	}
	
	public boolean checkStatus(ArrayList<String> blocks) {
		for(int i = 0; i < blocks.size(); i++) {
			String tempArr[] = blocks.get(i).split(" ");
			int a = Integer.parseInt(tempArr[0]);
			int b = Integer.parseInt(tempArr[1]);
			if(blockStatus[a][b] == true) {
				return true;
			}
		}
		return false;
	}
	
 }
