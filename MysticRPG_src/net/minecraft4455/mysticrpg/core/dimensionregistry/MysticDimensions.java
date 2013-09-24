package net.minecraft4455.mysticrpg.core.dimensionregistry;

import net.minecraft4455.mysticrpg.core.dimensionregistry.worldprovider.WorldProviderYirawia;
import net.minecraftforge.common.DimensionManager;

public class MysticDimensions {

    public static final int YirawiaID = 3;

    public static void init() {

        DimensionManager.registerProviderType(YirawiaID,
                WorldProviderYirawia.class, true);
        DimensionManager.registerDimension(YirawiaID, YirawiaID);

    }

}
