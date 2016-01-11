/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import rummikub.view.viewObjects.GameProp;

/**
 * FXML Controller class
 *
 * @author giladPe
 */
public class ServerSelectController implements Initializable, ControlledScreen, ResetableScreen {

    @FXML
    private GridPane GamesSettings;
    @FXML
    private Button StartPlayingButton;
    @FXML
    private Label errorMsg;
    @FXML
    private TableView<GameProp> gamesTableView;
    @FXML
    private TableColumn<GameProp,String> gameNameColumn;
    
    @FXML 
    private TableColumn<GameProp, Integer> computerPlayersColumn;
     
    
    TextField gameNameInput, numOfPlayersInput, numOfCopmputersInput;
    @FXML
    private TableColumn<GameProp, Integer> numOfPlayersColumn;
    private ScreensController myController;
    @FXML
    private VBox tableVBox;
    
    @FXML
    private void handleBackToMenuButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleStartPlayingButtonAction(ActionEvent event) {
    }    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Name input
        gameNameInput = new TextField();
        gameNameInput.setPromptText("Name");
        gameNameInput.setMinWidth(100);
        //Price input
        numOfPlayersInput = new TextField();
        numOfPlayersInput.setPromptText("Number");

        //Quantity input
        numOfCopmputersInput = new TextField();
        numOfCopmputersInput.setPromptText("Number");
        
        Button newGameButton = new Button("Add Game");
        newGameButton.setMinWidth(100);
        newGameButton.setOnAction(e -> addButtonClicked());
        Button joinButton = new Button("join");
        joinButton.setMinWidth(100);
        joinButton.setOnAction(e -> joinButtonClicked());
        
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(gameNameInput, numOfPlayersInput, numOfCopmputersInput, newGameButton, joinButton);
        this.tableVBox.getChildren().addAll(hBox);
    }
public void addButtonClicked(){
        GameProp gameProp = new GameProp();
        gameProp.setGameName(this.gameNameColumn.getText());
        gameProp.setNumOfComputerPlayers(Integer.parseInt(this.numOfCopmputersInput.getText()));
        gameProp.setNumOfPlayers(Integer.parseInt(this.numOfPlayersColumn.getText()));
        this.gamesTableView.getItems().add(gameProp);
        this.gameNameInput.clear();
        this.numOfPlayersInput.clear();
        this.numOfCopmputersInput.clear();
        ///Need TOADD SERVER FUNC and transfer to waiting room
    }

    //Delete button clicked
    public void joinButtonClicked(){
           //////TODO 
    }

    @Override
    public void setScreenParent(ScreensController parentScreen) {
       this.myController = parentScreen;
    }

    @Override
    public void resetScreen() {

    }

    
}
