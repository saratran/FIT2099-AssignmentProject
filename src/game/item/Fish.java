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

/**
 * A class to represent Fish.
 * 
 * Fish could have been an Actor but since there are a lot of them and making them move around
 * clutter the display so we chose to make Fish an Item instead
 * @author Sara Tran
 *
 */
public class Fish extends Item {
	private Random random = new Random();
	private int age = 0;

	public Fish() {
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
		age++;
		
		if(age > 20) {
			currentLocation.removeItem(this);
			return;
		}
		List<Location> locations = new ArrayList<Location>();
		for(Exit exit : currentLocation.getExits()) {
			Location destination = exit.getDestination();
			if(destination.getGround().canItemEnter(this)) {
				locations.add(destination);
			}
		}
		if (!locations.isEmpty()) {
			Location destination = locations.get(random.nextInt(locations.size()));
			destination.addItem(this);
		}
	}

}
