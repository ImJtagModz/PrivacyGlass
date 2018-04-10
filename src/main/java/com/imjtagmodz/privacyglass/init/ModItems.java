package com.imjtagmodz.privacyglass.init;

import com.imjtagmodz.privacyglass.Reference;
import com.imjtagmodz.privacyglass.items.ItemBasic;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModItems {
	
	static Item tutorialItem;
	public static void init() {
		tutorialItem = new ItemBasic("tutorial_item").setCreativeTab(CreativeTabs.MISC).setMaxStackSize(64);
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(tutorialItem);
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		registerRender(tutorialItem);
	}
	
	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
