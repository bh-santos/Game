/*
package com.nnw.game.entities.map;

import java.util.ArrayList;
import java.util.Scanner;

public class Mapa {

    private String[][] campos = new String[7][11];// 7 linhas e 11 colunas
    private ArrayList<Ninja> ninjasEmCombateP1 = new ArrayList<>();
    private int[] coordenadaLinhaP1 = new int[5];
    private int[] coordenadaColunaP1 = new int[5];

    private ArrayList<Ninja> ninjasEmCombateP2 = new ArrayList<>();

    private int[] coordenadaLinhaP2 = new int[5];
    private int[] coordenadaColunaP2 = new int[5];

    private final boolean[] verificarSelecionadoP1 = new boolean[5];

    private final boolean[] verificarSelecionadoP2 = new boolean[5];

    private ArrayList<Village> villageEscolhidaP1 = new ArrayList<>();
    private int[] coordenadaLinhaAldeiaP1 = new int[1];
    private int[] coordenadaColunaAldeiaP1 = new int[1];

    private ArrayList<Village> villageEscolhidaP2 = new ArrayList<>();
    private int[] coordenadaLinhaAldeiaP2 = new int[1];
    private int[] coordenadaColunaAldeiaP2 = new int[1];


    public Mapa() {

        for (int c = 0; c < 11; c++) {
            for (int l = 0; l < 7; l++) {
                campos[l][c] = "          ";
            }
        }


    }

    //Getters and Setters:
    public String[][] getCampos() {
        return campos;
    }


    public void setCampos(String[][] campos) {
        this.campos = campos;
    }

    public ArrayList<Ninja> getNinjasEmCombateP1() {
        return ninjasEmCombateP1;
    }

    public void setNinjasEmCombateP1(ArrayList<Ninja> ninjasEmCombateP1) {
        this.ninjasEmCombateP1 = ninjasEmCombateP1;
    }

    public int[] getCoordenadaLinhaP1() {
        return coordenadaLinhaP1;
    }

    public void setCoordenadaLinhaP1(int[] coordenadaLinhaP1) {
        this.coordenadaLinhaP1 = coordenadaLinhaP1;
    }

    public int[] getCoordenadaColunaP1() {
        return coordenadaColunaP1;
    }

    public void setCoordenadaColunaP1(int[] coordenadaColunaP1) {
        this.coordenadaColunaP1 = coordenadaColunaP1;
    }

    public ArrayList<Ninja> getNinjasEmCombateP2() {
        return ninjasEmCombateP2;
    }

    public void setNinjasEmCombateP2(ArrayList<Ninja> ninjasEmCombateP2) {
        this.ninjasEmCombateP2 = ninjasEmCombateP2;
    }

    public int[] getCoordenadaLinhaP2() {
        return coordenadaLinhaP2;
    }

    public void setCoordenadaLinhaP2(int[] coordenadaLinhaP2) {
        this.coordenadaLinhaP2 = coordenadaLinhaP2;
    }

    public int[] getCoordenadaColunaP2() {
        return coordenadaColunaP2;
    }

    public void setCoordenadaColunaP2(int[] coordenadaColunaP2) {
        this.coordenadaColunaP2 = coordenadaColunaP2;
    }

    public boolean[] getVerificarSelecionadoP1() {
        return verificarSelecionadoP1;
    }

    public void setVerificarSelecionadoP1(boolean verificarSelecionadoP1, int idArray) {
        this.verificarSelecionadoP1[idArray] = verificarSelecionadoP1;
    }

    public boolean[] getVerificarSelecionadoP2() {
        return verificarSelecionadoP2;
    }

    public void setVerificarSelecionadoP2(boolean verificarSelecionadoP2, int idArray) {
        this.verificarSelecionadoP2[idArray] = verificarSelecionadoP2;
    }


    public int[] getCoordenadaColunaAldeiaP1() {
        return coordenadaColunaAldeiaP1;
    }

    public void setCoordenadaColunaAldeiaP1(int[] coordenadaColunaAldeiaP1) {
        this.coordenadaColunaAldeiaP1 = coordenadaColunaAldeiaP1;
    }

    public int[] getCoordenadaLinhaAldeiaP1() {
        return coordenadaLinhaAldeiaP1;
    }

    public void setCoordenadaLinhaAldeiaP1(int[] coordenadaLinhaAldeiaP1) {
        this.coordenadaLinhaAldeiaP1 = coordenadaLinhaAldeiaP1;
    }

    public ArrayList<Village> getAldeiaEscolhidaP1() {
        return villageEscolhidaP1;
    }

    public void setAldeiaEscolhidaP1(ArrayList<Village> villageEscolhidaP1) {
        this.villageEscolhidaP1 = villageEscolhidaP1;
    }

    public ArrayList<Village> getAldeiaEscolhidaP2() {
        return villageEscolhidaP2;
    }

    public void setAldeiaEscolhidaP2(ArrayList<Village> villageEscolhidaP2) {
        this.villageEscolhidaP2 = villageEscolhidaP2;
    }

    public int[] getCoordenadaLinhaAldeiaP2() {
        return coordenadaLinhaAldeiaP2;
    }

    public void setCoordenadaLinhaAldeiaP2(int[] coordenadaLinhaAldeiaP2) {
        this.coordenadaLinhaAldeiaP2 = coordenadaLinhaAldeiaP2;
    }

    public int[] getCoordenadaColunaAldeiaP2() {
        return coordenadaColunaAldeiaP2;
    }

    public void setCoordenadaColunaAldeiaP2(int[] coordenadaColunaAldeiaP2) {
        this.coordenadaColunaAldeiaP2 = coordenadaColunaAldeiaP2;
    }

    //construtor:

    public void mostrarCampos() {
        System.out.println(
                "           " + "0" +
                        "            " + "1" +
                        "            " + "2" +
                        "            " + "3" +
                        "            " + "4" +
                        "            " + "5" +
                        "            " + "6" +
                        "            " + "7" +
                        "            " + "8" +
                        "            " + "9" +
                        "           " + " 10" + "  "
        );

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "0    | " + campos[0][0] +
                        " | " + campos[0][1] +
                        " | " + campos[0][2] +
                        " | " + campos[0][3] +
                        " | " + campos[0][4] +
                        " | " + campos[0][5] +
                        " | " + campos[0][6] +
                        " | " + campos[0][7] +
                        " | " + campos[0][8] +
                        " | " + campos[0][9] +
                        " | " + campos[0][10] + " |"
        );

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "1    | " + campos[1][0] +
                        " | " + campos[1][1] +
                        " | " + campos[1][2] +
                        " | " + campos[1][3] +
                        " | " + campos[1][4] +
                        " | " + campos[1][5] +
                        " | " + campos[1][6] +
                        " | " + campos[1][7] +
                        " | " + campos[1][8] +
                        " | " + campos[1][9] +
                        " | " + campos[1][10] + " |"
        );
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "2    | " + campos[2][0] +
                        " | " + campos[2][1] +
                        " | " + campos[2][2] +
                        " | " + campos[2][3] +
                        " | " + campos[2][4] +
                        " | " + campos[2][5] +
                        " | " + campos[2][6] +
                        " | " + campos[2][7] +
                        " | " + campos[2][8] +
                        " | " + campos[2][9] +
                        " | " + campos[2][10] + " |"
        );
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "3    | " + campos[3][0] +
                        " | " + campos[3][1] +
                        " | " + campos[3][2] +
                        " | " + campos[3][3] +
                        " | " + campos[3][4] +
                        " | " + campos[3][5] +
                        " | " + campos[3][6] +
                        " | " + campos[3][7] +
                        " | " + campos[3][8] +
                        " | " + campos[3][9] +
                        " | " + campos[3][10] + " |"
        );
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "4    | " + campos[4][0] +
                        " | " + campos[4][1] +
                        " | " + campos[4][2] +
                        " | " + campos[4][3] +
                        " | " + campos[4][4] +
                        " | " + campos[4][5] +
                        " | " + campos[4][6] +
                        " | " + campos[4][7] +
                        " | " + campos[4][8] +
                        " | " + campos[4][9] +
                        " | " + campos[4][10] + " |"
        );
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "5    | " + campos[5][0] +
                        " | " + campos[5][1] +
                        " | " + campos[5][2] +
                        " | " + campos[5][3] +
                        " | " + campos[5][4] +
                        " | " + campos[5][5] +
                        " | " + campos[5][6] +
                        " | " + campos[5][7] +
                        " | " + campos[5][8] +
                        " | " + campos[5][9] +
                        " | " + campos[5][10] + " |"
        );
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "6    | " + campos[6][0] +
                        " | " + campos[6][1] +
                        " | " + campos[6][2] +
                        " | " + campos[6][3] +
                        " | " + campos[6][4] +
                        " | " + campos[6][5] +
                        " | " + campos[6][6] +
                        " | " + campos[6][7] +
                        " | " + campos[6][8] +
                        " | " + campos[6][9] +
                        " | " + campos[6][10] + " |"
        );
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void atribuirNinjaJogador(int l, int c, Player player, int contador, Mapa mapa) {
        if (player.getPlayerId() == 1) {
            if (campos[l][c].equals("          ")) {
                String[] nome = new String[]{player.getPlayerSelectedNinjas().get(contador).getNinjaName().substring(0,4)};
                campos[l][c] = "J" + player.getPlayerId()+ "-" +nome[0]+"...";
                ninjasEmCombateP1.add(contador, player.getPlayerSelectedNinjas().get(contador));
                coordenadaLinhaP1[contador] = l;
                coordenadaColunaP1[contador] = c;
            } else {
                if (!campos[l][c].equals("          ")) {
                    int posLinha;
                    int posColuna;
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Posição ocupada");
                    System.out.println("Selecione a Linha entre 0 e 6:");
                    posLinha = scan.nextInt();
                    if (posLinha < 0 || posLinha > 6) {
                        while (posLinha < 0 || posLinha > 6) {
                            System.out.println("Linha Inválida! Digite novamente.");
                            posLinha = scan.nextInt();
                        }
                    }


                    if (player.getPlayerId() == 1) {
                        System.out.println("Selecione a Coluna entre 1 e 3:");
                        posColuna = scan.nextInt();
                        if (posColuna < 1 || posColuna > 3) {
                            while (posColuna < 1 || posColuna > 3) {
                                System.out.println("Coluna Inválida! Digite novamente.");
                                posColuna = scan.nextInt();
                            }
                        }
                    } else {
                        System.out.println("Selecione a Coluna entre 7 e 9:");
                        posColuna = scan.nextInt();
                        if (posColuna < 7 || posColuna > 9) {
                            while (posColuna < 7 || posColuna > 9) {
                                System.out.println("Coluna Inválida! Digite novamente.");
                                posColuna = scan.nextInt();
                            }
                        }
                    }
                    mapa.atribuirNinjaJogador(posLinha, posColuna, player, contador, mapa);
                    mapa.mostrarCampos();
                }
            }

        } else if (player.getPlayerId() == 2) {
            if (campos[l][c].equals("          ")) {
                String[] nome = new String[]{player.getPlayerSelectedNinjas().get(contador).getNinjaName().substring(0,4)};
                campos[l][c] = "J" + player.getPlayerId()+ "-" +nome[0]+"...";
                ninjasEmCombateP2.add(contador, player.getPlayerSelectedNinjas().get(contador));
                coordenadaLinhaP2[contador] = l;
                coordenadaColunaP2[contador] = c;
            } else {
                if (!campos[l][c].equals("          ")) {
                    int posLinha;
                    int posColuna;
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Posição ocupada");
                    System.out.println("Selecione a Linha entre 0 e 6:");
                    posLinha = scan.nextInt();
                    if (posLinha < 0 || posLinha > 6) {
                        while (posLinha < 0 || posLinha > 6) {
                            System.out.println("Linha Inválida! Digite novamente.");
                            posLinha = scan.nextInt();
                        }
                    }


                    if (player.getPlayerId() == 1) {
                        System.out.println("Selecione a Coluna entre 1 e 3:");
                        posColuna = scan.nextInt();
                        if (posColuna < 1 || posColuna > 3) {
                            while (posColuna < 1 || posColuna > 3) {
                                System.out.println("Coluna Inválida! Digite novamente.");
                                posColuna = scan.nextInt();
                            }
                        }
                    } else {
                        System.out.println("Selecione a Coluna entre 7 e 9:");
                        posColuna = scan.nextInt();
                        if (posColuna < 7 || posColuna > 9) {
                            while (posColuna < 7 || posColuna > 9) {
                                System.out.println("Coluna Inválida! Digite novamente.");
                                posColuna = scan.nextInt();
                            }
                        }
                    }
                    mapa.atribuirNinjaJogador(posLinha, posColuna, player, contador, mapa);
                    mapa.mostrarCampos();
                }
            }
        } else {
            System.out.println("Jogador invalido");
        }
    }

    public void atribuirAldeiaJogador(int l, int c, Player player){
        if (player.getPlayerId() == 1) {
            String[] nome = new String[]{player.getVillage().getVillageName().substring(0,7)};
            campos[l][c] =nome[0] + "...";
            villageEscolhidaP1.add(0, player.getVillage());
            coordenadaLinhaAldeiaP1[0] = l;
            coordenadaColunaAldeiaP1[0] = c;
        }else if(player.getPlayerId() == 2){
            String[] nome = new String[]{player.getVillage().getVillageName().substring(0,7)};
            campos[l][c] =nome[0] + "...";
            villageEscolhidaP2.add(0, player.getVillage());
            coordenadaLinhaAldeiaP2[0] = l;
            coordenadaColunaAldeiaP2[0] = c;
        }

    }
    public void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println("                                                                                                 ");
        }
    }

    public void verificarNinjasEmCombate(Player player, int id) {
        if (player.getPlayerId() == 1) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("Ninja " + (id + 1) + " - "+ninjasEmCombateP1.get(id).getNinjaName());
            System.out.printf("Ja realizou movimento: %s%n",getVerificarSelecionadoP1()[id] ? "Sim" : "Não");
            System.out.printf("Coordenadas: Linha: %d Coluna: %d%n", coordenadaLinhaP1[id], coordenadaColunaP1[id]);
        } else {
            System.out.println("-------------------------------------------------------------");
            System.out.println("Ninja " + (id + 1) + " - "+ninjasEmCombateP2.get(id).getNinjaName());
            System.out.printf("Ja realizou movimento: %s%n",getVerificarSelecionadoP2()[id] ? "Sim" : "Não");
            System.out.printf("Coordenadas: Linha: %d Coluna: %d%n", coordenadaLinhaP2[id], coordenadaColunaP2[id]);
        }
    }

    public void limparCampo(int l, int c) {
        if (!campos[l][c].equals("          ")) {
            campos[l][c] = "          ";
        }
    }

    public void moverNoMapa(Player player, int idPosicaoArray, int idSentido) {
        int cLinhaAnterior;
        int cColunaAnterior;
        int alcance;
        int cLinhaNova;
        int cColunaNova;
        int sentido;


        if (player.getPlayerId() == 1) {
            alcance = ninjasEmCombateP1.get(idPosicaoArray).getRange();
            cLinhaAnterior = coordenadaLinhaP1[idPosicaoArray];
            cColunaAnterior = coordenadaColunaP1[idPosicaoArray];
        } else {
            alcance = ninjasEmCombateP2.get(idPosicaoArray).getRange();
            cLinhaAnterior = coordenadaLinhaP2[idPosicaoArray];
            cColunaAnterior = coordenadaColunaP2[idPosicaoArray];
        }


        switch (idSentido) {
            case 1*/
