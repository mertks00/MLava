package me.oyuncozucu.mlava.instance;

import me.oyuncozucu.mlava.MLava;
import me.oyuncozucu.mlava.manager.MessageManager;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Game {

    /*

     author: @mertks00

                     */

    private MLava plugin;

    public Game(MLava plugin) {
        this.plugin = plugin;
    }

    public void startGame() {

        if(plugin.getGameState() == GameState.WAITING) {

            String name = plugin.getConfig().getString("world-name");
            World world = Bukkit.getWorld(name);

            ItemStack kazma = new ItemStack(Material.NETHERITE_PICKAXE, 1);
            ItemMeta kazma_meta = kazma.getItemMeta();
            kazma_meta.addEnchant(Enchantment.DURABILITY, 3, true);
            kazma_meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3,true);
            kazma_meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
            kazma.setItemMeta(kazma_meta);

            ItemStack feed = new ItemStack(Material.COOKED_BEEF, 16);


            for(Player players : Bukkit.getOnlinePlayers()) {

                players.setGameMode(GameMode.SURVIVAL);
                players.getInventory().clear();
                if(players.teleport(world.getSpawnLocation())) {
                    players.getInventory().setItem(1, kazma);
                    players.getInventory().setItem(2, feed);
                    players.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999999,999999999));
                    players.sendTitle(ChatColor.translateAlternateColorCodes('&', MessageManager.getStartGameTitle()), ChatColor.translateAlternateColorCodes('&',MessageManager.getStartGameSubTitle()));
                }

            }

        } else {
            Bukkit.broadcastMessage("Bitiyor...");
        }

    }

}
