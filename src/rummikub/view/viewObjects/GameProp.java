/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.view.viewObjects;

/**
 *
 * @author giladPe
 */
public class GameProp {

    private String gameName;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public int getNumOfComputerPlayers() {
        return numOfComputerPlayers;
    }

    public void setNumOfComputerPlayers(int numOfComputerPlayers) {
        this.numOfComputerPlayers = numOfComputerPlayers;
    }
    int numOfPlayers;
    int numOfComputerPlayers;

    public GameProp() {
        this.gameName = "";
        this.numOfPlayers = 2;
        this.numOfComputerPlayers = 0;
    }
    public GameProp(String gameName,int numOfPlayers,int numOfComputerPlayers) {
        this.gameName = gameName;
        this.numOfPlayers = numOfPlayers;
        this.numOfComputerPlayers = numOfComputerPlayers;
    }
}
