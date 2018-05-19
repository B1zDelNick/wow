package ru.avp.wow.components.base

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.mygdx.game.assets.Assets

/**
 * Created by User on 26.01.2015.
 */
class Image : Actor {

    private lateinit var texture: TextureRegion
    private lateinit var listener: InputListener
    private var scrollable: Boolean = false

    constructor(name: String, scrollable: Boolean) {
        texture = Assets.getTextureByName(name)!!
        setBounds(x, y, texture!!.regionWidth.toFloat(), texture!!.regionHeight.toFloat())
        touchable = Touchable.disabled
        this.scrollable = scrollable
    }

    constructor(name: String) {
        texture = Assets.getTextureByName(name)!!
        setBounds(x, y, texture!!.regionWidth.toFloat(), texture.regionHeight.toFloat())
        touchable = Touchable.disabled
        this.scrollable = false
    }

    constructor(texture: Texture, scrollable: Boolean) {
        this.texture = TextureRegion(texture, 0, 0, texture.width, texture.height)
        setBounds(x, y, this.texture!!.regionWidth.toFloat(), this.texture!!.regionHeight.toFloat())
        touchable = Touchable.disabled
        this.scrollable = scrollable
    }

    constructor(texture: Texture) {
        this.texture = TextureRegion(texture, 0, 0, texture.width, texture.height)
        setBounds(x, y, this.texture!!.regionWidth.toFloat(), this.texture!!.regionHeight.toFloat())
        touchable = Touchable.disabled
        this.scrollable = false
    }

    constructor(texture: Texture, isScroller: Boolean, scrollable: Boolean) {
        this.texture = TextureRegion(texture, 0, 0, texture.width, texture.height)
        setBounds(x, y, this.texture!!.regionWidth.toFloat(), this.texture!!.regionHeight.toFloat())
        if (!isScroller) {
            touchable = Touchable.disabled
            return
        }
        touchable = Touchable.enabled
        listener = object : InputListener() {
            private var lastX = -1f

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                lastX = -1f
            }

            override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                if (lastX != -1f) {
                    //GameView.currentScroll += (lastX - x) / 1.3
                }

                lastX = x
            }
        }

        addListener(listener)

        this.scrollable = scrollable
    }

    fun setImage(name: String) {
        texture = Assets.getTextureByName(name)!!
        setBounds(x, y, texture!!.regionWidth.toFloat(), texture!!.regionHeight.toFloat())
        touchable = Touchable.disabled
    }

    fun setImage(texture: Texture) {
        this.texture = TextureRegion(texture, 0, 0, texture.width, texture.height)
        setBounds(x, y, this.texture!!.regionWidth.toFloat(), this.texture!!.regionHeight.toFloat())
        touchable = Touchable.disabled
    }

    override fun act(delta: Float) {
        //if (!GameProcessor.isPaused())
            super.act(delta)
    }

    override fun draw(batch: Batch?, alpha: Float) {
        //if (!GameProcessor.isPaused())
        //batch!!.draw(texture, x + if (scrollable) GameView.currentScroll else 0, y, originX, originY, width, height, scaleX, scaleY, rotation)
        batch!!.draw(texture, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
    }

    fun dispose() {
        //texture. ?? TODO
    }
}
