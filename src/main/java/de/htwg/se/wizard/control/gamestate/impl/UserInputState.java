package de.htwg.se.wizard.control.gamestate.impl;

import de.htwg.se.wizard.control.GameControl;
import de.htwg.se.wizard.control.gamestate.IUserInputState;


public abstract class UserInputState extends State implements IUserInputState {

    public UserInputState(GameControl controller) {
        super(controller);
    }

    @Override
    public String getErrorMessage() {
        return "";
    }

}
