package org.spongepowered.proxy;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.KickedFromServerEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;

public class ConnectionListener {

    private ProxyPlugin plugin;

    public ConnectionListener(ProxyPlugin plugin) {
        this.plugin = plugin;
    }

    public void updatePlayerCount() {
        plugin.getProxy().setPlayerCount(plugin.getServer().getPlayerCount());
    }

    @Subscribe
    public void join(ServerConnectedEvent event) {
        updatePlayerCount();
    }

    @Subscribe
    public void leave(KickedFromServerEvent event) {
        updatePlayerCount();
    }

}
