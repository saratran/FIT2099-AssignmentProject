package game.ground;

import edu.monash.fit2099.engine.Ground;

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

}
