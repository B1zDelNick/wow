package ru.avp.wow

import com.badlogic.gdx.Game
import com.mygdx.game.assets.Assets
import com.mygdx.game.views.SplashView

class Main : Game() {

    override fun create() {
        // connect to server
        Assets.loadPreloader()
        if (Assets.isReady()) {
            Assets.initPreloader()
            setScreen(WowGame())
        }
    }

    override fun resume() {
        super.resume()
        //SoundManager.activate()
    }

    override fun pause() {
        super.pause()
        //SoundManager.deactivate()
    }

    override fun dispose() {
        super.dispose()
        Assets.dispose()
    }
}
