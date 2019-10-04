package game.ground;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Tree extends Vegetation {
	private int age = 0;

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
