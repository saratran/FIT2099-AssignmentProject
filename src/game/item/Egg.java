package game.item;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.dinosaur.Dinosaur;
import game.dinosaur.Maturity;

public class Egg extends PortableDinoItem {
	private int age = 0;
	private int hatch_age = 10;
	private int buyValue = 0;
	private int sellValue = 0;
	private Dinosaur dinosaur;

	public Egg(String name, char displayChar, Dinosaur dinosaur, int buyValue, int sellValue) {
		super(name, displayChar);
		assert dinosaur.getMaturity() == Maturity.BABY;
		this.dinosaur = dinosaur;
		this.buyValue = buyValue;
		this.sellValue = sellValue;
		addSkill(FoodSkill.CARNIVORE);
	}

	public Egg(Dinosaur dinosaur, int buyValue, int sellValue) {
		this(dinosaur.getClass().getSimpleName() + " egg", 'e', dinosaur, buyValue, sellValue);
	}

	@Override
	public void tick(Location currentLocation) {
		age++;
		// Hatches after certain age and only if there is no Actor standing on top of it
		if (age >= hatch_age) {
			for (Exit exit : currentLocation.getExits()) {
				Location destination = exit.getDestination();
				if (!destination.containsAnActor() && destination.getGround().canActorEnter(hatchInto())) {
					destination.addActor(this.hatchInto());
					currentLocation.removeItem(this);
					System.out.println(this.toString() + " hatches");
					return;
				}
			}
		}
	}

	private Dinosaur hatchInto() {
		return dinosaur;
	}

	@Override
	public int getFoodValue() {
		return 10;
	}

	@Override
	public int getBuyValue() {
		return buyValue;
	}

	@Override
	public int getSellValue() {
		return sellValue;
	}

	@Override
	public boolean isBuyable() {
		return true;
	}

	@Override
	public boolean isSellable() {
		return true;
	}

}
