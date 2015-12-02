package de.htwg.se.java.wizard.view.tui;

/**
 * Created by Tamara on 13.11.2015.
 */

import de.htwg.se.java.util.observer.IObserver;
import de.htwg.se.java.wizard.control.WizardController;
import de.htwg.se.java.wizard.control.gameStatus;
import de.htwg.se.java.wizard.model.card.Card;

import java.util.List;

import static java.lang.System.out;

public class TUI implements IObserver {

    private WizardController controller;

    public TUI(WizardController controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    @Override
    public void update() {
        printTUI();
    }

    public void printTUI() {
        List<Card> cardsCurPlayer = controller.getCardsOfCurrentPlayer();
        List<Card> playedCards = controller.getPlayedCards();
        int curPlayer = controller.getCurPlayer();
        int score = controller.getScores().get(curPlayer);

        out.printf("ROUND %d +++++++++++++++++++++++++++++++++++++++++\n", controller.getCurRound());
        for (Card c : playedCards) {
            out.printf("%s ", c.toString());
        }
        //leere Slots einf�gen
        for (int i = 0; i < controller.getNumberOfPlayers() - playedCards.size(); i++) {
            out.printf("[      ] ");
        }
        out.println();
        out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
        out.printf("Player %d:\n", controller.getCurPlayer() + 1);
        //Counter - only 6 cards per row
        int i = 0;
        for (Card c : cardsCurPlayer) {
            if (i++ == 6) {
                i = 0;
                out.println();
            }
            out.printf("%s ", c.toString());
        }
        out.println();

        if (controller.getStatus() == gameStatus.PREDICTION) {
            out.printf("Total: %d\n", score);
            out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
            out.println("Please type in your prediction: (q to quit)");
        } else {
            int trick = controller.getTricks().get(curPlayer);
            int prediction = controller.getPredictions().get(curPlayer);
            out.printf("Prediction for this round: %d tricks\t", prediction);
            out.printf("Tricks made: %d\t", trick);
            out.printf("Total: %d\n", score);
            out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
            out.println("Please type in the number of the card you want to play: (q to quit)");
        }
        out.println();
    }

    public boolean processInputLine(String line) {
        if (line.equalsIgnoreCase("q")) {
            //quit
            return true;
        }
        int input;
        try {
            input = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            out.println("Input is not a number! Try again, moron.");
            return false;
        }
        if (controller.getStatus() == gameStatus.PREDICTION) {
            controller.predict(input);
        } else {
            boolean endOfRound = (controller.getCurPlayer() == controller.getLastPlayer());
            controller.playCard(input);
            if (endOfRound) {
                printScores();
            }
        }
        out.println(controller.getStatusMessage());
        return false;
    }

    private void printScores() {
        List<Integer> predictions = controller.getPredictions();
        List<Integer> tricks = controller.getTricks();
        List<Integer> scores = controller.getScores();

        out.printf("RESULTS ROUND %d +++++++++++++++++++++++++++++++++++++++++\n", controller.getCurRound());
        for (int i=0; i < controller.getNumberOfPlayers(); i++) {
            out.printf("Player %d:\n", i + 1);
            //TODO: Stats ausgeben
        }
    }

    public int processInputLineNumberOfPlayers(int max, String line) {
        /* Number of players */
        if (line.matches("q")) {
            //quit
            return - 1;
        }
        int players;
        try {
            players = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            out.println("Invalid input! Type in a number between 2-6, you moron");
            return - 1;
        }

        return players;
    }

    public boolean processInputLineNames(String line) {
        if (line.matches("q")) {
            return true;
        }
        controller.addPlayer(line);

        return false;
    }


}