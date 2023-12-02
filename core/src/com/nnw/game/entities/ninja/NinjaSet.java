/*
package com.nnw.game.entities.ninja;

import com.almasb.fxgl.dsl.FXGL;
import com.brendondias.game.launcher.MainMenu;
import com.brendondias.game.util.AssetNames;
import com.brendondias.game.util.Effects;
import com.nnw.game.entities.element.ElementSet;
import com.nnw.game.entities.rankNinja.RankNinjaSet;
import com.nnw.game.entities.skill.SkillSet;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import lombok.Data;
import java.util.ArrayList;
import java.util.LinkedList;

import static com.almasb.fxgl.dsl.FXGLForKtKt.texture;
import static com.brendondias.game.launcher.MainMenu.*;
import static com.brendondias.game.util.AssetNames.Textures.UNDEFINED;
import static com.brendondias.game.util.Effects.ShadowEffects.ORANGE_SHADOW;


@Data
public class NinjaSet {

    private ArrayList<Ninja> ninjaSet = new ArrayList<>();
    private Ninja ninjaSelected;
    private LinkedList<Ninja> ninjasSelectedList = new LinkedList<>();
    private long lastClickTime = 0;
    private static final long DOUBLE_CLICK_TIME_DELTA = 300;
    private long lastClickTime2 = 0;
    private static final long DOUBLE_CLICK_TIME_DELTA2 = 300;
    private RankNinjaSet rankNinja = new RankNinjaSet();
    private ElementSet elements = new ElementSet();
    private SkillSet skills = new SkillSet();

    public NinjaSet() {


        final Ninja CLASSIC_NARUTO = new Ninja("Uzumaki Naruto - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), skills.getSkillSet().get(0), skills.getSkillSet().get(0), skills.getSkillSet().get(0), elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_NARUTO));
        final Ninja CLASSIC_SASUKE = new Ninja("Uchiha Sasuke - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(1), skills.getSkillSet().get(1), skills.getSkillSet().get(1), skills.getSkillSet().get(1), elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_SASUKE));
        final Ninja CLASSIC_SAKURA = new Ninja("Haruno Sakura - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_SAKURA));
        final Ninja CLASSIC_KAKASHI = new Ninja("Hatake Kakashi - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_KAKASHI));
        final Ninja CLASSIC_SHIKAMARU = new Ninja("Nara Shikamaru - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_SHIKAMARU));
        final Ninja CLASSIC_INO = new Ninja("Yamanaka Ino - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_INO));
        final Ninja CLASSIC_CHOJI = new Ninja("Akimichi Choji - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_CHOJI));
        final Ninja CLASSIC_ASUMA = new Ninja("Sarutobi Asuma - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_ASUMA));
        final Ninja CLASSIC_LEE = new Ninja("Rock Lee - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_LEE));
        final Ninja CLASSIC_NEJI = new Ninja("Hyuuga Neji - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_NEJI));
        final Ninja CLASSIC_TENTEN = new Ninja("Mitsashi Tenten - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_TENTEN));
        final Ninja CLASSIC_GAI = new Ninja("Maito Gai - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_GAI));
        final Ninja CLASSIC_HINATA = new Ninja("Hyuuga Hinata - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_HINATA));
        final Ninja CLASSIC_KIBA = new Ninja("Inuzuka Kiba - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_KIBA));
        final Ninja CLASSIC_SHINO = new Ninja("Aburame Shino - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_SHINO));
        final Ninja CLASSIC_KURENAI = new Ninja("Yuuhi Kurenai - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_KURENAI));
        final Ninja CLASSIC_GAARA = new Ninja("Sabaku no Gaara - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_GAARA));
        final Ninja CLASSIC_TEMARI = new Ninja("Sabaku no Temari - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_TEMARI));
        final Ninja CLASSIC_KANKURO = new Ninja("Sabaku no Kankuro - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_KANKURO));
        final Ninja CLASSIC_BAKI = new Ninja("Sabaku no Baki - Classic", 100, 100, 25, 20, rankNinja.getRankNinjaSet().get(0), skills.getSkillSet().get(0), null, null, null, elements.getElements().get(3), elements.getElements().get(2), texture(AssetNames.Textures.CLASSIC_BAKI));

        ninjaSet.add(CLASSIC_NARUTO);
        ninjaSet.add(CLASSIC_SASUKE);
        ninjaSet.add(CLASSIC_SAKURA);
        ninjaSet.add(CLASSIC_KAKASHI);
        ninjaSet.add(CLASSIC_SHIKAMARU);
        ninjaSet.add(CLASSIC_INO);
        ninjaSet.add(CLASSIC_CHOJI);
        ninjaSet.add(CLASSIC_ASUMA);
        ninjaSet.add(CLASSIC_LEE);
        ninjaSet.add(CLASSIC_NEJI);
        ninjaSet.add(CLASSIC_TENTEN);
        ninjaSet.add(CLASSIC_GAI);
        ninjaSet.add(CLASSIC_HINATA);
        ninjaSet.add(CLASSIC_KIBA);
        ninjaSet.add(CLASSIC_SHINO);
        ninjaSet.add(CLASSIC_KURENAI);
        ninjaSet.add(CLASSIC_GAARA);
        ninjaSet.add(CLASSIC_TEMARI);
        ninjaSet.add(CLASSIC_KANKURO);
        ninjaSet.add(CLASSIC_BAKI);


        //inicializar a selectedList
        for(int i = 0; i<5 ; i++){
            ninjasSelectedList.add(null);
        }




        onMouseEnteredInNinjaIcon();
        onMouseClickedInNinjaIcon();
        onMouseExitedInNinjaIcon();
        changeDimensionMin();
    }

    public void clearNinjaInfo(){
        MainMenu.getCurrentNinjaIcon().setImage(texture(UNDEFINED).getImage());
        MainMenu.getCurrentNinjaName().setText("Desconhecido");
        MainMenu.changeValueNinjaInfo(
                0,
                0,
                0,
                0,
                "Desconhecido",
                null,
                null,
                null,
                null,
                "Desconhecido",
                "Desconhecido",
                0);
    }

    public void clearAllLists(){

        ninjasSelectedList.replaceAll(ignored -> null);

        for (Ninja ninja : ninjaSet) {
            ninja.setSelected(false);
            ninja.setSelectedPartially(false);
            ninja.setSelectedPermanently(false);
            ninja.getNINJA_ICON().setEffect(null);
        }

        MainMenu.getCharSelectNinjaBar().replaceAll(ignored-> null);
        MainMenu.getCharSelectImageBar().replaceAll(ignored -> texture(UNDEFINED));
        MainMenu.getCurrentSkillList().replaceAll(ignored-> null);
        MainMenu.getCurrentSkillIcons().replaceAll(ignored -> texture(UNDEFINED));
        clearNinjaInfo();



    }
    public boolean verifySelectedNinjas(){
        boolean result = true;
        for (Ninja ninja : ninjasSelectedList) {
            if (ninja == null) {
                result = false;
            }
        }
        return result;
    }
    public void onMouseEnteredInNinjaIcon() {
        for (Ninja ninja : ninjaSet) {
            if (ninja.isSelectedPermanently()) {
                System.out.println("Aldeia Selecionada");
            } else {
                ninja.getNINJA_ICON().setOnMouseEntered(e -> {
                    if (!ninja.isSelected() && !ninja.isSelectedPermanently() && !ninja.isSelectedPartially()){
                        applyEffectOnMouseEntered(ninja.getNINJA_ICON());
                    }
                });
            }

        }

    }

    public void onMouseExitedInNinjaIcon() {
        for (Ninja ninja : ninjaSet) {
            if (ninja.isSelectedPermanently()) {
                System.out.println("Selected");
            } else {
                ninja.getNINJA_ICON().setOnMouseExited(e -> {
                    if ((!ninja.isSelected()) && !ninja.isSelectedPermanently() && !ninja.isSelectedPartially()) {
                        replaceEffectOnMouseExited(ninja.getNINJA_ICON());
                    }
                });
            }
        }

    }
    public void onMouseClickedInNinjaIcon() {
        for (Ninja ninja : ninjaSet) {
            if (ninja.isSelectedPermanently()) {
                System.out.println("Já Selecionado");
            } else {
                ninja.getNINJA_ICON().setOnMouseClicked(e -> {
                    if (!ninja.isSelected() && !ninja.isSelectedPermanently()) {
                        changeAllInfoNinja(ninja);

                        doubleClickOnIconInCharBar(ninja);

                    }
                });
            }
        }

    }
    public void changeAllInfoNinja(Ninja ninja){
        MainMenu.changeNinjaIcon(ninja.getNINJA_ICON().getImage());
        MainMenu.changeNinjaName(ninja.getNinjaName());
        MainMenu.changeNinjaInfo(
                "Vida Máxima:",
                "Chakra:",
                "Ataque:",
                "Defesa:",
                "Rank Ninja:",
                "Skills:",
                "Elemento 1:",
                "Elemento 2:",
                "Alcance:"
        );



        MainMenu.changeValueNinjaInfo(
                (int)ninja.getHealth(),
                (int)ninja.getChakra(),
                (int)ninja.getStrength(),
                (int)ninja.getDefense(),
                ninja.getRank().getRankName(),
                ninja.getSkillOne(),
                ninja.getSkillTwo(),
                ninja.getSkillThree(),
                ninja.getSkillFour(),
                ninja.getElement1().getElementName(),
                ninja.getElement2().getElementName(),
                ninja.getRange());
    }
    public int findEmptyIndexInSelectedList(){
        for (int i = 0; i < ninjasSelectedList.size(); i++) {
            if (ninjasSelectedList.get(i) == null) {
                return i;
            }
        }
        return -1;
    }
    public void doubleClickOnIconInCharBar(Ninja ninja){
        //clique duplo
        long clickTime = System.currentTimeMillis();
        if (clickTime - lastClickTime <= DOUBLE_CLICK_TIME_DELTA) {
            //removeEffectAllVillagesIcons();
            if(!ninja.isSelectedPartially() && ninjasSelectedList.size()==5) {//verifica se o ninja está selecionado parcialmente e se a selected list tem tamanho5

                int emptyIndexImageBar = findEmptyIndexCharSelectImageBar();//verifica na lista generica a primeira posição vazia(marcado com o ícone undefined) da esquerda para a direita
                int emptyIndexSelectedList = findEmptyIndexInSelectedList();//verifica na selectedolist a primeira posição vazia(null) da esquerda para a direita

                if (emptyIndexImageBar != -1) {//se houver posição vazia na undefinedlist ele realiza a ação abaixo.

                    if(emptyIndexImageBar == emptyIndexSelectedList) {//se as posições vazias em ambas as listas forem as mesmas ele realiza a ação abaixo.

                        ninja.setSelectedPartially(true);//marca o ninja como parcialmente selecionado

                        ninjasSelectedList.set(emptyIndexImageBar, ninja);//seta a posição vazia na selectedList como o ninja passado
                        MainMenu.getCharSelectNinjaBar().set(emptyIndexImageBar,ninja);
                        applyEffectOnMouseClicked(ninja.getNINJA_ICON());//aplica a ofuscação
                        MainMenu.getCharSelectImageBar().get(emptyIndexImageBar).setImage(MainMenu.getCharSelectNinjaBar().get(emptyIndexImageBar).getNINJA_ICON().getImage());//adiciona a imagem do ninja na undefined lista na primeira posição vazia encontrada da esquerda para a direita.
                        removeIconOfSelectNinjaBar();

                    }
                }

            }else if(ninja.isSelectedPartially()) {
                changeToUndefined(ninja);    // Atualize a ninjasSelectedList e UndefinedList
                replaceEffectOnMouseExited(ninja.getNINJA_ICON());
            }
        }
        lastClickTime = clickTime;
    }
    private void changeToUndefined(Ninja ninja){
        ninja.setSelectedPartially(false);
        MainMenu.getCharSelectNinjaBar().set(ninjasSelectedList.indexOf(ninja),null);

        if(MainMenu.getCharSelectNinjaBar().get(ninjasSelectedList.indexOf(ninja)) == null){
            MainMenu.getCharSelectImageBar().get(ninjasSelectedList.indexOf(ninja)).setImage(texture(UNDEFINED).getImage());
        }
        //seta como undefined na undefinedList
        ninjasSelectedList.set(ninjasSelectedList.indexOf(ninja),null);//atualiza a selectedList passando o indice do ninja que está lá atualmente e setando como null.
        replaceEffectOnMouseExited(ninja.getNINJA_ICON());
        System.out.println("foi");
    }
    private void removeIconOfSelectNinjaBar(){
        var icons = getCharSelectImageBar();
        var ninjas = getCharSelectNinjaBar();

            for (ImageView ninjaIcon : icons) {
                ninjaIcon.setOnMouseClicked(e -> {
                    long clickTime = System.currentTimeMillis();
                    if (clickTime - lastClickTime2 <= DOUBLE_CLICK_TIME_DELTA2) {
                        if (!ninjaIcon.equals(new ImageView(texture(UNDEFINED).getImage()))) {
                           if(verifyNinjaAtPosition(ninjas,icons.indexOf(ninjaIcon)) != -1){
                                ninjas.get(icons.indexOf(ninjaIcon)).setSelectedPartially(false);
                                replaceEffectOnMouseExited(ninjas.get(icons.indexOf(ninjaIcon)).getNINJA_ICON());
                                ninjas.set(icons.indexOf(ninjaIcon),null);

                                ninjasSelectedList.set(icons.indexOf(ninjaIcon),null);
                                ninjaIcon.setImage(texture(UNDEFINED).getImage());

                            }
                        }
                    }
                    lastClickTime2 = clickTime;
                });
            }
    }
    public int verifyNinjaAtPosition(LinkedList<Ninja> ninjas,int index){
        if(ninjas.get(index) != null){
            return 1;
        }else {
            return -1;
        }
    }
    public void applyEffectOnMouseEntered(ImageView icon){
        for(Ninja ninja: ninjaSet) {
            switch (ninja.getNinjaId()) {
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
        icon.setEffect(Effects.ColorEffects.LOW_SATURATION);
    }
    public void replaceEffectOnMouseExited(ImageView icon){
        icon.setEffect(null);
    }
    public void changeDimensionMin(){
        for(Ninja ninja: ninjaSet) {
            ninja.getNINJA_ICON().setFitWidth(80);
            ninja.getNINJA_ICON().setFitHeight(80);
        }
    }
    public void changeDimensionMax(){
        for(Ninja ninja: ninjaSet) {
            ninja.getNINJA_ICON().setFitWidth(80);
            ninja.getNINJA_ICON().setFitHeight(80);
        }
    }

//Métodos das Skills.

    public void onMouseActionsInSkillIcons(LinkedList<ImageView> list){
        Pane infoBox = new Pane();
        HBox organizer = new HBox();
        ImageView skillInfoIcon = new ImageView(texture(UNDEFINED).getImage());
        Pane bar = new Pane();
        VBox textPane = new VBox();
        Text skillInfo = FXGL.getUIFactoryService().newText("Desconhecido.",Color.BLACK,13.0);
        StackPane iconImage = new StackPane();

        organizer.setPrefSize(450,100);
        infoBox.setPrefSize(450,100);
        bar.setPrefSize(0,100);

        skillInfoIcon.setFitWidth(80);
        skillInfoIcon.setFitHeight(80);
        Rectangle clip = new Rectangle(80, 80);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        skillInfoIcon.setClip(clip);


        textPane.setPrefSize(380,100);
        textPane.setAlignment(Pos.CENTER);

        skillInfo.setWrappingWidth(340);
        skillInfo.setTextAlignment(TextAlignment.JUSTIFY);

        HBox.setMargin(iconImage,new Insets(10,0,10,10));

        textPane.getChildren().add(skillInfo);
        iconImage.getChildren().add(skillInfoIcon);
        organizer.getChildren().addAll(iconImage,bar,textPane);
        infoBox.getChildren().add(organizer);

        infoBox.setStyle("-fx-background-radius: 5 5 5 5; -fx-background-color: orange;");

        for(ImageView icon:list){
            icon.setOnMouseEntered(e->{
                icon.setOnMouseMoved(z->{
                    infoBox.setTranslateX(z.getSceneX()-infoBox.getWidth()-20);
                    infoBox.setTranslateY(z.getSceneY()+5);
                });
                if(MainMenu.getCurrentSkillList().get(list.indexOf(icon)) != null && !MainMenu.getCurrentSkillList().isEmpty()) {
                    skillInfo.setText(MainMenu.getCurrentSkillList().get(list.indexOf(icon)).getDescription());
                    skillInfoIcon.setImage(icon.getImage());
                    applyEffectsInSkillIcons(icon);
                    FXGL.getSceneService().getCurrentScene().getContentRoot().getChildren().add(infoBox);
                }
            });

            icon.setOnMouseExited(e->{
                removeEffectsInSkillIcons(icon);
                FXGL.getSceneService().getCurrentScene().getContentRoot().getChildren().remove(infoBox);
            });

            icon.setOnMouseClicked(e->{
                System.out.println("certo");
            });
        }
    }

    public void applyEffectsInSkillIcons(ImageView icon){
       icon.setEffect(ORANGE_SHADOW);
    }

    public void removeEffectsInSkillIcons(ImageView icon){
        icon.setEffect(null);
    }
}

*/
