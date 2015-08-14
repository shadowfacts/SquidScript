package coolsquid.squidscript.mod.modules;

import net.minecraft.item.Item;
import coolsquid.squidscript.SquidClass;
import coolsquid.squidscript.SquidMethod;

@SquidClass("Item")
public class ItemModule {

	@SquidMethod
	public static void setStackSize(String item, int value) {
		getItem(item).setMaxStackSize(value);
	}

	@SquidMethod
	public static void setHarvestLevel(String item, String toolClass, int value) {
		getItem(item).setHarvestLevel(toolClass, value);
	}

	@SquidMethod
	public static void setMaxDamage(String item, int value) {
		getItem(item).setMaxDamage(value);
	}

	@SquidMethod
	public static void setTexture(String item, String texture) {
		getItem(item).setTextureName(texture);
	}

	private static Item getItem(String item) {
		return (Item) Item.itemRegistry.getObject(item);
	}
}