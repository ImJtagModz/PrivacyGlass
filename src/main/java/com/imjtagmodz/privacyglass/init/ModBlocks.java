package com.imjtagmodz.privacyglass.init;

import com.imjtagmodz.privacyglass.Reference;
import com.imjtagmodz.privacyglass.blocks.PrivacyGlassBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=Reference.MOD_ID)

public class ModBlocks {
	static Block privacyGlassBlock;
	
	public static void init() {
		privacyGlassBlock = new PrivacyGlassBlock("privacy_glass");
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(privacyGlassBlock);
	}
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(new ItemBlock(privacyGlassBlock).setRegistryName(privacyGlassBlock.getRegistryName()));
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		registerRender(Item.getItemFromBlock(privacyGlassBlock));
	}
	
	public static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
	}
}
