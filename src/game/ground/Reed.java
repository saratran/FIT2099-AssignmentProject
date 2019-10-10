package game.ground;

import edu.monash.fit2099.engine.Exit;
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
		if (neighbourCount(location) >= 6) {
			location.setGround(eatenGround());
		}
		
	}
	
	private int neighbourCount(Location location) {
		int neighbourCount = 0;
		for(Exit exit: location.getExits()) {
			Location nearbyLocation = exit.getDestination();
			// TODO: note, this may not be very flexible
			// since it can only count other with the same class (what if more rules in the future)
			if(nearbyLocation.getClass().equals(this.getClass())) {
				neighbourCount++;
			}
		}
		return neighbourCount;
	}

	@Override
	public Ground eatenGround() {
		return new Water();
	}
	
	

}
