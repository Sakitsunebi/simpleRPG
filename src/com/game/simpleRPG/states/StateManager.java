package com.game.simpleRPG.states;

import java.awt.Graphics;

public class StateManager extends State{
	private State currentState = null;

	public State getCurrentState() {
		return this.currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
	
}
