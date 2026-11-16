package dev.xoapp.portals.commands;

import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Collection;

import org.powernukkitx.Player;
import org.powernukkitx.command.Command;
import org.powernukkitx.command.CommandSender;
import org.powernukkitx.utils.TextFormat;

import dev.xoapp.portals.factory.PortalFactory;
import dev.xoapp.portals.factory.SessionFactory;
import dev.xoapp.portals.object.Portal;
import dev.xoapp.portals.session.Session;
import dev.xoapp.portals.session.process.MakePortalProcess;

public class PortalCommand extends Command
{
    public PortalCommand()
    {
        super("portal");
        setAliases(new String[]{"portals", "prt"});
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args)
    {
        if (!(sender instanceof Player player)) {
            return false;
        }

        exec(player, commandLabel, Arrays.asList(args));
        return super.execute(sender, commandLabel, args);
    }

    void exec(Player player, String label, List<String> args)
    {
        if (args.get(0) == null) {
            player.sendMessage(TextFormat.colorize("&cUse /" + label + " help"));
            return;
        }

        switch (args.get(0).toString())
        {
            case "help" -> {
                String[] messages = new String[]{
                    "&6Use /" + label + " make <name>",
                    "&6Use /" + label + " delete <name>",
                    "&6Use /" + label + " edit <name>",
                    "&6Use /" + label + " list"
                };

                for (String message : messages) {
                    player.sendMessage(TextFormat.colorize(message));
                }
                return;
            }

            case "list" -> {
                Set<String> keys = PortalFactory.getPortals().keySet();
                player.sendMessage(TextFormat.colorize("&aAvailable Portals: &e" + String.join(",", keys)));

                return;
            }

            case "make" -> {
                if (args.get(1) == null) {
                    player.sendMessage(TextFormat.colorize("&cUse /" + label + " make <name>"));
                    return;
                }

                String portalName = args.get(1).toString();
                Portal portal = PortalFactory.getPortals().get(portalName);

                if (portal != null) {
                    player.sendMessage(TextFormat.colorize("&cThis portal already exists"));
                    return;
                }

                Session session = SessionFactory.getSessions().get(player.getName());

                if (session == null) {
                    return;
                }

                MakePortalProcess process = new MakePortalProcess(portalName, session);
                process.initialize();
                return;
            }

            case "delete" -> {
                if (args.get(1) == null) {
                    player.sendMessage(TextFormat.colorize("&cUse /" + label + " delete <name>"));
                    return;
                }

                String portalName = args.get(1).toString();
                Portal portal = PortalFactory.getPortals().get(portalName);

                if (portal != null) {
                    player.sendMessage(TextFormat.colorize("&cThis portal doesn't exists"));
                    return;
                }

                PortalFactory.getPortals().remove(portalName);
                player.sendMessage(TextFormat.colorize("&aPortal successfully deleted"));
                return;
            }

            default -> {
                player.sendMessage(TextFormat.colorize("&cUse /" + label + " help"));
                return;
            }
        }
    }
}
