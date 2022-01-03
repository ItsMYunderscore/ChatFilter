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

    private static ConfigFile wordsFile;

    public static List<String> forbidden_WORDS;

    public ForbiddenWords() {
        try {
            ConfigFile forbiddenWords = new ConfigFile("forbidden_words.yml");

            forbidden_WORDS = forbiddenWords.getStringList("forbidden_words");

            wordsFile = forbiddenWords;
        } catch (Exception exception) {
            Message.log("Critical error! 1x02");
        }
    }

    public static ConfigFile getWordsFile() {
        return wordsFile;
    }
}
