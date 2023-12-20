package com.nnw.game.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.net.*;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import static com.badlogic.gdx.Gdx.net;

public class CreateAccount {
    public static HttpStatus status;

    public static void createAccount(String userNickName, String userName, String password, String birthday, String email) {
        // Construir a URL desejada
        String url = "http://localhost:8080/users";

        JsonValue jsonAccountInformation = new JsonValue(JsonValue.ValueType.object);

// Criar o objeto 'customer'
        JsonValue jsonCustomer = new JsonValue(JsonValue.ValueType.object);
        jsonCustomer.addChild("username", new JsonValue(userName));
        jsonCustomer.addChild("userNickname", new JsonValue(userNickName));
        jsonCustomer.addChild("email", new JsonValue(email));
        jsonCustomer.addChild("birthday", new JsonValue(birthday));
        jsonCustomer.addChild("customerType", new JsonValue("ADMIN"));
        jsonCustomer.addChild("password", new JsonValue(password));

// Criar o objeto 'profile'
        JsonValue jsonProfile = new JsonValue(JsonValue.ValueType.object);
        jsonProfile.addChild("profileImage", new JsonValue("exampleImageURL"));
        jsonProfile.addChild("level", new JsonValue(1));
        jsonProfile.addChild("experience", new JsonValue(0.0));
        jsonProfile.addChild("moonCoin", new JsonValue(100));
        jsonProfile.addChild("ryous", new JsonValue(50));
        jsonProfile.addChild("playerRank", new JsonValue("RANK E"));
        jsonProfile.addChild("playerTitle", new JsonValue("Newbie"));
        jsonProfile.addChild("wins", new JsonValue(0));
        jsonProfile.addChild("loses", new JsonValue(0));
        jsonProfile.addChild("maxLevel", new JsonValue(1));
        jsonProfile.addChild("streak", new JsonValue(0));

// Adicionar os objetos 'customer' e 'profile' ao objeto principal
        jsonAccountInformation.addChild("customer", jsonCustomer);
        jsonAccountInformation.addChild("profile", jsonProfile);

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



        // Enviar a requisição e aguardar a resposta
        Net.HttpResponseListener listener = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                status = httpResponse.getStatus();
                String response = httpResponse.getResultAsString();

                // Lidar com a resposta aqui
                System.out.println("Status da resposta: " + status.getStatusCode());
                System.out.println("Corpo da resposta: " + response);
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
       Gdx.net.sendHttpRequest(httpRequest, listener);

    }
}
