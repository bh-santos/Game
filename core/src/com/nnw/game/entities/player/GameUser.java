
package com.nnw.game.entities.player;



public class GameUser {

    private String loginCode;

    private String nickname;
    private String profileImage;
    private int level;
    private float experience;
    private int moonCoin;
    private int ryous;
    private String playerRank;
    private String playerTitle;
    private int wins;
    private int loses;
    private float winPercentage;
    private int maxLevel;
    private int streak;

    // getters and setters


    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getExperience() {
        return experience;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }

    public int getMoonCoin() {
        return moonCoin;
    }

    public void setMoonCoin(int moonCoin) {
        this.moonCoin = moonCoin;
    }

    public int getRyous() {
        return ryous;
    }

    public void setRyous(int ryous) {
        this.ryous = ryous;
    }

    public String getPlayerRank() {
        return playerRank;
    }

    public void setPlayerRank(String playerRank) {
        this.playerRank = playerRank;
    }

    public String getPlayerTitle() {
        return playerTitle;
    }

    public void setPlayerTitle(String playerTitle) {
        this.playerTitle = playerTitle;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public float getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(float winPercentage) {
        this.winPercentage = winPercentage;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    //constructor

    public void viewAllInformation(){
        System.out.println("Login code: "+loginCode);
        System.out.println("Profile Image: "+ profileImage);//aparece
        System.out.println("Level: " +level);//aparece
        System.out.println("EXP: "+ experience);//aparece
        System.out.println("MoonCoin: " +moonCoin);//aparece
        System.out.println("Player Rank: " +playerRank);//perfil
        System.out.println("Player Title: " +playerTitle);//aparece
        System.out.println("Wins: "+ wins);//perfil
        System.out.println("Loses: "+ loses);//perfil
        System.out.println("Win Percentage: "+winPercentage);//perfil
        System.out.println("Max Level: "+maxLevel);//perfil
        System.out.println("Sequencia de batalhas: "+streak);//perfil
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