/*direita*//*
:

                if (player.getPlayerId() == 1) {
                    cLinhaNova = coordenadaLinhaP1[idPosicaoArray];
                    cColunaNova = cColunaAnterior + alcance;

                } else {
                    cLinhaNova = coordenadaLinhaP2[idPosicaoArray];
                    cColunaNova = cColunaAnterior + alcance;
                }


                if (verificarSentido(cLinhaNova,cColunaNova)){
                    sentido = digitarSentido();
                    moverNoMapa(player,idPosicaoArray,sentido);
                }else {

                    limparCampo(cLinhaAnterior, cColunaAnterior);
                    marcarCampo(player, idPosicaoArray, cLinhaNova, cColunaNova);
                }

                break;

            case 2*/
/*esquerda*//*
:
                if (player.getPlayerId() == 1) {
                    cLinhaNova = coordenadaLinhaP1[idPosicaoArray];
                    cColunaNova = cColunaAnterior - alcance;
                } else {
                    cLinhaNova = coordenadaLinhaP2[idPosicaoArray];
                    cColunaNova = cColunaAnterior - alcance;
                }

                if (verificarSentido(cLinhaNova,cColunaNova)){
                    sentido = digitarSentido();
                    moverNoMapa(player,idPosicaoArray,sentido);
                }else {

                    limparCampo(cLinhaAnterior, cColunaAnterior);
                    marcarCampo(player, idPosicaoArray, cLinhaNova, cColunaNova);
                }
                break;
            case 3 */
