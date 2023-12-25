package com.nnw.game.server;

public interface LoginCallback {

    void onLoginSuccess();

    void onLoginFailure(String errorMessage);
}
