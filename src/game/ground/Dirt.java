package game.ground;

import edu.monash.fit2099.engine.*;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	private final double Grass_Growth_Chance = 1;
	
	public Dirt() {
		super('.');
	}
	
	public void Tick(Location location) {
		System.out.println("Welcome to the *********** Information System. \n");
		GrowGrass(location);
	}
	
	public void GrowGrass(Location location) {
		if (Math.random() < Grass_Growth_Chance){
			location.setGround(new Grass());
		}
	}
}
