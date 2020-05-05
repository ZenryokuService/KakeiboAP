/**
 * Copyright (c) 2012-present Lightweight Java Game Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Lightweight Java Game Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package zenryokuservice.gui.lwjgl.kakeibo.engine;

import org.joml.Vector3f;

import zenryokuservice.gui.lwjgl.kakeibo.engine.graph.Mesh;
import zenryokuservice.gui.lwjgl.kakeibo.engine.graph.TexturedMesh;

public class GameItem {

	private Mesh mesh;
	
    private TexturedMesh txtMesh;
    
    private final Vector3f position;
    
    private float scale;

    private final Vector3f rotation;

    /**
     * テクスチャを使用するMesh用のコンストラクタ
     * @param mesh
     */
    public GameItem(TexturedMesh mesh) {
        this.txtMesh = mesh;
        this.mesh = null;
        position = new Vector3f(0, 0, 0);
        scale = 1;
        rotation = new Vector3f(0, 0, 0);
    }

    public GameItem(Mesh mesh) {
        this.mesh = mesh;
        this.txtMesh = null;
        position = new Vector3f(0, 0, 0);
        scale = 1;
        rotation = new Vector3f(0, 0, 0);
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }
    
    public Mesh getMesh() {
        return mesh;
    }

    public TexturedMesh getTexturedMesh() {
        return txtMesh;
    }

}