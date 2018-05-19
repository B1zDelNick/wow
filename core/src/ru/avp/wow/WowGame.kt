package ru.avp.wow

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Timer
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.GameConstants
import com.mygdx.game.assets.Assets
import ru.avp.wow.components.preload.ContentPreloader
import ru.avp.wow.components.preload.Preloader
import ru.avp.wow.views.BaseView
import ru.avp.wow.views.intro.IntroView

class WowGame: Screen {

    private val stage: Stage = Stage(ScreenViewport())

    private var currentView: BaseView? = null
    private var contentPreloader: ContentPreloader? = null
    private var preloader: Preloader? = null

    init {
        showContentPreloader()
    }

    private fun showContentPreloader() {
        if (contentPreloader == null) {
            contentPreloader = ContentPreloader()
            stage.addActor(contentPreloader)
            contentPreloader!!.startLoadingAssets({ onContentPreloaderComplete() })
        }
    }

    private fun onContentPreloaderComplete() {
        println("On content loading complete")

        contentPreloader?.remove()
        contentPreloader?.dispose()
        contentPreloader = null

        showPreloader({})
        completeInitialization()
    }

    /**
     * Screens
     */

    private fun destroyCurrentView() {
        if (currentView != null) {
            if (currentView!!.hasParent()) {
                currentView?.remove()
            }
            currentView?.dispose()
            currentView = null
        }
    }

    private fun addView(view: BaseView) {
        //saveUser()

        if (view.showLoading) {
            addListeningAssetLoading()
            if (!Assets.isReady()) {
                showPreloader({})
            } else {
                removeListeningAssetLoading()
            }
        } else {
            removeListeningAssetLoading()
            removePreloader()
        }

        currentView = view
        stage.addActor(currentView)
    }

    private fun showIntro() {
        destroyCurrentView()
        addView(IntroView())
    }

    /**
     * Service functions
     */

    private fun initMainGui() {

    }

    private fun completeInitialization() {
        initMainGui()
        showIntro()
    }

    private fun showPreloader(func: () -> Unit) {
        if (preloader == null) {
            preloader = Preloader()
        }

        /*if (Localizer.IsInitialized())
        {
            var preloadMessages : Array = [];

            for (var i : int = 100; i < 110; i++)
            preloadMessages.push(Localizer.getText(i));

            _mPreloader.Init(preloadMessages[Math.floor(Math.random() * preloadMessages.length)]);
        }
        else
        {
            _mPreloader.Init("");
        }*/

        stage.addActor(preloader)

        Timer.instance().scheduleTask(object : Timer.Task() {
            override fun run() {
                func.invoke()
            }
        }, 1.5f)
    }

    private fun removePreloader() {
        if (preloader != null && preloader!!.hasParent()) {
            preloader?.remove()
        }
        removeListeningAssetLoading()
    }

    private fun addListeningAssetLoading() {

    }

    private fun removeListeningAssetLoading() {

    }

    /**
     * GDX functions
     */

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