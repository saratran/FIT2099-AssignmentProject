package game.ground;

import edu.monash.fit2099.engine.*;

public class Grass extends Vegetation {

	public Grass() {
		super('~', 0.003);
	}

	/**
	 * TODO: it's actually Dirt that grows into Grass and it's different from grow Tree that the Dirt doesn't check nearby locations. It only checks itself.
	 * I'm not sure of the best way to do this without repetition but maybe using interfaces (interface segregation)?
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);

		grow(location, new Grass());

	}

	public int getFoodValue() {
		return 5;
	}
}
