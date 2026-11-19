package dev.xoapp.portals.utils;

import org.powernukkitx.level.Position;
import org.powernukkitx.level.Level;
import org.powernukkitx.Server;

public class PortalUtils
{
    public static String posToString(Position selectedPosition)
    {
        double x = selectedPosition.getX();
        double y = selectedPosition.getY();
        double z = selectedPosition.getZ();

        String worldName = selectedPosition.getLevelName();

        return x + ":" + y + ":" + z + ":" + worldName;
    }

    public static Position posFromString(String data)
    {
        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] realData = data.split(":");
        Level world = Server.getInstance().getLevelByName(realData[3]);

        double x = Double.parseDouble(realData[0]);
        double y = Double.parseDouble(realData[1]);
        double z = Double.parseDouble(realData[2]);

        return new Position(x, y, z, world);
    }
}
