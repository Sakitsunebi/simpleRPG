package com.game.simpleRPG.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	private String title;
	private int width;
	private int height;
	
	private void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public JFrame getFrame() {
		return this.frame;
	}
	private void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	public Canvas getCanvas(){
		return this.canvas;
	}
	private void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
	private void setWidth(int width) {
		this.width = width;
	}	
	public int getWidth() {
		return this.width;
	}
	private void setHeight(int height) {
		this.height = height;
	}	
	public int getHeight() {
		return this.height;
	}
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;		
		init();		
	}	
	private void init() {
		createDisplay();
		createCanvas();
	}	
	private void createDisplay() {
		this.frame = new JFrame(getTitle());
		frame.setSize(getWidth(), getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);		
	}
	private void createCanvas() {
		this.canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(getWidth(), getHeight()));
		canvas.setMaximumSize(new Dimension(getWidth(), getHeight()));
		canvas.setMinimumSize(new Dimension(getWidth(), getWidth()));		
		frame.add(getCanvas());
		frame.pack();
	}
}
