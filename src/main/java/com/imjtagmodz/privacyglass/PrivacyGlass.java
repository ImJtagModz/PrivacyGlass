package com.imjtagmodz.privacyglass;

import com.imjtagmodz.privacyglass.init.ModBlocks;
import com.imjtagmodz.privacyglass.init.ModItems;
import com.imjtagmodz.privacyglass.proxy.CommonProxy;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class PrivacyGlass {
	
	@Instance
	public static PrivacyGlass instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println("Pre Init");
		ModItems.init();
		ModBlocks.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("Init");
		
		/*GameRegistry.addShapedRecipe(new ResourceLocation("minecraftbyexample:mbe35_recipe_ender_eye"), new ResourceLocation(""), new ItemStack(Items.DIAMOND), new Object[] {
				 " D ",
		            "DED",
		            " D ",
		            'D', Items.DIAMOND,
		            'E', Items.EMERALD
		});*/
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("Post Init");
	}
}
