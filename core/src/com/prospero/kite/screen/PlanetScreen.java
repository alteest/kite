package com.prospero.kite.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.prospero.kite.Kite;
import com.prospero.kite.model.GO;
import com.prospero.kite.model.StationType;
import com.prospero.kite.model.factory.AssetFactory;
import com.prospero.kite.screen.menu.BuildSelectionWindow;

public class PlanetScreen extends ObjectScreen {

    TiledMap tiledMap;
    TiledMap tiledMapNonSelected;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    TiledMapTile tile1;
    TiledMapTile tile2;
    TiledMapTile tile3;

	private Vector3 position = new Vector3();
    
    private final Vector2 mousePosition;
    private int mouseButton = -1;
    private boolean mouseInside = false;
    private boolean mouseWasMoved = false;

    private final BuildSelectionWindow buildSelectionWindow;
    private StationType buildingType = StationType.Unknown;

    public PlanetScreen(final Kite game, GO obj) {
		super(game, obj);

        //Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(300, 40, 0);
        camera.update();
        /*tiledMap = new TmxMapLoader().load("data/a/map2.tmx");
        tiledMapNonSelected = new TmxMapLoader().load("data/a/map2.tmx");
        tiledMapRenderer = new IsometricTiledMapRenderer(tiledMap);
    	TiledMapTileSet tileSet = tiledMapNonSelected.getTileSets().getTileSet(0);
    	tile1 = tileSet.getTile(1);
    	tile2 = tileSet.getTile(2);
    	tile3 = new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("data/a/red_xoffset_0__yoffset_0.png"))));*/

        tile1 = new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("images/images/tiles_01.png"))));
    	tile2 = new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("images/images/tiles_02.png"))));
    	tile3 = new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("images/images/tiles_03.png"))));

        tiledMap = new TmxMapLoader().load("data/a/map3.tmx");
        tiledMapNonSelected = new TmxMapLoader().load("data/a/map3.tmx");
        TiledMap [] maps = {tiledMap, tiledMapNonSelected};
    	for (TiledMap map : maps) {
    		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
    		for (int i = 0 ; i < layer.getWidth(); i++) {
    			for (int j = 0; j < layer.getHeight(); j++) {
    				try {
    					Cell cell = layer.getCell(i, j);
    					if (cell == null) {
    						cell = new Cell();
    						layer.setCell(i, j, cell);
    					}
    					cell.setTile(tile1);
    				} catch (NullPointerException e) {
    					System.out.println("Init : " + Integer.toString(i) + " - " + Integer.toString(j));
    				}
    			}
    		}
    	}

        tiledMapRenderer = new IsometricTiledMapRenderer(tiledMap);
    	
    	// ------ UI ---------------
		Skin skin = game.getSkin();
		buildSelectionWindow = new BuildSelectionWindow("Select building", skin, this);
		buildSelectionWindow.setVisible(false);
		stage.addActor(buildSelectionWindow);

		Texture t = new Texture("images/ui/build.png");
		TextureRegion tr = new TextureRegion(t);
		ImageButtonStyle style = new ImageButtonStyle(skin.get(ButtonStyle.class));
		style.imageUp = new TextureRegionDrawable(tr);
		style.imageDown = new TextureRegionDrawable(tr);
		ImageButton b1 = new ImageButton(style);
		b1.setSize(Gdx.graphics.getWidth() / 5, Gdx.graphics.getWidth() / 5);
		b1.setPosition(0, 0);
		b1.addListener(new ClickListener() {
	    	@Override
	        public void clicked(InputEvent e, float x, float y) {
	        	buildSelectionWindow.setVisible(!buildSelectionWindow.isVisible());
	            Gdx.app.log("Click", "performed");
	        }
	    });		
		stage.addActor(b1);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, this));

        mousePosition = new Vector2();
	}

	@Override
	protected String getBackgroundFileName() {
		return "images/bg3.jpg";
	}

	public void setBuildingType(StationType type) {
		buildingType = type;
	}
	
	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        if (background != null) {
        	background.draw();
        }

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        if (stringBuilder != null) {
        	stringBuilder.setLength(0);
        	stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());
        	if (selectedObj != null) {
        		stringBuilder.append(" : ").append(selectedObj.getName());
        	}
        	label.setText(stringBuilder);
        }
        if (stage != null) {
        	stage.draw();
        }
	}		

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK || keycode == Keys.ESCAPE){
			if (buildSelectionWindow.isVisible()) {
				buildSelectionWindow.setVisible(false);
			} else {
				game.setScreen(new SpaceSystemScreen(game, object.getParent()));
			}
			return true;
		 }
		 return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
	    if(keycode == Input.Keys.LEFT)
	        camera.translate(-32,0);
	    if(keycode == Input.Keys.RIGHT)
	        camera.translate(32,0);
	    if(keycode == Input.Keys.UP)
	        camera.translate(0,-32);
	    if(keycode == Input.Keys.DOWN)
	        camera.translate(0,32);
	    if(keycode == Input.Keys.NUM_1)
	        tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
	    return false;
	}	

	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMapNonSelected.getLayers().get(0);
		position = worldToIso(x, y,  (int) layer.getTileWidth(), (int) layer.getTileHeight());
		if (buildingType != StationType.Unknown) {
			// We are in bulding mode
			if (button == Input.Buttons.LEFT) {
				StaticTiledMapTile tile = new StaticTiledMapTile(new TextureRegion(AssetFactory.getStationImage(buildingType)));
				int posX, posY;
				posX = Math.min(layer.getWidth() - 1, Math.max((int)position.x, 0));
				posY = Math.min(layer.getHeight() - 1, Math.max((int)position.y, 0));
				layer.getCell(posX, posY).setTile(tile);
			}
	    	buildingType = StationType.Unknown;
		} else {
			if ((position.x >= 0) && (position.y >= 0) && (position.x < layer.getWidth()) && (position.y < layer.getHeight())) {
				mouseInside = true;
				mousePosition.set(position.x, position.y);
			} else {
				mouseInside = false;
				if (button == Input.Buttons.LEFT) {
					mousePosition.set(x, y);
				}
			}
			mouseButton = button;
		}
	    return false;
	}

	private Vector3 worldToIso(Vector3 point, int tileWidth, int tileHeight) {
	    camera.unproject(point);
	    point.x /= tileWidth;
	    point.y = (point.y - tileHeight / 2) / tileHeight + point.x;
	    point.x -= point.y - point.x;
	    return point;
	}

	private Vector3 worldToIso(int x, int y, int tileWidth, int tileHeight) {
		Vector3 point = new Vector3(x, y, 0);
		return worldToIso(point, tileWidth, tileHeight);
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button)
	{
		if (mouseInside && !mouseWasMoved) {
			TiledMapTileLayer layer = (TiledMapTileLayer) tiledMapNonSelected.getLayers().get(0);
		    position = worldToIso(x, y,  (int) layer.getTileWidth(), (int) layer.getTileHeight());
			if (((int) position.x == (int) mousePosition.x) && ((int) position.y == (int) mousePosition.y)) {
				Cell cell = layer.getCell((int)position.x, (int)position.y);
				try {
					if (cell.getTile() == tile1) {
						cell.setTile(tile2);
					} else if (cell.getTile() == tile2) {
						cell.setTile(tile1);
					}
				} catch (NullPointerException e) {
					System.out.println("NULL");
					System.out.println(position.toString());
				}
			}
			copyMap(tiledMapNonSelected, tiledMap);
		}
		mouseButton = -1;
		mouseInside = false;
		mouseWasMoved = false;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer)
	{
		if (mouseInside) {
	    	TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		    position = worldToIso(x, y,  (int) layer.getTileWidth(), (int) layer.getTileHeight());
	    	position.x = (position.x < 0) ? 0 : position.x;
	    	position.x = (position.x > layer.getWidth()) ? layer.getWidth() - 1 : position.x;
	    	position.y = (position.y < 0) ? 0 : position.y;
	    	position.y = (position.y > layer.getHeight()) ? layer.getHeight() - 1 : position.y;
	    	int maxX, maxY, minX, minY;
	    	if (mousePosition.x > position.x) {
	    		maxX = (int)mousePosition.x;
	    		minX = (int)position.x;
	    	} else {
	    		minX = (int)mousePosition.x;
	    		maxX = (int)position.x;
	    	}
	    	if (mousePosition.y > position.y) {
	    		maxY = (int)mousePosition.y;
	    		minY = (int)position.y;
	    	} else {
	    		minY = (int)mousePosition.y;
	    		maxY = (int)position.y;
	    	}
	    	copyMap(tiledMapNonSelected, tiledMap);
	    	if ((minX != maxX) || (minY != maxY)) {
	    		mouseWasMoved = true;
	    		for (int i = minX; i <= maxX; i++) {
	    			for (int j = minY; j <= maxY; j++) {
	    				try {
	    					Cell cell = layer.getCell(i, j);
	    					if (cell != null) {
	    						cell.setTile(tile3);
	    					}
	    				} catch (NullPointerException e) {
	    					System.out.println("NULL : " + Integer.toString(i) + " - " + Integer.toString(j));
	    				}
	    			}
	    		}
	    	}
		} else if (mouseButton == Input.Buttons.LEFT)
		{
			camera.translate(mousePosition.x - x, y - mousePosition.y);
			mousePosition.set(x, y);
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int x, int y) {
		if (buildingType != StationType.Unknown) {
	    	TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		    position = worldToIso(x, y,  (int) layer.getTileWidth(), (int) layer.getTileHeight());
		    
		    StaticTiledMapTile tile = new StaticTiledMapTile(new TextureRegion(AssetFactory.getStationImage(buildingType)));
	    	int posX, posY;
	    	posX = Math.min(layer.getWidth() - 1, Math.max((int)position.x, 0));
	    	posY = Math.min(layer.getHeight() - 1, Math.max((int)position.y, 0));

	    	copyMap(tiledMapNonSelected, tiledMap);

	    	layer.getCell(posX, posY).setTile(tile);
		}
		return false;
	}
	
	private void copyMap(TiledMap source, TiledMap destination) {
    	TiledMapTileLayer layer1 = (TiledMapTileLayer) source.getLayers().get(0);
    	TiledMapTileLayer layer2 = (TiledMapTileLayer) destination.getLayers().get(0);
    	for (int i = 0 ; i < layer1.getWidth(); i++) {
	    	for (int j = 0; j < layer1.getHeight(); j++) {
	    		try {
	    			layer2.getCell(i, j).setTile(layer1.getCell(i, j).getTile());
	    		} catch (NullPointerException e) {
	    			System.out.println("Copy : " + Integer.toString(i) + " - " + Integer.toString(j));
	    		}
	    	}
    	}
	}
}
