/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2021.
 */

package me.itsmyunderscore.config;

import me.itsmyunderscore.utils.ConfigFile;
import me.itsmyunderscore.utils.Message;

import java.util.List;

public class ForbiddenWords {

    public static List<String> FORBIDDEN_WORDS;
    private static ConfigFile wordsFile;

    public ForbiddenWords() {
        try {
            ConfigFile forbiddenWords = new ConfigFile("forbidden_words.yml");

            FORBIDDEN_WORDS = forbiddenWords.getStringList("forbidden_words");

            wordsFile = forbiddenWords;
        } catch (Exception exception) {
            Message.log("Critical error! 1x02");
        }
    }

    public static void save() {
        wordsFile.set("forbidden_words", FORBIDDEN_WORDS);
        wordsFile.save();
    }

    public static void reload() {
        ConfigFile forbiddenWords = new ConfigFile("forbidden_words.yml");

        FORBIDDEN_WORDS = forbiddenWords.getStringList("forbidden_words");

        wordsFile = forbiddenWords;

        Message.debug("Forbidden words reloaded");
    }

    public static ConfigFile getWordsFile() {
        return wordsFile;
    }
}
