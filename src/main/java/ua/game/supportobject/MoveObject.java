package ua.game.supportobject;

/**
 * Created by vasyl on 2/11/15.
 */
public class MoveObject {
    private int DiceMove;
    private int[] StartPositions;
    private int[] StopPositions;
    private boolean PermissionFlag = false;

    public void setDiceMove(int Dice) {
        this.DiceMove = Dice;
    }

    public int getDiceMove() {
        return this.DiceMove;
    }

    public void setStartPositions(int[] Positions) {
        this.StartPositions = Positions;
    }

    public int[] getStartPositions() {
        return this.StartPositions;
    }

    public void setStopPosition(int[] Positions) {
        this.StopPositions = Positions;
    }

    public int[] getStopPositions() {
        return this.StopPositions;
    }

    public void setPermissionFlagAvl()  {
        this.PermissionFlag = true;
    }

    public void setPermissionFlagDis()  {
        this.PermissionFlag = false;
    }

    public boolean isPermissionFlag() {
        return this.PermissionFlag;
    }
}
