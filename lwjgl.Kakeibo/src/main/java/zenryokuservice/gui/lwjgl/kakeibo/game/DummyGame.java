package zenryokuservice.gui.lwjgl.kakeibo.game;

import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

import zenryokuservice.gui.lwjgl.kakeibo.engine.graph.Mesh;
import zenryokuservice.gui.lwjgl.kakeibo.engine.graph.Texture;
import zenryokuservice.gui.lwjgl.kakeibo.engine.graph.TexturedMesh;
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
    // １週間分の配列
    private static final String[] weekTexture = new String[]{"Mon", "Tue", "Wed", "Thi", "Fri", "Sat", "Sun"};

    public DummyGame() {
        renderer = new Renderer();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        // Cubeの高さ
        ArrayList<float[]> floats = new ArrayList<>();
        floats.add(new float[] {0.0001f, 0.12f, 0.3f, 0.001f, 0.1f, 0.25f, 0.1f});
        floats.add(new float[] {0.15f, 0.19f, 0.23f, 0.2f, 0.08f, 0.13f, 0.12f});
        floats.add(new float[] {0.1f, 0.2f, 0.4f, 0.001f, 0.2f, 0.05f, 0.15f});
        floats.add(new float[] {0.11f, 0.12f, 0.3f, 0.001f, 0.1f, 0.25f, 0.1f});
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
        
        // 初めの1回目だけ曜日のテキストプレートを作成する
        boolean isCreateTexture = false;
        // 1ヶ月分(5週間分のマスを作る)
        for(int j = 1; j <= 5; j++) {
        	// 開始点より一列文ずらす
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
        	System.out.println(isCreateTexture);
            for(int i = 1; i <= 7; i++) {
            	float xAdd = xWidth * i;
            	float yAdd = yWidth * i;
            	float zAdd = zWidth * i;
            	if (isCreateTexture) {
            		arr.add(putOnTexturePlate(xAdd, yAdd, zAdd, i-1));
            	}
            	float val = weekArr[i - 1];
        		arr.add(createCube(val,
        				cubeSize, xStart + xAdd, yStart + yAdd, zStart - zAdd));
            }
        }
        // 配列の要素数を指定する
        GameItem[] items = new GameItem[arr.size()];
        // 配列の取り出し
        gameItems = arr.toArray(items);
    }

    /**
     * Texture作成メソッド
     * @param xPos
     * @param yPos
     * @param zPos
     * @param num 曜日の番号0:月〜7:日
     * @return GameItem
     */
    private GameItem putOnTexturePlate(float xPos, float yPos, float zPos, int num) {
    	float[] positions = new float[] {-0.1f, 0.1f, 0
    			, -0.1f, -0.1f, 0
    			, 0.1f, 0.1f, 0
    			, -0.1f, 0.1f, 0,};
    	float[] colours = new float[] {1, 1, 1
    			, 1, 1, 1
    			, 1, 1, 1
    			, 1, 1, 1};
    	int[] indices = new int[] {0, 1, 3, 3, 1, 2};
    	Texture texture = null;

    	try {
        	texture = new Texture("/textures/" + weekTexture[num] +  ".png");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        TexturedMesh mesh = new TexturedMesh(positions, colours, indices, texture);
        GameItem gameItem = new GameItem(mesh);
//        gameItem.setPosition(posX, posY, posZ);
        gameItem.setRotation(20, 30, 0);
        return gameItem;
    }
    
    /* 追加したメソッドです */
    private GameItem createCube(float height, float cubeSize, float posX, float posY, float posZ) {
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
        Mesh mesh = new Mesh(positions, colours, indices);
        GameItem gameItem = new GameItem(mesh);
        gameItem.setPosition(posX, posY, posZ);
        gameItem.setRotation(20, 30, 0);
        return gameItem;
    }
    /**
     * 元々のCubeデータの定義
     * @return GameItem Cubeを作成する
     */
    private GameItem createOriginCube() {
        // Create the Mesh
        float[] positions = new float[]{
            // VO
            -0.5f,  0.5f,  0.5f,
            // V1
            -0.5f, -0.5f,  0.5f,
            // V2
             0.5f, -0.5f,  0.5f,
            // V3
             0.5f,  0.5f,  0.5f,
            // V4
            -0.5f,  0.5f, -0.5f,
            // V5
             0.5f,  0.5f, -0.5f,
            // V6
            -0.5f, -0.5f, -0.5f,
            // V7
             0.5f, -0.5f, -0.5f,
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
        Mesh mesh = new Mesh(positions, colours, indices);
        GameItem gameItem = new GameItem(mesh);
        gameItem.setPosition(0, 0, -2);
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

}
