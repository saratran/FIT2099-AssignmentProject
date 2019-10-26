package game.ground;

import edu.monash.fit2099.engine.*;
import game.dinosaur.DinoSkill;

/**
 * A class that represents grass.
 * 
 */
public class Grass extends Vegetation{
	private double growTreeChance = 0.001;
	/**
	 * Constructor. All grass is represented by the char '~'
	 */
	public Grass() {
		super(',');
		addSkill(GroundSkill.LAND);
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		growThisLocation(location, growTreeChance, new Tree());

	}

	public int getFoodValue() {
		return 5;
	}

	@Override
	public Ground eatenGround() {
		return new Dirt();
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasSkill(DinoSkill.LAND);
	}
}
