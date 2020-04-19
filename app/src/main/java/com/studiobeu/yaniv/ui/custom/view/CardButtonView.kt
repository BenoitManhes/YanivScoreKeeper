package com.studiobeu.yaniv.ui.custom.view

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.studiobeu.yaniv.R
import kotlinx.android.synthetic.main.card_button.view.*

@Suppress("DEPRECATION")
class CardButtonView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr) {

    // Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.card_button, this)
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        // Obtain a typed array of attributes
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CardButtonView,
                0, 0)

        try{
            // Extract custom attributes into member variables
            val textRes = typedArray.getResourceId(R.styleable.CardButtonView_text, 0)
            val imgRes = typedArray.getResourceId(R.styleable.CardButtonView_image,
                    R.drawable.carte_verso_02_h)
            val backgroundTextRes = typedArray.getResourceId(R.styleable.CardButtonView_textBackgroung,
                    R.drawable.rect_red_radius)
            val paddingRatio = typedArray.getFloat(R.styleable.CardButtonView_paddingTextRation,0.15f)

            // Apply attributes to components
            if(textRes !=0) card_text_view.text = resources.getString(textRes)
            card_view_image_verso.setImageResource(imgRes)
            card_text_view.background = resources.getDrawable(backgroundTextRes)
            if(paddingRatio <= 1f && paddingRatio >= 0f){
                card_top_guideline.setGuidelinePercent(paddingRatio)
                card_left_guideline.setGuidelinePercent(paddingRatio)
                card_bot_guideline.setGuidelinePercent(1-paddingRatio)
                card_right_guideline.setGuidelinePercent(1-paddingRatio)
            }

        }finally {
            // TypedArray objects are shared and must be recycled.
            typedArray.recycle()
        }


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}