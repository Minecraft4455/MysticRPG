package pizzana.lib.sided;

import java.util.EnumSet;

import pizzana.lib.common.VersionCheck;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        if (type.contains(TickType.CLIENT)) {

            VersionCheck.tick();

        }

    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() {

        return "PizzAna Core tick handler";
    }

}
