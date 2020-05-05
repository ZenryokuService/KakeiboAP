/**
 * Copyright (c) 2012-present Lightweight Java Game Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Lightweight Java Game Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package zenryokuservice.gui.lwjgl.kakeibo.game;

import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

import zenryokuservice.gui.lwjgl.kakeibo.engine.graph.Mesh;
import zenryokuservice.gui.lwjgl.kakeibo.engine.graph.Texture;
import zenryokuservice.gui.lwjgl.kakeibo.engine.graph.TexturedMesh;
import zenryokuservice.gui.lwjgl.kakeibo.util.CalendarPosit;
import zenryokuservice.gui.lwjgl.kakeibo.engine.GameItem;
import zenryokuservice.gui.lwjgl.kakeibo.engine.IGameLogic;
import zenryokuservice.gui.lwjgl.kakeibo.engine.Window;

public class DummyGame implements IGameLogic {

    private int displxInc = 0;

    private int displyInc = 0;

    private int displzInc = 0;

    private int scaleInc = 0;

    private final Renderer renderer;

    private GameItem[] gameItems;
    /**
     *  曜日の表示順
     *  true: 月曜始まり false: 日曜始まり
     */
    private boolean isStartMon;
    /** 日曜始まり１週間分の配列 */
    private static final String[] WEEK_TEXTURE_SUN = new String[]{"Sun", "Mon", "Tue", "Wed", "Thi", "Fri", "Sat"};
    /** 月曜始まり１週間分の配列 */
    private static final String[] WEEK_TEXTURE_MON = new String[]{"Mon", "Tue", "Wed", "Thi", "Fri", "Sat", "Sun"};

    public DummyGame() {
        renderer = new Renderer();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        isStartMon = true;
        CalendarPosit pos = new CalendarPosit();
        // 月初の曜日
        int firstDayOfWeek = pos.getStartPoint(isStartMon);
        // 今月の最大日数
        int maxDayOfMonth = pos.getMaxDayOfMonth();
        // Cubeの高さ
        ArrayList<float[]> floats = new ArrayList<>();
        floats.add(new float[] {0.0001f, 0.12f, 0.3f, 0.001f, 0.1f, 0.25f, 0.1f});
        floats.add(new float[] {0.15f, 0.19f, 0.23f, 0.2f, 0.08f, 0.13f, 0.12f});
        floats.add(new float[] {0.1f, 0.2f, 0.4f, 0.001f, 0.2f, 0.05f, 0.15f});
        floats.add(new float[] {0.11f, 0.12f, 0.3f, 0.001f, 0.1f, 0.25f, 0.1f});
        floats.add(new float[] {0.12f, 0.13f, 0.14f, 0.015f, 0.16f, 0.17f, 0.18f});
        floats.add(new float[] {0.12f, 0.13f, 0.14f, 0.015f, 0.16f, 0.17f, 0.18f});

        // Cubeの底面のサイズ(正方形)
        final float cubeSize = 0.1f;
        // x軸の初期値
        final float xInit = -0.5f;
        // y軸の初期値
        final float yInit = -0.8f;
        // z軸の初期値
        final float zInit = -2;
        // x軸の増減幅
        final float xWidth = 0.185f;
        // y軸の増減幅
        final float yWidth = 0.033f;
        // z軸の増減幅
        final float zWidth = 0.1f;
        ArrayList<GameItem> arr = new ArrayList<GameItem>();
        
        // マスのカウント
        int box = 0;
        // 日数
        int day = 0;
        // 初めの1回目だけ曜日のテキストプレートを作成する
        boolean isCreateTexture = false;
        // 1ヶ月分(5週間分のマスを作る)
        for(int j = 1; j <= 6; j++) {
        	// 開始点より一列ずらす
        	// X軸の開始点
        	float xStart = xInit - (0.1f * j);
        	// Y軸の開始点
        	float yStart = yInit + (0.06f * j) ;
        	// Z軸の開始点
        	float zStart = zInit - (0.16f * j);
            // １週間分
        	float[] weekArr = floats.get(j-1);
        	
        	// 1回目だけテクスチャを作成する
        	isCreateTexture = j == 1 ? true: false;
            for(int i = 1; i <= 7; i++) {
            	// Cubeをループごとにずらす幅(x, y, z)
            	float xAdd = xWidth * i;
            	float yAdd = yWidth * i;
            	float zAdd = zWidth * i;
        		// 日付をインクリメント
        		box++;
            	if (isCreateTexture) {
            		// 
            		arr.add(putOnTexturePlate(xStart + xAdd + 0.01f , yStart + yAdd - 0.03f, zStart - zAdd + 0.03f, i-1));
            	}
            	// 描画開始の位置
           	    if (box < firstDayOfWeek) {
            		continue;
            	}
           	    // 最大日付に達した時に描画処理を抜ける
           	    day++;
            	if (day >= maxDayOfMonth) {
            		break;
            	}
            	float val = weekArr[i - 1];
            	// val = 高さ
        		arr.add(createCube(val,
        				// Cube底面のサイズと位置
        				cubeSize, xStart + xAdd, yStart + yAdd, zStart - zAdd
        				// j=第〜週, i=曜日
        				, j, i));
            }
        }
        // 配列の要素数を指定する
        GameItem[] items = new GameItem[arr.size()];
        // 配列の取り出し
        gameItems = arr.toArray(items);
        
        // DEBUG
        //debug();
    }

    @Deprecated
    private void debug() {
    	for(GameItem item : gameItems) {
    		System.out.println("VAO_ID: " + item.getTexturedMesh().getVaoId() + "VAO_CNT" + item.getTexturedMesh().getVertexCount());
    	}
    }
    
    /**
     * Textureをつけたメッシュの作成メソッド
     * 
     * @param xPos X軸のポジション(FLOAT)
     * @param yPos Y軸のポジション(FLOAT)
     * @param zPos Z軸のポジション(FLOAT)
     * @param num 曜日の番号[0:月〜7:日]
     * @return GameItem 作成したメッシュのゲーム用ラッパークラス
     */
    private GameItem putOnTexturePlate(float xPos, float yPos, float zPos, int num) {
    	float size = 0.08f;
    	float[] positions = new float[] {
    			-1 * size, size, size, // V0
    			-1 * size, -1 * size, size, //V1 
    			size, -1 * size, size, // V2
    			size, size, size, // V3
    			
    			-1 * size, size, -1 * size, // V4
    			size, -1 * size, size, //V5 
    			-1 * size, -1 * size, -1 * size, // V6
    			size, -1 * size, -1 * size, // V7
    			};
    	float[] textCoords = new float[]{
                0.0f, 0.0f, 
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f,
            };
    	int[] indices = new int[] {
    			0, 1, 3, 3, 1, 2
			};
    	Texture texture = null;

    	try {
    		if(isStartMon) {
    			num = num < 6 ? num + 1 : 0;
        		texture = new Texture("/textures/" + WEEK_TEXTURE_MON[num] +  ".png");
    		}
    		texture = new Texture("/textures/" + WEEK_TEXTURE_SUN[num] +  ".png");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        TexturedMesh mesh = new TexturedMesh(positions, textCoords, indices, texture);
        GameItem gameItem = new GameItem(mesh);
        gameItem.setPosition(xPos, yPos, zPos);
        gameItem.setRotation(20, 30, 0);
        return gameItem;
    }
    
    /* 追加したメソッドです */
    private GameItem createCube(float height, float cubeSize, float posX, float posY, float posZ, int week, int day_of_week) {
        // Create the Mesh
        float[] positions = new float[]{
            // VO
            -1 * cubeSize,  height,  cubeSize,
            // V1
            -1 * cubeSize, -1 * cubeSize,  cubeSize,
            // V2
            cubeSize, -1 * cubeSize,  cubeSize,
            // V3
            cubeSize,  height,  cubeSize,
            // V4
            -1 * cubeSize,  height, -1 * cubeSize,
            // V5
            cubeSize,  height, -1 * cubeSize,
            // V6
            -1 * cubeSize, -1 * cubeSize, -1 * cubeSize,
            // V7
            cubeSize, -1 * cubeSize, -1 * cubeSize,
        };
        float[] colours = new float[]{
            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
            0.0f, 0.5f, 0.5f,
            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
            0.0f, 0.5f, 0.5f,
        };
        int[] indices = new int[]{
            // Front face
            0, 1, 3, 3, 1, 2,
            // Top Face
            4, 0, 3, 5, 4, 3,
            // Right face
            3, 2, 7, 5, 3, 7,
            // Left face
            6, 1, 0, 6, 0, 4,
            // Bottom face
            2, 1, 6, 2, 6, 7,
            // Back face
            7, 6, 4, 7, 4, 5,
        };
    	Texture texture = null;
    	String pngName = getPngName(week, day_of_week);
    	try {
        	texture = new Texture("/textures/wood1.png");
        	
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        TexturedMesh mesh = new TexturedMesh(positions, colours, indices, texture);
        GameItem gameItem = new GameItem(mesh);
        gameItem.setPosition(posX, posY, posZ);
        gameItem.setRotation(20, 30, 0);
        return gameItem;
    }

    @Override
    public void input(Window window) {
        displyInc = 0;
        displxInc = 0;
        displzInc = 0;
        scaleInc = 0;
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            displyInc = 1;
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            displyInc = -1;
        } else if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            displxInc = -1;
        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            displxInc = 1;
        } else if (window.isKeyPressed(GLFW_KEY_A)) {
            displzInc = -1;
        } else if (window.isKeyPressed(GLFW_KEY_Q)) {
            displzInc = 1;
        } else if (window.isKeyPressed(GLFW_KEY_Z)) {
            scaleInc = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            scaleInc = 1;
        }
    }

    @Override
    public void update(float interval) {
        for (GameItem gameItem : gameItems) {
            // Update position
            Vector3f itemPos = gameItem.getPosition();
            float posx = itemPos.x + displxInc * 0.01f;
            float posy = itemPos.y + displyInc * 0.01f;
            float posz = itemPos.z + displzInc * 0.01f;
            gameItem.setPosition(posx, posy, posz);
            
            // Update scale
            float scale = gameItem.getScale();
            scale += scaleInc * 0.05f;
            if ( scale < 0 ) {
                scale = 0;
            }
            gameItem.setScale(scale);
            
            // Update rotation angle
//            float rotation = gameItem.getRotation().x + 1.5f;
//            if ( rotation > 360 ) {
//                rotation = 0;
//            }
//            gameItem.setRotation(rotation, rotation, rotation);            
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, gameItems);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
        	if (gameItem.getMesh() != null) {
        		gameItem.getMesh().cleanUp();
        	} else {
        		gameItem.getTexturedMesh().cleanUp();
        	}
        }
    }

    private String getPngName(int week, int day_of_week) {
    	String png = "";
    	
    	return png;
    }
}