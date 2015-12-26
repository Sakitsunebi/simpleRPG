package com.game.simpleRPG;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.game.simpleRPG.display.Display;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

public class Game implements Runnable {
	
	// ------------ VARIABLES ------------------------
	
	private Display display;
	private Thread thread;
	private boolean running = false;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
	final int bufferAmount = 3;
	
	// ----------- SETTERS AND GETTERS -----------------
	
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
		return this.bufferAmount;
	}
//	public void setBufferAmount(int bufferAmount) {
//		this.bufferAmount = bufferAmount;
//	}
	
	// ----------- CONSTRUCTOR --------------------- 
	
	public Game(String title, int width, int height) {
		setDisplay(new Display(title, width, height));
		start();
	}
	
	// ------------- METHODS ------------------------
	
	private void update() {
		
	}
	private void render() {
//		bufferStrategy = getDisplay().getCanvas().getBufferStrategy();
//		if (bufferStrategy == null) {
//			display.getCanvas().createBufferStrategy(getBufferAmount());
//			return;
//		}
//		graphics = getBufferStrategy().getDrawGraphics();
//		graphics.fillRect(0, 0, getDisplay().getWidth(), getDisplay().getHeight());
//		bufferStrategy.show();
//		graphics.dispose();
		
		setBufferStrategy(getDisplay().getCanvas().getBufferStrategy());
		if (getBufferStrategy() == null) {
			getDisplay().getCanvas().createBufferStrategy(getBufferAmount());
			return;
		}
		setGraphics(getBufferStrategy().getDrawGraphics());		
		getGraphics().fillRect(0, 0, getDisplay().getWidth(), getDisplay().getHeight());
		getBufferStrategy().show();
		getGraphics().dispose();
		System.out.println(getDisplay().getWidth());
		System.out.println(getDisplay().getHeight());
	}
	public void run() {
		render();
//		while (getRunning()) {
//			update();
//			render();
//		}
//		stop();
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
			thread.join();
		} catch(Exception e) {
			System.out.println("Exception Caught: " + e);
		}
	}
}
