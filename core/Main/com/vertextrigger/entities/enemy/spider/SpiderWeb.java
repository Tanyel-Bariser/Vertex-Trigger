package com.vertextrigger.entities.enemy.spider;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Optional accompaniment to {@link Spider}
 *
 * Draws a web sprite that the Spider appears to come down and hang from
 * To use, make sure you add the sprite to the background sprites array in levelbuilder (along with portal sprites etc)
 */
public class SpiderWeb {

    private final Sprite sprite;

    public SpiderWeb(final Spider spider) {
        this.sprite = this.makeSpite(spider);
        spider.setWebSprite(this.sprite);
    }

    private Sprite makeSpite(final Spider spider) {
        final Pixmap pixmap = new Pixmap(1, 5, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(0, 2, pixmap.getWidth(), pixmap.getHeight());

        final Texture texture = new Texture(pixmap);
        pixmap.dispose();

        final Sprite sprite = new Sprite(texture);
        sprite.setSize(0.01f, 10);
        sprite.setPosition(spider.getPosition().x, spider.getPosition().y - Spider.TRAVEL_DISTANCE);
        return sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
