package com.nnw.game.server;

public interface CreateAccountCallback {

    void onLoginSuccess();

    void onLoginFailure(String errorMessage);
}
