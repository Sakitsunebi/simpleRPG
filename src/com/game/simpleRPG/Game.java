package com.game.simpleRPG;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import com.game.simpleRPG.display.Display;
import com.game.simpleRPG.graphics.ImageLoader;
import com.game.simpleRPG.graphics.SpriteSheet;
import com.game.simpleRPG.graphics.TileManager;

public class Game implements Runnable {
	
	// ------------ VARIABLES ---------------------------------------
	
	final int BUFFER_AMOUNT = 3;
	final int DISPLAY_START_X = 0;
	final int DISPLAY_START_Y = 0;
	private Display display;
	private Thread thread;
	private boolean running = false;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;	
	private BufferedImage testImage;
	private SpriteSheet spriteSheet;
	
	// ----------- SETTERS AND GETTERS ------------------------------
	
 	private void setDisplay(Display display) {
		this.display = display;
	}
	private Display getDisplay() {
		return this.display;
	}
	private void setThread(Thread thread) {
		this.thread = thread;
	}
	private Thread getThread() {
		return this.thread;
	}
	private void setRunning(boolean running) {
		this.running = running;
	}
	private boolean getRunning() {
		return this.running;
	}		
	public BufferStrategy getBufferStrategy() {
		return this.bufferStrategy;
	}
	public void setBufferStrategy(BufferStrategy bufferStrategy) {
		this.bufferStrategy = bufferStrategy;
	}
	public Graphics getGraphics() {
		return this.graphics;
	}
	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}
	public int getBufferAmount() {
		return this.BUFFER_AMOUNT;
	}
	private int getDisplayStartX() {
		return this.DISPLAY_START_X;
	}
	private int getDisplayStartY() {
		return this.DISPLAY_START_Y;
	}
	private BufferedImage getTestImage() {
		return testImage;
	}
	private void setTestImage(BufferedImage bufferedImage) {
		this.testImage = bufferedImage;
	}
	private SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}
	private void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	// ----------- CONSTRUCTOR ---------------------------------------
		
	public Game(String title, int width, int height) {
		setDisplay(new Display(title, width, height));
		setTestImage(ImageLoader.loadImage("/textures/spritesheet.png"));
		setSpriteSheet(new SpriteSheet(testImage));
		TileManager.init();
		start();
	}
	
	// ------------ METHODS -------------------------------------------
	
	int x = 0;
	
	private void update() {
		x += 1;
	}
	private void render() {		
		setBufferStrategy(getDisplay().getCanvas().getBufferStrategy());
		if (getBufferStrategy() == null) {
			getDisplay().getCanvas().createBufferStrategy(getBufferAmount());
			return;
		}
		setGraphics(getBufferStrategy().getDrawGraphics());		
		// Clear
		getGraphics().clearRect(getDisplayStartX(), getDisplayStartY(), getDisplay().getWidth(), getDisplay().getHeight());
		//Start Draw		
		getGraphics().drawImage(TileManager.stone, x, x, null);		
		//End Draw
		getBufferStrategy().show();
		getGraphics().dispose();
	}
	public void run() {
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		while (getRunning()) {
			now = System.nanoTime();
			delta += (now -lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1){
				update();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000){
				System.out.println("Ticks: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}	
	public synchronized void start() {
		if(getRunning()){
			return;
		}
		setRunning(true);
		setThread(new Thread(this));
		getThread().start();
	}
	public synchronized void stop() {
		if(!getRunning()){
			return;
		}
		try {
			getThread().join();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
