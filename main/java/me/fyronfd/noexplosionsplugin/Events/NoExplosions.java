package me.fyronfd.noexplosionsplugin.Events;

import me.fyronfd.noexplosionsplugin.NoExplosionsPlugin;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class NoExplosions implements Listener {
    private Plugin plugin = NoExplosionsPlugin.getPlugin(NoExplosionsPlugin.class);

    @EventHandler
    public void onExplode(EntityExplodeEvent event){
        //Tells the console the entity and where it will explode
        Entity entity = event.getEntity();
        Location entityLocation = entity.getLocation();
        plugin.getLogger().info("A " + entity + " is about to explode at " + entityLocation.getBlockX() + " " + entityLocation.getBlockY() + " " + entityLocation.getBlockZ());

        //Gets all affected blocks, and will cancel if a claimed block is affected
        List<Block> blocks = event.blockList();
        boolean claimedBlocks = false;
        for (Block block : blocks) {
            if(isClaimedBlock(block.getLocation())){
                claimedBlocks = true;
                break;
            }
        }

        if(claimedBlocks){
            event.setCancelled(true);
            plugin.getLogger().info("The explosion was cancelled since it affected a claim. ");
        }

    }

    private boolean isClaimedBlock (Location location){
        //Sample coordinates
        Location pos1 = new Location(location.getWorld(), -120, 0, -9);
        Location pos2 = new Location(location.getWorld(), -105, 256, -22);

        int x1 = pos1.getBlockX(); int x2 = pos2.getBlockX();
        int y1 = pos1.getBlockY(); int y2 = pos2.getBlockY();
        int z1 = pos1.getBlockZ(); int z2 = pos2.getBlockZ();

        //if the block is between the 2 values, it is a "claimed" block
        return isBetween(location.getBlockX(), x1, x2) &&
                isBetween(location.getBlockY(), y1, y2) &&
                isBetween(location.getBlockZ(), z1, z2);
    }

    //N is the value being checked between a and b
    private boolean isBetween(int n, int a, int b){
        int min = Math.min(a,b);
        int max = Math.max(a,b);

        return n >= min && n <= max;
    }
}
