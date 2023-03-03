package me.oyuncozucu.mlava.command;

import me.oyuncozucu.mlava.MLava;
import me.oyuncozucu.mlava.instance.Game;
import me.oyuncozucu.mlava.instance.GameState;
import me.oyuncozucu.mlava.instance.LavaStart;
import me.oyuncozucu.mlava.manager.MessageManager;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    /*

     author: @mertks00

                     */

    private MLava plugin;

    public StartCommand(MLava plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        LavaStart start = new LavaStart(plugin);
        Game game = new Game(plugin);

        if (command.getName().equals("lava")) {

            if (args.length == 1 && args[0].equalsIgnoreCase("start")) {

                if (sender instanceof Player) {

                    Player player = (Player) sender;

                    if (player.hasPermission("mlava.admin")) {

                        start.runLavaTask();
                        game.startGame();
                        plugin.setGameState(GameState.STARTED);

                        return true;

                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',   MessageManager.getNoPermMessage()));
                    }
                }

            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/lava start"));
            }

        }


        return true;
    }

}
