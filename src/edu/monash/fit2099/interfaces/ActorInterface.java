package edu.monash.fit2099.interfaces;

import game.dinosaur.Consumer;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {
	default Consumer asConsumer() {
		return this instanceof Consumer ? (Consumer) this : null;
	}
	
}
