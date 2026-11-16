package dev.xoapp.portals.factory;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.powernukkitx.level.Position;
import org.powernukkitx.utils.Config;
import org.powernukkitx.utils.ConfigSection;
import org.powernukkitx.utils.TextFormat;

import dev.xoapp.portals.Loader;
import dev.xoapp.portals.object.Portal;
import dev.xoapp.portals.object.PortalCube;
import dev.xoapp.portals.utils.PortalUtils;
import lombok.Getter;

public class PortalFactory
{
    @Getter
    public static HashMap<String, Portal> portals = new HashMap<>();

    private static Config config;

    public static void initialize()
    {
        config = new Config(new File(Loader.getInstance().getDataFolder() + "/portals.json"), Config.JSON);

        config.getSections().forEach((key, value) -> {
            ConfigSection section = (ConfigSection) value;
            List<String> encodedPositions = new ArrayList<>();

            Position destination = null;
            PortalCube portalCube = new PortalCube();

            encodedPositions.add(section.getString("firstPosition"));
            encodedPositions.add(section.getString("secondPosition"));

            if (section.getString("destination") != null) {
                destination = PortalUtils.posFromString(section.getString("destination"));
            }

            portalCube.load(encodedPositions);
            portals.put(key, new Portal(key, portalCube, destination));

            Loader.getInstance().getLogger().info(TextFormat.colorize("&aLoaded portal(&e" + key + "&a)"));
        });

        Loader.getInstance().getLogger().info(TextFormat.colorize("&aLoaded &e" + portals.size() + "&a Portals"));
    }

    public static Portal getByPosition(Position position)
    {
        Portal selectedPortal = null;

        for (Portal portal : portals.values()) if (portal.cube().isInside(position)) {
            selectedPortal = portal;
        }

        return selectedPortal;
    }

    public static void save()
    {
        config.setAll(new LinkedHashMap<>());
        config.save();

        portals.forEach((name, portal) -> config.set(name, portal.toSection()));
        config.save();
    }
}