/* cima *//*
:
                if (player.getPlayerId() == 1) {
                    cLinhaNova = cLinhaAnterior - alcance;
                    cColunaNova = coordenadaColunaP1[idPosicaoArray];
                } else {
                    cLinhaNova = cLinhaAnterior - alcance;
                    cColunaNova = coordenadaColunaP2[idPosicaoArray];
                }

                if (verificarSentido(cLinhaNova,cColunaNova)){
                    sentido = digitarSentido();
                    moverNoMapa(player,idPosicaoArray,sentido);
                }else {

                    limparCampo(cLinhaAnterior, cColunaAnterior);
                    marcarCampo(player, idPosicaoArray, cLinhaNova, cColunaNova);
                }
                break;
            case 4 */
/*baixo*//*
:
                if (player.getPlayerId() == 1) {
                    cLinhaNova = cLinhaAnterior + alcance;
                    cColunaNova = coordenadaColunaP1[idPosicaoArray];
                } else {
                    cLinhaNova = cLinhaAnterior + alcance;
                    cColunaNova = coordenadaColunaP2[idPosicaoArray];
                }

                if (verificarSentido(cLinhaNova,cColunaNova)){
                    sentido = digitarSentido();
                    moverNoMapa(player,idPosicaoArray,sentido);
                }else {

                    limparCampo(cLinhaAnterior, cColunaAnterior);
                    marcarCampo(player, idPosicaoArray, cLinhaNova, cColunaNova);
                }
                break;
            case 5 */
