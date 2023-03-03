package me.oyuncozucu.mlava.instance;

import me.oyuncozucu.mlava.MLava;
import me.oyuncozucu.mlava.manager.MessageManager;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

public class LavaStart {

    /*

     author: @mertks00

                     */

    private MLava plugin;

    public LavaStart(MLava plugin) {
        this.plugin = plugin;
    }

    public void runLavaTask() {

        String name = plugin.getConfig().getString("world-name");
        World world = Bukkit.getWorld(name);
        Location center = world.getSpawnLocation();
        int time = plugin.getConfig().getInt("start-lava");
        int delay = plugin.getConfig().getInt("again-lava");

        if(plugin.getGameState() == GameState.WAITING) {

            new BukkitRunnable() {
                int y = plugin.getConfig().getInt("start-height");;

                @Override
                public void run() {

                    plugin.setGameState(GameState.STARTED);
                    plugin.setEnable(false);
                    int radius = plugin.getConfig().getInt("border-size");

                    if (y > world.getMaxHeight()) {
                        cancel();
                        return;
                    }

                    for (int x = -radius; x <= radius; x++) {
                        for (int z = -radius; z <= radius; z++) {
                            world.getBlockAt(center.getBlockX() + x, y, center.getBlockZ() + z).setType(Material.LAVA, false);
                        }
                    }
                    y++;
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getLavaRisingMessage() + y));
                }

            }.runTaskTimer(plugin, time * 1200L, delay * 20L);

        } else {
            Bukkit.broadcastMessage("Oyun Başlatılamadı");
        }
        // 20 = 1 saniye
        // 200 = 10 saniye
        // 600 = 30 saniye
        // 1.200 = 1 dakika
        // 30.000 = 25 dakika

    }

}
