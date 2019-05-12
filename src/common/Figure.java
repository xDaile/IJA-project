package project.src.common;

public interface Figure {
    public boolean isWhite();
	public String getType();
	public String getState();
    public Field getPosition();
    public void decNumOfMoves();
    public void setPosition(Field field);
    public boolean canMove(Field moveTo);
    public boolean move(Field moveTo);

}