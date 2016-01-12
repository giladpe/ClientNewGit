/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.view;

import com.sun.javafx.binding.BindingHelperObserver;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import rummikub.view.viewObjects.GameProp;

/**
 * FXML Controller class
 *
 * @author giladPe
 */
public class ServerSelectController implements Initializable, ControlledScreen, ResetableScreen {
    private static final String INVALID_GAME_NAME_MSG = "Invalid game name!";
    private static final String NO_HUMAN_MSG = "Chose atleast one human player!";
    private static final String EMPTY_STRING="";
    private static final String CONTAINS_WHITE_SPACES_MSG="Name can not statr with whitespaces!";
    private static final String INVALID_NUMBER="Invalid number!";
    private static final String INVALID_COMPUTERS_NUMBER="invalid Computer players number";
    private static final String INVALID_PLAYERS_NUMBER="invalid players number";
    
    
    
    
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
    @FXML
    TextField gameNameInput;
     
    @FXML
    TextField numOfPlayersInput, numOfCopmputersInput;
    @FXML
    private TableColumn<GameProp, Integer> numOfPlayersColumn;
    private ScreensController myController;
    @FXML
    private VBox tableVBox;
    @FXML
    private Button joinButton;
    @FXML
    private Button addButton;
    @FXML
    private TableColumn<?, ?> gameStatus;
    
    
    
    @FXML
    private void handleBackToMenuButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleStartPlayingButtonAction(ActionEvent event) {
    }    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        this.gameNameColumn.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        this.numOfPlayersColumn.setCellValueFactory(new PropertyValueFactory<>("numOfPlayers"));
        this.computerPlayersColumn.setCellValueFactory(new PropertyValueFactory<>("numOfComputerPlayers"));
        this.gameStatus.setCellValueFactory(new PropertyValueFactory<>("gameStatus"));
        this.joinButton.setDisable(true);
        
        this.addButton.setOnAction(e -> addButtonClicked());   
        this.joinButton.setOnAction(e -> joinButtonClicked());
        initAddButton();
        //this.gamesTableView.selectionModelProperty().addListener(new PropertyDescriptor.Listener<>);
              gamesTableView.getSelectionModel().getSelectedItems().addListener((Change<? extends GameProp> change) -> joinButton.setDisable(false));
        
        this.gameNameInput.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
                isValidGameName();
                initAddButton();
        });
        this.numOfPlayersInput.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
                isNumericChar(this.numOfPlayersInput);
                initAddButton();
        });
        this.numOfCopmputersInput.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
                isNumericChar(this.numOfCopmputersInput);
                initAddButton();
        });
    }
    @FXML
    public void addButtonClicked(){
        GameProp gameProp = new GameProp();
        gameProp.setGameName(this.gameNameInput.getText());
        gameProp.setNumOfPlayers(getNumOfPlayers());
        gameProp.setNumOfComputerPlayers(getNumOfComputerPlayers());
        this.gamesTableView.getItems().add(gameProp);
        this.gameNameInput.clear();
        this.numOfPlayersInput.clear();
        this.numOfCopmputersInput.clear();
        ///Need TOADD SERVER FUNC and transfer to waiting room
    }
    public int getNumOfPlayers(){
        int num=0;
        if(!this.numOfPlayersInput.getText().isEmpty()){
            num=Integer.parseInt(this.numOfPlayersInput.getText());
        }
        return num;
    }
    public int getNumOfComputerPlayers(){
        int num=0;
        if(!this.numOfCopmputersInput.getText().isEmpty()){
            num=Integer.parseInt(this.numOfCopmputersInput.getText());
        }
        return num;
    }
    //Delete button clicked
    @FXML
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

    private void isNumericChar(TextField text) {
        if (!text.getText().isEmpty()&&!Character.isDigit(text.getText().charAt(0))){//.matches("\\d*")) {
            text.clear();
            showErrorMsg(errorMsg, INVALID_NUMBER);
        }
        else{
        if(text.getText().length()>1){
            char [] charArr = new char[1];
            charArr[0]= text.getText().charAt(0);
            String num=new String(charArr);
            text.setText(num);
        }
        
        }
    }
//        private void isNumericChar(TextField text) {
//        if (!text.getText().isEmpty()&&!text.getText().matches("\\d*")) {
//            text.clear();
//            showErrorMsg(errorMsg, INVALID_NUMBER);
//        }
//    }
    

    private void isValidNumOfComputerPlayers(String text) {
        //TODO
    }
   private boolean isValidTextField(String str) {
        return !(str.trim().isEmpty() || isInvalidFirstChar(str));
    }
   private boolean isInvalidFirstChar(String str){
        boolean retVal=false;
        if(Character.isWhitespace(str.charAt(0)))
        {
            showErrorMsg(errorMsg, CONTAINS_WHITE_SPACES_MSG);

            retVal=true;
        }

        return retVal;
            
    }
       public static void disappearAnimation(Node node) {
        FadeTransition animation = new FadeTransition();
        animation.setNode(node);
        animation.setDuration(Duration.seconds(3));
        animation.setFromValue(1.0);
        animation.setToValue(0.0);
        animation.play();
    }
public static void showErrorMsg(Label label,String msg){
        label.setText(msg);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), (ActionEvent event) -> {
            disappearAnimation(label);
        }));  
        timeline.setCycleCount(1);  
        timeline.play();  
    }

   
   
   
   private boolean isValidGameName() {
        if (this.gameNameInput.getText() != null) {
            if (isValidTextField(gameNameInput.getText())) {
                return true;
            }
        }
        showErrorMsg(this.errorMsg,INVALID_GAME_NAME_MSG);
        return false;
    }

    private void initAddButton() {
       this.addButton.setDisable(!isAllSet());
    }

    private boolean isAllSet() {
       boolean allSet=true;
       if(this.gameNameInput.getText().isEmpty()||this.numOfCopmputersInput.getText().isEmpty()||this.numOfPlayersInput.getText().isEmpty()){
           allSet=false;
       }
       if(getNumOfPlayers()>4||getNumOfPlayers()<2){
           if(!this.numOfPlayersInput.getText().isEmpty()){
               showErrorMsg(errorMsg, INVALID_PLAYERS_NUMBER);
           }
           allSet=false;
       }else if(getNumOfComputerPlayers()>getNumOfPlayers()-1){
           showErrorMsg(errorMsg, INVALID_COMPUTERS_NUMBER);
           allSet=false;
       }
       
       return allSet;
    }

    
}
//public class NumberTextField extends TextField
//{
//
//    @Override
//    public void replaceText(int start, int end, String text)
//    {
//        if (validate(text))
//        {
//            super.replaceText(start, end, text);
//        }
//    }
//
//    @Override
//    public void replaceSelection(String text)
//    {
//        if (validate(text))
//        {
//            super.replaceSelection(text);
//        }
//    }
//
//    private boolean validate(String text)
//    {
//        return text.matches("[0-9]*");
//    }
//}