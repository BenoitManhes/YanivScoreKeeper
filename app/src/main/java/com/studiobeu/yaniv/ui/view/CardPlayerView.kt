package com.studiobeu.yaniv.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.studiobeu.yaniv.R
import com.studiobeu.yaniv.data.local.entity.Player
import kotlinx.android.synthetic.main.carte_player.view.*

class CardPlayerView  @JvmOverloads constructor(context: Context,
                                                val player: Player? = null) :
        ConstraintLayout(context) {

    var isSelect = false
    set(value) {
        field = value
        refreshCard()
    }

    init {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.carte_player, this)
        showDataPlayer()
        refreshCard()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * Pick player information and show it : name, image, color
     */
    private fun showDataPlayer(){
        pseudo_player_card.text = player?.name
        //TODO
        // image
        // color
    }

    /**
     * Reverse the card and toggle selection
     */
    fun toggleCard(){
        isSelect = !isSelect
        refreshCard()
    }

    /**
     * udpate view according to selection
     */
    private fun refreshCard(){
        if(isSelect) {
            image_player_card.visibility = View.VISIBLE
            back_player_card.setImageResource(idBackgroungRecto)
        }else{
            image_player_card.visibility = View.INVISIBLE
            back_player_card.setImageResource(idBackGroungVerso)
        }
    }

    companion object{
        val idBackgroungRecto = R.drawable.carte_verso
        val idBackGroungVerso = R.drawable.carte2
    }

}