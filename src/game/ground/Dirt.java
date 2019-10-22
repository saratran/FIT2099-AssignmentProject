package game.ground;

import edu.monash.fit2099.engine.*;
import game.dinosaur.DinoSkill;

/**
 * A class that represents bare dirt.
 * 
 */
public class Dirt extends GrowableGround {
	private double grass_growth_chance = 0.002;
	
	/**
	 * Constructor. All dirt is represented by the char '.'
	 */
	public Dirt() {
		super('.');
		addSkill(GroundSkill.LAND);
	}
	
	@Override
	public void tick(Location location) {
		growCurrentLocation(location, grass_growth_chance, new Grass() );
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasSkill(DinoSkill.LAND);
	}
}
