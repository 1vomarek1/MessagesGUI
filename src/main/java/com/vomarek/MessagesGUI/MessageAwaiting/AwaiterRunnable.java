package com.vomarek.MessagesGUI.MessageAwaiting;

import org.bukkit.scheduler.BukkitRunnable;

public abstract class AwaiterRunnable extends BukkitRunnable {
    private String message;

    public AwaiterRunnable setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }
}
