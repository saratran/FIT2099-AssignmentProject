package game.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.dinosaur.DinoSkill;

public class FishItem extends Item {
	private Random random = new Random();

	public FishItem() {
		super("fish", 'f', false);
		addSkill(DinoSkill.MARINE);
		addSkill(FoodSkill.CARNIVORE);
	}

	@Override
	public int getFoodValue() {
		return 20;
	}

	@Override
	public void tick(Location currentLocation) {
		super.tick(currentLocation);
		List<Location> locations = new ArrayList<Location>();
		// TODO: check nearby location canEnter() and then move
		for(Exit exit : currentLocation.getExits()) {
			Location destination = exit.getDestination();
			if(destination.getGround().canItemEnter(this)) {
				locations.add(destination);
			}
		}
		if (!locations.isEmpty()) {
			Location destination = locations.get(random.nextInt(locations.size()));
			currentLocation.removeItem(this);
			destination.addItem(this);
		}
	}

}
