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
 * A Flying dinosaur
 * @author Sara Tran
 *
 */
public class Pteranodon extends Dinosaur {

	public Pteranodon(String name, Maturity maturity) {
		super(name, 'W', 100, maturity, 2);
		edibleFoodSkills.add(FoodSkill.CARNIVORE);
		layEggChance = 0.02;
		addSkill(DinoSkill.LAND);
		addSkill(DinoSkill.MARINE);

	}

	public Pteranodon(String name) {
		this(name, Maturity.ADULT);
	}

	public Pteranodon(Maturity maturity) {
		this("Pteranodons", maturity);
	}

	public Pteranodon() {
		this("Pteranodons");
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
		return new Egg(new Pteranodon(Maturity.BABY), 2000, 500);
	}

	@Override
	public Corpse getCorpse() {
		return new Corpse("pteranodon corpse", 300);
	}

}
