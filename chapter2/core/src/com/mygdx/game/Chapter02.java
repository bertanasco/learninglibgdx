package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.MathUtils;

public class Chapter02 implements ApplicationListener {
    /* Orthographic viewport
     * Learn more about ortographic camera
	 * read http://iphonedevelopment.blogspot.de/2009/04/opengl-es-from-ground-up-part-3.html
	 */
    private OrthographicCamera camera;

    private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
    private float rotation = 0;


	@Override
	public void create () {
        /**
         * Use graphics module to query the width and height of the display
         */
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1, h/w); //Define the orthograpic camera
		batch = new SpriteBatch();

		texture =new Texture(Gdx.files.internal("data/libgdx.png"));
        /**
         * Read https://github.com/mattdesl/lwjgl-basics/wiki/LibGDX-Textures
         * Snippet:
         * Filtering
         *  The minification/magnification filters define how the image is handled upon scaling. For "pixel-art" style games,
         *  generally Filter.Nearest is suitable as it leads to hard-edge scaling without blurring. Specifying Filter.Linear will
         *  use bilinear scaling for smoother results, which is generally effective for 3D games (e.g. a 1024x1024 rock or grass
         *  texture) but not always so for a 2D game. In OpenGL, the terms used are GL_NEAREST and GL_LINEAR, respectively.
         */
		texture.setFilter( TextureFilter.Linear, TextureFilter.Linear); // p

        /**
         * Cut out certain portion of the texture referenced by texture object
         */
		TextureRegion region = new TextureRegion(texture, 0, 0 , 512, 275);
        /**
         * Read https://github.com/libgdx/libgdx/wiki/Spritebatch%2C-Textureregions%2C-and-Sprites
         */
		sprite = new Sprite(region);
        /**
         *  set the sprite's size.
         *  width = 0.9f = 90% of the image
         *  height  =
         */

		sprite.setSize(0.9f, 0.9f * sprite.getHeight()/sprite.getWidth());
        sprite.setOrigin(sprite.getWidth() /2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);

	}

	@Override
	public void dispose(){
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
        final float dps = 10.0f;
        float degreesPerSecond = 10.0f;
        rotation = (rotation+ Gdx.graphics.getDeltaTime() *
                degreesPerSecond) % 360;
        final float shakeAmplitudeInDegrees = 5.0f;
        float shake = MathUtils.sin(rotation) * shakeAmplitudeInDegrees;
        sprite.setRotation(shake);
		sprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

}
