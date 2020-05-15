package com.cutout.display

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        var decorView = window.decorView
        var option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = option

        actionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            //设刘海屏样式
//            val param = window.attributes
//            param.layoutInDisplayCutoutMode =
//                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
//            window.attributes = param

            //获取安全显示区域，交互控件进行相应的位置偏移
            root_layout.setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener { view, windowInsets ->
                val displayCutout = windowInsets.displayCutout
                if (displayCutout != null) {
                    val left = displayCutout.safeInsetLeft
                    val top = displayCutout.safeInsetTop
                    val right = displayCutout.safeInsetRight
                    val bottom = displayCutout.safeInsetBottom

                    val paramsTop = top_btn.layoutParams as ConstraintLayout.LayoutParams
                    paramsTop.setMargins(0, top, 0, 0)

                    val paramsLeft = left_btn.layoutParams as ConstraintLayout.LayoutParams
                    paramsLeft.setMargins(left, 0, 0, 0)

                }
                return@OnApplyWindowInsetsListener windowInsets.consumeStableInsets()
            })
        }

    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView;
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
