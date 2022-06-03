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
	
	/**
	 * Generiert ein zweidimensionales Array mit den Koordinaten aller Felder
	 */
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
	
	/**
	 * Generiert ein zweidimensionales Array mit dem Status der Felder (belegt oder unbelegt)
	 */
	private void setupStatus() {
		for(int a = 0; a < 9; a++) {
			for(int b = 0; b < 9; b++) {
				blockStatus[a][b] = false;
			}
		}
	}
	
	/**
	 * Generiert ein zweidimensionales Array mit den IDs aller Felder
	 */
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
				//System.out.println("Arr: " + blockCords[a][b] + " Cords: " + cords);
				if(blockCords[a][b].equals(cords)) {
					selectedA = a;
					selectedB = b;
					cP.add(selectedA + " " + selectedB + " " + checkID(selectedA, selectedB));
					//System.out.println("A (Y): " + selectedA + " | B (X): " + selectedB);
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
	
	/**
	 * Setzt den Status der Blöcke aus einer ArrayList auf true
	 * @param blocks Die Blöcke
	 */
	public void setStatusTrue(ArrayList<String> blocks) {
		for(int i = 0; i < blocks.size(); i++) {
			String tempArr[] = blocks.get(i).split(" ");
			int a = Integer.parseInt(tempArr[0]);
			int b = Integer.parseInt(tempArr[1]);
			blockStatus[a][b] = true;
		}
	}
	
	/**
	 * Setzt den Status der Blöcke aus einer ArrayList auf false
	 * @param blocks Die Blöcke
	 */
	public void setStatusFalse(ArrayList<String> blocks) {
		for(int i = 0; i < blocks.size(); i++) {
			for(int b = 0; b < 9; b++) {
				for(int a = 0; a < 9; a++) {
					if(blockIDs[a][b] == Integer.parseInt(blocks.get(i))) {
						blockStatus[a][b] = false;
					}
				}
			}
		}
	}
	
	/**
	 * Prüft, ob der gewählte Platz des Teils schon belegt ist
	 * @param blocks Die A und B Koordinaten der Blöcke als String
	 * @return true wenn der Platz schon belegt ist, false wenn nicht
	 */
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
	
	/**
	 * Prüft, ob eine 3x3, 1x9 oder 9x1 Kombination gelöst wurde
	 * @return ArrayList mit den Feldern, die in der gelösten Kombination vorkommen
	 */
	public ArrayList<String> checkSolve() {
		ArrayList<String> blocks = new ArrayList<String>();
		
		for(int i = 0; i < 9; i++) {
			if(blockStatus[i][0] && blockStatus[i][1] && blockStatus[i][2] && blockStatus[i][3] && blockStatus[i][4] && blockStatus[i][5] && blockStatus[i][6] && blockStatus[i][7] && blockStatus[i][8] == true) {
				for(int j = 0; j < 9; j++) {
					blocks.add(String.valueOf(checkID(i, j)));
				}
			}
		}
		for(int i = 0; i < 9; i++) {
			if(blockStatus[0][i] && blockStatus[1][i] && blockStatus[2][i] && blockStatus[3][i] && blockStatus[4][i] && blockStatus[5][i] && blockStatus[6][i] && blockStatus[7][i] && blockStatus[8][i] == true) {
				for(int j = 0; j < 9; j++) {
					blocks.add(String.valueOf(checkID(j, i)));
				}
			}
		}
		for(int b = 0; b < 7; b = b + 3) {
			for(int a = 0; a < 7; a = a + 3) {
				if(blockStatus[a][b] && blockStatus[a+1][b] && blockStatus[a+2][b] && blockStatus[a][b+1] && blockStatus[a+1][b+1] && blockStatus[a+2][b+1] && blockStatus[a][b+2] && blockStatus[a+1][b+2] && blockStatus[a+2][b+2] == true) {
					blocks.add(String.valueOf(checkID((a), (b))));
					blocks.add(String.valueOf(checkID((a+1), (b))));
					blocks.add(String.valueOf(checkID((a+2), (b))));
					blocks.add(String.valueOf(checkID((a), (b+1))));
					blocks.add(String.valueOf(checkID((a+1), (b+1))));
					blocks.add(String.valueOf(checkID((a+2), (b+1))));
					blocks.add(String.valueOf(checkID((a), (b+2))));
					blocks.add(String.valueOf(checkID((a+1), (b+2))));
					blocks.add(String.valueOf(checkID((a+2), (b+2))));
				}
			}
		}
		
		return blocks;
	}
	
 }
