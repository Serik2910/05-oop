package com.epam.knight.controller;

import com.epam.knight.model.Knight;
import com.epam.knight.view.ConsoleView;

public class KnightApplication {

    public static void main(String[] args) {
        KnightApplication application = new KnightApplication();
        application.start();
    }

    private void start() {
        Knight knight = KnightGenerator.generateKnight();
        ConsoleView.menu(knight);
    }

}
