package com.prospero.kite.model;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;

public class SpaceSystem extends GO {
	static int w = 1000;
	static int h = 1000;
	
	protected Array<GO> staticObjects = new Array<GO>();
	private Vector3 position = new Vector3();

	public void addStaticObject(GO obj) {
		obj.setParent(this);
		staticObjects.add(obj);
	}
	
	public void generate() {
		addStaticObject(new Star("Star1", "Ako", 1.5f));
		addStaticObject(new Planet("Planet1", "Song", 2f, 1f, 0.6f));
		addStaticObject(new Planet("Planet2", "Rim", 4.5f, 2.5f, 1f));
	}

	@Override
	public void render(ModelBatch modelBatch, Environment environment, GO parent) {
		for (GO obj : staticObjects) {
			obj.render(modelBatch, environment, parent);
		}
	}
	
    public int getObjectInt(Ray ray) {
        int result = -1;
        float distance = -1;
        for (int i = 0; i < staticObjects.size; ++i) {
            final GO obj = staticObjects.get(i);
            obj.instance.transform.getTranslation(position);
            //position.add(obj.getCenter());
            position.add(obj.center);
            float dist2 = ray.origin.dst2(position);
            if (distance >= 0f && dist2 > distance) continue;
            if (Intersector.intersectRaySphere(ray, position, obj.radius, null)) {
                result = i;
                distance = dist2;
            }
        }
        return result;
    }	

    public GO getObject(Ray ray) {
        GO result = null;
        float distance = -1;
        for (int i = 0; i < staticObjects.size; ++i) {
            final GO obj = staticObjects.get(i);
            obj.instance.transform.getTranslation(position);
            //position.add(obj.getCenter());
            position.add(obj.center);
            float dist2 = ray.origin.dst2(position);
            if (distance >= 0f && dist2 > distance) continue;
            if (Intersector.intersectRaySphere(ray, position, obj.radius, null)) {
                result = obj;
                distance = dist2;
            }
        }
        return result;
    }	
    
}
