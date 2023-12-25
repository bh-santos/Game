package com.nnw.game.server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nnw.game.entities.player.GameUser;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class GameClient {
    public static WebSocketClient webSocketClient;
    public static String responseMessage;
    private static LoginCallback loginCallback;
    private static String loginCode;
    private static GeneralCallback generalCallback;
    private static LoginLocalCredentials loginLocalCredentials;

    public GameClient(final GameUser gameUser){
        try {
            URI serverUri = new URI("ws://localhost:8080/gameClient");
            webSocketClient = new WebSocketClient(serverUri) {

                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("Conexão aberta com o servidor");
                }

                @Override
                public void onMessage(String message) {
                    System.out.println("Mensagem recebida do servidor: " + message);

                    JsonObject json = new Gson().fromJson(message, JsonObject.class);

                    System.out.println("JSON recebido: " + json);
                    String jsonType = json.get("jsonType").getAsString();

                    switch (jsonType) {
                        case "create_account_response":
                            responseMessage = json.getAsJsonObject("data").get("response_message").getAsString();
                                if("success".equals(responseMessage)){
                                    generalCallback.onRequisitionSuccess();
                                }else{
                                    generalCallback.onRequisitionFailed(responseMessage);
                                }
                                break;
                        case "login_response":
                            // Extraia o valor da chave "response_message"
                            responseMessage = json.getAsJsonObject("data").get("response_message").getAsString();

                            // Chame o callback adequado com base no valor da resposta
                            if ("success".equals(responseMessage)) {
                                loginCode = json.getAsJsonObject("data").get("login_code").getAsString();
                                loginCallback.onLoginSuccess();
                            } else {
                                loginCallback.onLoginFailure(responseMessage);
                            }
                            break;
                        case "request_profile_response":
                            JsonObject data = json.getAsJsonObject("data");
                            if (data != null) {
                                gameUser.setNickname(getStringFromJson(data,"nickname"));
                                gameUser.setProfileImage(getStringFromJson(data, "profileImage"));
                                gameUser.setLevel(getIntFromJson(data, "level"));
                                gameUser.setExperience(getFloatFromJson(data, "experience"));
                                gameUser.setMoonCoin(getIntFromJson(data, "moonCoin"));
                                gameUser.setRyous(getIntFromJson(data, "ryous"));
                                gameUser.setPlayerRank(getStringFromJson(data, "playerRank"));
                                gameUser.setPlayerTitle(getStringFromJson(data, "playerTitle"));
                                gameUser.setWins(getIntFromJson(data, "wins"));
                                gameUser.setLoses(getIntFromJson(data, "loses"));
                                gameUser.setWinPercentage(getFloatFromJson(data, "winPercentage"));
                                gameUser.setMaxLevel(getIntFromJson(data, "maxLevel"));
                                gameUser.setStreak(getIntFromJson(data, "streak"));
                                gameUser.setLoginCode(loginCode);

                                generalCallback.onRequisitionSuccess();
                            }else{
                                System.out.println("data is null");
                            }

                    }
                }


                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("Conexão fechada com o servidor");
                }

                @Override
                public void onError(Exception ex) {
                    ex.printStackTrace();
                }
            };

            webSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    private String getStringFromJson(JsonObject json, String key) {
        return json.has(key) && !json.get(key).isJsonNull() ? json.get(key).getAsString() : "";
    }

    private int getIntFromJson(JsonObject json, String key) {
        return json.has(key) && !json.get(key).isJsonNull() ? Integer.parseInt(json.get(key).getAsString()) : 0;
    }

    private float getFloatFromJson(JsonObject json, String key) {
        return json.has(key) && !json.get(key).isJsonNull() ? Float.parseFloat(json.get(key).getAsString()) : 0.0f;}

    public static void sendLoginMessageToServer(String text, LoginCallback callback) {
        loginCallback=callback;
        webSocketClient.send(text);
    }

    public static void sendCreateAccountMessageToServer(String text, GeneralCallback callback){
        generalCallback = callback;
        webSocketClient.send(text);
    }

    public static void sendRequestProfileMessageToServer(String text, GeneralCallback callback){
        generalCallback = callback;
        webSocketClient.send(text);
    }


    public static void createAccount(String nickname, String username, String password, String birthday, String email, GeneralCallback callback){

        JsonObject json = new JsonObject();

        json.addProperty("jsonType","create_account");

        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("username", username);
        jsonData.addProperty("password", password);
        jsonData.addProperty("email", email);
        jsonData.addProperty("nickname", nickname);
        jsonData.addProperty("birthday", birthday);
        jsonData.addProperty("gameUserType", "ADMIN");


        json.add("data", jsonData);
        String jsonString = new Gson().toJson(json);
        sendCreateAccountMessageToServer(jsonString, callback);
    }

    public static void login(String username, String password,LoginCallback callback){
        JsonObject json = new JsonObject();

        loginLocalCredentials = new LoginLocalCredentials(username,password);

        json.addProperty("jsonType","login");

        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("username",username);
        jsonData.addProperty("password",password);
        json.add("data",jsonData);

        String jsonString = new Gson().toJson(json);

        sendLoginMessageToServer(jsonString,callback);
    }

    public static void requestProfile(GeneralCallback callback){
         JsonObject json = new JsonObject();

         json.addProperty("jsonType","request_profile");


         JsonObject jsonData = new JsonObject();

         jsonData.addProperty("requestInformation",true);

         jsonData.addProperty("username", loginLocalCredentials.getUsername());

         jsonData.addProperty("password", loginLocalCredentials.getPassword());

         jsonData.addProperty("loginCode", loginCode);

         json.add("data", jsonData);

         String jsonString = new Gson().toJson(json);


         sendRequestProfileMessageToServer(jsonString,callback);
    }
}
