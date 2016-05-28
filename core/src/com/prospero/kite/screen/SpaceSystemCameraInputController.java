package com.prospero.kite.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;

public class SpaceSystemCameraInputController extends CameraInputController {
	private final float minDistance = 7f;
	private final float maxDistance = 17f;

	private final Vector3 tmpV1 = new Vector3();
	
	public SpaceSystemCameraInputController (final Camera camera) {
		super(camera);
	}

	@Override
	public boolean zoom (float amount) {
		Vector3 newPos = camera.position.cpy().add(new Vector3().set(camera.direction).scl(amount));
		float distance = newPos.dst(0f, 0f, 0f);
		if ((distance > minDistance) && (distance < maxDistance)) {
			return super.zoom(amount);
		}
		return true;
	}

	@Override
	protected boolean process (float deltaX, float deltaY, int button) {
		if (button == rotateButton) {
			deltaY = 0f;
		}
	    return super.process(deltaX, deltaY, button);
	}

//	private final VelocityTracker tracker = new VelocityTracker();
//	
//	boolean longPressFired;
//	Vector2 pointer1 = new Vector2();
//	private final Vector2 pointer2 = new Vector2();
//	private final Vector2 initialPointer1 = new Vector2();
//	private final Vector2 initialPointer2 = new Vector2();
//	GestureListener listener;
//	private boolean inTapSquare;
//	private boolean pinching;
//	private boolean panning;
//	private float tapSquareCenterX, tapSquareCenterY;
//	private float tapSquareSize;
//	//private float startX, startY;
//
//	private final Task longPressTask = new Task() {
//		@Override
//		public void run () {
//			if (!longPressFired) longPressFired = listener.longPress(pointer1.x, pointer1.y);
//		}
//	};
//
//	protected SpaceCameraInputController (final CameraGestureListener gestureListener, final Camera camera) {
//		super(gestureListener);
//		this.gestureListener = gestureListener;
//		this.gestureListener.controller = this;
//		this.camera = camera;
//	}
//
//	public SpaceCameraInputController (final Camera camera) {
//		this(new CameraGestureListener(), camera);
//	}	
//
//	@Override
//	public boolean touchDragged (float x, float y, int pointer) {
//		if (pointer > 1) return false;
//		if (longPressFired) return false;
//
//		if (pointer == 0)
//			pointer1.set(x, y);
//		else
//			pointer2.set(x, y);
//
//		// handle pinch zoom
//		if (pinching) {
//			if (listener != null) {
//				boolean result = listener.pinch(initialPointer1, initialPointer2, pointer1, pointer2);
//				return listener.zoom(initialPointer1.dst(initialPointer2), pointer1.dst(pointer2)) || result;
//			}
//			return false;
//		}
//
//		// update tracker
//		tracker.update(x, y, Gdx.input.getCurrentEventTime());
//
//		// check if we are still tapping.
//		if (inTapSquare && !isWithinTapSquare(x, y, tapSquareCenterX, tapSquareCenterY)) {
//			longPressTask.cancel();
//			inTapSquare = false;
//		}
//
//		// if we have left the tap square, we are panning
//		if (!inTapSquare) {
//			panning = true;
//			return listener.pan(x, y, tracker.deltaX, tracker.deltaY);
//		}
//
//		return false;
//	}
//
//	private boolean isWithinTapSquare (float x, float y, float centerX, float centerY) {
//		return Math.abs(x - centerX) < tapSquareSize && Math.abs(y - centerY) < tapSquareSize;
//	}
//	
//	static class VelocityTracker {
//		int sampleSize = 10;
//		float lastX, lastY;
//		float deltaX, deltaY;
//		long lastTime;
//		int numSamples;
//		float[] meanX = new float[sampleSize];
//		float[] meanY = new float[sampleSize];
//		long[] meanTime = new long[sampleSize];
//
//		public void start (float x, float y, long timeStamp) {
//			lastX = x;
//			lastY = y;
//			deltaX = 0;
//			deltaY = 0;
//			numSamples = 0;
//			for (int i = 0; i < sampleSize; i++) {
//				meanX[i] = 0;
//				meanY[i] = 0;
//				meanTime[i] = 0;
//			}
//			lastTime = timeStamp;
//		}
//
//		public void update (float x, float y, long timeStamp) {
//			long currTime = timeStamp;
//			deltaX = x - lastX;
//			//deltaY = y - lastY;
//			deltaY = 0;
//			lastX = x;
//			lastY = y;
//			long deltaTime = currTime - lastTime;
//			lastTime = currTime;
//			int index = numSamples % sampleSize;
//			meanX[index] = deltaX;
//			meanY[index] = deltaY;
//			meanTime[index] = deltaTime;
//			numSamples++;
//		}
//
//		public float getVelocityX () {
//			float meanX = getAverage(this.meanX, numSamples);
//			float meanTime = getAverage(this.meanTime, numSamples) / 1000000000.0f;
//			if (meanTime == 0) return 0;
//			return meanX / meanTime;
//		}
//
//		public float getVelocityY () {
//			return 0;
//			/*float meanY = getAverage(this.meanY, numSamples);
//			float meanTime = getAverage(this.meanTime, numSamples) / 1000000000.0f;
//			if (meanTime == 0) return 0;
//			return meanY / meanTime;*/
//		}
//
//		private float getAverage (float[] values, int numSamples) {
//			numSamples = Math.min(sampleSize, numSamples);
//			float sum = 0;
//			for (int i = 0; i < numSamples; i++) {
//				sum += values[i];
//			}
//			return sum / numSamples;
//		}
//
//		private long getAverage (long[] values, int numSamples) {
//			numSamples = Math.min(sampleSize, numSamples);
//			long sum = 0;
//			for (int i = 0; i < numSamples; i++) {
//				sum += values[i];
//			}
//			if (numSamples == 0) return 0;
//			return sum / numSamples;
//		}
//
//		private float getSum (float[] values, int numSamples) {
//			numSamples = Math.min(sampleSize, numSamples);
//			float sum = 0;
//			for (int i = 0; i < numSamples; i++) {
//				sum += values[i];
//			}
//			if (numSamples == 0) return 0;
//			return sum;
//		}
//	}	
}
