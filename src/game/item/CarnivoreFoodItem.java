package game.item;

import game.FoodSkill;

public class CarnivoreFoodItem extends FoodItem {

	public CarnivoreFoodItem(String name, char displayChar) {
		super(name, displayChar);
		addSkill(FoodSkill.CARNIVORE);
	}

}