/*diagonal direita-cima*//*
:
                if (player.getPlayerId() == 1) {
                    cLinhaNova = cLinhaAnterior - alcance;
                    cColunaNova = cColunaAnterior + alcance;
                } else {
                    cLinhaNova = cLinhaAnterior - alcance;
                    cColunaNova = cColunaAnterior + alcance;
                }

                if (verificarSentido(cLinhaNova,cColunaNova)){
                    sentido = digitarSentido();
                    moverNoMapa(player,idPosicaoArray,sentido);
                }else {

                    limparCampo(cLinhaAnterior, cColunaAnterior);
                    marcarCampo(player, idPosicaoArray, cLinhaNova, cColunaNova);
                }
                break;
            case 6 */
/*diagonal direita-baixo*//*
:
                if (player.getPlayerId() == 1) {
                    cLinhaNova = cLinhaAnterior + alcance;
                    cColunaNova = cColunaAnterior + alcance;
                } else {
                    cLinhaNova = cLinhaAnterior + alcance;
                    cColunaNova = cColunaAnterior + alcance;
                }

                if (verificarSentido(cLinhaNova,cColunaNova)){
                    sentido = digitarSentido();
                    moverNoMapa(player,idPosicaoArray,sentido);
                }else {

                    limparCampo(cLinhaAnterior, cColunaAnterior);
                    marcarCampo(player, idPosicaoArray, cLinhaNova, cColunaNova);
                }
                break;
            case 7 */
