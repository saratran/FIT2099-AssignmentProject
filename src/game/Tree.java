package game;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Tree extends Ground {
	private int age = 0;
	private double GROW_NEW_TREE = 0.005;

	public Tree() {
		super('+');
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		int x = location.x();
		int y = location.y();
		GameMap map = location.map();
		
		
		for(Exit exit : location.getExits()) {
			Location location1 = exit.getDestination();
			if(location1.getGround().getClass().equals(Dirt.class)){
				if (Math.random() <= this.GROW_NEW_TREE) {
					location1.setGround(new Tree());
				}
			}
		}

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
	}

	@Override
	public int getFoodValue() {
		// TODO Auto-generated method stub
		return 10;
	}
	
	
}
