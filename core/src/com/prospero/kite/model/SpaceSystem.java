package com.prospero.kite.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.prospero.kite.Kite;

public class SpaceSystem extends GO {
	
	protected Array<GO> staticObjects = new Array<GO>();
	private final Vector3 position = new Vector3();

	public void addStaticObject(GO obj) {
		obj.setParent(this);
		staticObjects.add(obj);
	}
	
	public void generate() {
		addStaticObject(new Star("Star1", "Ako", 100));

/*		Planet planet1 = new Planet("Planet1", "Song", 2f * Kite.multi, 60f, 25);
		addStaticObject(planet1);

		Planet planet2 = new Planet("Planet2", "Bog1200", 4.5f * Kite.multi, 215f, 40);

		Station station21 = new Station("Station21", 1.05f * 0.4f * Kite.multi, 0f, 20);
		planet2.addStation(station21);
		Station station22 = new Station("Station21", 1.1f * 0.4f * Kite.multi, 30f, 16);
		planet2.addStation(station22);
		Station station23 = new Station("Station21", 1.12f * 0.4f * Kite.multi, -35f, 22);
		planet2.addStation(station23);

		addStaticObject(planet2);

		Planet planet3 = new Planet("Planet3", "Cerca Trova", 8f * Kite.multi, 135f, 50);
		addStaticObject(planet3);

		Planet planet4 = new Planet("Planet3", "Blink", 13f * Kite.multi, 80f, 60);
		addStaticObject(planet4);*/
	}

	@Override
	public void render(ModelBatch modelBatch, Environment environment, GO parent) {
		for (GO obj : staticObjects) {
			obj.render(modelBatch, environment, parent);
		}
	}

	public void draw(SpriteBatch batch) {
		for (GO obj : staticObjects) {
			obj.draw(batch);
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
