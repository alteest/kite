package com.prospero.kite.model;

import java.util.Optional;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.prospero.kite.model3d.ModelFactory;
import com.prospero.kite.model3d.TextureFactory;

public abstract class Sphere extends GO {
	
	private Optional<String> texture = null;

    protected Sphere(final String name, final String texture, final float distance, float direction, final int r) {
    	super(name, distance, direction, r);
		this.texture = Optional.ofNullable(texture);
		initObjects();
	}

    protected void initObjects() {

    	Pixmap pixmap = new Pixmap(2 * r + 1, 2 * r + 1, Pixmap.Format.RGBA8888);
        
        //Draw two lines forming an X
        pixmap.setColor(Color.YELLOW);
        pixmap.drawCircle(r, r, r);
        
        sprite = Optional.of(new Sprite(new Texture(pixmap)));    	
        pixmap.dispose();
    }

    protected void loadModel() {

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
	}
}
