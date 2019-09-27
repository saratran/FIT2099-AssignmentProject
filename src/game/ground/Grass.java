package game.ground;

import edu.monash.fit2099.engine.*;

public class Grass extends Vegetation {

	public Grass() {
		super('~', 0.008);
	}
	
	@Override
	public void tick(Location location) {
		super.tick(location);
		
		grow(location, new Grass());
		
	}
	
	@Override
	public int getFoodValue() {
		return 5;
	}
}
