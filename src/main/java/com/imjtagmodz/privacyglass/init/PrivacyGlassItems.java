package com.imjtagmodz.privacyglass.init;

import com.imjtagmodz.privacyglass.Reference;
import com.imjtagmodz.privacyglass.items.ItemPrivacyGlass;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PrivacyGlassItems {
	
	public static Item privacyGlass;
	
	public static void init() {
		privacyGlass = new ItemPrivacyGlass();
	}
	
	public static void register() {
		//GameRegistry.register(privacyGlass);
	}
	
	public static void registerRenders() {
		registerRender(privacyGlass);
	}
	
	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
