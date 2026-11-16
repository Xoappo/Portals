package dev.xoapp.portals.object;

import org.powernukkitx.level.Position;
import org.powernukkitx.utils.ConfigSection;

import dev.xoapp.portals.utils.PortalUtils;

public record Portal(String name, PortalCube cube, Position destination)
{
    public Portal(String name)
    {
        this(name, new PortalCube(), null);
    }

    public Portal(String name, PortalCube portalCube)
    {
        this(name, portalCube, null);
    }

    public ConfigSection toSection()
    {
        ConfigSection section = new ConfigSection();

        section.put("firstPosition", PortalUtils.posToString(cube.getFirstPosition()));
        section.put("secondPosition", PortalUtils.posToString(cube.getSecondPosition()));
        section.put("destination", PortalUtils.posToString(destination));

        return section;
    }
}
