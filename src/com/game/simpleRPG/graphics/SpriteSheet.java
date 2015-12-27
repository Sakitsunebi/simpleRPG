package com.game.simpleRPG.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	//------------- VARIABLES -------------------------------------
	
	private BufferedImage sheet;
	
	//------------- SETTERS / GETTERS -----------------------------
	
	private BufferedImage getSheet() {
		return this.sheet;
	}
	private void setSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	//------------- CONSTRUCTOR -----------------------------------
	
	public SpriteSheet(BufferedImage sheet) {
		setSheet(sheet);
	}
	
	//------------- METHODS ---------------------------------------
	
	public BufferedImage getTile(int x, int y, int width, int height){
		return getSheet().getSubimage(x, y, width, height);
	}
}
