package com.nnw.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TextFieldListener implements TextField.TextFieldListener {

    public static boolean dateIsValid;

    @Override
    public void keyTyped(TextField textField, char c) {

        // O usuário pressionou Enter, você pode validar e processar a data aqui
            validateAndProcessDate(textField.getText());

    }

    private void validateAndProcessDate(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(input);
            // A data é válida, você pode prosseguir com o processamento aqui
            if(date.before(dateFormat.parse("01/01/1940"))){
                System.out.println("Data antes de 1940");
                dateIsValid = false;
            }else if(date.after(dateFormat.parse("01/01/2030"))){
                System.out.println("Data depois de 2030");
                dateIsValid = false;
            }else{
                System.out.println("tudo certo");
                dateIsValid= true;
            }

        } catch (ParseException e) {
            // A entrada não é uma data válida
            System.out.println("Formato de data inválido");
            dateIsValid = false;
        }
    }
}
