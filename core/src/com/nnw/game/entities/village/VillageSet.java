/*
package com.nnw.game.entities.village;

import com.brendondias.game.launcher.MainMenu;
import com.brendondias.game.util.AssetNames;
import com.brendondias.game.util.Effects;
import javafx.scene.image.ImageView;
import lombok.Data;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGLForKtKt.texture;

@Data
public class VillageSet {
    private final ArrayList<Village> villageSet = new ArrayList<>();
    private Village villageSelected;
    public VillageSet(){
        var leaf = new Village("Konohagakure no Sato", texture(AssetNames.Textures.LEAF_VILLAGE));
        var sand = new Village("Sunagakure no Sato", texture(AssetNames.Textures.SAND_VILLAGE));
        var mist = new Village("Kirigakure no Sato", texture(AssetNames.Textures.MIST_VILLAGE));
        var stone = new Village("Iwagakure no Sato", texture(AssetNames.Textures.STONE_VILLAGE));
        var cloud = new Village("Kumogakure no Sato", texture(AssetNames.Textures.CLOUD_VILLAGE));
        var undefined1 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        var undefined2 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        var undefined3 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        var undefined4 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        var undefined5 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        var undefined6 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        var undefined7 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        var undefined8 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        var undefined9 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        var undefined10 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        var undefined11 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        var undefined12 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        var undefined13 = new Village("Vila Indefinida ainda!", texture(AssetNames.Textures.UNDEFINED));
        villageSet.add(leaf);
        villageSet.add(sand);
        villageSet.add(mist);
        villageSet.add(stone);
        villageSet.add(cloud);
        villageSet.add(undefined1);
        villageSet.add(undefined2);
        villageSet.add(undefined3);
        villageSet.add(undefined4);
        villageSet.add(undefined5);
        villageSet.add(undefined6);
        villageSet.add(undefined7);
        villageSet.add(undefined8);
        villageSet.add(undefined9);
        villageSet.add(undefined10);
        villageSet.add(undefined11);
        villageSet.add(undefined12);
        villageSet.add(undefined13);

        hoverIconEntered();
        hoverIconExited();
        hoverIconClicked();
        changeDimensionMin();


    }

    public void hoverIconEntered(){
        for (Village village : villageSet) {
            if(village.isSelectedPermanently()){
                System.out.println("Aldeia Selecionada");
            }else {
                    village.getVILLAGE_ICON().setOnMouseEntered(e -> {
                        if (!village.isSelected() && !village.isSelectedPermanently()) {
                            applyEffectOnMouseEntered(village.getVILLAGE_ICON());
                        }
                    });
                }

        }

    }
    public void hoverIconExited(){
        for (Village village : villageSet) {
            if(village.isSelectedPermanently()){
                System.out.println("Selected");
            }else {
                village.getVILLAGE_ICON().setOnMouseExited(e -> {
                    if ((!village.isSelected()) && !village.isSelectedPermanently()) {
                        replaceEffectOnMouseExited(village.getVILLAGE_ICON());
                    }
                });
            }
        }

    }

    public void hoverIconClicked(){
        for(Village village: villageSet) {
            if (village.isSelectedPermanently()) {
                System.out.println("Já Selecionado");
            } else {
                village.getVILLAGE_ICON().setOnMouseClicked(e -> {
                    if (!village.isSelected() && !village.isSelectedPermanently()) {

                        MainMenu.changeVillageIcon(village.getVILLAGE_ICON().getImage());
                        MainMenu.changeNameVillage(village.getVillageName());
                        MainMenu.changeInfoVillage("+Ataque:","+Defesa","+HP");
                        MainMenu.changeInfoVillageValue(village.getVillageAtk(),village.getVillageDefense(),village.getVillageHp());

                        removeEffectAllVillagesIcons();
                        selectVillage(village.getVillageId());
                        applyEffectOnMouseClicked(village.getVILLAGE_ICON());
                    }
                });
            }
        }
    }

    public void applyEffectOnMouseEntered(ImageView icon){
        for(Village village: villageSet) {
            switch (village.getVillageId()) {
                case 1:
                    icon.setEffect(Effects.ShadowEffects.LEAF_SHADOW);
                    break;
                case 2:
                    icon.setEffect(Effects.ShadowEffects.SAND_SHADOW);
                    break;
                case 3:
                    icon.setEffect(Effects.ShadowEffects.MIST_SHADOW);
                    break;
                case 4:
                    icon.setEffect(Effects.ShadowEffects.STONE_SHADOW);
                    break;
                case 5:
                    icon.setEffect(Effects.ShadowEffects.CLOUD_SHADOW);
                    break;
                default:
                    icon.setEffect(Effects.ShadowEffects.WHITE_SHADOW);
                    break;
            }
        }
    }

    public void applyEffectOnMouseClicked(ImageView icon){
        icon.setEffect(Effects.ColorEffects.BLACK_WHITE);
    }
    public void replaceEffectOnMouseExited(ImageView icon){
        icon.setEffect(null);
    }
    public void selectVillage(int villageId){
        for (Village village: villageSet){
            if(village.getVillageId() == villageId){
                village.setSelected(true);
                setVillageSelected(village);
            }else {
                village.setSelected(false);
            }
        }
    }


    public void removeEffectAllVillagesIcons(){
        for (Village village : villageSet) {
            if (!village.isSelectedPermanently()) {
                village.getVILLAGE_ICON().setEffect(null);
            }
        }
    }


    public void infoVillage() {
        for (var village : villageSet) {
            System.out.println("ID: " + village.getVillageId());
            System.out.println("NOME: " + village.getVillageName());
            System.out.println("SELECIONADA :" + village.isSelected());
        }
    }

    public void removeSelected(){
        for(Village village: villageSet){
            village.setSelected(false);
            village.setSelectedPermanently(false);
            village.getVILLAGE_ICON().setEffect(null);
        }
    }


    public void changeDimensionMin(){
        for(Village village: villageSet) {
            village.getVILLAGE_ICON().setFitWidth(80);
            village.getVILLAGE_ICON().setFitHeight(80);
        }
    }

    public void changeDimensionMax(){
        for(Village village: villageSet) {
            village.getVILLAGE_ICON().setFitWidth(80);
            village.getVILLAGE_ICON().setFitHeight(80);
        }
    }
}
*/
