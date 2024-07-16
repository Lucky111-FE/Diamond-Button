package com.example.diamond

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.TouchDelegate
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diamond.databinding.ActivityMainBinding


private const val TAG = "MainActivity_싸피"

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var center: Rect

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDiamondShapeTouchArea(binding.diamondButton1.parent as View, binding.diamondButton1)
        setDiamondShapeTouchArea(binding.diamondButton2.parent as View, binding.diamondButton2)
        setDiamondShapeTouchArea(binding.diamondButton3.parent as View, binding.diamondButton3)
        setDiamondShapeTouchArea(binding.diamondButton4.parent as View, binding.diamondButton4)
        setDiamondShapeTouchArea(binding.diamondButton5.parent as View, binding.diamondButton5)

        setContentView(binding.root)



        binding.frame.setOnClickListener {
            Log.d(TAG, "onCreate: 클릭 확인")
            Toast.makeText(this, "가장 바깥프레임은 잘 클릭되네요", Toast.LENGTH_SHORT).show()
        }

        binding.frame2.setOnClickListener {
            Log.d(TAG, "onCreate: 클릭 확인")
            Toast.makeText(this, "왼쪽 버튼 프레임은 잘 클릭되네요", Toast.LENGTH_SHORT).show()
        }

        binding.diamondButton1.setOnClickListener {
            Log.d(TAG, "onCreate: 클릭 확인")
            Toast.makeText(this, "좌측 상단은 잘 클릭되네요", Toast.LENGTH_SHORT).show()
        }

        binding.diamondButton2.setOnClickListener {
            Log.d(TAG, "onCreate: 클릭 확인")
            Toast.makeText(this, "우측 상단은 잘 클릭되네요", Toast.LENGTH_SHORT).show()
        }

        binding.diamondButton4.setOnClickListener {
            Log.d(TAG, "onCreate: 클릭 확인")
            Toast.makeText(this, "좌측 하단은 잘 클릭되네요", Toast.LENGTH_SHORT).show()
        }

        binding.diamondButton5.setOnClickListener {
            Log.d(TAG, "onCreate: 클릭 확인")
            Toast.makeText(this, "우측 하단은 잘 클릭되네요", Toast.LENGTH_SHORT).show()
        }

        binding.diamondButton3.setOnClickListener {
            Toast.makeText(this, "가운데 영역도 잘 클릭되네요", Toast.LENGTH_SHORT).show()
        }
    }

    // 이 함수를 호출하여 마름모 모양의 터치 영역을 설정합니다.
    private fun setDiamondShapeTouchArea(parent: View, diamondButton: View) {

//        parent.post {
//            val rect = Rect()
//            diamondButton.getHitRect(rect)
//            parent.touchDelegate = TouchDelegate(rect, binding.diamondButton1)
//        }
        var left = -1
        var right = -1;
        var top = -1
        var bottom = -1
        parent.post {

            val rect = Rect()
            diamondButton.getHitRect(rect)

            Log.d(TAG, "setDiamondShapeTouchArea: 잘난 $rect")

            // 마름모 모양의 영역을 정의합니다.
            left = rect.left
             top = rect.top + (rect.height() - rect.width()) / 2
             right = rect.right
             bottom = rect.bottom - (rect.height() - rect.width()) / 2

            // 마름모 모양의 영역을 Rect으로 설정합니다.
            val diamondRect = Rect(left, top, right, bottom)

            center = diamondRect

            val overlay = OverlayView(binding.root.context, center)
            binding.root.addView(overlay)
            binding.root.invalidate()
            Log.d(TAG, "setDiamondShapeTouchArea: addView 실행")


            // TouchDelegate를 설정하여 마름모 모양의 영역을 확장합니다.
            parent.touchDelegate = object : TouchDelegate(diamondRect, diamondButton) {
                override fun onTouchEvent(event: MotionEvent): Boolean {
                    // 여기에 터치 이벤트를 처리하는 로직을 추가할 수 있습니다.
                    return super.onTouchEvent(event)
                }
            }
        }
    }
}