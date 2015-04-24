package ua.game.baselogic;

import ua.game.gamealgorithm.GameAlgorithm;
import ua.game.supportobject.GameObject;

import java.util.ArrayList;

/**
 * Created by vasyl on 1/15/15.
 */

public class BaseLogic {
    GameAlgorithm ga = new GameAlgorithm();

    public GameObject rollDiceFirstTime(GameObject game) {
        int[] tmpDice = ga.rollDice();
        game.setDice(tmpDice);
        if (tmpDice[0] != tmpDice[1]) {
            game.incStepCalc();
        }
        if (tmpDice[0] > tmpDice[1]) {
            game.setBlackPriority();
        }
        if (tmpDice[0] < tmpDice[1]) {
            game.setWhitePriority();
        }
        return game;
    }

    public GameObject rollDice(GameObject game) {
        int[] tmpDice = ga.rollDice();
        game.setDice(tmpDice);
        if (game.getStepCalc() > 1) {
            if (game.isWhite()) {
                game.setBlackPriority();
            } else {
                game.setWhitePriority();
            }
        }
        game.setMoves(ga.calcNumMoves(tmpDice));
        game.incStepCalc();
        return game;
    }

    public ArrayList calcMoves(GameObject game) {
        return ga.calcPossibleMovements(game);
    }
}
