package me.oyuncozucu.mlava.manager;

import me.oyuncozucu.mlava.MLava;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageManager {

    /*

     author: @mertks00

                     */

    private static YamlConfiguration message;

    public static void setupMessageFile(MLava mLava) {

        File file = new File(mLava.getDataFolder(), "message.yml");
        if(!file.exists()) {
            mLava.saveResource("message.yml", false);
        }
        message = YamlConfiguration.loadConfiguration(file);

    }

    public static String getLavaRisingMessage() {
        return message.getString("lava-rise-message");
    }

    public static String getNoPermMessage() {
        return message.getString("no-perm-message");
    }

    public static String getJoinMessage() {
        return message.getString("join-message");
    }

    public static String getStartGameTitle() {
        return message.getString("start-game-title");
    }

    public static String getStartGameSubTitle() {
        return message.getString("start-game-subtitle");
    }

    public static String getConfigReloadMessage() {
        return message.getString("config-reload-message");
    }


}
