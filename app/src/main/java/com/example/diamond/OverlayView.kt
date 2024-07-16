package com.example.diamond

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View

private const val TAG = "OverlayView_싸피"
class OverlayView(context: Context, private val rect: Rect) : View(context) {

    private val paint = Paint().apply {
        color = Color.argb(100, 0, 0, 255) // 파란색의 반투명 색상
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw: $rect")
        // 받은 Rect 영역을 파란색으로 칠합니다.
        canvas.drawRect(rect, paint)
    }
}