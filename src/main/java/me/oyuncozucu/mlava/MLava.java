package me.oyuncozucu.mlava;

import me.oyuncozucu.mlava.command.StartCommand;
import me.oyuncozucu.mlava.instance.GameState;
import me.oyuncozucu.mlava.listener.GameListener;
import me.oyuncozucu.mlava.manager.MessageManager;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class MLava extends JavaPlugin implements CommandExecutor {

    private GameState gameState;

    /*

     author: @mertks00

                     */
    private boolean isEnable = true;
    private int radius;
    private StartCommand command;


    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        MessageManager.setupMessageFile(this);

        setGameState(GameState.WAITING);
        getCommand("lava").setExecutor(new StartCommand(this));
        getCommand("reload").setExecutor(this);

        Bukkit.getPluginManager().registerEvents(new GameListener(this), this);


        String name = getConfig().getString("world-name");
        World world = Bukkit.getWorld(name);
        WorldBorder border = world.getWorldBorder();

        Location center = world.getSpawnLocation();

        radius = getConfig().getInt("border-size");

        border.setCenter(center);
        border.setSize(2 * radius);


    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equals("reload")) {

            if (sender instanceof Player) {

                if (sender.hasPermission("mlava.admin")) {

                    this.reloadConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getConfigReloadMessage()));

                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getNoPermMessage()));
                }

            }

        }

        return true;
    }
}
