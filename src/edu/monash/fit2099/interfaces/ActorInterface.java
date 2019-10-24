package edu.monash.fit2099.interfaces;

import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Skill;
import game.dinosaur.Consumer;

/**
 * This interface provides the ability to add methods to Actor, without
 * modifying code in the engine, or downcasting references in the game.
 */

public interface ActorInterface {
	default Consumer asConsumer() {
		return this instanceof Consumer ? (Consumer) this : null;
	}

	default int getSellValue() {
		return 0;
	}

	default void addTaggedActor(Actor actor) {
	}

	default List<Actor> getTaggedActors() {
		return null;
	}

	default void die(GameMap map) {
		return;
	}

	default int getFoodPriority() {
		return 1;
	}
	
	default int getSpeed() {
		return 1;
	}
}
