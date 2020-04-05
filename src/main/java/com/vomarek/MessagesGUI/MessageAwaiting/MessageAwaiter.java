package com.vomarek.MessagesGUI.MessageAwaiting;

import com.vomarek.MessagesGUI.MessagesGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;

public class MessageAwaiter implements Listener {
    private MessagesGUI plugin;
    private AwaiterRunnable onFinish;
    private MessageAwaiter awaiter;
    private Player player;

    public MessageAwaiter(AwaiterRunnable onFinish, Player player, MessagesGUI plugin) {
        this.awaiter = this;
        this.plugin = plugin;
        this.onFinish = onFinish;
        this.player = player;
        plugin.getServer().getPluginManager().registerEvent(AsyncPlayerChatEvent.class, this, EventPriority.LOWEST, this.executor, (Plugin)plugin, true);
    }


    public EventExecutor executor = new EventExecutor() {
        public void execute(Listener listener, Event e) throws EventException {
            if (!(e instanceof AsyncPlayerChatEvent)) return;

            AsyncPlayerChatEvent event = (AsyncPlayerChatEvent) e;

            if (!event.getPlayer().equals(player)) return;

            event.setCancelled(true);

            onFinish.setMessage(event.getMessage());
            onFinish.runTask(plugin);

            event.getHandlers().unregister(awaiter);
        }
    };
}
