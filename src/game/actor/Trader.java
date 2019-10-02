package game.actor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import game.Skill;

/**
 * A type of Actor that has money and can support trading actions
 * 
 * TODO: similar justification as Consumer abstract class
 * @author saratran
 *
 */

public abstract class Trader extends Actor {
	private int money = 0;
	
	public Trader(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		addSkill(Skill.BUYER);
	}

	public void addMoney(int value) {
		//TODO: needs exception?
		money += value;
	}
	
	public void deductMoney(int value) {
		money -= value;
	}
	
	public int getMoney() {
		return money;
	}
	
}
