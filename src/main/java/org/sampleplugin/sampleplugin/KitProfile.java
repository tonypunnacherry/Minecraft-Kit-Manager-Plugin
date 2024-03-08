package org.sampleplugin.sampleplugin;
import org.bukkit.entity.Player;
import java.util.*;

// This class will keep track of the favorite kit and previous kit of a player
// (that will be kept track).
public class KitProfile {
    private Map<String, Kit> savedKits;
    private Kit lastKit;
    private Player owner;

    public KitProfile(Player player) {
        savedKits = new HashMap<>();
        owner = player;
    }

    /** TODO: Create new kit */
    public void createKit(String name, Kit kit) {
        savedKits.put(name, kit);
        lastKit = kit;
    }

    public Kit getKit(String name) {
        return savedKits.get(name);
    }

    public Kit getLastKit() {
        return lastKit;
    }

    public Set<String> getKitNames() {
        return savedKits.keySet();
    }

    public void removeKit(String name) {
        savedKits.remove(name);
    }

    public boolean kitExists(String kitName) {
        return savedKits.containsKey(kitName);
    }
}
