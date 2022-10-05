/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegamebydaniel;

import java.util.ArrayList;

/**
 *
 * @author wangd
 */
public class Snake {

    private ArrayList<Point> body;
    private Direction direction;
    private Point head = new Point(0, 0);
    private int width, height, blockSize;
    private boolean isAlive;
    private int score;

    public int getScore() {
        return score;
    }

    public void update() {
        score = body.size();

        if (body.size() > 0) {
            body.remove(body.size() - 1);
            body.add(0, new Point(head.getX(), head.getY()));
        }

        if (null != direction) {
            switch (direction) {
                case RIGHT:
                    head.setX(head.getX() + blockSize);

                    if (head.getX() >= width) {
                        head.setX(width - blockSize);
                        isAlive = false;
                        reset();
                    }
                    break;
                case LEFT:
                    head.setX(head.getX() - blockSize);
                    if (head.getX() < 0) {
                        head.setX(0);
                        isAlive = false;
                        reset();
                    }
                    break;
                case DOWN:
                    head.setY(head.getY() + blockSize);
                    if (head.getY() >= height) {
                        head.setY(height - blockSize);
                        isAlive = false;
                        reset();
                    }
                    break;
                case UP:
                    head.setY(head.getY() - blockSize);
                    if (head.getY() < 0) {
                        head.setY(0);
                        isAlive = false;
                        reset();
                    }
                    break;
                default:
                    break;
            }
        }
        hit();

    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public Snake(Direction direction, int width, int height, int blockSize) {
        this.direction = direction;

        this.width = width;
        this.height = height;
        this.blockSize = blockSize;
        body = new ArrayList<>();
        isAlive = false;
    }

    public void add() {
        body.add(0, new Point(head.getX(), head.getY()));
    }

    /**
     *
     * @return
     */
    public ArrayList<Point> getBody() {
        return body;
    }

    public void hit() {

        for (Point p : body) {
            if (p.getX() == head.getX() && p.getY() == head.getY()) {
                isAlive = false;
                reset();
                break;
            }
        }
    }

    public Point getHead() {
        return head;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setHead(Point head) {
        this.head = head;
    }

    public void reset() {
        body.clear();
        this.direction = null;
    }

}
