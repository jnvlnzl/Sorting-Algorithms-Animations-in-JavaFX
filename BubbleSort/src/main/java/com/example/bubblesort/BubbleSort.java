package com.example.bubblesort;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BubbleSort extends Application {

    private static final int ARRAY_SIZE = 10;
    private static final int ELEMENT_WIDTH = 40;
    private static final int ELEMENT_GAP = 5;

    private int[] array;
    private Pane pane;
    private Timeline timeline;
    private int currentIndex = 0;
    private int pass = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        array = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = (int) (Math.random() * 100) + 1;
        }

        pane = new Pane();
        drawArray();

        Button startButton = new Button("Start Bubble Sort");
        startButton.setOnAction(event -> startSorting());

        pane.getChildren().add(startButton);

        Scene scene = new Scene(pane, 450, 320);
        primaryStage.setTitle("Bubble Sort Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawArray() {
        pane.getChildren().clear();

        for (int i = 0; i < ARRAY_SIZE; i++) {
            Rectangle rectangle = new Rectangle(i * (ELEMENT_WIDTH + ELEMENT_GAP),
                    300 - array[i] * 3,
                    ELEMENT_WIDTH,
                    array[i] * 3);
            rectangle.setFill(Color.LIGHTBLUE);
            pane.getChildren().add(rectangle);
        }
    }

    private void startSorting() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), e -> {
            if (pass < ARRAY_SIZE - 1) {
                if (currentIndex < ARRAY_SIZE - pass - 1) {
                    performComparison();
                } else {
                    currentIndex = 0;
                    pass++;
                }
            } else {
                timeline.stop();
            }
        }));

        timeline.play();
    }
    private void performComparison() {
        Rectangle currentRect = (Rectangle) pane.getChildren().get(currentIndex);
        currentRect.setFill(Color.LIGHTPINK);

        Rectangle nextRect = (Rectangle) pane.getChildren().get(currentIndex + 1);
        nextRect.setFill(Color.LIGHTPINK);

        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(event -> {
            if (array[currentIndex] > array[currentIndex + 1]) {
                int temp = array[currentIndex];
                array[currentIndex] = array[currentIndex + 1];
                array[currentIndex + 1] = temp;

                drawArray();
            }

            currentIndex++;

            currentRect.setFill(Color.LIGHTBLUE);
            nextRect.setFill(Color.LIGHTBLUE);
        });
        pause.play();
    }
}
