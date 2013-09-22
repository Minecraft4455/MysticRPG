package net.minecraft4455.mysticrpg.core.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft4455.mysticrpg.core.items.MysticItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class MysticRecipes {
	
	public static void init() {
		
		addShaped();
		addShapeless();
		
	}

	private static void addShapeless() {

		
		
	}

	private static void addShaped() {

		GameRegistry.addShapedRecipe(new ItemStack(MysticItems.Scroll, 1, 0), "PPP", "PPP", "PPP", 'P', Item.paper);
		GameRegistry.addShapedRecipe(new ItemStack(MysticItems.GreenInk, 1, 0), "G", "W", 'G', new ItemStack(Item.dyePowder, 1, 2), 'W', new ItemStack(Item.potion, 1, 0));
		GameRegistry.addShapedRecipe(new ItemStack(MysticItems.GreenInk, 1, 0), "G", "W", 'G', new ItemStack(Item.dyePowder, 1, 2), 'W', new ItemStack(Item.potion, 1, 0));
		GameRegistry.addShapedRecipe(new ItemStack(MysticItems.NatureScroll), "G", "S", 'G', MysticItems.GreenInkWitgFeather, 'S', MysticItems.Scroll);
		
	}
	
}
