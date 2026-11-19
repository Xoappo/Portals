package dev.xoapp.portals.session.process;

import org.powernukkitx.Player;
import org.powernukkitx.event.player.PlayerInteractEvent;
import org.powernukkitx.inventory.Inventory;
import org.powernukkitx.item.Item;
import org.powernukkitx.item.ItemID;
import org.powernukkitx.level.Position;
import org.powernukkitx.nbt.tag.CompoundTag;
import org.powernukkitx.nbt.tag.Tag;
import org.powernukkitx.utils.DyeColor;
import org.powernukkitx.utils.TextFormat;

import dev.xoapp.portals.factory.PortalFactory;
import dev.xoapp.portals.object.Portal;
import dev.xoapp.portals.object.PortalCube;
import dev.xoapp.portals.session.Session;

public class MakePortalProcess
{
    Position firstPosition = null;
    Position secondPosition = null;

    Position destination = null;

    public Session session;
    public String name;

    public MakePortalProcess(String name, Session session)
    {
        this.session = session;
        this.name = name;
    }

    public void initialize()
    {
        Player player = session.getPlayer();
        if (player == null) {
            return;
        }

        Inventory inventory = player.getInventory();

        Item firstPosItem = Item.get(ItemID.GOLDEN_AXE)
                                .setCustomName(TextFormat.colorize("&2First Position"))
                                .setNbt(new CompoundTag().putString("portal", "firstPosition"));
        Item secondPosItem = Item.get(ItemID.DIAMOND_AXE)
                                .setCustomName(TextFormat.colorize("&aSSecond Position"))
                                .setNbt(new CompoundTag().putString("portal", "secondPosition"));
        Item destinationItem= Item.get(ItemID.IRON_AXE)
                                .setCustomName(TextFormat.colorize("&aDestination"))
                                .setNbt(new CompoundTag().putString("portal", "destination"));
        Item saveItem = Item.get(ItemID.DYE, DyeColor.GREEN.getDyeData())
                                .setCustomName(TextFormat.colorize("&aSAVE"))
                                .setNbt(new CompoundTag().putString("portal", "save"));
        Item exitItem = Item.get(ItemID.DYE, DyeColor.RED.getDyeData())
                                .setCustomName(TextFormat.colorize("&cEXIT"))
                                .setNbt(new CompoundTag().putString("portal", "exit"));

        inventory.setItem(0, firstPosItem);
        inventory.setItem(1, secondPosItem);
        inventory.setItem(2, destinationItem);
        inventory.setItem(7, saveItem);
        inventory.setItem(8, exitItem);

        session.setProcess(this);
        player.sendMessage(TextFormat.colorize("&aYou are now in portal making process"));
    }

    public void handleInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        Item item = player.getInventory().getItemInMainHand();

        CompoundTag nbt = item.getNbt();
        assert nbt != null;

        Tag tag = nbt.getTags().get("portal");

        assert event.getBlock() != null;
        Position eyePosition = event.getBlock().getPosition();

        if (tag == null) {
            return;
        }

        if (session.process == null) {
            return;
        }

        switch (tag.parseValue().toString())
        {
            case "firstPosition" -> {
                firstPosition = eyePosition;
                player.sendMessage(TextFormat.colorize("&aFirst Position saved"));
            }

            case "secondPosition" -> {
                if (firstPosition == null) {
                    player.sendMessage(TextFormat.colorize("&cPlease put first position"));
                    return;
                }

                secondPosition = eyePosition;
                player.sendMessage(TextFormat.colorize("&aSecond Position saved"));
            }

            case "destination" -> {
                destination = eyePosition;
                player.sendMessage(TextFormat.colorize("&aDestination Position saved"));
            }

            case "save" -> {
                if (firstPosition == null || secondPosition == null || destination == null) {
                    player.sendMessage(TextFormat.colorize("&cInvalid parsed positions"));
                    return;
                }

                PortalCube cube = new PortalCube();

                cube.setFirstPosition(firstPosition);
                cube.setSecondPosition(secondPosition);

                Portal portal = new Portal(name, cube, destination);
                PortalFactory.getPortals().put(name, portal);

                player.getInventory().clearAll();
                session.setProcess(null);

                player.sendMessage(TextFormat.colorize("&aPortal successfully saved"));
            }

            case "exit" -> {
                player.getInventory().clearAll();
                player.sendMessage(TextFormat.colorize("&aPortal process exit"));

                session.setProcess(null);
            }
        }
    }
}
