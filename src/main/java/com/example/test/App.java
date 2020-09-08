package com.example.test;

import com.example.test.controller.GameController;
import com.example.test.controller.impl.GameControllerImpl;

public class App {

    public static void main(String[] args) {
        System.out.println("\n\n\nWelcome to the trading card game!");
        GameController gameController = new GameControllerImpl();
        gameController.run();
    }

}
