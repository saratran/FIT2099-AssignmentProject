package game.ground;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;

public abstract class Vegetation extends Ground{
	
protected final double GROWTH_RATE;

	public Vegetation(char displayChar, double growthRate) {
		super(displayChar);
		this.GROWTH_RATE = growthRate;
		addSkill(FoodSkill.HERBIVORE);
		
	}

	public void grow(Location location, Vegetation vegetation) {
		for(Exit exit : location.getExits()) {
			Location location1 = exit.getDestination();
			if(location1.getGround().getClass().equals(Dirt.class)){
				if (Math.random() <= this.GROWTH_RATE) {
					location1.setGround(vegetation);
				}
			}
		}
	}
	
}
