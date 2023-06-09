package com.mewz.wechat.views.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.mewz.wechat.R

class RoundedProfileImage @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    private var contextCircular = context

    private var cornerRadius = 0f
    private var isActive = false
    private var hasBorderCircle = true
    private val path = Path()

    private val DEFAULT_ACTIVE_CIRCLE_COLOR =
        ContextCompat.getColor(contextCircular, R.color.colorAccent)

    private val paintForBorder = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = DEFAULT_BORDER_COLOR
        style = Paint.Style.STROKE
        strokeWidth = DEFAULT_BORDER_WIDTH
    }

    companion object {
        private const val DEFAULT_BORDER_WIDTH = 8.0f
        private const val DEFAULT_BORDER_COLOR = Color.WHITE
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.RoundedProfileImage) {
            cornerRadius = getDimension(R.styleable.RoundedProfileImage_cornerRadius, 0f)
            hasBorderCircle = getBoolean(R.styleable.RoundedProfileImage_hasBorderCircle,true)
        }

        translationZ = 8f
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        val rectangle = RectF(0f, 0f, width.toFloat(), height.toFloat())
        path.addRoundRect(rectangle, cornerRadius, cornerRadius, Path.Direction.CCW)
        canvas?.clipPath(path)
        super.onDraw(canvas)

        if(hasBorderCircle) {
            onDrawBorderCircle(canvas)
        }

        if (isActive) {
            onDrawActiveCircle(canvas)
        }
    }

    private fun onDrawBorderCircle(canvas: Canvas?) {
        canvas?.drawCircle(
            width / 2f,
            height / 2f,
            cornerRadius - DEFAULT_BORDER_WIDTH / 2f,
            paintForBorder
        )
    }

    private fun onDrawActiveCircle(canvas: Canvas?) {

        // For Active Circle
        val paintForActiveCircle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = DEFAULT_ACTIVE_CIRCLE_COLOR
            translationZ = 8f
            elevation = 8f
            bringToFront()
        }
        canvas?.drawCircle(
            width.toFloat() * 0.73f,
            height.toFloat() * 0.9f,
            cornerRadius / 8,
            paintForActiveCircle
        )

        // For Active Circle Border
        canvas?.drawCircle(
            width.toFloat() * 0.73f,
            height.toFloat() * 0.9f,
            (cornerRadius / 8) + DEFAULT_BORDER_WIDTH / 2f,
            paintForBorder
        )
    }
}