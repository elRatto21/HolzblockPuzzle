package gui;

import java.io.IOException;

import logic.IDcontroller;

public class Menu {
	
	private IDcontroller ic = new IDcontroller();

	public Menu() throws IOException {
		
		ZeichenFlaeche14 zf = new ZeichenFlaeche14();
		zf.macheZeichenFlaecheSichtbar(600, 800);
		new GameScreen(zf, ic);
		
	}
	
	public static void main(String[] args) throws IOException {
		new Menu();
	}
	

}
