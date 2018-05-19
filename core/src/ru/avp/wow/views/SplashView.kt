package com.mygdx.game.views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.GameConstants
import com.mygdx.game.assets.Assets
import ru.avp.wow.Main
import ru.avp.wow.components.base.Image

class SplashView(private val owner: Main): Screen {

    private val stage: Stage = Stage(ScreenViewport())

    private lateinit var bar: Image
    private lateinit var preloader: Image
    private lateinit var title: Image

    init {
        makePreloader()
        startLoadingAssets()
    }

    private fun makePreloader() {
        bar = Image("bar")
        stage.addActor(bar)
        preloader = Image("preloader")
        stage.addActor(preloader)
        title = Image("title")
        stage.addActor(title)

        title.setPosition(GameConstants.GAME_WIDTH / 2 - title.width / 2, GameConstants.GAME_HEIGHT / 2 - title.height / 2 + 55)
        preloader.setPosition(GameConstants.GAME_WIDTH / 2 - preloader.width / 2, title.y - 110)

        bar.setPosition(preloader.x + 10, preloader.y + 10)
    }

    private fun startLoadingAssets() {
        Assets.loadAssets()
        while (!Assets.isReady()) {
            val progress = Assets.getProgress()
            bar.width = 315 * progress
        }
        Assets.initAssets()
        /*Timer.instance().scheduleTask(object : Timer.Task() {
            fun run() {
                //owner.showLogo();
                owner.showMenu()
            }
        }, 0.2f)*/
    }

    override fun show() {
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0F, 0F, 0F, 0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.camera.update()
        stage.act(delta)
        stage.draw()
    }

    override fun pause() {
        //
    }

    override fun resume() {
        //
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, true)
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }

    override fun dispose() {
        stage.clear()
        stage.dispose()
    }
}