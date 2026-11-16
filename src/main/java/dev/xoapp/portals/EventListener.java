package dev.xoapp.portals;

import org.powernukkitx.Player;
import org.powernukkitx.event.EventHandler;
import org.powernukkitx.event.Listener;
import org.powernukkitx.event.player.PlayerInteractEvent;
import org.powernukkitx.event.player.PlayerJoinEvent;
import org.powernukkitx.event.player.PlayerMoveEvent;
import org.powernukkitx.event.player.PlayerQuitEvent;
import org.powernukkitx.utils.TextFormat;

import dev.xoapp.portals.factory.PortalFactory;
import dev.xoapp.portals.factory.SessionFactory;
import dev.xoapp.portals.object.Portal;
import dev.xoapp.portals.session.Session;

public class EventListener implements Listener
{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        SessionFactory.getSessions().put(player.getName(), new Session(player.getName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        Session session = SessionFactory.getSessions().get(player.getName());

        if (session != null) {
            SessionFactory.getSessions().remove(player.getName());
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        Session session = SessionFactory.getSessions().get(player.getName());

        if (session == null) {
            return;
        }

        if (session.getProcess() != null) {
            session.getProcess().handleInteract(event);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        Session session = SessionFactory.getSessions().get(player.getName());

        if (session == null) {
            return;
        }

        if (event.getFrom().equals(event.getTo())) {
            return; // avoid head movement
        }

        Portal portal = PortalFactory.getByPosition(player.getPosition());

        if (portal == null) {
            return;
        }

        player.teleport(portal.destination());
        player.sendMessage(TextFormat.colorize("&aYou used portal &e" + portal.name()));
    }
}
