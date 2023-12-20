package com.nnw.game.server;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Timer;
import com.nnw.game.ui.MainMenuFirstScreen;
import com.nnw.game.ui.MainMenuSecondScreen;
import com.nnw.game.util.UIType;

import static com.badlogic.gdx.Gdx.net;
import static com.nnw.game.server.PingManager.isConnected;

public class AuthenticationLogin {
    public static int status;
    public static int value;
    private static Net.HttpResponseListener listener;



    public static void login(String userName, String password, final LoginCallback callback, final Game game) {
        // Construir a URL desejada
        String url = "http://localhost:8080/login";

        // Criar um objeto para representar os dados que você deseja enviar
        JsonValue jsonAccountInformation = new JsonValue(JsonValue.ValueType.object);
        jsonAccountInformation.addChild("username", new JsonValue(userName));
        jsonAccountInformation.addChild("password", new JsonValue(password));

        // Serializar os dados para uma string JSON
        String requisitionContent = jsonAccountInformation.toJson(JsonWriter.OutputType.json);

        // Construir a requisição HTTP POST
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest()
                .method(Net.HttpMethods.POST)
                .url(url)
                .header("Content-Type", "application/json") // Definir o cabeçalho para indicar que estamos enviando JSON
                .content(requisitionContent)
                .build();

        listener = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                status = httpResponse.getStatus().getStatusCode();
                String response = httpResponse.getResultAsString();

                // Lidar com a resposta aqui
                System.out.println("Status da resposta: " + status);
                System.out.println("Corpo da resposta: " + response);

                callback.onLoginCompleted(isLogged(status));


            }

            @Override
            public void failed(Throwable t) {
                // Lidar com falhas na requisição aqui
                t.printStackTrace();
            }

            @Override
            public void cancelled() {
                // Lidar com o cancelamento da requisição aqui
            }
        };



        // Usar os métodos estáticos de Net para enviar a requisição
        net.sendHttpRequest(httpRequest, listener);

    }


    public static boolean isLogged(int status){
        int HTTP_OK = 200;
        int HTTP_ACCEPTED = 202;

        return status == HTTP_OK || status == HTTP_ACCEPTED;
    }

}
