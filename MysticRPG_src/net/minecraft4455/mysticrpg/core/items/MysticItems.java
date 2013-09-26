package net.minecraft4455.mysticrpg.core.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft4455.mysticrpg.core.configs.MysticConfig;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MysticItems {

    public static Item Scroll;
    public static Item BlackMercySeed;
    public static Item BlackMercySubstance;
    public static Item NatureScroll;
    public static Item GreenInk;
    public static Item GreenInkWitgFeather;

    public static void init() {

        initItems();
        // addNames();

    }

    private static void initItems() {

        Scroll = new Scroll(MysticConfig.ScrollID).setUnlocalizedName("Scroll");
        BlackMercySeed = new BlackMercySeed(MysticConfig.BlackMercySeedID,
                MysticConfig.BlackMercyID, Block.tilledField.blockID)
                .setUnlocalizedName("BlackMercySeed");
        BlackMercySubstance = new BlackMercySubstance(
                MysticConfig.BlackMercySubstanceID)
                .setUnlocalizedName("BlackMercySubstance");
        NatureScroll = new NatureScroll(MysticConfig.NatureScrollID)
                .setUnlocalizedName("NatureScroll");
        GreenInk = new GreenInk(MysticConfig.GreenInkID)
                .setUnlocalizedName("GreenInk");
        GreenInkWitgFeather = new GreenInkWithFeather(
                MysticConfig.GreenInkWithFeatherID)
                .setUnlocalizedName("GreenInkWitgFeather");

    }

    @SuppressWarnings("unused")
    private static void addNames() {

        // LanguageRegistry.addName(Scroll, "Scroll");
        // LanguageRegistry.addName(BlackMercySeed, "Black Mercy Seed");
        // LanguageRegistry.addName(BlackMercySubstance, "Black Mercy Substance");
        // LanguageRegistry.addName(NatureScroll, "Nature Scroll");
        // LanguageRegistry.addName(GreenInk, "Green Ink");
        // LanguageRegistry.addName(GreenInkWitgFeather, "Green Ink With Feather");

    }

}
