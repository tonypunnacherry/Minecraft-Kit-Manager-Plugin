package org.sampleplugin.sampleplugin;
import java.util.*;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

///kit create [name]: copies inventory
//
///kit load: most recent inventory
//
///kit load [name]: kit with name
//
///kit update [name]: replaces kit (confirmation)
//
///kit delete [name] deletes kit (confirmation)
//
///kit rename [name] [new name]: renames the kit with new name
//
///kit list: list of kits
public class KitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            return false;
        }

        String subcommand = args[0];

        KitManager allKits = KitManager.getInstance();
        Player player = (Player) sender;
        KitProfile profile = allKits.getKitProfile(player);

        if (subcommand.equals("create")) {
            if (args.length < 2) {
                return false;
            } else {
                String kitName = args[1];
                if (profile.kitExists(kitName)) {
                    player.sendMessage("This name is already in use. (" + kitName + ")");
                    return false;
                }
                ItemStack[] inventory = player.getInventory().getContents();
                Kit newKit = new Kit(inventory, player);
                profile.createKit(kitName, newKit);
                player.sendMessage("Saved kit " + kitName + " successfully!");
                //newKit.debug();
            }
        } else if (subcommand.equals("load")) {
            if (args.length == 1) {
                Kit lastKit = profile.getLastKit();
                if (lastKit == null) {
                    player.sendMessage("Please create a kit first😏");
                    return false;
                }
                lastKit.load(player);
            } else {
                String kitName = args[1];
                Kit kit = profile.getKit(kitName);
                if (kit == null) {
                    player.sendMessage("Kit does not exist 🤢");
                    return false;
                }
                kit.load(player);
            }
        } else if (subcommand.equals("update")) {
            if (args.length < 2) {
                player.sendMessage(("Please provide the kit's name."));
                return false;
            }
            String kitName = args[1];
            Kit kit = profile.getKit(kitName);
            if (kit == null) {
                player.sendMessage("Kit does not exist 🤢");
                return false;
            }
            kit.update(player.getInventory().getContents());
            player.sendMessage("Kit " + kitName + " has been updated. 😊");
        } else if (subcommand.equals("delete")) {
            if (args.length < 2) {
                player.sendMessage("Please provide the kit's name.");
                return false;
            }
            String kitName = args[1];
            if (!profile.kitExists(kitName)) {
                player.sendMessage("Kit does not exist 🤢");
                return false;
            }
            profile.removeKit(kitName);
            player.sendMessage("Kit " + kitName + " has been removed.");
        } else if (subcommand.equals("rename")) {
            String kitName = args[1];
            String replaceName = args[2];
            if (!profile.kitExists(kitName)) {
                player.sendMessage("Kit does not exist 😊");
                return false;
            }
            if (profile.kitExists(replaceName)) {
                player.sendMessage("You cannot use kit name (" + replaceName + ") that already exists.");
                return false;
            }
            Kit kit = profile.getKit(kitName);
            profile.removeKit(kitName);
            profile.createKit(replaceName, kit);
        } else if (subcommand.equals("list")) {
            Set<String> names = profile.getKitNames();
            if (names.isEmpty()) {
                player.sendMessage("No kits 😡😡");
            } else {
                player.sendMessage("Kits: " + String.join(", ", names));
            }
        } else {
            return false;
        }
        return true;
    }
}
