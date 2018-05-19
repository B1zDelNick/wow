package ru.avp.wow.views.intro

import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import ru.avp.wow.components.base.Image
import ru.avp.wow.views.BaseView
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction

class IntroView: BaseView() {

    private var a: Image? = null
    private var v: Image? = null
    private var p: Image? = null
    private var gaming: Image? = null

    private var listener: InputListener? = null

    private var skipped = false

    init {
        showTopGui = false
        showDownGui = false

        listener = object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                if (skipped) return
                skipped = true
                skip()
            }
        }

        addListener(listener)

        touchable = Touchable.enabled

        gaming = Image("gaming")
        addActor(gaming)
        a = Image("A")
        addActor(a)
        v = Image("V")
        addActor(v)
        p = Image("P")
        addActor(p)

        gaming?.setPosition(50f, 330f)
        a?.setPosition(130f, 415f)
        //a?.isVisible = false
        v?.setPosition(205f, 410f)
        p?.setPosition(295f, -100f)

        /*val action = object : Action() {
            override fun act(delta: Float): Boolean {
                onV()
                return true
            }
        }

        val delayAction = DelayAction()
        delayAction.duration = .5f

        val moveToAction = MoveToAction()
        moveToAction.duration = 0.75f
        moveToAction.setPosition(215f, 270f)
        moveToAction.interpolation = Interpolation.linear

        val sequenceAction = SequenceAction()
        sequenceAction.addAction(delayAction)
        sequenceAction.addAction(moveToAction)
        sequenceAction.addAction(action)

        v?.addAction(sequenceAction)*/

        println("!@#")
    }

    private fun onV() {

    }

    private fun skip() {
        if (skipped) skipView()
    }

    private fun skipView() {
        println("SKIP")
    }

    override fun dispose() {

    }

    override fun toString(): String {
        return "IntroView"
    }
}