package game.ground;

import edu.monash.fit2099.engine.*;

/**
 * A class that represents bare dirt.
 * 
 */
public class Dirt extends Ground {

	private final double Grass_Growth_Chance = 0.0001;
	/**
	 * Constructor. All dirt is represented by the char '.'
	 */
	public Dirt() {
		super('.');
	}
	
	@Override
	public void tick(Location location) {
		growGrass(location);
	}
	
	/**
	 * Grows grass at the dirts location if the grass growth chance
	 * is met.
	 * 
	 * @param location the location at which the grass will grow.
	 */
	public void growGrass(Location location) {
		if (Math.random() < Grass_Growth_Chance){
			location.setGround(new Grass());
		}
	}
}
