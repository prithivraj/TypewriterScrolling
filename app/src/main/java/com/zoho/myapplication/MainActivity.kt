package com.zoho.myapplication

import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (editor as DragonEdittext).setCursorCallback { start: Int, end: Int ->
            val lineForOffset = editor.layout.getLineForOffset(start)
            val lineTop = editor.layout.getLineTop(lineForOffset)
            val layoutParams = editor.layoutParams as FrameLayout.LayoutParams
            val totalParentHeight = (editor.parent as View).height
            val lineTopFromCurrentVisible = lineTop % totalParentHeight
            if(lineTop<totalParentHeight/2){
                layoutParams.topMargin = (editor.parent as View).height.div(2).minus(lineTopFromCurrentVisible)
            } else {
                layoutParams.topMargin = lineTopFromCurrentVisible.minus((editor.parent as View).height.div(2))
            }

            editor.layoutParams = layoutParams
        }
    }
}
