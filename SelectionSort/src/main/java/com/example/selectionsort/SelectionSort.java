package com.example.selectionsort;

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

public class SelectionSort extends Application {

    private static final int ARRAY_SIZE = 10;
    private static final int ELEMENT_WIDTH = 40;
    private static final int ELEMENT_GAP = 5;

    private int[] array;
    private Pane pane;
    private Timeline timeline;
    private int currentIndex = 0;
    private int pass = 0;
    private int minIndex;

    @Override
    public void start(Stage primaryStage) {
        array = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = (int) (Math.random() * 100) + 1;
        }

        pane = new Pane();
        drawArray();

        Button startButton = new Button("Start Selection Sort");
        startButton.setOnAction(event -> startSorting());

        pane.getChildren().add(startButton);

        Scene scene = new Scene(pane, 450, 320);
        primaryStage.setTitle("Selection Sort Visualization");
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
                performSelection();
            } else {
                timeline.stop();
            }
        }));

        timeline.play();
    }

    private void performSelection() {
        minIndex = currentIndex;

        Rectangle currentRect = (Rectangle) pane.getChildren().get(currentIndex);
        currentRect.setFill(Color.LIGHTPINK);

        for (int i = currentIndex + 1; i < ARRAY_SIZE; i++) {
            Rectangle rect = (Rectangle) pane.getChildren().get(i);
            rect.setFill(Color.YELLOW);

            if (array[i] < array[minIndex]) {
                if (minIndex != currentIndex) {
                    Rectangle oldMinRect = (Rectangle) pane.getChildren().get(minIndex);
                    oldMinRect.setFill(Color.LIGHTBLUE);
                }
                minIndex = i;
            }
        }

        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(event -> {
            swap(currentIndex, minIndex);
            currentIndex++;
            pass++;

            currentRect.setFill(Color.LIGHTBLUE);
            drawArray();
        });
        pause.play();
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        launch(args);
    }
}