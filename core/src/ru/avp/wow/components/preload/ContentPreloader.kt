package ru.avp.wow.components.preload

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.utils.Align
import com.mygdx.game.GameConstants
import com.mygdx.game.assets.Assets
import ru.avp.wow.components.base.Image

class ContentPreloader: Group() {

    private var bg: Image? = null
    private var logo: Image? = null

    init {
        bg = Image("bg_preload")
        addActor(bg)
        logo = Image("logo_preload")
        addActor(logo)

        logo!!.setPosition(GameConstants.GAME_WIDTH / 2f, 220f, Align.center)
    }

    fun startLoadingAssets(func: () -> Unit) {
        Assets.loadAssets()
        while (!Assets.isReady()) {
            /*val progress = Assets.getProgress()
            bar.width = 315 * progress*/
        }
        Assets.initAssets()
        func.invoke()
    }

    fun dispose() {
        bg?.remove()
        bg?.dispose()
        bg = null
        logo?.remove()
        logo?.dispose()
        logo = null
    }
}