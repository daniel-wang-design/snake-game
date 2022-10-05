/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegamebydaniel;

import java.util.*;

/**
 *
 * @author wangd
 */
public class Food {

    private Point location;
    private int width, length, size;

    public Point getLocation() {
        return location;
    }

    public Food(int width, int length, int size) {
        this.width = width;
        this.length = length;
        this.size = size;
    }

    public boolean foundFood(Snake snake) {
        if (snake.getHead().getX() == location.getX() && snake.getHead().getY() == location.getY()) {
            return true;
        }
        for (Point p : snake.getBody()) {
            if (p.getX() == location.getX() && p.getY() == location.getY()) {
                return true;
            }
        }
        return false;
    }

    public void newFood() {
        Random rand = new Random();
        int x = rand.nextInt(width);
        int y = rand.nextInt(length);

        x = Math.round(x / size) * size;
        y = Math.round(y / size) * size;

        location = new Point(x, y);

    }

}
