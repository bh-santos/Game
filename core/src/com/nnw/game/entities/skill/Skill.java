/*
package com.nnw.game.entities.skill;

import com.nnw.game.entities.type.Type;
import com.nnw.game.entities.element.Element;
import com.nnw.game.entities.ninja.Ninja;
import com.nnw.game.entities.rank.Rank;
import lombok.Data;

@Data
public class Skill {
    private String skillName;
    private Rank skillRank;
    private Type skillType;
    private Element skillElement;
    private double damage;
    private boolean isUsed;
    private int skillId = 0;
    private String skillFeature;//ofensiva, defensiva, cura, buff e debuff;
    private ImageView SKILL_ICON;
    private String description;

    //Construtor:
    public Skill(String skillName, Rank skillRank, Type skillType, Element skillElement, String skillFeature, ImageView skillIcon,String description) {
        this.skillName = skillName;
        this.skillRank = skillRank;
        this.skillType = skillType;
        this.skillElement = skillElement;
        this.skillFeature = skillFeature;
        SKILL_ICON = skillIcon;
        isUsed = false;
        this.skillId++;
        this.description = description;
    }


    //Função genérica de seleção:
    public void useSkill(String skillName, Ninja skillUser, Ninja targetNinja, Type skillType, Element skillElement){
        switch(skillId){
            case 1:
                double resultado;
                resultado = useRasengan(skillUser, targetNinja, skillType, skillElement);//dano da habilidade
                if(skillUser.getChakra() > 50){
                    skillUser.setChakra(skillUser.getChakra()-50);
                    targetNinja.receiveSkill(resultado);
                    System.out.printf("Voce causou %.0f de dano! %n", resultado);
                }else{
                    System.out.println("Chakra insuficiente!");
                }
                break;
        }
    }

    //formulas das habilidades:
    public double useRasengan(Ninja skillUser, Ninja ninjaTarget, Type skillType, Element skillElement) {//função de dano da habilidade Rasengan
        double result;
        if(skillUser.getStrength()>ninjaTarget.getDefense()){
            result = skillUser.getStrength()-ninjaTarget.getDefense();
            return result;
        }else{
            return 0;
        }

    }















    //função de informações:


    public void infoHabilidade(){

        System.out.println("----------------Habilidade----------------");
        System.out.println("Nome: " + this.getSkillName());
        System.out.println("Tipo: " + this.getSkillType().getTypeName());
        System.out.println("Elemento: "+ this.getSkillElement().getElementName());
        System.out.println("ID: "+ this.getSkillId());

    }



}*/
