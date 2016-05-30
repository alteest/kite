package com.prospero.kite.model;

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.prospero.kite.model3d.ModelFactory;
import com.prospero.kite.model3d.TextureFactory;

public abstract class Sphere extends GO {
	
	private String texture = null;

    protected Sphere(final String name, final String texture, final float x, final float y, final float z, final float r) {
    	super(name, x, y, z, r);
		this.texture = texture;
	}

	protected void loadModel() {

        instance = new ModelInstance(ModelFactory.getSphere(r), x, y, z);
        calculateCoordinates();

        NodePart blockPart = instance.nodes.get(0).parts.get(0);

        if (texture != null) {
            Attribute attribute = TextureAttribute.createDiffuse(TextureFactory.getTexture(texture));
            blockPart.material.set(attribute);
        } else {
            Renderable renderable = new Renderable();
            blockPart.setRenderable(renderable);
            renderable.environment = null;
            renderable.worldTransform.idt();
           
            //RenderContext renderContext = new RenderContext(new DefaultTextureBinder(DefaultTextureBinder.WEIGHTED, 1));
            //String vert = Gdx.files.internal("data/test.vertex.glsl").readString();
            //String frag = Gdx.files.internal("data/test.fragment.glsl").readString();
            //shader = new DefaultShader(renderable, new DefaultShader.Config(vert, frag));
            //shader.init();
        } 
	}
}
