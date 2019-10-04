package game.ground;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
/**
 * A class that represents trees.
 * 
 * @author Harun Ergi
 *
 */
public class Tree extends Vegetation {
	private int age = 0;
	/**
	 * Constructor. Ttrees are represented by the char '+' until they age
	 * 10 turns at which they are represented by 't' and finally after 20
	 * turns: 'T'.
	 */
	public Tree() {
		super('+', 0.002);
		addSkill(GroundSkill.CANNOT_GROW_ON);
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		grow(location, new Tree());
		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
	}

	public int getFoodValue() {
		return 10;
	}

	public Ground eatenGround() {
		return new Dirt();
	}

}
