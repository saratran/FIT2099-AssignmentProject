package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Reed extends Vegetation {
	
	public Reed() {
		super('|');
	}

	@Override
	public void tick(Location location) {
		super.tick(location);
		
		// Die if overcrowded
		if (neighbourCount(location, this.getClass()) >= 6) {
			location.setGround(eatenGround());
		}
		
	}
	
	@Override
	public Ground eatenGround() {
		return new Water();
	}
	
	

}
