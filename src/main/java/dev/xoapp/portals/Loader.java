package dev.xoapp.portals;

import org.powernukkitx.plugin.PluginBase;

import dev.xoapp.portals.commands.PortalCommand;
import dev.xoapp.portals.factory.PortalFactory;
import lombok.Getter;

public class Loader extends PluginBase
{
    @Getter private static Loader instance;

    @Override
    public void onEnable()
    {
        instance = this;

        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getServer().getCommandMap().register("portal", new PortalCommand());

        PortalFactory.initialize();
    }

    @Override
    public void onDisable()
    {
        PortalFactory.save();
    }
}
