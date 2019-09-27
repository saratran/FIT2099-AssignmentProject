package game.ground;

import edu.monash.fit2099.engine.Ground;
import game.FoodSkill;

public abstract class Vegetation extends Ground{

	public Vegetation(char displayChar) {
		super(displayChar);
		addSkill(FoodSkill.HERBIVORE);
	}

}
