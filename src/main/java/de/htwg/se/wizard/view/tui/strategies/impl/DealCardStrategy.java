package de.htwg.se.wizard.view.tui.strategies.impl;

import de.htwg.se.wizard.view.tui.TextUI;

public class DealCardStrategy extends TUIStrategy{


    public DealCardStrategy(TextUI tui) {
        super(tui);
    }

    @Override
    public void execute() {
        System.out.println("Cards will be dealed");
    }

    @Override
    public String toString() {
       return "DEAL-CARDS";
    }
}
