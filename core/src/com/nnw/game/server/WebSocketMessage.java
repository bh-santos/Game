package com.nnw.game.server;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketMessage {
    private String text;

public String toJson(){
    Gson gson = new Gson();
    return  gson.toJson(this);
}

    public static WebSocketMessage fromJson(String json) {
        // Use Gson para converter JSON de volta para objeto
        Gson gson = new Gson();
        return gson.fromJson(json, WebSocketMessage.class);
    }
}
