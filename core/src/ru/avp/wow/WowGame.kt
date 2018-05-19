package ru.avp.wow

import com.badlogic.gdx.Game

class WowGame : Game() {
    override fun create() {
        /*Assets.loadPreloader()
        if (Assets.isReady()) {
            Assets.initPreloader()
            println("Init SPlash")
            setScreen(SplashView(this))
        }*/
    }

    override fun dispose() {
        super.dispose()
        //Assets.dispose()
    }
}
