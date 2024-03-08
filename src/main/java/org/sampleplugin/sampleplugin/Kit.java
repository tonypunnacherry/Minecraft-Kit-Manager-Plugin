package org.sampleplugin.sampleplugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Kit {
    private ItemStack[] inventory;
    private Player owner;

    public Kit(ItemStack[] inventory, Player owner) {
        this.inventory = inventory;
        this.owner = owner;
    }

    public void load(Player player) {
        if (!player.equals(owner)) {
            throw new IllegalStateException("Mismatch between owner and person calling the command");
        }
        owner.getInventory().setContents(inventory);
    }

    public void update(ItemStack[] newInventory) {
        inventory = newInventory;
    }

    /** TEMPORARY */
    public void debug() {
        List<Material> inventoryItems = new LinkedList<>();
        for (ItemStack item : inventory) {
            if (item == null) {
                inventoryItems.add(null);
            } else {
                inventoryItems.add(item.getType());
            }
        }
        System.out.println("Inventory: " + inventoryItems.toString());
    }
}
