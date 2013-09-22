package net.minecraft4455.mysticrpg.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft4455.mysticrpg.core.info.MysticModInfo;

public class GreenInkWithFeather extends Item {
	
	public GreenInkWithFeather(int id) {
		
		super(id);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		
		itemIcon = icon.registerIcon(MysticModInfo.MODID + ":GreenInkWithFeather");
		
	}
	
}
