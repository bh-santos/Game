/*
package com.nnw.game.entities.ninja;


import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.nnw.game.entities.element.Element;
import com.nnw.game.entities.map.Mapa;
import com.nnw.game.entities.player.Player;
import com.nnw.game.entities.rankNinja.RankNinja;
import com.nnw.game.entities.skill.Skill;
import lombok.Data;

import java.util.Scanner;
@Data
public class Ninja implements NinjaInterface{

    private String ninjaName;
    private double health;
    private double chakra;
    private double strength;
    private double defense;
    private RankNinja rank;
    private Skill skillOne, skillTwo, skillThree, skillFour;
    private Element element1;
    private Element element2;
    private int range;
    private boolean isAlive;
    private double damageTaken;
    private boolean isSelected;
    private boolean isSelectedPartially;
    private boolean isSelectedPermanently;
    private Image NINJA_ICON;
    private int ninjaId;
    private static int nextNinja = 1;

    public Ninja(String ninjaName, double health, double chakra, double strength, double defense, RankNinja rank, Skill skillOne,
                 Skill skillTwo, Skill skillThree, Skill skillFour, Element element1, Element element2, Image NINJA_ICON) {
        this.ninjaName = ninjaName;
        this.health = health;
        this.chakra = chakra;
        this.strength = strength;
        this.defense = defense;
        this.rank = rank;
        this.skillOne = skillOne;
        this.skillTwo = skillTwo;
        this.skillThree = skillThree;
        this.skillFour = skillFour;
        this.element1 = element1;
        this.element2 = element2;
        switch (getRank().getRankName()) {
            case "Kage":
            case "Anbu":
            case "Sannin":
                this.range = 3;
                break;
            case "Jounin":
            case "Chunnin":
                this.range = 2;
                break;
            case "Genin":
            case "Estudante Ninja":
                this.range = 1;
                break;
        }
        this.isAlive = true;
        isSelected = false;
        isSelectedPermanently = false;
        isSelectedPartially = false;
        this.NINJA_ICON = NINJA_ICON;
        this.ninjaId = nextNinja++;


    }

    // Métodos

    @Override
    public void basicAttack(Ninja target) {
        // Implemente sua lógica de ataque básico aqui
    }

    @Override
    public void useSkillOne(Ninja target) {
        if (target.getHealth() > 0 && this.isAlive && target.isAlive()) {
            skillOne.useSkill(skillOne.getSkillName(), this, target, skillOne.getSkillType(), skillOne.getSkillElement());
        } else {
            System.out.println("Alvo já está morto!");
        }
    }

    @Override
    public void useSkillTwo(Ninja target) {
        // Implemente a lógica de uso da habilidade 2 aqui
    }

    @Override
    public void useSkillThree(Ninja target) {
        // Implemente a lógica de uso da habilidade 3 aqui
    }

    @Override
    public void useSkillFour(Ninja target) {
        // Implemente a lógica de uso da habilidade 4 aqui
    }

    @Override
    public void receiveSkill(double damageTaken) {
        if (damageTaken > this.getHealth()) {
            System.out.println("Você recebeu " + damageTaken + " de dano e morreu!");
            this.setHealth(0);
            this.setAlive(false);
        } else {
            this.setHealth(getHealth() - damageTaken);
        }
    }

    @Override
    public void infoNinja() {
        System.out.println("------------------Ninja-------------------");
        System.out.println("Nome: " + this.getNinjaName());
        System.out.println("HP: " + this.getHealth());
        System.out.println("Chakra: " + this.getChakra());
        System.out.println("Força: " + this.getStrength());
        System.out.println("Defesa: " + this.getDefense());
        System.out.println("Rank: " + this.getRank().getRankName());

        if (this.getSkillOne() == null) {
            System.out.println("Habilidade 1: Sem Habilidade! ");
        } else {
            System.out.println("Habilidade 1: " + this.getSkillOne().getSkillName());
        }

        if (this.getSkillTwo() == null) {
            System.out.println("Habilidade 2: Sem Habilidade! ");
        } else {
            System.out.println("Habilidade 2: " + this.getSkillTwo().getSkillName());
        }

        if (this.getSkillThree() == null) {
            System.out.println("Habilidade 3: Sem Habilidade! ");
        } else {
            System.out.println("Habilidade 3: " + this.getSkillThree().getSkillName());
        }

        if (this.getSkillFour() == null) {
            System.out.println("Habilidade 4: Sem Habilidade! ");
        } else {
            System.out.println("Habilidade 4: " + this.getSkillFour().getSkillName());
        }

        if (this.getElement1() == null) {
            System.out.println("Elemento 1: Sem elemento! ");
        } else {
            System.out.println("Elemento 1: " + this.getElement1().getElementName());
        }

        if (this.getElement2() == null) {
            System.out.println("Elemento 2: Sem elemento! ");
        } else {
            System.out.println("Elemento 2: " + this.getElement2().getElementName());
        }
        System.out.println("Alcance : " + this.getRange());
        System.out.printf("Status: %s%n", this.isAlive() ? "Vivo" : "Morto");
        System.out.println("------------------------------------------");
    }

    @Override
    public void useSkill(int skillSelect, Ninja targetNinja) {
        switch (skillSelect) {
            case 1:
                basicAttack(targetNinja);
                break;
            case 2:
                useSkillOne(targetNinja);
                break;
            case 3:
                useSkillTwo(targetNinja);
                break;
            case 4:
                useSkillThree(targetNinja);
                break;
            case 5:
                useSkillFour(targetNinja);
                break;
        }
    }

    public void moverNinja(Player player, Mapa mapa, int idPosicaoArray, int idSentido) {
        mapa.moverNoMapa(player, idPosicaoArray, idSentido);
    }

    public int selectSkill() {
        Scanner scan = new Scanner(System.in);
        int habilidadeSelecionada;
        System.out.println("Selecione uma habilidade: ");

        if (this.getSkillOne() == null) {
            System.out.println("Habilidade 1: Sem Habilidade! ");
        } else {
            System.out.println("Habilidade 1: " + this.getSkillOne().getSkillName());
        }

        if (this.getSkillTwo() == null) {
            System.out.println("Habilidade 2: Sem Habilidade! ");
        } else {
            System.out.println("Habilidade 2: " + this.getSkillTwo().getSkillName());
        }

        if (this.getSkillThree() == null) {
            System.out.println("Habilidade 3: Sem Habilidade! ");
        } else {
            System.out.println("Habilidade 3: " + this.getSkillThree().getSkillName());
        }

        if (this.getSkillFour() == null) {
            System.out.println("Habilidade 4: Sem Habilidade! ");
        } else {
            System.out.println("Habilidade 4: " + this.getSkillFour().getSkillName());
        }
        habilidadeSelecionada = scan.nextInt();

        while (habilidadeSelecionada < 0 || habilidadeSelecionada > 4) {
            System.out.println("Valor inválido. Digite novamente.");
            habilidadeSelecionada = scan.nextInt();
        }

        switch (habilidadeSelecionada) {
            case 1:
                if (getSkillOne() == null) {
                    System.out.println("Sem habilidade");
                    System.out.println("Selecione novamente");
                    selectSkill();
                }
                break;
            case 2:
                if (getSkillTwo() == null) {
                    System.out.println("Sem habilidade");
                    System.out.println("Selecione novamente");
                    selectSkill();
                }
                break;
            case 3:
                if (getSkillThree() == null) {
                    System.out.println("Sem habilidade");
                    System.out.println("Selecione novamente");
                    selectSkill();
                }
                break;
            case 4:
                if (getSkillFour() == null) {
                    System.out.println("Sem habilidade");
                    System.out.println("Selecione novamente");
                    selectSkill();
                }
                break;
        }
        return habilidadeSelecionada;
    }
}
*/
