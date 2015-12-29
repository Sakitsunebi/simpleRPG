package com.game.simpleRPG.states;

import java.awt.Graphics;

import com.game.simpleRPG.graphics.TileManager;

public class GameState extends State{
	
	public GameState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(TileManager.player, 5, 5, null);		
	}	
	
	
}
