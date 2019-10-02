package game.ground;

import edu.monash.fit2099.engine.*;

/**
 * A class that represents grass.
 * 
 * @author Harun Ergi
 */
public class Grass extends Vegetation{
	/**
	 * Constructor. All grass is represented by the char '~'
	 */
	public Grass() {
		super('~', 0.0025);
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		grow(location, new Grass());

	}

	public int getFoodValue() {
		return 5;
	}

	@Override
	public Ground eatenGround() {
		return new Dirt();
	}
}
