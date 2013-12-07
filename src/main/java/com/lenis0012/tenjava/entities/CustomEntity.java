package com.lenis0012.tenjava.entities;

public interface CustomEntity {
	
	/**
	 * Called whenever a player presses an input button in this entity.
	 * 
	 * @param forward Forward movement
	 * @param sideways Sideways movement
	 * @param jumping Holding spacebar?
	 * @param sneaking Holding shift?
	 * @return Precent from sending to server publicly?
	 */
	public boolean onPlayerInput(float forward, float sideways, boolean jumping, boolean sneaking);
}