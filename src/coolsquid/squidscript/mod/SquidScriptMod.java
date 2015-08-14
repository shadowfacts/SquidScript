package coolsquid.squidscript.mod;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Throwables;

import coolsquid.squidscript.SquidScript;
import coolsquid.squidscript.mod.modules.BlockModule;
import coolsquid.squidscript.mod.modules.ItemModule;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = SquidScriptMod.MODID, name = SquidScriptMod.NAME, version = SquidScriptMod.VERSION)
public class SquidScriptMod {

	public static final String MODID = "SquidScript";
	public static final String NAME = "SquidScript";
	public static final String VERSION = "0.0.4 ALPHA";

	@EventHandler
	public void onPostInit(FMLPostInitializationEvent event) {
		SquidScript.registerClass(BlockModule.class);
		SquidScript.registerClass(ItemModule.class);
		File dir = new File("./config/SquidScript");
		if (!dir.exists())
			dir.mkdirs();
		for (File file: dir.listFiles())
			if (file.getName().endsWith(".ss"))
				try {
					SquidScript.parse(FileUtils.readFileToString(file));
				} catch (IOException e) {
					throw Throwables.propagate(e);
				}
	}

	public static void main(String[] args) {
		SquidScript.registerClass(BlockModule.class);
		SquidScript.registerClass(ItemModule.class);
		SquidScript.parse("Item.setStackSize(\"minecraft:diamond\", 999);");
	}
}