package ua.game.ui;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import ua.game.baselogic.BaseLogic;
import ua.game.supportobject.GameObject;
import ua.game.supportobject.MoveObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author vasyl
 */
public class UI {
    private String DicePathName = "sources/zara-0";
    private String WhitePiece = "sources/FishkaWhite.png";
    private String BlackPiece = "sources/FishkaBlack.png";
    private String GamerPriorityW = "Білі";
    private String GamerPriorityB = "Чорні";

    int x0 = 550, x1 = 12, y0 = 25, Dx = 46, dx = 31, y1 = 540, Dy = 37;

    final Display display = new Display();
    final Shell shell = new Shell(display);
    final Canvas canvas = new Canvas(shell, SWT.NONE);
    private Image piece00;
    private Image piece01;
    private BaseLogic baseLogic = new BaseLogic();
    private GameObject game;

    private Label Movelab = new Label(shell, SWT.NONE);
    private Label[] MoveLabels = new Label[4];

    private Label randomZara1 = new Label(shell, SWT.NONE);
    private Label randomZara2 = new Label(shell, SWT.NONE);
    private Image randomZaraImg1;
    private Image randomZaraImg2;

    private Label gamerColor = new Label(shell, SWT.NONE);
    private Image gamerColorImg;
    private Label gamerName = new Label(shell, SWT.NONE);

    private Label tableTitle = new Label(shell, SWT.NONE);
    private Label[] DicesDeficitLabels = new Label[6];
    private Image[] DicesDeficitImages = new Image[6];
    private Label[] DeficitLabelsWhite = new Label[6];
    private Label[] DeficitLabelsBlack = new Label[6];

    private Label testX = new Label(shell, SWT.NONE);
    private Label testY = new Label(shell, SWT.NONE);
    ArrayList<int[]>[] ActiveZones;

    private MouseAdapter CanvasUp = new MouseAdapter() {
        @Override
        public void mouseUp(MouseEvent mouseEvent) {
            super.mouseUp(mouseEvent);
            reDrawForm();
        }
    };
    private MouseMoveListener CanvasMouseMove = new MouseMoveListener() {
        @Override
        public void mouseMove(MouseEvent mouseEvent) {
            TestMouseMove(mouseEvent.x, mouseEvent.y);
        }
    };
    private ArrayList possibleMoves;

    private void TestMouseMove(int x, int y) {
        if (game.getStepCalc() > 1 & game.isWhite()) {
            Iterator xIter = ActiveZones[0].iterator();
            Iterator yIter = ActiveZones[1].iterator();
            while (xIter.hasNext() & yIter.hasNext()) {
                int[] xx = (int[]) xIter.next();
                int[] yy = (int[]) yIter.next();
                if (x <= xx[0] & x >= xx[1] & y >= yy[0] & y <= yy[1]) {
                    testX.setText("bingo");
                    testX.update();
                    testX.redraw();
                } else {
                    testX.setText("loose");
                    //testX.update();
                    //testX.redraw();
                    testY.setText("x is [" + xx[0] + ", " + xx[1] +
                            "]\ny is [" + yy[0] + ", " + yy[1] + "]");
                }
            }
            xIter = null;
            yIter = null;
        }
    }

