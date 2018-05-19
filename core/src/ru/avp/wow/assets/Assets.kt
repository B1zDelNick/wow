package com.mygdx.game.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

object Assets {

    private val manager = AssetManager()

    private val textureAtlases = mutableMapOf<String, TextureAtlas>()

    /**
     * Initial load for splash and preloader
     */
    fun loadPreloader() {
        manager.load("android/assets/atlases/preload.atlas", TextureAtlas::class.java)
        manager.finishLoading()
    }

    fun initPreloader() {
        textureAtlases["preload"] = manager.get("android/assets/atlases/preload.atlas", TextureAtlas::class.java)
    }

    fun loadAssets() {
        manager.load("android/assets/atlases/intro.atlas", TextureAtlas::class.java)
    }

    fun initAssets() {
        textureAtlases["intro"] = manager.get("android/assets/atlases/intro.atlas", TextureAtlas::class.java)
    }

    fun getTextureByName(name: String): TextureRegion? {
        textureAtlases.values.forEach {
            if (it.findRegion(name) != null) {
                return it.findRegion(name) as TextureRegion
            }
        }
        return null
    }

    fun isReady() = manager.update()
    fun getProgress() = manager.progress

    fun dispose() {
        textureAtlases.onEach { it.value.dispose() }
    }
}