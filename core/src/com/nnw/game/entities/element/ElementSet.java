package com.nnw.game.entities.element;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ElementSet {
    private ArrayList<Element> elements = new ArrayList<>();

    public ElementSet(){
        Element katon = new Element("Katon");
        Element doton = new Element("Doton");
        Element suiton = new Element("Suiton");
        Element fuuton = new Element("Fuuton");
        Element raiton = new Element("Raiton");
        Element neutro = new Element("Neutro");
        elements.add(katon);
        elements.add(doton);
        elements.add(suiton);
        elements.add(fuuton);
        elements.add(raiton);
        elements.add(neutro);
    }
}
