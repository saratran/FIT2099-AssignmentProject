package game.item;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableDinoItem extends Item {
	protected int buyValue = 0;
	protected int sellValue = 0;

	public PortableDinoItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
	
	@Override
	public int getBuyValue() {
		return buyValue;
	}

	@Override
	public int getSellValue() {
		return sellValue;
	}

}
