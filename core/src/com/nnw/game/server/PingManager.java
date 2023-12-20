package com.nnw.game.server;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;

import static com.badlogic.gdx.Gdx.net;

public class PingManager {

    public static boolean isConnected;
    public static void pingServer(final PingCallback callback){
        String pingUrl = "http://localhost:8080/ping";

        Net.HttpRequest httpRequest = new HttpRequestBuilder().newRequest()
                .method(Net.HttpMethods.GET)
                .url(pingUrl)
                .build();

        final long startTime = System.currentTimeMillis();

        Net.HttpResponseListener listener = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {

                long endTime = System.currentTimeMillis();

                long latency = endTime - startTime;


                // Lidar com a resposta do servidor de ping (opcional)
                String response = httpResponse.getResultAsString();

                System.out.println("Ping Response: " + response);
                System.out.println("Latência: " + latency + " ms");

                isConnected = true;
                if (callback != null) {
                    callback.onPingResult(isConnected, latency);
                }
            }

            @Override
            public void failed(Throwable t) {
                // Lidar com falhas na requisição de ping (opcional)
                isConnected = false;
                if (callback != null) {
                    callback.onPingResult(isConnected, -1); // Use -1 para indicar falha
                }
            }

            @Override
            public void cancelled() {
                // Lidar com o cancelamento da requisição de ping (opcional)
            }
        };

        // Enviar a requisição de ping
        net.sendHttpRequest(httpRequest, listener);
    }
}
