package dev.filipebezerra.android.popthriftstore.ui.badge

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import dev.filipebezerra.android.popthriftstore.R

class BadgeDrawable(context: Context) : Drawable() {

    var count = 0
        set(value) {
            if (value != field) {
                field = value
                mustRedraw = true
                invalidateSelf()
            }
        }

    private val badgePaint: Paint = Paint()
    private val textPaint: Paint
    private val textSize = context.resources.getDimension(R.dimen.badge_text_size)
    private val textRect = Rect()
    private var mustRedraw = false

    init {
        badgePaint.color = ContextCompat.getColor(context, R.color.red_500)
        badgePaint.isAntiAlias = true
        badgePaint.style = Paint.Style.FILL
        textPaint = Paint()
        textPaint.color = ContextCompat.getColor(context, R.color.white)
        textPaint.typeface = Typeface.DEFAULT
        textPaint.textSize = textSize
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.CENTER
    }

    override fun draw(canvas: Canvas) {
        if (!mustRedraw) return

        val localRect = bounds
        val width = localRect.right - localRect.left.toFloat()
        val height = localRect.bottom - localRect.top.toFloat()

        val circleRadius = if (count < 10) {
            width.coerceAtMost(height) / 4.0f + 2.5f
        } else {
            width.coerceAtMost(height) / 4.0f + 4.5f
        }
        val circleX = width - circleRadius + 6.2f
        val circleY = circleRadius - 9.5f
        canvas.drawCircle(circleX, circleY, circleRadius, badgePaint)

        val badgeText = count.toString()
        textPaint.getTextBounds(badgeText, 0, badgeText.length, textRect)
        var textY = circleY + (textRect.bottom - textRect.top) / 2.0f
        var textX = circleX
        if (count >= 10) {
            textX -= 1.0f
            textY -= 1.0f
        }
        canvas.drawText(badgeText, textX, textY, textPaint)
    }

    override fun getOpacity(): Int = PixelFormat.TRANSPARENT

    override fun setAlpha(paramInt: Int) {}

    override fun setColorFilter(colorFilter: ColorFilter?) {}

    companion object {
        fun create(context: Context) = BadgeDrawable(context)
    }
}