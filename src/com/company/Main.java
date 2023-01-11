import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Scanner;

import javafx.scene.control.Button;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a text field
        TextField textField = new TextField();
        Scanner in = new Scanner(System.in);

        // Set prompt text for the text field
        textField.setPromptText("Enter your name");

        // Create a VBox layout
        VBox root = new VBox();

        // Add the text field to the layout
        root.getChildren().add(textField);

        // Create a scene and add the layout to it
        Scene scene = new Scene(root, 600, 400);








        // Set the stage title and scene, and show the stage
        primaryStage.setTitle("Text Box Example");
        primaryStage.setScene(scene);
        primaryStage.show();

        // This sets an event so that when the user presses enter the letters they have are converted to a string
        //so that it can be used to solve for best word and it also clears the text box
        textField.setOnAction(e -> {
             String word = textField.getText();
             textField.clear();
             System.out.println(word);
        });

    }
}
