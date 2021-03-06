/**
 * King: Trieda reprezentujuca figurku krala
 * @author Simon Kobyda, xkobyd00
 * @author Michal Zelenak, xzelen24
 * Project: Chess
 * University: Brno University of Technology
 * Course: IJA
 */

package common;

import java.util.*;

public class King implements Figure {
	private boolean isWhite;
    private Field position;
    private String type;
    private int numOfMoves;

    /**
     * Konstruktor pre vytvorenie Krala.
     * @param isWhite - Farba krala.
     * @param typeStr - Typ figurky.
     */
	public King(boolean isWhite, String typeStr) {
        this.isWhite = isWhite;
        this.type = typeStr;
        this.numOfMoves = 0;
    }

    /**
     * Vrati farbu figurky.
     * @return true/false
     */
	public boolean isWhite() {
        return isWhite;
    }

    /**
     * Vrati typ figurky.
     * @return blackKing/whiteKing
     */
	public String getType() {
        return type;
    }

    /**
     * Vrati farbu a poziciu figurky.
     * @return V[W/B)pos:pos
     */
    @Override
    public String getState() {
        String color;
        if (isWhite)
            color = "W";
        else
            color = "B";
        int[] pos = position.getPosition();

        return "V[" + color + "]" + pos[0] + ":" + pos[1];
    }

    /**
     * Vrati poziciu figurky krala.
     * @return Field - Policko kde sa figurka krala nachadza.
     */
    @Override
    public Field getPosition() {
        return position;
    }

    /**
     * Dekrementuje pocet pohybov.
     */
    public void decNumOfMoves() {
        this.numOfMoves--;
    }

    /**
     * Zmeni poziciu figurky krala.
     */
    @Override
    public void setPosition(Field field) {
        this.position = field;
    }

    /**
     * Vrati informaciu o tom, ci je mozne sa na zadane policko pohnut, v ceste nesmu stat ziadne figurky.
     * @return true/false
     */
    public boolean canMove(Field moveTo) {
        // Figure did not move
        if (position == null || position.equals(moveTo))
            return false;

        // Do not capture piece of same color
        if (moveTo.get() != null && moveTo.get().isWhite() == isWhite)
            return false;

        // List of allowed directions for this figure
        List<Field.Direction> directions = new ArrayList<Field.Direction>();
        directions.add(Field.Direction.U);
        directions.add(Field.Direction.D);
        directions.add(Field.Direction.L);
        directions.add(Field.Direction.R);
        directions.add(Field.Direction.LU);
        directions.add(Field.Direction.LD);
        directions.add(Field.Direction.RU);
        directions.add(Field.Direction.RD);

        // Try to find the destination in certain direction
        for (Field.Direction dir : directions) {
            Field field = position.nextField(dir);

            // Successfully found the destination field
            if (field != null && field.equals(moveTo))
                return true;
        }

        return false;
    }

    /**
     * Posunie figurku na zadane policko.
     * @return ture/false
     */
    @Override
    public boolean move(Field moveTo) {
        if (canMove(moveTo)) {
            position.remove(this);
            this.position = moveTo;
            this.numOfMoves++;
            return moveTo.put(this);
        }

        return false;
    }
}
