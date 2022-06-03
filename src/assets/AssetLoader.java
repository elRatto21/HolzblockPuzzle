package assets;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import gui.ZeichenFlaeche14;
import logic.BlockCalculation;
import logic.IDcontroller;

public class AssetLoader {
	
	private ZeichenFlaeche14 zf;
	private IDcontroller ic;
	private BlockCalculation bc;
	private BufferedImage bild;
	private BufferedImage block;
	
	private JButton part1 = new JButton();
	private JButton part2 = new JButton();
	private JButton part3 = new JButton();
	private JButton exit = new JButton();
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	private int score = 0;
	
	private String[] Parts = new String[3];
	
	
	public AssetLoader(ZeichenFlaeche14 zf, IDcontroller ic, BlockCalculation bc) throws IOException {
		this.zf = zf;
		this.ic = ic;
		this.bc = bc;
		block = ImageIO.read(AssetLoader.class.getResource("block.png"));
		zf.setzeText(99, String.valueOf(score), 330, 57, 30, Color.WHITE);
		zf.setzeText(100, "Points", 430, 57, 30, Color.WHITE);
	}
	
	public void loadGameScreen() throws IOException {
		bild = ImageIO.read(AssetLoader.class.getResource("Gamescreen.png"));
		zf.setzeBild(0, bild, 0, 0, 600, 800);
		
		part1.setBounds(53, 624, 160, 160);
		part1.setVisible(true);
		part1.setOpaque(false);
		part1.setContentAreaFilled(false);
		part1.setBorderPainted(false);
		part1.setRolloverEnabled(false);
		part1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				part1_ActionPerformed(evt);
			}
		});
		zf.add(part1);
		
		part2.setBounds(223, 624, 160, 160);
		part2.setVisible(true);
		part2.setOpaque(false);
		part2.setContentAreaFilled(false);
		part2.setBorderPainted(false);
		part2.setRolloverEnabled(false);
		part2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				part2_ActionPerformed(evt);
			}
		});
		zf.add(part2);
		
		part3.setBounds(393, 624, 160, 160);
		part3.setVisible(true);
		part3.setOpaque(false);
		part3.setContentAreaFilled(false);
		part3.setBorderPainted(false);
		part3.setRolloverEnabled(false);
		part3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				part3_ActionPerformed(evt);
			}
		});
		zf.add(part3);
		
		exit.setBounds(48, 13, 70, 70);
		exit.setVisible(true);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.setRolloverEnabled(false);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					exit_ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		zf.add(exit);
		
		zf.repaint();
	}
	
	public void generateButtons() {
		int x = 0;
		int y = 0;
		for(int i = 0; i < 82; i++) {
			buttons.add(new JButton());
			for(int a = 0; a < 9; a++) {
				for(int b = 0; b < 9; b++) {
					if(bc.checkID(a, b) == i) {
						x = bc.getX(a, b);
						y = bc.getY(a, b);
						break;
					}
				}
			}
			buttons.get(i).setBounds(x, y, 50, 50);
			buttons.get(i).setVisible(true);
			buttons.get(i).setOpaque(false);
			buttons.get(i).setContentAreaFilled(false);
			buttons.get(i).setBorderPainted(false);
			buttons.get(i).setRolloverEnabled(false);
			zf.add(buttons.get(i));
			buttons.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						button_ActionPerformed(evt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}

	public String[] generateParts(String Parts[]) throws IOException {
		Random rnd = new Random();
		for(int i = 0; i < 3; i++) {
			int temp = rnd.nextInt(27);
			//System.out.println(temp);
			if(temp < 3) {
				bild = ImageIO.read(AssetLoader.class.getResource("dickesDach.png"));
				Parts[i] = "dickesDach";
			} else if(temp >= 3 && temp <= 4) {
				bild = ImageIO.read(AssetLoader.class.getResource("dickesDachReversed.png"));
				Parts[i] = "dickesDachReversed";
			} else if(temp >= 5 && temp <= 6) {
				bild = ImageIO.read(AssetLoader.class.getResource("dickesL.png"));
				Parts[i] = "dickesL";
			} else if(temp >= 7 && temp <= 8) {
				bild = ImageIO.read(AssetLoader.class.getResource("dickesLreversed.png"));
				Parts[i] = "dickesLreversed";
			} else if(temp >= 9 && temp <= 11) {
				bild = ImageIO.read(AssetLoader.class.getResource("kleinesSenkrecht.png"));
				Parts[i] = "kleinesSenkrecht";
			} else if(temp >= 12 && temp <= 14) {
				bild = ImageIO.read(AssetLoader.class.getResource("kleinesWaagerecht.png"));
				Parts[i] = "kleinesWaagerecht";
			} else if(temp == 15) {
				bild = ImageIO.read(AssetLoader.class.getResource("kreuz.png"));
				Parts[i] = "kreuz";
			} else if(temp >= 16 && temp <= 18) {
				bild = ImageIO.read(AssetLoader.class.getResource("l.png"));
				Parts[i] = "l";
			} else if(temp == 19) {
				bild = ImageIO.read(AssetLoader.class.getResource("mitteBlock.png"));
				Parts[i] = "mitteBlock";
			} else if(temp >= 20 && temp <= 21) {
				bild = ImageIO.read(AssetLoader.class.getResource("quadrat.png"));
				Parts[i] = "quadrat";
			} else if(temp >= 22 && temp <= 24) {
				bild = ImageIO.read(AssetLoader.class.getResource("senkrecht.png"));
				Parts[i] = "senkrecht";
			} else if(temp >= 25 && temp <= 27) {
				bild = ImageIO.read(AssetLoader.class.getResource("waagerecht.png"));
				Parts[i] = "waagerecht";
			}
			if(i == 0) {
				zf.setzeBild(82, bild, 58, 629, 150, 150);
			} else if(i == 1) {
				zf.setzeBild(83, bild, 228, 629, 150, 150);
			} else if(i == 2) {
				zf.setzeBild(84, bild, 398, 629, 150, 150);
			}
		}
		zf.repaint();
		this.Parts = Parts;
		return Parts;
	}
	
	private void part1_ActionPerformed(ActionEvent evt) {
		//System.out.println("Part 1 clicked");
		bc.setPart(1);
	}
	
	private void part2_ActionPerformed(ActionEvent evt) {
		//System.out.println("Part 2 clicked");
		bc.setPart(2);
	}
	
	private void part3_ActionPerformed(ActionEvent evt) {
		//System.out.println("Part 3 clicked");
		bc.setPart(3);
	}
	
	private void exit_ActionPerformed(ActionEvent evt) throws IOException {
	}
	
	private void increaseScore(int inc) {
		score = score + inc;
	}
	
	private void button_ActionPerformed(ActionEvent evt) throws IOException {
		String in = evt.toString();
		String out = in.substring(111, 118); //Filtert die x und y Koordinate aus Buttonevent
		String[] temp = out.split(",");
		String cords = temp[0] + " " + temp[1];
		//System.out.println(cords);
		generateBlocks(bc.calcBlocks(cords, Parts));
		
	}
	
	private void generateBlocks(ArrayList<String> cP) throws IOException {
		if(bc.checkStatus(cP) == false) {
			increaseScore(10);
			zf.loeschen(99);
			zf.setzeText(99, String.valueOf(score), 330, 57, 30, Color.WHITE);
			ArrayList<String> statusCords = new ArrayList<String>();
			String tempArr[] = new String[3];
			int blockData[] = new int[3];
			for(int i = 0; i < cP.size(); i++) {
				tempArr = cP.get(i).split(" ");
				for(int j = 0; j < 3; j++) {
					blockData[j] = Integer.parseInt(tempArr[j]);
				}
				zf.setzeBild(blockData[2], block, bc.getX(blockData[0], blockData[1]), bc.getY(blockData[0], blockData[1]), 50, 50);
				statusCords.add(bc.getX(blockData[0], blockData[1]) + " " + bc.getY(blockData[0], blockData[1]));
				bc.setStatusTrue(cP);
				zf.repaint();
			}
			switch(bc.getPart()) {
				case 1:
					zf.loeschen(82);
					Parts[0] = "null";
					break;
				case 2:
					zf.loeschen(83);
					Parts[1] = "null";
					break;
				case 3:
					zf.loeschen(84);
					Parts[2] = "null";
					break;
			}
			bc.setPart(-1);
			if(Parts[0].equals("null") && Parts[1].equals("null") && Parts[2].equals("null")) {
				generateParts(Parts);
			}
			ArrayList<String> solvedBlocks = bc.checkSolve();
			for(int i = 0; i < solvedBlocks.size(); i++) {
				zf.loeschen(Integer.parseInt(solvedBlocks.get(i)));
				zf.repaint();
			}
			switch(solvedBlocks.size()) {
				case 9:
					increaseScore(50);
					zf.loeschen(99);
					zf.setzeText(99, String.valueOf(score), 330, 57, 30, Color.WHITE);
					break;
				case 18:
					increaseScore(150);
					zf.loeschen(99);
					zf.setzeText(99, String.valueOf(score), 330, 57, 30, Color.WHITE);
					break;
				case 27:
					increaseScore(300);
					zf.loeschen(99);
					zf.setzeText(99, String.valueOf(score), 330, 57, 30, Color.WHITE);
			}
			bc.setStatusFalse(solvedBlocks);
			zf.repaint();
		}
	}
}
