package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class CustomBtnProgressBar : FrameLayout {
    lateinit var rootlayout: android.widget.FrameLayout
    lateinit var txtview: TextView
    lateinit var progressBAR: ProgressBar
    var text = ""
    var isButtonenabled = true
    var bgColor = 0
    var showingloader = false

    constructor(context: Context) : super(context) {
        initKBtnProgressbar(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        getfromxml(attrs, context)
        initKBtnProgressbar(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyAttr: Int) : super(
        context,
        attrs,
        defStyAttr
    ) {
        getfromxml(attrs, context)
        initKBtnProgressbar(context)
    }

    private fun getfromxml(attrs: AttributeSet?, context: Context) {
        val data = context.obtainStyledAttributes(attrs,R.styleable.ProgressBar)
        text = data.getString(R.styleable.ProgressBar_text).toString()
        isButtonenabled = data.getBoolean(R.styleable.ProgressBar_enable,true)
        bgColor = data.getColor(R.styleable.ProgressBar_bgcolor,context.resources.getColor(R.color.white))
        data.recycle()
    }

    private fun initKBtnProgressbar(context: Context) {

        var layoutParms =
            android.widget.FrameLayout.LayoutParams(
                android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        LayoutInflater.from(context).inflate(R.layout.activity_custom_btn_progress_bar, this, true)
        rootlayout = findViewById(R.id.frameroot)
        txtview = findViewById(R.id.txt_view)
        progressBAR = findViewById(R.id.progressbar)
        if (text.isNotEmpty()) {
            txtview.text = text
        }
        refreshDrawableState()
    }

    fun showingloader() {
        showingloader = true
        txtview.visibility = View.GONE
        progressBAR.visibility = View.VISIBLE
    }

    fun hideloader() {
        showingloader = true
        txtview.visibility = View.VISIBLE
        progressBAR.visibility = View.GONE
    }

}