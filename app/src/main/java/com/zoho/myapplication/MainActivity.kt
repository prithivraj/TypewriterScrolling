package com.zoho.myapplication

import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var height: Int =0
    var lastLineTop:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (editor as DragonEdittext).setCursorCallback { start: Int, end: Int ->
            val lineForOffset = editor.layout.getLineForOffset(start)
//            val scrollValue = editor.layout.getLineBottom(start) - editor.layout.getLineTop(start)
            val lineTop = editor.layout.getLineBaseline(lineForOffset)
            val layoutParams = editor.layoutParams as FrameLayout.LayoutParams
            if(height==0){
                val totalParentHeight=Rect()
                (editor.parent.parent as ScrollView).getLocalVisibleRect(totalParentHeight)
                height=(totalParentHeight.height()/2)
                topPadding.layoutParams.height=height
                bottomPadding.layoutParams.height=height
            }
            /*if(lineTop<height) {
                editor.translationY = height!!.toFloat()-lineTop
                Log.e("first",""+height+" "+lineTop+" "+editor.translationY+" "+(editor.parent as ScrollView).maxScrollAmount)
            }
            *//*else if(((editor.parent as ScrollView).height-height)<lineTop){
                editor.translationY = (((editor.parent as ScrollView).height-height)-lineTop).toFloat()
                Log.e("last",""+height+" "+lineTop+" "+editor.translationY)

            }*//*
            else*/ if (lastLineTop!=lineTop) {
            (editor.parent.parent as ScrollView).isSmoothScrollingEnabled=true
            (editor.parent.parent as ScrollView).scrollTo(0,lineTop)
            Log.e("mid",""+height+" "+lineTop+" "+editor.translationY)

        }
            lastLineTop=lineTop
//            (editor.parent.parent as ScrollView).scrollTo(0,lineTop)
            val lineTopFromCurrentVisible =   height!!



            /*if(lineTop<totalParentHeight.height()/2){
                layoutParams.topMargin = (editor.parent as View).height.div(2).minus(lineTopFromCurrentVisible)
            } else {
                layoutParams.topMargin = lineTopFromCurrentVisible.minus((editor.parent as View).height.div(2))
            }*/
//            editor.layoutParams = layoutParams
        }
    }
}
