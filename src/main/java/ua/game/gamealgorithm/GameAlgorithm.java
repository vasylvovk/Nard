package ua.game.gamealgorithm;

import ua.game.supportobject.GameObject;
import ua.game.supportobject.MoveObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by vasyl on 2/5/15.
 */
public class GameAlgorithm {
    public int[] rollDice() {
        int[] dice = new int[2];
        Random random = new Random();
        for (int i = 0; i < dice.length; i++) {
            dice[i] = random.nextInt(6) + 1;
        }
        return dice;
    }

    public int[] calcNumMoves(int[] dice) {
        int[] res = new int[4];
        if (dice[0] == dice[1]) {
            for (int i = 0; i < res.length; i++) {
                res[i] = dice[0];
            }
        } else {
            for (int i = 0; i < res.length; i++) {
                if (i < dice.length) {
                    res[i] = dice[i];
                } else {
                    res[i] = 0;
                }
            }
        }
        return res;
    }

    private int[] convertIntegers(ArrayList<Integer> integers) {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }

    public MoveObject calcDiceMove(int[] WPA, boolean[] WFA, int[] BPA,
                                   boolean[] BFA, int Dice) {
        MoveObject res = new MoveObject();

        int cp = 0;
        ArrayList<Integer> TmpStart = new ArrayList<Integer>();
        ArrayList<Integer> TmpStop = new ArrayList<Integer>();
        for (int i = 0; i < WPA.length; i++) {
            if (i + Dice < WPA.length) {
                if (WPA[i] > 0 & WFA[i] & WFA[i + Dice] & BPA[i + Dice] == 0) {
                    TmpStart.add(i);
                    TmpStop.add(i + Dice);
                    cp++;
                }
            }
        }
        res.setDiceMove(Dice);
        res.setStartPositions(convertIntegers(TmpStart));
        res.setStopPosition(convertIntegers(TmpStop));
        if (cp > 0)
            res.setPermissionFlagAvl();
        return res;
    }

    public ArrayList calcPossibleMovements(GameObject game) {
        ArrayList<MoveObject> res = new ArrayList();
        int[] WPA = game.getWhitePieces();
        int[] BPA = game.getBlackPieces();
        int[] Dices = game.getDice();
        boolean[] WFA = game.getWhiteFlags();
        boolean[] BFA = game.getBlackFlags();
        int counter = game.getStepCalc();

        if (Dices[0] != Dices[1]) {
            for (int d = 0; d < Dices.length; d++) {
                res.add(calcDiceMove(WPA, WFA, BPA, BFA, Dices[d]));
            }

            if ((res.get(0).getStartPositions().length > 0) |
                    (res.get(1).getStartPositions().length > 0)) {
                int DicesSum = Dices[0] + Dices[1];
                res.add(calcDiceMove(WPA, WFA, BPA, BFA, DicesSum));
            }
        } else {
            if (counter == 2 | counter == 3) {
                if (Dices[0] == 3) {
                    res.add(calcDiceMove(WPA, WFA, BPA, BFA, 3));
                    res.add(calcDiceMove(WPA, WFA, BPA, BFA, 9));
                } else if (Dices[0] == 4) {
                    res.add(calcDiceMove(WPA, WFA, BPA, BFA, 4));
                    res.add(calcDiceMove(WPA, WFA, BPA, BFA, 8));
                } else if (Dices[0] == 6) {
                    res.add(calcDiceMove(WPA, WFA, BPA, BFA, 6));
                    res.add(calcDiceMove(WPA, WFA, BPA, BFA, 6));
                } else {
                    res.add(calcDiceMove(WPA, WFA, BPA, BFA, Dices[0] * 4));
                }
            } else {
                for (int d = 1; d < 5; d++) {
                    res.add(calcDiceMove(WPA, WFA, BPA, BFA, Dices[0] * d));
                }
            }
        }
        return res;
    }
}
