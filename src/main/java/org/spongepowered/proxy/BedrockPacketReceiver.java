package org.spongepowered.proxy;

import com.whirvis.jraknet.RakNetPacket;
import com.whirvis.jraknet.server.RakNetServerListener;
import com.whirvis.jraknet.session.RakNetClientSession;

import java.net.InetSocketAddress;

public class BedrockPacketReceiver implements RakNetServerListener {

    private ProxyPlugin plugin;

    public BedrockPacketReceiver(ProxyPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onClientConnect(RakNetClientSession session) {
        InetSocketAddress address = session.getAddress();
        // tell velocity that the client has connected?
    }

    @Override
    public void handleMessage(RakNetClientSession session, RakNetPacket packet, int channel) {
        // convert packet & send to velocity
    }

}