/*diagonal esquerda-cima*//*
:
                if (player.getPlayerId() == 1) {
                    cLinhaNova = cLinhaAnterior - alcance;
                    cColunaNova = cColunaAnterior - alcance;
                } else {
                    cLinhaNova = cLinhaAnterior - alcance;
                    cColunaNova = cColunaAnterior - alcance;
                }

                if (verificarSentido(cLinhaNova,cColunaNova)){
                    sentido = digitarSentido();
                    moverNoMapa(player,idPosicaoArray,sentido);
                }else {

                    limparCampo(cLinhaAnterior, cColunaAnterior);
                    marcarCampo(player, idPosicaoArray, cLinhaNova, cColunaNova);
                }
                break;
            case 8 */
/*diagonal esquerda-baixo*//*
:

                if (player.getPlayerId() == 1) {
                    cLinhaNova = cLinhaAnterior + alcance;
                    cColunaNova = cColunaAnterior - alcance;
                } else {
                    cLinhaNova = cLinhaAnterior + alcance;
                    cColunaNova = cColunaAnterior - alcance;
                }
                if (verificarSentido(cLinhaNova,cColunaNova)){
                    sentido = digitarSentido();
                    moverNoMapa(player,idPosicaoArray,sentido);
                }else {

                    limparCampo(cLinhaAnterior, cColunaAnterior);
                    marcarCampo(player, idPosicaoArray, cLinhaNova, cColunaNova);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + idSentido);
        }

    }


    public void marcarCampo(Player player, int idPosicaoArray, int cLinha, int cColuna) {
        if (player.getPlayerId() == 1) {
            String[] nome = new String[]{player.getPlayerSelectedNinjas().get(idPosicaoArray).getNinjaName().substring(0,7)};
            campos[cLinha][cColuna] = "J" + player.getPlayerId()+ "-" +nome[0];
            coordenadaLinhaP1[idPosicaoArray] = cLinha;
            coordenadaColunaP1[idPosicaoArray] = cColuna;


        } else if (player.getPlayerId() == 2) {
            String[] nome = new String[]{player.getPlayerSelectedNinjas().get(idPosicaoArray).getNinjaName().substring(0,7)};
            campos[cLinha][cColuna] = "J" + player.getPlayerId()+"-"+nome[0];
            coordenadaLinhaP2[idPosicaoArray] = cLinha;
            coordenadaColunaP2[idPosicaoArray] = cColuna;

        }
    }

    public boolean verificarSentido(int cLinhaNova, int cColunaNova) {

        return (cLinhaNova > 6) || (cLinhaNova < 0) || (cColunaNova > 10) || (cColunaNova < 0) ||
                ((cColunaNova == 0 || cColunaNova == 10) && (cLinhaNova == 2 || cLinhaNova == 3 || cLinhaNova == 4)) || !campos[cLinhaNova][cColunaNova].equals("          ");
    }



    public int digitarSentido(){
        int sentido;
        Scanner scan = new Scanner(System.in);
        System.out.println("Movimento Inválido. Selecione outra opção.");
        System.out.println("Qual o sentido?");
        System.out.println("1 - direita");
        System.out.println("2 - esquerda");
        System.out.println("3 - cima");
        System.out.println("4 - baixo");
        System.out.println("5 - diagonal direita-cima");
        System.out.println("6 - diagonal direita-baixo");
        System.out.println("7 - diagonal esquerda-cima");
        System.out.println("8 - diagonal esquerda-baixo");

        sentido = scan.nextInt();

        if (sentido < 1 || sentido > 8) {
            while (sentido < 1 || sentido > 8) {
                System.out.println("Sentido Inválido!");
                sentido = scan.nextInt();
            }
        }

        return sentido;
    }


    public void limparVerificarSelecionadoP1P2(){
        for(int i = 0; i < 5; i++){
            setVerificarSelecionadoP1(false,i);
            setVerificarSelecionadoP2(false,i);
        }
    }



}
*/
