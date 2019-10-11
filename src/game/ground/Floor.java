package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.dinosaur.DinoSkill;

/**
 * A class that represents the floor inside a building.
 * 
 */
public class Floor extends Ground {

	/**
	 * Constructor. All floor is represented by the char '_'
	 */
	public Floor() {
		super('_');
		addSkill(GroundSkill.CANNOT_GROW_ON);
		addSkill(GroundSkill.LAND);
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasSkill(DinoSkill.LAND);
	}
}
