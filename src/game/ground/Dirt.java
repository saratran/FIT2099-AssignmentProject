package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	private double GROW_GRASS_CHANCE = 0.002;
	
	public Dirt() {
		super('.');
	}

	@Override
	public void tick(Location location) {
		super.tick(location);
		if (Math.random() <= this.GROW_GRASS_CHANCE) {
			location.setGround(new Grass());
		}
	}
	
	
}
