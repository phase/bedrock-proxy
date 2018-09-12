package org.spongepowered.proxy;

import com.whirvis.jraknet.identifier.MinecraftIdentifier;
import com.whirvis.jraknet.server.RakNetServer;

public class BedrockProxy {

    public static int DEFAULT_PORT = 19132;

    private ProxyPlugin plugin;
    private RakNetServer server;
    private MinecraftIdentifier identifier;

    public BedrockProxy(ProxyPlugin plugin, int port, int maxConnections) {
        this.plugin = plugin;

        this.identifier = new MinecraftIdentifier();
        this.identifier.setMaxPlayerCount(maxConnections);
        this.identifier.setOnlinePlayerCount(plugin.getServer().getPlayerCount());
        this.identifier.setServerProtocol(282);
        this.identifier.setVersionTag("v1.6");
        // TODO: get this from the velocity API
        this.identifier.setServerName("Bedrock Proxy");

        this.server = new RakNetServer(port, maxConnections, identifier);
        this.server.addListener(new BedrockPacketReceiver(this.plugin));
    }

    public void setPlayerCount(int playerCount) {
        this.identifier.setOnlinePlayerCount(playerCount);
    }

    public void start() {
        plugin.getLogger().info("Starting RakNet Server");
        server.startThreaded();
    }

}
