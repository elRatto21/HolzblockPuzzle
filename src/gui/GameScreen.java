package gui;

import java.io.IOException;

import assets.AssetLoader;
import logic.BlockCalculation;
import logic.IDcontroller;

public class GameScreen {
	
	private ZeichenFlaeche14 zf;
	private AssetLoader al;
	private IDcontroller ic;
	private BlockCalculation bc = new BlockCalculation();
	private String Parts[] = {"empty", "empty", "empty"};

	public GameScreen(ZeichenFlaeche14 zf, IDcontroller ic) throws IOException {
		this.zf = zf;
		this.ic = ic;
		al = new AssetLoader(zf, ic, bc);
		al.loadGameScreen();
		al.generateButtons();
		Parts = al.generateParts(Parts);
	}
	
	/**
	 * @return Die Art der Teile
	 */
	public String[] getParts() {
		return Parts;
	}
	
}
