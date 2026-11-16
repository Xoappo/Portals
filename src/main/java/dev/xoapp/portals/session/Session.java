package dev.xoapp.portals.session;

import org.powernukkitx.Player;
import org.powernukkitx.Server;

import dev.xoapp.portals.session.process.MakePortalProcess;
import lombok.Getter;
import lombok.Setter;

public class Session
{
    @Getter
    public String name;

    @Getter @Setter
    public Object portal = null;

    @Getter @Setter
    public MakePortalProcess process = null;

    public Session(String name)
    {
        this.name = name;
    }

    public Player getPlayer()
    {
        return Server.getInstance().getPlayerExact(name);
    }
}
