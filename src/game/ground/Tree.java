package game.ground;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.EdibleGroundInterface;
import edu.monash.fit2099.interfaces.EdibleInterface;

public class Tree extends Vegetation {
	private int age = 0;

	public Tree() {
		super('+', 0.002);
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
