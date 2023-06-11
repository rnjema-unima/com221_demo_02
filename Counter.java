/*
 * Simple JavaFX controlled counter application
 * @author Ramsey Njema
 * @version 1.0
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class Counter extends Application {

    private int counter = 0;
    private boolean countDownActive = false;
    private Label counterTextLabel = new Label("" + counter);
    private Button counterButton = new Button("START");

    @Override
    public void start(Stage primaryStage) {
        VBox vBoxPane = new VBox();

        // Set click action listener for counterButton
        counterButton.setOnAction(e -> {
            String currentState = ((Button) e.getSource()).getText();
            if (currentState.equalsIgnoreCase("start")) {
                countDownActive = true;
                counterButton.setText("STOP");
                new Thread(new CountThread()).start();
            } else {
                countDownActive = false;
                counterButton.setText("START");
            }
        });

        counterTextLabel.setFont(Font.font(35));
        Scene scene = new Scene(vBoxPane, 200, 100);

        vBoxPane.getChildren().add(counterTextLabel);
        vBoxPane.getChildren().add(counterButton);
        vBoxPane.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Counter Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class CountThread implements Runnable {
        @Override
        public void run() {
            while (countDownActive) {
                try {
                    counter++;
                    Platform.runLater(() -> {
                        counterTextLabel.setText(String.format("%d", counter));
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

}