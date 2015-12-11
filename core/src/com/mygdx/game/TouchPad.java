package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by Dinna on 09/12/2015.
 */
public class TouchPad extends Actor {
    public Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin skTouchPad;
    private Drawable drwTouchbg;
    private Drawable drwTouchpad;
    private float fSpeed;
    Sprite spFG, spBG;
    Character character;
    boolean[] arbDirections = new boolean[4];
    boolean bStop;
    int nCurrentIndex;

    public void setCharacter(Character _character, boolean[] _arbDirs, boolean _bStop) {
        character = _character;
        arbDirections = _arbDirs;
        bStop = _bStop;
    }

    // https://github.com/JStruk/Bomberman/blob/master/core/src/com/mygdx/game/Thumbstick.java
    public TouchPad() {
        //Create a skin for the touchpad
        skTouchPad = new Skin();
        //Set background and knob imgs
        spFG = new Sprite(new Texture("ThumbstickFGsmall.png"));
        spBG = new Sprite(new Texture("ThumbstickBG.png"));
        skTouchPad.add("drwTouchbg", spBG);
        skTouchPad.add("drwTouchpad", spFG);
        touchpadStyle = new com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle();
        //make drawables based off the skin
        drwTouchbg = skTouchPad.getDrawable("drwTouchbg");
        drwTouchpad = skTouchPad.getDrawable("drwTouchpad");
        //Apply the bg and knob drawables to the touchpad
        touchpadStyle.background = drwTouchbg;
        touchpadStyle.knob = drwTouchpad;

        //Initiate the touchpad based on the style we just created
        touchpad = new com.badlogic.gdx.scenes.scene2d.ui.Touchpad(10, touchpadStyle);
        //set where the touchpad will be on the screen
        touchpad.setBounds(30, 30, 100, 100);
    }

    public void render() {
        if (touchpad.getKnobPercentX() > .75) {//up,down,right,left
            character.setCharacterVelocity(1, 0);
            arbDirections[3] = true;
            nCurrentIndex = 3;
            bStop = false;
        } else if (touchpad.getKnobPercentX() < -.75) {
            character.setCharacterVelocity(-1, 0);
            arbDirections[2] = true;
            nCurrentIndex = 2;
            bStop = false;
        } else if (touchpad.getKnobPercentY() > .75) {
            character.setCharacterVelocity(0, 1);
            arbDirections[0] = true;
            nCurrentIndex = 0;
            bStop = false;
        } else if (touchpad.getKnobPercentY() < -.75) {
            character.setCharacterVelocity(0, -1);
            arbDirections[1] = true;
            nCurrentIndex = 1;
            bStop = false;
        }
        if (touchpad.getKnobPercentY() > -.75 && touchpad.getKnobPercentY() < .75 && touchpad.getKnobPercentX() > -.75 && touchpad.getKnobPercentX() < .75) {
            character.setCharacterVelocity(0, 0);
            bStop = true;
        }
        character.getBoolsBack(arbDirections, bStop, nCurrentIndex);
    }
}