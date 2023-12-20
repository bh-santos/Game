package com.nnw.game.entities.player;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerTest {

    String firstName;
    long document;

    String password;

    String email;

    UserType userType;

    long balance;

}
