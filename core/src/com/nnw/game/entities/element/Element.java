package com.nnw.game.entities.element;

import lombok.Data;

@Data
public class Element {
    private String elementName;

    public Element(String elementName){
        this.elementName = elementName;
    }



}
