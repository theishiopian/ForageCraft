package com.theishiopian.foragecraft.init;

import com.theishiopian.foragecraft.blocks.Fascine;
import com.theishiopian.foragecraft.blocks.RockBlock;
import com.theishiopian.foragecraft.blocks.Scarecrow;
import com.theishiopian.foragecraft.blocks.StrawBale;
import com.theishiopian.foragecraft.items.RockItem;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks 
{
	
	public static Block fascine;
	public static Block straw_bale;
	public static Block scarecrow;
	public static Block rock_normal;
	public static Block rock_flat;
	
	public static enum RockType{NORMAL, FLAT};
	
	public static void init()
	{
		fascine = new Fascine();
		straw_bale = new StrawBale();
		scarecrow = new Scarecrow();
		rock_normal = new RockBlock(RockType.NORMAL);
		rock_flat = new RockBlock(RockType.FLAT);
	}
	
	public static void register()
	{
		registerBlock(fascine);
		registerBlock(straw_bale);
		registerBlock(scarecrow);
		splitRegisterBlock(rock_normal, new RockItem(rock_normal, RockType.NORMAL));
		splitRegisterBlock(rock_flat, new RockItem(rock_flat, RockType.FLAT));
	}
	
	private static void registerBlock(Block block)
	{
		GameRegistry.register(block);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		GameRegistry.register(item);
	}
	
	private static void splitRegisterBlock(Block block, ItemBlock item)
	{
		GameRegistry.register(block);
		GameRegistry.register(item);
	}
	
	public static void registerRenders()
	{
		registerRender(fascine);
		registerRender(straw_bale);
		registerRender(scarecrow);
		registerRender(rock_normal);
		registerRender(rock_flat);
	}
	
	public static void registerRender(Block block)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "Inventory"));
	}
}
