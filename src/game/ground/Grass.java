package game.ground;

import edu.monash.fit2099.engine.*;

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
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		growCurrentLocation(location, growTreeChance, new Tree());

	}

	public int getFoodValue() {
		return 5;
	}

	@Override
	public Ground eatenGround() {
		return new Dirt();
	}
}
