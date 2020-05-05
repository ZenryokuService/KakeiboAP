/**
 * Copyright (c) 2012-present Lightweight Java Game Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Lightweight Java Game Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package zenryokuservice.gui.lwjgl.kakeibo.game;

import java.util.Locale;

import zenryokuservice.gui.lwjgl.kakeibo.engine.GameEngine;
import zenryokuservice.gui.lwjgl.kakeibo.engine.IGameLogic;
 
public class Main {
 
	/**
	 * メインメソッド<br/>
	 * 対象の国(ロケール)を取得してシステムプロパティに設定する。
	 * <システムプロパティ>
	 * ・AppLang = システムの起動する国(ロケール)を設定する<br/>
	 * ーーーーー＞これにより日本語と英語を切り替えて起動する日本語("ja_JP")
	 * 
	 * @param args プログラム引数
	 */
    public static void main(String[] args) {
    	System.out.println("LOCALE: " + Locale.getDefault().toString());
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