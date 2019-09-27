package game.ground;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Tree extends Vegetation {
	private int age = 0;

	public Tree() {
		super('+', 0.005);
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		// Seemingly never used? Is it necessary at the moment
		int x = location.x();
		int y = location.y();
		GameMap map = location.map();
		
		
		grow(location, new Tree());
		
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
	
	
}
