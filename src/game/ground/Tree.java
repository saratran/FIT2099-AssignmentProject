package game.ground;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
/**
 * A class that represents trees.
 *
 */
public class Tree extends Vegetation {
	private int age = 0;
	private double newTreeChance = 0.005;
	
	/**
	 * Constructor. Trees are represented by the char '+' until they age
	 * 10 turns at which they are represented by 't' and finally after 20
	 * turns: 'T'.
	 */
	public Tree() {
		super('+');
		addSkill(GroundSkill.CANNOT_GROW_ON);
		addSkill(GroundSkill.LAND);
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		growNearbyLocations(location, newTreeChance, new Tree());
		
		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
	}
	
	@Override
	public int getFoodValue() {
		return 10;
	}

	@Override
	public Ground eatenGround() {
		return new Dirt();
	}

}
