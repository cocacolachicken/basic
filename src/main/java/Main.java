package main.java;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author john
 * @version 1.0
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        //creating text
        Text text = new Text("Please input the letters you have replace unknown letters with ?\nFor letters that you know the exact placement of make them uppercase\nFor letters that you know but not the exact position make then lowercase");
        text.setX(100);
        text.setY(50);


        //Creating Textfeild for the word input
        TextField textField = new TextField();
        textField.setPrefWidth(100);
        textField.setPrefHeight(50);



        //Creating Buttons for submission and errors
        Button button = new Button("Submit");
        button.setPrefWidth(625);
        button.setPrefHeight(100);

        Button button1 = new Button("ERROR");
        button1.setPrefWidth(625);
        button1.setPrefHeight(200);



        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(800, 700);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the textboxs and buttons in the grid
        gridPane.add(text, 1, 0);
        gridPane.add(textField, 1, 1);
        gridPane.add(button, 1, 5);


        //Styling nodes
        button1.setFont(Font.font(40));
        button.setFont(Font.font(40));
        button.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        button1.setStyle("-fx-background-color: RED; -fx-text-fill: white;");
        text.setStyle("-fx-font: normal bold 20px 'Comic Sans' ");

        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        stage.setTitle("Textbox");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
        //when they press the button or the enter key run the program to get the words
        button.setOnAction(actionEvent ->  {
            String word = textField.getText();
            textField.clear();
            System.out.println(word);
            InputProcessor.takeInput(word);
            String suggestions = InputProcessor.getList().get(0);
            button1.setVisible(false);

            if (word.equals("?????")) {
                suggestions = InputProcessor.getSuggestions();
            }

            //Creating a Button and styling it
            Button button2 = new Button(suggestions);

            button2.setPrefWidth(625);
            button2.setPrefHeight(100);
            button2.setStyle("-fx-background-color: PURPLE; -fx-text-fill: white;");
            button2.setFont(Font.font(40));
            if((word.length() !=5)){
                button2.setVisible(false);
                gridPane.add(button1, 1, 9);
                button1.setVisible(true);

            } else{
                button2.setVisible(true);
            }
            gridPane.add(button2, 1, 7);
        });
        textField.setOnAction(ae -> {
            String word = textField.getText();
            textField.clear();
            System.out.println(word);
            InputProcessor.takeInput(word);
            String suggestions = InputProcessor.getList().get(0);
            button1.setVisible(false);

            if (word.equals("?????")) {
                suggestions = InputProcessor.getSuggestions();
            }
            //Creating a Button and styling it for the words you should input
            Button button2 = new Button(suggestions);

            button2.setPrefWidth(625);
            button2.setPrefHeight(100);
            button2.setStyle("-fx-background-color: PURPLE; -fx-text-fill: white; 20");
            button2.setFont(Font.font(40));
            //if there is an invalid word automatic error
            if((word.length() !=5)){
                gridPane.add(button1, 1, 7);
                button2.setVisible(false);
                button1.setVisible(true);
            }
            else{ //set the suggestions visible
                button2.setVisible(true);
            }// add the button to the game
            gridPane.add(button2, 1, 7);
                }
        );


    }

    public static void main(String args[]){

        launch(args);
    }
}

