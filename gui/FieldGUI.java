package ija.ija2018.homework2.gui;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class FieldGUI extends Rectangle {
    protected int col;
    protected int row;
    protected int size;

    public FieldGUI(boolean white, int column, int row, int size) {
        this.col = column;
        this.row = row;
        this.size = size;

        setWidth(size);
        setHeight(size);

        relocate(row * size, column * size);

        setFill(white ? Color.valueOf("#fff") : Color.valueOf("#000"));
    }

}
