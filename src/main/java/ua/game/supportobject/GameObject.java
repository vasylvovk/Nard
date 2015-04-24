package ua.game.supportobject;

/**
 * @author vasyl
 * @version 0.0.0
 * @date 2014-02-01
 * @parameter WhitePieces and BlackPieces represents where pieces on the board
 * are.
 * @parameter WhiteFlags and BlackFlags show where players cannot move on the
 * board.
 * @parametr StepCalc calc how many times roll the dice
 * @parametr WB shows who moves first -- True White, False Black
 * <p/>
 * This class example contain itself game situation.
 */
public class GameObject {

    private boolean[] WhiteFlags;
    private boolean[] BlackFlags;
    private int[] WhitePieces;
    private int[] BlackPieces;
    private int[] Dice;
    private int[] Moves;
    private int StepCalc = 0;
    private boolean WB;

    public void setMoves(int[] newMoves) {
        this.Moves = new int[newMoves.length];
        this.Moves = newMoves;
    }

    public int[] getMoves() {
        return this.Moves;
    }

    public void setWhitePriority() {
        this.WB = true;
    }

    public void setBlackPriority() {
        this.WB = false;
    }

    public boolean isWhite() {
        return this.WB;
    }

    public void setDice(int[] NewDice) {
        this.Dice = NewDice;
    }

    public int[] getDice() {
        return this.Dice;
    }

    public int getStepCalc() {
        return this.StepCalc;
    }

    public void incStepCalc() {
        this.StepCalc++;
    }

    public GameObject() {
        this.WhiteFlags = new boolean[]{
                true, true, true, true, true, true,
                true, true, true, true, true, true,
                false, true, true, true, true, true,
                true, true, true, true, true, true
        };

        this.BlackFlags = new boolean[]{
                false, true, true, true, true, true,
                true, true, true, true, true, true,
                true, true, true, true, true, true,
                true, true, true, true, true, true
        };

        this.WhitePieces = new int[]{
                15, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0
        };

        this.BlackPieces = new int[]{
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                15, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0
        };
    }

    public boolean[] getWhiteFlags() {
        return this.WhiteFlags;
    }

    public boolean[] getBlackFlags() {
        return this.BlackFlags;
    }

    public int[] getWhitePieces() {
        return this.WhitePieces;
    }

    public int[] getBlackPieces() {
        return this.BlackPieces;
    }

    public void setWhiteFlags(boolean[] newWhiteFlags) {
        this.WhiteFlags = newWhiteFlags;
    }

    public void setBlackFlags(boolean[] newBlackFlags) {
        this.BlackFlags = newBlackFlags;
    }

    public void setWhitePieces(int[] newWhitePieces) {
        this.WhitePieces = newWhitePieces;
    }

    public void setBlackPieces(int[] newBlackPieces) {
        this.BlackPieces = newBlackPieces;
    }
}
