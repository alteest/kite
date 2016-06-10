package com.prospero.kite.model;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.prospero.kite.model.factory.AssetFactory;

public abstract class Sphere extends GO {
	
	private String textureFile = null;

    protected Sphere(final String name, final String texture, final float distance, float direction, final float scale) {
    	super(name, distance, direction, scale);
		this.textureFile = texture;
		initObjects();
	}

    protected void initObjects() {
        sprite = new Sprite(AssetFactory.getTexture(textureFile));
        sprite.setScale(scale);
    }

    /*protected void loadModel() {

        instance = new ModelInstance(ModelFactory.getSphere(r), x, y, z);
        calculateCoordinates();

        NodePart blockPart = instance.nodes.get(0).parts.get(0);

        if (texture.isPresent()) {
            Attribute attribute = TextureAttribute.createDiffuse(TextureFactory.getTexture(texture.get()));
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
	}*/
}
