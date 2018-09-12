package org.spongepowered.proxy;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.whirvis.jraknet.windows.UniversalWindowsProgram;
import org.slf4j.Logger;

@Plugin(
        id = "bedrock-proxy",
        name = "Bedrock Proxy"
)
public class ProxyPlugin {

    private final ProxyServer server;
    private final Logger logger;
    private BedrockProxy proxy;

    @Inject
    public ProxyPlugin(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;

        if (!UniversalWindowsProgram.MINECRAFT.addLoopbackExempt()) {
            logger.warn("Failed to add loopback exemption for Minecraft");
        }

        // TODO: Velocity API needs max connections
        int maxConnections = 1000;

        ProxyPlugin plugin = this;

        this.proxy = new BedrockProxy(plugin, BedrockProxy.DEFAULT_PORT, maxConnections);
        this.proxy.start();

        this.server.getEventManager().register(this, new ConnectionListener(this));

    }

    public BedrockProxy getProxy() {
        return this.proxy;
    }

    public ProxyServer getServer() {
        return this.server;
    }

    public Logger getLogger() {
        return this.logger;
    }

}
