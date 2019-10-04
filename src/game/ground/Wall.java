package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents walls.
 * 
 */
public class Wall extends Ground {
	
	/**
	 * Constructor. Walls are represented by the character '#'
	 * 
	 * Walls can not be entered by actors and block thrown objects. They 
	 * also do not allow vegetation to grow on them.
	 */
	public Wall() {
		super('#');
		addSkill(GroundSkill.CANNOT_GROW_ON);
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
