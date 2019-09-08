package game;

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
		
		// Go through surrounding locations
		for (int m = x - 1; m <= x + 1; m++) {
			for (int n = y - 1; n <= y + 1; n++) {
				// Is it a good idea to use an empty try-catch block for out-of-bound cases?
				try {
					// TODO: what if ground is wall or something else
					if (!map.at(m, n).getGround().getClass().equals(Tree.class)) {
						if (Math.random() <= this.GROW_NEW_TREE) {
							map.at(m, n).setGround(new Tree());
						}
					}
				} catch (Exception e) {
				}
			}
		}

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
	}
}
