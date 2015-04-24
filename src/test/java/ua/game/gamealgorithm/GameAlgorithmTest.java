package ua.game.gamealgorithm;

import org.junit.Test;
import ua.game.supportobject.GameObject;
import ua.game.supportobject.MoveObject;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameAlgorithmTest {
    private int[] TestDice;
    private GameObject GO;
    private GameAlgorithm GA;
/*
    @Test
    public void testCalcPossibleMovements23() throws Exception {
        ArrayList<MoveObject> tst;
        ArrayList<MoveObject> etalon = new ArrayList<MoveObject>();

        MoveObject tstMove = new MoveObject();
        tstMove.setDiceMove(2);
        tstMove.setStartPositions(new int[]{0});
        tstMove.setStopPosition(new int[]{2});
        tstMove.setPermissionFlagAvl();
        etalon.add(tstMove);
        tstMove = new MoveObject();
        tstMove.setDiceMove(3);
        tstMove.setStartPositions(new int[]{0});
        tstMove.setStopPosition(new int[]{3});
        tstMove.setPermissionFlagAvl();
        etalon.add(tstMove);
        tstMove = new MoveObject();
        tstMove.setDiceMove(5);
        tstMove.setStartPositions(new int[]{0});
        tstMove.setStopPosition(new int[]{5});
        tstMove.setPermissionFlagAvl();
        etalon.add(tstMove);

        TestDice = new int[]{2,3};
        GO = new GameObject();
        GA = new GameAlgorithm();

        GO.setDice(TestDice);
        GO.incStepCalc();

        tst = GA.calcPossibleMovements(GO);

        //System.out.println(tst.get(0).isPermissionFlag());

        assertTrue("Test CalcPossibleMovements with dice = [2:3]",
                tst.equals(etalon));
    }
*/
    @Test
    public void testCalcDiceMove1() throws Exception {
        GO = new GameObject();
        GA = new GameAlgorithm();

        MoveObject mv = GA.calcDiceMove(GO.getWhitePieces(), GO.getWhiteFlags(),
                GO.getBlackPieces(), GO.getBlackFlags(), 1);

        MoveObject tstMove = new MoveObject();
        tstMove.setDiceMove(1);
        tstMove.setStartPositions(new int[]{0});
        tstMove.setStopPosition(new int[]{1});
        tstMove.setPermissionFlagAvl();

        assertTrue("test CalcDiceMove with 1", mv.isPermissionFlag());
    }
}