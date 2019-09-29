package game.item;

import game.FoodSkill;

public class HerbivoreFoodItem extends FoodItem {

	public HerbivoreFoodItem(String name, char displayChar) {
		super(name, displayChar);
		addSkill(FoodSkill.HERBIVORE);
		buyValue = 10;
	}
	
	public HerbivoreFoodItem() {
		this("Herbivore food item", 'f');
	}

}
