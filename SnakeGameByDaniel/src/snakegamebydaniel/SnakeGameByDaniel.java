
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegamebydaniel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 *
 * @author wangd
 */
public class SnakeGameByDaniel extends Application {

    private GraphicsContext graphicsContext;
    private Canvas canvas;
    private final int window_height = 800, window_width = 1000,
            blockSize = 50, delay = 100, rate = 125;
    private Snake snake;
    private Food food;
    private Timer timer;
    private TimerTask task;
    private Button button;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Snake");
        canvas = new Canvas(window_width, window_height);
        graphicsContext = canvas.getGraphicsContext2D();
        Pane root = new Pane();
        root.getChildren().add(canvas);

        button = new Button();
        button.setText("START");
        button.setStyle("-fx-background-color: Black");
        button.setTextFill(Color.WHITE);

        snake = new Snake(null, window_width, window_height, blockSize);
        food = new Food(window_width, window_height, blockSize);

        VBox layout = new VBox();
        layout.getChildren().add(button);
        layout.prefWidthProperty().bind(canvas.widthProperty());
        layout.prefHeightProperty().bind(canvas.heightProperty());
        layout.setAlignment(Pos.CENTER);
        drawGrid(true);
        root.getChildren().add(layout);
        Scene scene = new Scene(root);

        button.setOnAction((ActionEvent event) -> {
            snake.setIsAlive(true);
            button.setVisible(false);
            task = createTimerTask();
            timer = new Timer("Timer");
            snake.setHead(new Point(0, 0));
            snake.reset();
            food.newFood();
            timer.scheduleAtFixedRate(task, delay, rate);
        });
        scene.setOnKeyPressed((e) -> {
            if (null != e.getCode()) {
                switch (e.getCode()) {
                    case UP:
                    case W:
                        if (snake.getDirection() != Direction.DOWN) {
                            snake.setDirection(Direction.UP);
                        }
                        break;
                    case DOWN:
                    case S:
                        if (snake.getDirection() != Direction.UP) {
                            snake.setDirection(Direction.DOWN);
                        }
                        break;
                    case LEFT:
                    case A:
                        if (snake.getDirection() != Direction.RIGHT) {
                            snake.setDirection(Direction.LEFT);
                        }
                        break;
                    case RIGHT:
                    case D:
                        if (snake.getDirection() != Direction.LEFT) {
                            snake.setDirection(Direction.RIGHT);
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private TimerTask createTimerTask() {
        TimerTask task = new TimerTask() {  
            @Override
            public void run() {
                if (snake.isIsAlive()) {
                    drawGrid(true);
                    snake.update();
                    drawSnake();
                    drawFood();
                    drawGrid(false);
                    if (!snake.isIsAlive()) {
                        drawGrid(true);
                        timer.cancel();
                        endGame();
                        button.setVisible(true);
                    }

                    if (food.foundFood(snake)) {
                        snake.add();
                        newFood();
                        while (food.foundFood(snake)) {
                            newFood();
                        }

                    }
                }
            }

        };
        return task;
    }

    private void newFood() {
        while (true) {
            food.newFood();
            boolean good = true;

            for (Point p : snake.getBody()) {
                if (p.getX() == food.getLocation().getX() && p.getY() == food.getLocation().getY()) {
                    good = false;
                    break;
                }
            }
            if (snake.getHead().getX() == food.getLocation().getX() && snake.getHead().getY() == food.getLocation().getY()) {
                good = false;
            }
            if (good) {
                break;
            }
        }
    }

    private void drawSnake() {
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(snake.getHead().getX(), snake.getHead().getY(), blockSize, blockSize);
        graphicsContext.setFill(Color.GREEN);

        for (Point p : snake.getBody()) {
            graphicsContext.fillRect(p.getX(), p.getY(), blockSize, blockSize);
        }
    }

    private void drawFood() {
        graphicsContext.setFill(Color.DARKRED);
        graphicsContext.fillRect(food.getLocation().getX(), food.getLocation().getY(), blockSize, blockSize);
    }

    public void drawGrid(boolean flag) {
        if (flag) {
            graphicsContext.setStroke(Color.LIGHTGRAY);
            graphicsContext.setLineWidth(0.5);
            graphicsContext.setFill(Color.BLACK);
            graphicsContext.fillRect(0, 0, window_width, window_height);
            for (int x = 0; x < window_width; x += blockSize) {
                graphicsContext.strokeLine(x, 0, x, window_height);
            }
            for (int y = 0; y < window_height; y += blockSize) {
                graphicsContext.strokeLine(0, y, window_width, y);
            }
        }
        String gameOverText = ("MADE BY DANIEL WANG [2019-07-18]");
        double textWidth = gameOverText.length();
        graphicsContext.setFill(Color.DARKGRAY);
        graphicsContext.fillText(gameOverText, 730, window_height - 20);
    }

    private void endGame() {
        String gameOverText = ("GAME OVER [SCORE=" + snake.getScore() + "]");
        double textWidth = gameOverText.length();
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText(gameOverText, (window_width / 2) - textWidth - 80, window_height / 2 - 30);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        timer.cancel();
        super.stop();
    }
}
