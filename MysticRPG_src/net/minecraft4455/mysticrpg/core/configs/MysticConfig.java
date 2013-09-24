package net.minecraft4455.mysticrpg.core.configs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

import net.minecraftforge.common.Configuration;

public class MysticConfig {

    @Retention(RetentionPolicy.RUNTIME)
    private static @interface CfgBool {
    }

    @Retention(RetentionPolicy.RUNTIME)
    private static @interface CfgID {
        public boolean block() default false;
    }

    public static @CfgID
    int ScrollID = 6000;
    public static @CfgID
    int BlackMercySeedID = 6001;
    public static @CfgID
    int BlackMercySubstanceID = 6002;
    public static @CfgID
    int NatureScrollID = 6003;
    public static @CfgID
    int GreenInkID = 6004;
    public static @CfgID
    int GreenInkWithFeatherID = 6005;

    public static @CfgID(block = true)
    int BlackMercyID = 3000;
    public static @CfgID(block = true)
    int YirawiaPortalID = 3001;
    public static @CfgID(block = true)
    int YirawiaBlockID = 3002;
    public static @CfgID(block = true)
    int ViriaFireID = 3003;

    public static void load(final Configuration config) {
        try {
            config.load();
            final Field[] fields = MysticConfig.class.getFields();
            for (final Field field : fields) {
                final CfgID annotation = field.getAnnotation(CfgID.class);
                if (annotation != null) {
                    int id = field.getInt(null);
                    if (annotation.block()) {
                        id = config.getBlock(field.getName(), id).getInt();
                    } else {
                        id = config.getItem(field.getName(), id).getInt();
                    }
                    field.setInt(null, id);
                } else if (field.isAnnotationPresent(CfgBool.class)) {
                    boolean bool = field.getBoolean(null);
                    bool = config.get(Configuration.CATEGORY_GENERAL,
                            field.getName(), bool).getBoolean(bool);
                    field.setBoolean(null, bool);
                }
            }
        } catch (final Exception e) {
        } finally {
            config.save();
        }
    }

}