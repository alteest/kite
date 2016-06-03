package com.prospero.kite.model;

import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public abstract class GO {

	protected boolean owner;
	protected GO parent = null;

	final protected String name;
	final protected int r;
	final protected int x;
	final protected int y;
	final protected float z;
	final protected float distance;
	protected float direction;
	
    protected final Vector3 center = new Vector3();
    protected final Vector3 dimensions = new Vector3();
    protected float radius;
    protected static BoundingBox bounds = new BoundingBox();
    
	protected ModelInstance instance = null;
	protected Optional<Sprite> sprite = Optional.empty();
    protected Shader shader = null;
    
    protected GO() {
    	name = "";
    	x = 0;
    	y = 0;
    	z = 0;
    	r = 0;
    	distance = 0;
    	direction = 0;
    }

    protected GO(String name) {
    	this.name = name;
    	x = 0;
    	y = 0;
    	z = 0;
    	r = 0;
    	distance = 0;
    	direction = 0;
    }

    protected GO(final String name, float distance, float direction, final int r) {
    	this.name = name;
    	x = Math.round(distance * MathUtils.sin(MathUtils.degreesToRadians * direction));
    	y = Math.round(distance * MathUtils.cos(MathUtils.degreesToRadians * direction));
    	z = 0;
    	this.r = r;
    	this.distance = distance;
    	this.direction = direction;
    }
    
/*    protected GO(final String name, final float x, final float y, final float z, final float r) {
    	this.name = name;
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	this.r = r;
    }*/
    
	public void setOwner(boolean owner) {
		this.owner = owner;
	}
	
	public boolean isOwner() {
		return owner;
	}

	public void setParent(GO obj) {
		parent = obj;
	}

	public GO getParent() {
		return parent;
	}
	
	public Vector2 getCenter() {
		return new Vector2(x, y);
	}

	public Vector3 getCenter3() {
		return new Vector3().set(x, y, z);
	}
	
	public float getRadius() {
		return r;
	}
	
	public String getName() {
		return name;
	}
	
	public void render(ModelBatch modelBatch, Environment environment, GO parent) {}

    protected void calculateCoordinates() {
    	instance.calculateBoundingBox(bounds);
    	bounds.getCenter(center);
    	bounds.getDimensions(dimensions);
    	radius = dimensions.len() / 2f;
    }

	public void draw(SpriteBatch batch) {
		if (sprite.isPresent()) {
			sprite.get().setPosition(x, y);
			sprite.get().draw(batch);
			sprite.get().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
			sprite.get().draw(batch);
		}
	}
}
