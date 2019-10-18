package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Ground;
import game.ground.Dirt;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface GroundInterface {

	/**
	 * Override this to change Ground food value
	 * @return
	 */
	default int getFoodValue() {
		return 0;
	};
	
	/**
	 * Override this to change the Ground returned when the current Ground is eaten
	 * @return
	 */
	default Ground eatenGround() {
		return new Dirt();
	}

	default int getFoodPriority() {
		return 2;
	}
}
