package main.java;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        //creating text
        Text text = new Text("Please input the letters you have");



        //Creating Textfeild for the word input
        TextField textField = new TextField();
        textField.setPrefWidth(100);
        textField.setPrefHeight(50);



        //Creating Buttons for submission and errors
        Button button = new Button("Submit");
        button.setPrefWidth(400);
        button.setPrefHeight(100);

        Button button1 = new Button("ERROR");
        button1.setPrefWidth(400);
        button1.setPrefHeight(100);



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
        button.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        button1.setStyle("-fx-background-color: RED; -fx-text-fill: white;");
        text.setStyle("-fx-font: normal bold 20px 'serif' ");

        gridPane.setStyle("-fx-background-color: WHITE;");

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

            //Creating a Button and styling it
            Button button2 = new Button("suggestions");
            gridPane.add(button2, 1, 7);
            button2.setPrefWidth(400);
            button2.setPrefHeight(100);
            button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

            if((word.length() >5)|| word.equals("")){
                gridPane.add(button1, 1, 7);
            }

        });
        textField.setOnAction(ae -> {
            String word = textField.getText();
            textField.clear();
            System.out.println(word);
            InputProcessor.takeInput(word);

            //Creating a Button and styling it
            Button button2 = new Button("suggestions");
            gridPane.add(button2, 1, 7);
            button2.setPrefWidth(400);
            button2.setPrefHeight(100);
            button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

            if((word.length() >5)|| word.equals("")){
                gridPane.add(button1, 1, 7);
            }
                }
        );


    }

    public static void main(String args[]){

        launch(args);
    }
}

