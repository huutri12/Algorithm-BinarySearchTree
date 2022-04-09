/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;

/**
 *
 * @author ADMIN
 */
public interface Node {
    public void paint(Node parent, Graphics2D g2d);
        public void setColor(Color color);
        public Color getColor();
        public Node getParent();
        public Node getLeft();
        public Node getRight();
        public void setLeftNode(Node node);
        public void setRightNode(Node node);
        public Point getLocation();
        public void setLocation(Point p);
        public void addNode(int item);
        public int getNumber();
        public void setNumber(int number);
        public void setParent(Node parent);
}
