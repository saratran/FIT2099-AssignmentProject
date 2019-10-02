package game.ground;

import edu.monash.fit2099.engine.*;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	private final double Grass_Growth_Chance = 0.0001;
	
	public Dirt() {
		super('.');
	}
	
	@Override
	public void tick(Location location) {
		GrowGrass(location);
	}
	
	public void GrowGrass(Location location) {
		if (Math.random() < Grass_Growth_Chance){
			location.setGround(new Grass());
		}
	}
}
