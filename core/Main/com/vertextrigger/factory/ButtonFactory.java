package com.vertextrigger.factory;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.vertextrigger.assets.Assets;
import com.vertextrigger.main.VertexTrigger;

public class ButtonFactory {

    private static final Assets assets = VertexTrigger.ASSETS;

    public static Drawable getMainScreenButtonUp() {
        return assets.getMainScreenSkin().getDrawable("red_button12");
    }

    public static Drawable getMainScreenButtonDown() {
        return assets.getMainScreenSkin().getDrawable("red_button02");
    }

    public static Drawable getLeftButton() {
        return assets.getCoreSkin().getDrawable("leftButton");
    }

    public static Drawable getRightButton() {
        return assets.getCoreSkin().getDrawable("rightButton");
    }

    public static Drawable getShootButton() {
        return assets.getCoreSkin().getDrawable("shootButton");
    }

    public static Drawable getJumpButton() {
        return assets.getCoreSkin().getDrawable("jumpButton");
    }

    public static Drawable getPauseButton() {
        return assets.getCoreSkin().getDrawable("pauseButton");
    }

    public static Drawable getResumeButton() {
        return assets.getCoreSkin().getDrawable("resumeButton");
    }

}
