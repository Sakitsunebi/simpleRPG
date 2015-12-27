package com.game.simpleRPG.graphics;

import java.awt.image.BufferedImage;

public class TileManager {
	
	// -------------- Variables ------------------------------
	
	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;
	public static BufferedImage player;
	public static BufferedImage grass;
	public static BufferedImage dirt;
	public static BufferedImage water;
	public static BufferedImage snow;
	public static BufferedImage lava;
	public static BufferedImage stone;
	
	// --------------- SETTERS / GETTERS ---------------------
	
	private static void setPlayer(BufferedImage player) {
		TileManager.player = player;
	}
	private static void setGrass(BufferedImage grass) {
		TileManager.grass = grass;
	}	
	private static void setDirt(BufferedImage dirt) {
		TileManager.dirt = dirt;
	}	
	private static void setWater(BufferedImage water) {
		TileManager.water = water;
	}	
	private static void setSnow(BufferedImage snow) {
		TileManager.snow = snow;
	}	
	private static void setLava(BufferedImage lava) {
		TileManager.lava = lava;
	}
	private static void setStone(BufferedImage stone) {
		TileManager.stone = stone;
	}
	private static int getWidth() {
		return TileManager.WIDTH;
	}
	private static int getHeight() {
		return TileManager.HEIGHT;
	}
	
	// --------------- METHODS -------------------------------
	
	public static void init(){
		SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"));
		setPlayer(spriteSheet.getTile(getWidth() * 3, getWidth() * 3, getWidth(), getHeight()));
		setGrass(spriteSheet.getTile(0, 0, getWidth(), getHeight()));
		setDirt(spriteSheet.getTile(getWidth(), 0, getWidth(), getHeight()));
		setWater(spriteSheet.getTile(getHeight() * 2, 0, getWidth(), getHeight()));
		setSnow(spriteSheet.getTile(getWidth() * 3, 0, getWidth(), getHeight()));
		setLava(spriteSheet.getTile(0, getWidth(), getWidth(), getHeight()));
		setStone(spriteSheet.getTile(getWidth(), getWidth(), getWidth(), getHeight()));
	}

	
}
