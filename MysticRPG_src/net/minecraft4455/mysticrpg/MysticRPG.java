package net.minecraft4455.mysticrpg;

import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft4455.mysticrpg.core.biomes.MysticBiomes;
import net.minecraft4455.mysticrpg.core.blocks.MysticBlocks;
import net.minecraft4455.mysticrpg.core.configs.MysticConfig;
import net.minecraft4455.mysticrpg.core.dimensionregistry.MysticDimensions;
import net.minecraft4455.mysticrpg.core.info.MysticModInfo;
import net.minecraft4455.mysticrpg.core.items.MysticItems;
import net.minecraft4455.mysticrpg.core.proxys.CommonProxy;
import net.minecraft4455.mysticrpg.core.recipes.MysticRecipes;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(name = MysticModInfo.NAME, modid = MysticModInfo.MODID, version = MysticModInfo.VERSION)
@NetworkMod(clientSideRequired = MysticModInfo.CLIENT, serverSideRequired = MysticModInfo.SERVER)
public class MysticRPG {

    @Instance(MysticModInfo.MODID)
    public static MysticRPG instance;
    @SidedProxy(clientSide = MysticModInfo.CLIENTPROXY, serverSide = MysticModInfo.COMMONPROXY)
    public static CommonProxy proxy;
    
    private static Logger logger;

    public static CreativeTabs tab;

    public static final String URL = "https://raw.github.com/Minecraft4455/MysticRPG/master/versioncheck/";

    private static final String[] languages = new String[] { "en_US.xml" };

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {

        VersionCheck.updateCheckMRPG(MysticModInfo.NAME, MysticModInfo.VERSION,
                URL);

        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);

        logger = Logger.getLogger(MysticModInfo.MODID);
        logger.setParent(FMLLog.getLogger());
        final Configuration config = new Configuration(
                e.getSuggestedConfigurationFile());
        MysticConfig.load(config);
        tab = new CreativeTabsMRPG(CreativeTabs.getNextID(), "tabMysticRPG");

        MysticItems.init();
        MysticBlocks.init();
        MysticRecipes.init();
        MysticDimensions.init(); // TODO Localize biomes and dimensions
        MysticBiomes.init();
        LangUtil.addNames("/assets/" + MysticModInfo.MODID + "/lang/", languages);

    }

}
