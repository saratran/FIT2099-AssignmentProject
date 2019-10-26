package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.item.Corpse;
import game.item.Egg;

/**
 * A carnivorous marine dinosaur
 * @author Sara Tran
 *
 */
public class Plesiosaur extends Dinosaur {		
	/**
	 * Constructor
	 * @param name name of this plesiosaur
	 * @param maturity adult or baby
	 */
	public Plesiosaur(String name, Maturity maturity) {
		super(name, 'L', 200, maturity, 1);		
		edibleFoodSkills.add(FoodSkill.CARNIVORE);
		edibleFoodSkills.add(FoodSkill.MARINE);
		addSkill(DinoSkill.MARINE);
	}

	public Plesiosaur(String name) {
		this(name, Maturity.ADULT);	
	}

	public Plesiosaur(Maturity maturity) {
		this("Plesiosaur", maturity);
	}

	public Plesiosaur() {
		this("Plesiosaur");
	}
	
	@Override
	protected void initFoodLevel() {
		if (this.maturity == Maturity.ADULT) {
			setFoodLevel(150, 200, 100);
		} else {
			setFoodLevel(80, 200, 100);
		}
	}
	
	@Override
	public int getSellValue() {
		return 400;
	}
	
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(40, "bites");
	}
	
	@Override
	public Egg getEgg() {
		return new Egg(new Plesiosaur(Maturity.BABY), 2000, 500);
	}

	@Override
	public Corpse getCorpse() {
		return new Corpse("plesiosaur corpse", 300);
	}

}
