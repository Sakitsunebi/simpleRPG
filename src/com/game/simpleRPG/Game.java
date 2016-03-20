package com.game.simpleRPG;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.game.simpleRPG.display.Display;
import com.game.simpleRPG.graphics.ImageLoader;
import com.game.simpleRPG.graphics.SpriteSheet;
import com.game.simpleRPG.graphics.TileManager;
import com.game.simpleRPG.states.GameState;
import com.game.simpleRPG.states.State;
import com.game.simpleRPG.states.StateManager;

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
	
	// ----------- States -------------------------------------------
	
	private StateManager stateManager; 
	
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
	private boolean isRunning() {
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
	private StateManager getStateManager() {
		return this.stateManager;
	}
	private void setStateManager(StateManager state) {
		this.stateManager = state;
	}
	// ----------- CONSTRUCTOR ---------------------------------------		
	
	public Game(String title, int width, int height) {
		setDisplay(new Display(title, width, height));
		setTestImage(ImageLoader.loadImage("/textures/spritesheet.png"));
		setSpriteSheet(new SpriteSheet(testImage));
		TileManager.init();
		setStateManager(new StateManager());
		getStateManager().setCurrentState(new GameState());
		start();
	}
	
	// ------------ METHODS -------------------------------------------
	
//	int x = 0;
	
	private void update() {
//		x += 1;
		if (getStateManager().getCurrentState() != null) {
			getStateManager().tick();
		}
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
//		getGraphics().drawImage(TileManager.stone, x, 5, null);
		if (getStateManager().getCurrentState() != null) {
			getStateManager().render(getGraphics());
		}
		//End Draw
		getBufferStrategy().show();
		getGraphics().dispose();
	}
	public void run() {
		int fps = 60;
		int nanoSecond = 1000000000;
		double timePerTick = nanoSecond / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		while (isRunning()) {
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
			if(timer >= nanoSecond){
				System.out.println("Ticks: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}	
	public synchronized void start() {
		
		// If it's already running, return
		if(isRunning()){
			return;
		}
		
		// Set running to true once we start the game
		setRunning(true);
		
		// Initialize the thread and pass which class you want to run
		// in this case, we want the Game class to be threaded
		setThread(new Thread(this));
		
		// start() automaticall calls run()
		getThread().start();
	}
	public synchronized void stop() {
		
		// If we're not running, return
		if(!isRunning()){
			return;
		}
		
		// Otherwise, stop the game
		try {
			// join() kills threads
			getThread().join();
		} catch(Exception e) {
			// Throw an exception of there is an error
			e.printStackTrace();
		}
	}
}
