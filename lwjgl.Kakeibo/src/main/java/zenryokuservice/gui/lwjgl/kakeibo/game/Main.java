package zenryokuservice.gui.lwjgl.kakeibo.game;

import zenryokuservice.gui.lwjgl.kakeibo.engine.GameEngine;
import zenryokuservice.gui.lwjgl.kakeibo.engine.IGameLogic;
 
public class Main {
 
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new DummyGame();
            GameEngine gameEng = new GameEngine("GAME", 600, 480, vSync, gameLogic);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}