package ru.avp.wow.views

import com.badlogic.gdx.scenes.scene2d.Group

open class BaseView: Group() {

    var showTopGui = true
    var showDownGui = true
    var sound: String = "none"
    var showLoading = false

    open fun update(delta: Float) {}
    open fun render(delta: Float) {}
    open fun onScreenEffectComplete() {}
    open fun dispose() {}

    override fun toString(): String {
        return "BaseView"
    }
}