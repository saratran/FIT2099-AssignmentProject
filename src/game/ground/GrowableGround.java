package game.ground;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A base class for Ground that can grow into something else at the current or nearby locations.
 * @author Sara Tran
 *
 */
public abstract class GrowableGround extends Ground {
	protected List<Ground> grounds = new ArrayList<Ground>();

	public GrowableGround(char displayChar) {
		super(displayChar);
	}

	/**
	 * Set the location to the given ground based on the chance
	 */
	protected void growThisLocation(Location location, double chance, Ground ground) {
		if (Math.random() < chance) {
			location.setGround(ground);
		}
	}

	/**
	 * Set nearby locations to the given ground based on the chance
	 */
	protected void growNearbyLocations(Location location, double chance, Ground ground) {
		for (Exit exit : location.getExits()) {
			Location nearbyLocation = exit.getDestination();		
			// TODO: maybe flip the skill around; instead of spawner knowing, let the spawnee know?
			if (this.canGrowOn(nearbyLocation.getGround())) {
				growThisLocation(nearbyLocation, chance, ground);
			}
		}
	}
	

	/**
	 * @return number of neighbours with the given class
	 */
	protected int neighbourCount(Location location, Class<?> groundClass) {
		int neighbourCount = 0;
		for (Ground ground : getNeighbourGrounds(location)) {
			if (ground.getClass().equals(groundClass)) {
				neighbourCount++;
			}
		}
		return neighbourCount;
	}

	/**
	 * @return number of neighbours with the given skill
	 */
	protected int neighbourCount(Location location, Enum<?> skill) {
		int neighbourCount = 0;
		for (Ground ground : getNeighbourGrounds(location)) {
			if (ground.hasSkill(skill)) {
				neighbourCount++;
			}
		}
		return neighbourCount;
	}

	// TODO: check if correct
	private List<Ground> getNeighbourGrounds(Location location) {
		return location.getExits().stream().map(exit -> exit.getDestination().getGround()).collect(Collectors.toList());
	}

	private boolean canGrowOn(Ground ground) {
		for (Ground canGrowOnGround : grounds) {
			if (ground.getClass().equals(canGrowOnGround.getClass())) {
				return true;
			}
		}
		return false;
	}

}
