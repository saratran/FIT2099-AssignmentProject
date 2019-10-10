package game.ground;

import java.util.List;
import java.util.stream.Collectors;

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
	protected void growCurrentLocation(Location location, double chance, Ground ground) {
		if (Math.random() < chance) {
			location.setGround(ground);
		}
	}

	protected void growNearbyLocations(Location location, double chance, Ground ground) {
		for (Exit exit : location.getExits()) {
			Location nearbyLocation = exit.getDestination();
			if(!nearbyLocation.getGround().hasSkill(GroundSkill.CANNOT_GROW_ON)) {
				growCurrentLocation(nearbyLocation, chance, ground);
			}
		}
	}

	/*
	 * count neighbour with the given class
	 */
	protected int neighbourCount(Location location, Class<?> groundClass) {
		int neighbourCount = 0;
//		for(Exit exit: location.getExits()) {
//			Ground nearbyGround = exit.getDestination().getGround();
//			if(nearbyGround.getClass().equals(groundClass)) {
//				neighbourCount++;
//			}
//		}
		
		for(Ground ground : getNeighbourGrounds(location)) {
			if(ground.getClass().equals(groundClass)) {
				neighbourCount++;
			}
		}
		return neighbourCount;
	}
	
	/*
	 * count neighbour if they have a skill
	 */
	protected int neighbourCount(Location location, Enum<?> skill) {
		int neighbourCount = 0;
//		for(Exit exit: location.getExits()) {
//			Ground nearbyGround = exit.getDestination().getGround();
//			if(nearbyGround.hasSkill(skill)) {
//				neighbourCount++;
//			}
//		}
		for(Ground ground : getNeighbourGrounds(location)) {
			if(ground.hasSkill(skill)) {
				neighbourCount++;
			}
		}
		return neighbourCount;
	}
	
	// TODO: check if correct
	private List<Ground> getNeighbourGrounds(Location location){
		return location.getExits().stream().map(exit -> exit.getDestination().getGround()).collect(Collectors.toList());
	}

}
