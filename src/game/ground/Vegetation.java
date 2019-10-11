package game.ground;

import edu.monash.fit2099.engine.Ground;
import game.FoodSkill;

/**
 * Abstraction for a category of ground classes that can be eaten
 * by consumers. 
 *
 */
public abstract class Vegetation extends GrowableGround {
		
	/**
	 * Constructor.
	 * 
	 * @param displayChar	the character that will represent the vegetation
	 * @param growthRate	the rate at which the vegetation grows
	 */
	public Vegetation(char displayChar) {
		super(displayChar);
		addSkill(FoodSkill.HERBIVORE);
	}

//	/**
//	 * Method that grows the vegetation to nearby locations. Checks
//	 * the nearby locations and determines if they are suitable to grow
//	 * on. Multiple neighbouring locations can observe growth in one turn.
//	 * 
//	 * @param location	the current location of the vegetation.
//	 * @param vegetation	the vegetation that will be spread.
//	 */
//	public void grow(Location location, Vegetation vegetation) {
//		for(Exit exit : location.getExits()) {
//			Location location1 = exit.getDestination();
//			if(!location1.getGround().hasSkill(GroundSkill.CANNOT_GROW_ON)){
//				if (Math.random() <= this.GROWTH_RATE) {
//					location1.setGround(vegetation);
//				}
//			}
//		}
//	}
	
}
