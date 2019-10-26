package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.ground.Tree;
import game.item.Corpse;
import game.item.Egg;
import game.item.FoodItem;

/**
 * A carnivorous dinosaur.
 * 
 */
public class Velociraptor extends Dinosaur {
	/**
	 * Constructor. Adult Velociraptors are represented by a 'V' and have 175 hit
	 * points. Baby Velociraptors are represented by a 'v'.
	 * 
	 * @param name the name of this Velociraptor
	 */
	public Velociraptor(String name, Maturity maturity) {
		super(name, 'V', 175, maturity, 1);
		edibleFoodSkills.add(FoodSkill.CARNIVORE);
		addSkill(DinoSkill.LAND);
	}

	public Velociraptor(String name) {
		this(name, Maturity.ADULT);
	}

	public Velociraptor(Maturity maturity) {
		this("Velociraptor", maturity);
	}

	public Velociraptor() {
		this("Velociraptor");
	}

	@Override
	protected void initFoodLevel() {
		if (this.maturity == Maturity.ADULT) {
			setFoodLevel(40, 100, 30);
		} else {
			setFoodLevel(10, 40, 30);
		}
	}

	@Override
	public int getSellValue() {
		return 400;
	}

	@Override
	public Egg getEgg() {
		return new Egg(new Velociraptor(Maturity.BABY), 500, 100);
	}

	@Override
	public Corpse getCorpse() {
		// TODO Auto-generated method stub
		return new Corpse("Velociraptor corpse", 50);
	}

}
