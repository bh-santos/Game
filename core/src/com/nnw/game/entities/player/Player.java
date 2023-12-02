/*
package com.nnw.game.entities.player;

import com.nnw.game.entities.village.Village;
import com.nnw.game.entities.ninja.Ninja;
import lombok.Data;

import java.util.LinkedList;

@Data
public class Player {

    private LinkedList<Ninja> playerSelectedNinjas = new LinkedList<>();
    private Village village;
    private int playerId;
    private static int nextPlayerId = 1;

    public Player(){
        this.playerId = nextPlayerId;
        nextPlayerId++;
    }

    public void playerInfo(){
        System.out.println("ID: "+ getPlayerId());
        System.out.println("Ninja 1: " + getPlayerSelectedNinjas().get(0).getNinjaName());
        System.out.println("Ninja 2: " + getPlayerSelectedNinjas().get(1).getNinjaName());
        System.out.println("Ninja 3: " + getPlayerSelectedNinjas().get(2).getNinjaName());
        System.out.println("Ninja 4: " + getPlayerSelectedNinjas().get(3).getNinjaName());
        System.out.println("Ninja 5: " + getPlayerSelectedNinjas().get(4).getNinjaName());

    }

}
*/
