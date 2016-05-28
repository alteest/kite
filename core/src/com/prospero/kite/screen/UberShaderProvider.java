package com.prospero.kite.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.BaseShaderProvider;

public class UberShaderProvider extends BaseShaderProvider {
	 
    private String shaderName;
 
    public UberShaderProvider (String shaderName) {
        this.shaderName = shaderName;
    }
 
	@Override
	protected Shader createShader(Renderable renderable) {
        String vert = Gdx.files.internal("data/test.vertex.glsl").readString();
        String frag = Gdx.files.internal("data/test.fragment.glsl").readString();
        return new DefaultShader(renderable, new DefaultShader.Config(vert, frag));
	}
 
}