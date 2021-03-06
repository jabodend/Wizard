package de.htwg.se.wizard.view.tui.strategies;

import de.htwg.se.wizard.view.tui.TextUI;
import de.htwg.se.wizard.view.tui.strategies.impl.*;

public class StrategyFactory {

    private StrategyFactory() {

    }

    public static TUIStrategy createStrategy(String strategyName, TextUI tui) {
        TUIStrategy strategy;
        switch (strategyName) {
            case "PREPARING_COUNT":
                strategy = new PreparingCountStrategy(tui);
                break;
            case "PREPARING_NAME":
                strategy = new PreparingNameStrategy(tui);
                break;
            case "DEAL_CARDS":
                strategy = new DealCardStrategy(tui);
                break;
            case "PLAY_CARDS":
                strategy = new PlayCardsStrategy(tui);
                break;
            case "MATCH_ANALYZING":
                strategy = new MatchAnalyzingStrategy(tui);
                break;
            case "PREDICTION":
                strategy = new PredictionStrategy(tui);
                break;
            case "EVALUATION":
                strategy = new EvaluationStrategy(tui);
                break;
            case "GAMEEND":
                strategy = new GameEndStrategy(tui);
                break;
            default:
                throw new IllegalArgumentException("No matched Strategy");
        }

        return strategy;
    }

}
