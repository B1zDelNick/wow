package ru.avp.wow.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mygdx.game.GameConstants
import ru.avp.wow.WowGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        System.setProperty("user.name", "Public")
        val config = LwjglApplicationConfiguration()
        config.width = GameConstants.GAME_WIDTH
        config.height = GameConstants.GAME_HEIGHT
        config.useGL30 = false
        config.title = "World of Wizards"
        LwjglApplication(WowGame(), config)
    }
}
