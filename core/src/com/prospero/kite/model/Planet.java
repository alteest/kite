package com.prospero.kite.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.prospero.kite.model3d.ModelFactory;

public class Planet extends Sphere {

	private float distance = 0;
	ModelInstance orbit = null;
	protected Array<Station> stations = new Array<Station>();
	private final Vector3 position = new Vector3();
	
	public Planet(String name, String texture, float distance, float position, float r) {
		super(name, texture, distance * MathUtils.sin(MathUtils.degreesToRadians * position), 0f, distance * MathUtils.cos(MathUtils.degreesToRadians * position), r);
		this.distance = distance;
		loadModel();
	}

	public void addStation(Station station) {
		station.setParent(this);
		stations.add(station);
	}
	
	public void replaceStation(Station origStation, Station newStation) {
		if (newStation != null) {
			int i = stations.indexOf(origStation, true);
			if (i > -1) {
				stations.set(i, newStation);
				newStation.setParent(this);
			}
		}
	}
	
	@Override
	protected void loadModel() {
		super.loadModel();
        orbit = new ModelInstance(ModelFactory.getCircle(distance), 0, 0, 0);
        orbit.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse, Color.WHITE));
	}

	@Override
	public void render(ModelBatch modelBatch, Environment environment, GO parent) {
		//modelBatch.render(instance, shader);
		if (this.equals(parent)) {
			instance.transform.setToTranslation(0, 0, 0);
			modelBatch.render(instance, environment);
			for (Station station : stations) {
				station.render(modelBatch, environment, parent);
			}
		} else {
			instance.transform.setToTranslation(x, y, z);
			modelBatch.render(instance, environment);
			modelBatch.render(orbit, environment);
		}
	}

    public GO getObject(Ray ray) {
        GO result = null;
        float distance = -1;
        for (int i = 0; i < stations.size; ++i) {
            final GO obj = stations.get(i);
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
