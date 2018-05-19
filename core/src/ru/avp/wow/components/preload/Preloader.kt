package ru.avp.wow.components.preload

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.utils.Align
import com.mygdx.game.GameConstants
import ru.avp.wow.components.base.Image

class Preloader: Group() {

    private var bg: Image? = null
    private var logo: Image? = null

    init {
        bg = Image("bg_preload")
        addActor(bg)
        logo = Image("logo_preload")
        addActor(logo)

        logo!!.setPosition(GameConstants.GAME_WIDTH / 2f, 220f, Align.center)
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