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
		setTitle(title);
		setWidth(width);
		setHeight(height);		
		init();		
	}	
	private void init() {
		createDisplay();
		createCanvas();
	}	
	private void createDisplay() {
		setFrame(new JFrame(getTitle()));
		getFrame().setSize(getWidth(), getHeight());
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setResizable(false);
		getFrame().setLocationRelativeTo(null);
		getFrame().setVisible(true);		
	}
	private void createCanvas() {
		setCanvas(new Canvas());
		getCanvas().setPreferredSize(new Dimension(getWidth(), getHeight()));
		getCanvas().setMaximumSize(new Dimension(getWidth(), getHeight()));
		getCanvas().setMinimumSize(new Dimension(getWidth(), getWidth()));		
		getFrame().add(getCanvas());
		getFrame().pack();
	}
}
