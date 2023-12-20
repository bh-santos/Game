package com.nnw.game.server;

public interface PingCallback {
    void onPingResult(boolean isConnected, long latency);
}
