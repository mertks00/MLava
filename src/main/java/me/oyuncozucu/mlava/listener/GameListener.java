package me.oyuncozucu.mlava.listener;

import me.oyuncozucu.mlava.MLava;
import me.oyuncozucu.mlava.instance.GameState;
import me.oyuncozucu.mlava.manager.MessageManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class GameListener implements Listener {

    /*

     author: @mertks00

                     */

    private MLava plugin;

    public GameListener(MLava plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        String name = plugin.getConfig().getString("world-name");
        World world = Bukkit.getWorld(name);

        Player player = e.getPlayer();
        Location location = world.getSpawnLocation();


        if (player.teleport(location)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getJoinMessage()));
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player player = e.getEntity();

        if(plugin.getGameState() == GameState.STARTED) {

            player.setGameMode(GameMode.SPECTATOR);

        }


    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {


            if(!plugin.isEnable()) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
            }



    }

    @EventHandler
    public void onPvP(EntityDamageEvent e) {

            if(!plugin.isEnable()) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
            }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent b) {

        if(plugin.getGameState() == GameState.STARTED) {
            b.setCancelled(false);
        } else {
            if(!b.getPlayer().hasPermission("mlava.admin")) {
                b.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent p) {
        if(plugin.getGameState() == GameState.STARTED) {
            p.setCancelled(false);
        } else {
            if(p.getPlayer().hasPermission("mlava.admin")) {
                p.setCancelled(true);
            }
        }
    }

}
