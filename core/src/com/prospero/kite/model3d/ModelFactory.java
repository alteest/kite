package com.prospero.kite.model3d;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;

public class ModelFactory {

	private static ModelBuilder modelBuilder = new ModelBuilder();
	private static Map<String, Model> models = new HashMap<String, Model>();

	private static Model getModel(String name) {
		if (models.containsKey(name)) {
			return models.get(name);
		}
		return null;
	}

	public static Model getSphere(float r) {
		String name = "sphere_" + Float.toString(r);
		Model model = getModel(name);
		if (model == null) {
		    model = modelBuilder.createSphere(r, r, r, 50, 50, 
		    		new Material(),
		    		Usage.Position | Usage.Normal | Usage.TextureCoordinates);
		    models.put(name, model);
		}
		return model;
	}

	public static Model getCircle(float r) {
		String name = "circle_" + Float.toString(r);
		Model model = getModel(name);
		if (model == null) {
			model = createCircle(r, 50,
					GL20.GL_LINES, new Material(),
					Usage.Position | Usage.Normal | Usage.TextureCoordinates);
		    models.put(name, model);
		}
		return model;
	}

    private static Model createCircle(float radius, int divisions, int primitiveType, final Material material, final long attributes) {
        return createCircle(radius, divisions, primitiveType, 0, 360, material, attributes);
    }

    private static Model createCircle(float radius, int divisions, int primitiveType, float angleFrom, float angleTo, final Material material, final long attributes) {
    	modelBuilder.begin();
    	MeshPartBuilder partBuilder = modelBuilder.part("circle", primitiveType, attributes, material);
    	float x1 = radius * MathUtils.sin(0);
    	float z1 = radius * MathUtils.cos(0);
    	float x2 = 0;
    	float z2 = 0;
    	for (int i = 1; i < divisions + 1; i++) {
    		x2 = radius * MathUtils.sin(2 * MathUtils.PI * i / divisions);
    		z2 = radius * MathUtils.cos(2 * MathUtils.PI * i / divisions);
    		partBuilder.line(x1, 0, z1, x2, 0, z2);
    		x1 = x2;
    		z1 = z2;
    	} 
    	//modelBuilder.part("circle", primitiveType, attributes, material).circle(radius, divisions, 0, 0, 0, 0, 1, 0, angleFrom, angleTo);
    	return modelBuilder.end();
    }

//  @SuppressWarnings("deprecation")
//  private static Model createEllipse(float width, float height, float innerWidth, float innerHeight, int divisions, int primitiveType, float angleFrom, float angleTo, final Material material, final long attributes) {
//    	modelBuilder.begin();
//    	modelBuilder.part("ellipse", primitiveType, attributes, material).ellipse(width, height, innerWidth, innerHeight, divisions, 0, 0, 0, 0, 1, 0, angleFrom, angleTo);
//      return modelBuilder.end();
//  }	
}
