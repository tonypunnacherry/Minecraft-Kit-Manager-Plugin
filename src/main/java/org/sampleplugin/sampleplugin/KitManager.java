package org.sampleplugin.sampleplugin;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.K;

import java.util.*;

// This class manages the kit profiles all the players in the server.
public class KitManager {
    private static KitManager instance;
    private Map<Player, KitProfile> kits;

    public KitManager() {
        kits = new HashMap<Player, KitProfile>();
    }

    /**
     * Gets a kit given the player
     * @param player the Player who owns the kit
     * @return a kit profile (see KitProfile)
     */
    public KitProfile getKitProfile(Player player) {
        if (!kits.containsKey(player)) {
            kits.put(player, new KitProfile(player));
        }
        return kits.get(player);
    }

    /**
     * Checks if there is an instance of a KitManager and creates a new one if not.
     */
    public static KitManager getInstance() {
        if (instance == null) {
            instance = new KitManager();
        }
        return instance;
    }
}
