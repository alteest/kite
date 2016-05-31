package com.prospero.kite.model;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public abstract class GO {

	protected boolean owner;
	protected GO parent = null;

	final protected String name;
	final protected float r;
	final protected float x;
	final protected float y;
	final protected float z;
    protected final Vector3 center = new Vector3();
    protected final Vector3 dimensions = new Vector3();
	protected float radius;
    protected static BoundingBox bounds = new BoundingBox();
    
	protected ModelInstance instance = null;
    protected Shader shader = null;
    
    protected GO() {
    	this.name = "";
    	this.x = 0;
    	this.y = 0;
    	this.z = 0;
    	this.r = 0;
    }

    protected GO(String name) {
    	this.name = name;
    	this.x = 0;
    	this.y = 0;
    	this.z = 0;
    	this.r = 0;
    }
    
    protected GO(final String name, final float x, final float y, final float z, final float r) {
    	this.name = name;
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	this.r = r;
    }
    
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
	
	public Vector3 getCenter() {
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
	
}