    public UI() {
        game = new GameObject();

        shell.setText("Довгі нарди");
        shell.setSize(900, 630);

        makeForm();

        canvas.addMouseListener(CanvasUp);
        canvas.addMouseMoveListener(CanvasMouseMove);

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    private Image loadAplphaPiece(String adr) {
        ImageData imageData = new ImageData(adr);
        byte[] alphaValues = new byte[imageData.height * imageData.width];
        for (int j = 0; j < imageData.height - 1; j++) {
            for (int i = 0; i < imageData.width - 1; i++) {
                if ((imageData.getPixel(j, i) == 0xffffff) |
                        (imageData.getPixel(j, i) == 0x000000)) {
                    alphaValues[j * imageData.width + i] = (byte) 255;
                }
            }

        }
        imageData.alphaData = alphaValues;

        return new Image(display, imageData);
    }

    private void plotCanvas(String DeskPath) {
        canvas.setBounds(0, 0, 600, 600);
        Image image = new Image(display, DeskPath);
        canvas.setBackgroundImage(image);
    }

    private void changeLabelImage(Label lab, final Image img) {
        lab.addPaintListener(new PaintListener() {
            //@Override
            public void paintControl(PaintEvent paintEvent) {
                paintEvent.gc.drawImage(img, 0, 0);
            }
        });
        lab.redraw();
        lab.update();
    }

    private void plotRandomDice(String Dice0, String Dice1) {
        randomZaraImg1 = new Image(display, Dice0);
        randomZaraImg2 = new Image(display, Dice1);

        randomZara1.setBounds(610, 10, 40, 40);
        changeLabelImage(randomZara1, randomZaraImg1);
        randomZara2.setBounds(660, 10, 40, 40);
        changeLabelImage(randomZara2, randomZaraImg2);
    }

    private void plotGemerPriority() {
        String TpmGamerPiece;
        String TmpGamerText;
        if (!game.isWhite()) {
            TpmGamerPiece = BlackPiece;
            TmpGamerText = GamerPriorityB;
        } else {
            TpmGamerPiece = WhitePiece;
            TmpGamerText = GamerPriorityW;
        }
        gamerColorImg = loadAplphaPiece(TpmGamerPiece);
        gamerColor.setBounds(710, 10, 40, 40);
        changeLabelImage(gamerColor, gamerColorImg);
        gamerName.setBounds(760, 10, 800, 40);
        gamerName.setText(TmpGamerText);

        Movelab.setBounds(610, 60, 100, 20);
        Movelab.setText("Ходи фішок");
    }

    private void plotMoves() {
        int x = 610, y = 80, dx = 20;
        int[] tmpMoves = game.getMoves();
        for (int i = 0; i < tmpMoves.length; i++) {
            MoveLabels[i].setBounds(x + dx * i, y, dx, 20);
            MoveLabels[i].setText(Integer.toString(tmpMoves[i]));
            MoveLabels[i].redraw();
            MoveLabels[i].update();
        }
    }

    private void plotDeficitTable() {
        tableTitle.setText("Таблиця дефіцитів ходів\n Білі/Чорні");
        tableTitle.setBounds(610, 110, 200, 40);
        int x = 670, y = 160, dyx = 40;
        for (int i = 1; i < 7; i++) {
            DicesDeficitLabels[i - 1] = new Label(shell, SWT.NONE);
            DicesDeficitImages[i - 1] = new Image(display, DicePathName + i +
                    ".png");
            DicesDeficitLabels[i - 1].setImage(DicesDeficitImages[i - 1]);
            DicesDeficitLabels[i - 1].setBounds(x, y + dyx * (i - 1), dyx, dyx);
        }
    }

    private void showDeficitValues(int[] WhiteDeficit, int[] BlackDeficit) {
        int X = 610, Y = 165, dX = 110, dY = 40;
        for (int i = 0; i < 6; i++) {
            DeficitLabelsWhite[i] = new Label(shell, SWT.NONE);
            DeficitLabelsWhite[i].setText(Integer.toString(WhiteDeficit[i]));
            DeficitLabelsWhite[i].setBounds(X, Y + i * dY, 50, 20);

            DeficitLabelsBlack[i] = new Label(shell, SWT.NONE);
            DeficitLabelsBlack[i].setText(Integer.toString(BlackDeficit[i]));
            DeficitLabelsBlack[i].setBounds(X + dX, Y + i * dY, 50, 20);
        }
    }

    private void makeForm() {
        plotCanvas("sources/nardDesk.png");

        plotRandomDice("sources/zara-01.png", "sources/zara-01.png");
        for (int i = 0; i < MoveLabels.length; i++)
            MoveLabels[i] = new Label(shell, SWT.NONE);
        plotDeficitTable();
        showDeficitValues(new int[]{0, 0, 0, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0});

        testX.setBounds(610, 450, 90, 20);
        testY.setBounds(710, 450, 150, 50);
    }

    private void printTestMoves(ArrayList pm) {
        Iterator<MoveObject> iterMove = pm.iterator();
        MoveObject tmp;
        while (iterMove.hasNext()) {
            tmp = iterMove.next();
            System.out.print(tmp.isPermissionFlag() + "\t" +
                    tmp.getDiceMove());
            for (int i = 0; i < tmp.getStartPositions().length; i++) {
                System.out.print("\t" + tmp.getStartPositions()[i]);
                System.out.print("\t" + tmp.getStopPositions()[i]);
            }
            System.out.print("\n");
        }
    }

    private void reDrawForm() {
        if (game.getStepCalc() == 0) {
            baseLogic.rollDiceFirstTime(game);
        } else {
            baseLogic.rollDice(game);
            drawPieces(game);
            plotGemerPriority();
            plotMoves();
            if (game.isWhite()) {
                possibleMoves = baseLogic.calcMoves(game);
                printTestMoves(possibleMoves);
                ActiveZones = calcActiveZones(possibleMoves);
            }
        }
        String DiceName0 = DicePathName + game.getDice()[0] + ".png";
        String DiceName1 = DicePathName + game.getDice()[1] + ".png";
        System.out.println(game.getDice()[0] + "\t" + game.getDice()[1] + "\t" +
                game.getStepCalc() + "\t" + game.isWhite());
        plotRandomDice(DiceName0, DiceName1);
    }

    private ArrayList<int[]>[] calcActiveZones(ArrayList pm) {
        ArrayList<int[]> resX = new ArrayList<>();
        ArrayList<int[]> resY = new ArrayList<>();
        Iterator<MoveObject> iterMove = pm.iterator();
        MoveObject tmp;
        while (iterMove.hasNext()) {
            tmp = iterMove.next();
            int a[], b[];
            int[] tmpStart = tmp.getStartPositions();
            for (int q = 0; q < tmpStart.length; q++) {
                a = new int[]{x0 + Dx / 2, x0 - (tmpStart[q] + 1) * Dx + Dx / 2};
                b = new int[]{y0, game.getWhitePieces()[tmpStart[q]] * Dy + y0};
                resX.add(a);
                resY.add(b);
            }
        }
        return new ArrayList[]{resX, resY};
    }

    private void plotPieceOnCanvas(final int x, final int y, final Image img) {
        canvas.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                e.gc.drawImage(img, x, y);
            }
        });

    }

    private void drawPieces(GameObject go) {
        //int x0 = 550, x1 = 12, y0 = 25, Dx = 46, dx = 31, y1 = 540, Dy = 37;
        String bp = "sources/FishkaBlack.png";
        String wp = "sources/FishkaWhite.png";

        piece00 = loadAplphaPiece(wp);
        piece01 = loadAplphaPiece(bp);
        Image piece;

        int lengthPieces;

        int[] ArrWhite = go.getWhitePieces();
        int[] ArrBlack = go.getBlackPieces();
        for (int q = 0; q < ArrWhite.length; q++) {
            if (ArrWhite[q] >= ArrBlack[q]) {
                lengthPieces = ArrWhite[q];
                piece = piece00;
            } else {
                lengthPieces = ArrBlack[q];
                piece = piece01;
            }

            for (int w = 0; w < lengthPieces; w++) {
                if (q < 6) {
                    final int finalTmpx = x0 - q * Dx;
                    final int finalTmpy = y0 + w * Dy;
                    plotPieceOnCanvas(finalTmpx, finalTmpy, piece);
                }
                if ((q > 5) & (q < 12)) {
                    final int finalTmpx = x0 - q * Dx - dx;
                    final int finalTmpy = y0 + w * Dy;
                    plotPieceOnCanvas(finalTmpx, finalTmpy, piece);
                }
                if ((q > 11) & (q < 18)) {
                    final int finalTmpx = x1 + (q - 12) * Dx;
                    final int finalTmpy = y1 - w * Dy;
                    plotPieceOnCanvas(finalTmpx, finalTmpy, piece);
                }
                if ((q > 17) & (q < 24)) {
                    final int finalTmpx = x1 + (q - 12) * Dx + dx;
                    final int finalTmpy = y1 - w * Dy;
                    plotPieceOnCanvas(finalTmpx, finalTmpy, piece);
                }
            }
        }
        canvas.update();
        canvas.redraw();
    }

    public static void main(String[] args) {
        new UI();
    }
}
// run the java class file
//
//java -classpath ".:/home/vasyl/.m2/repository/org/eclipse/swt/org.eclipse.swt.
//gtk.linux.x86_64/4.4/org.eclipse.swt.gtk.linux.x86_64-4.4.jar" ua.game.
//baselogic.BaseLogic