/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2022.
 */

package me.itsmyunderscore.utils;

import me.itsmyunderscore.config.Config;

public class Filter {

    private boolean active;

    public Filter() {
        this.active = Config.FILTER_ENABLED;
    }

    public boolean isActive() {
        this.active = Config.FILTER_ENABLED;
        return active;
    }

}
