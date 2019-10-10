package game.ground;

import java.util.List;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public abstract class GrowableGround extends Ground {

	public GrowableGround(char displayChar) {
		super(displayChar);
	}

	/**
	 * TODO: modify this Grows grass at the dirts location if the grass growth
	 * chance is met.
	 * 
	 * @param location the location at which the grass will grow.
	 */
	public void growCurrentLocation(Location location, double chance, Ground ground) {
		if (Math.random() < chance) {
			location.setGround(ground);
		}
	}

	public void growNearbyLocations(Location location, double chance, Ground ground) {
		for (Exit exit : location.getExits()) {
			Location nearbyLocation = exit.getDestination();
			if(!nearbyLocation.getGround().hasSkill(GroundSkill.CANNOT_GROW_ON)) {
				growCurrentLocation(nearbyLocation, chance, ground);
			}
		}
	}

}
