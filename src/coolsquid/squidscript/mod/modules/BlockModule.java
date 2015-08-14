package coolsquid.squidscript.mod.modules;

import net.minecraft.block.Block;
import coolsquid.squidscript.SquidClass;
import coolsquid.squidscript.SquidMethod;

@SquidClass("Block")
public class BlockModule {

	@SquidMethod
	public static void setHardness(String block, float value) {
		getBlock(block).setHardness(value);
	}

	@SquidMethod
	public static void setResistance(String block, float value) {
		getBlock(block).setResistance(value);
	}

	@SquidMethod
	public static void setSlipperiness(String block, float value) {
		getBlock(block).slipperiness = value;
	}

	@SquidMethod
	public static void setLightLevel(String block, float value) {
		getBlock(block).setLightLevel(value);
	}

	@SquidMethod
	public static void setLightOpacity(String block, int value) {
		getBlock(block).setLightOpacity(value);;
	}

	@SquidMethod
	public static void setTexture(String block, String texture) {
		getBlock(block).setBlockTextureName(texture);
	}

	private static Block getBlock(String block) {
		return Block.getBlockFromName(block);
	}
}