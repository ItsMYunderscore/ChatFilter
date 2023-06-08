/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2023.
 */

package me.itsmyunderscore.config;

import me.itsmyunderscore.utils.ConfigFile;
import me.itsmyunderscore.utils.Message;
import me.itsmyunderscore.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Chars {
    private static ConfigFile charsFile;

    public static List<String[]> CHARACTERS;

    public Chars() {
        if (Config.DB_ENABLED) return;

        try {
            ConfigFile config = new ConfigFile("chars.yml");

            charsFile = config;

            CHARACTERS = loadCharacters(config.getStringList("characters"));
        } catch (Exception exception) {
            Message.log("Critical error! 1x04");
        }
    }

    public static void save() {
        charsFile.set("characters", saveCharacters(CHARACTERS));

        charsFile.save();
    }

    public static void reload() {
        ConfigFile config = new ConfigFile("chars.yml");

        charsFile = config;

        CHARACTERS = loadCharacters(config.getStringList("characters"));

        Message.debug("Chars reloaded");
    }

    private static List<String[]> loadCharacters(List<String> characterList) {
        List<String[]> characters = new ArrayList<>();

        for (String character : characterList) {
            String[] parts = character.split(",");
            if (parts.length == 2) {
                characters.add(parts);
            }
        }

        return characters;
    }

    private static List<String> saveCharacters(List<String[]> characters) {
        List<String> characterList = new ArrayList<>();

        for (String[] parts : characters) {
            if (parts.length == 2) {
                String character = parts[0] + "," + parts[1];
                characterList.add(character);
            }
        }

        return characterList;
    }
}
