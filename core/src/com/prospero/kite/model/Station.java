package com.prospero.kite.model;

public class Station extends GO {

	//ModelInstance orbit = null;
	protected StationType type = StationType.Unknown;
	
	public Station(final String name, float distance, float direction, final float scale) {
		super(name, distance, direction, scale);
		//initModel();
	}

	/*protected void initModel() {
        instance = new ModelInstance(ModelFactory.getSphereLines(r), x, y, z);
        calculateCoordinates();

        orbit = new ModelInstance(ModelFactory.getCircle(distance), 0, 0, 0);
        orbit.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse, Color.WHITE));
	}

	@Override
	public void render(ModelBatch modelBatch, Environment environment, GO parent) {
		if (this.equals(parent)) {
			instance.transform.setToTranslation(0, 0, 0);
			modelBatch.render(instance);
		} else {
			instance.transform.setToTranslation(x, y, z);
			modelBatch.render(instance);
			modelBatch.render(orbit);
		}
	}*/

	public StationType getStationType() {
		return type;
	}
	
	public static String getStringImage() {
		return "images/images/tiles_04.png";
	}
}
