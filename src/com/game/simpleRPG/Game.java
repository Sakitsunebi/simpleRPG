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
	
	// ----------- CONSTRUCTOR --------------------- 
	
	public Game(String title, int width, int height) {
		this.display = new Display(title, width, height);
		start();
	}
	
	// ------------- METHODS ------------------------
	
	private void update() {
		
	}
	private void render() {
		bufferStrategy = display.getCanvas().getBufferStrategy();
		if (bufferStrategy == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		graphics = bufferStrategy.getDrawGraphics();
		graphics.fillRect(0, 0, display.getWidth(), display.getHeight());
		bufferStrategy.show();
		graphics.dispose();
		
	}
	public void run() {
//		render();
		while (running) {
			update();
			render();
		}
		stop();
	}	
	public synchronized void start() {
		if(running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop() {
		if(!running){
			return;
		}
		try {
			thread.join();
		} catch(Exception e) {
			System.out.println("Exception Caught: " + e);
		}
	}
}
