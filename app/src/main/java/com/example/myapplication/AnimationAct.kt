package com.example.myapplication

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import android.util.Pair as UtilPair

class AnimationAct : AppCompatActivity() {

    lateinit var bounceBtn: Button
    lateinit var imgView: ImageView
//    lateinit var imgView2: ImageView
    lateinit var fadeBtn: Button
    lateinit var moveBtn: Button
    lateinit var rotateBtn: Button
    lateinit var multiTransitionBtn: Button
    lateinit var objectAnimator: ObjectAnimator
    lateinit var objectAnimator1: ObjectAnimator
    lateinit var textViewTitle: TextView
    lateinit var singleTransitionBtn: Button
    lateinit var slideUpButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)


        imgView= findViewById(R.id.iv_car)
//        imgView2= findViewById(R.id.iv_tom)
        bounceBtn= findViewById(R.id.btn_bounce)
        fadeBtn= findViewById(R.id.fade_btn)
        moveBtn = findViewById(R.id.move_btn)
        rotateBtn= findViewById(R.id.rotate_btn)
        multiTransitionBtn= findViewById(R.id.multiple_transition_btn)
        textViewTitle = findViewById(R.id.tv_title)
        singleTransitionBtn= findViewById(R.id.single_transition_btn)
        slideUpButton= findViewById(R.id.activity_btn)

        objectAnimator= ObjectAnimator.ofFloat(imgView,"x",600.0f)

        objectAnimator1= ObjectAnimator.ofFloat(imgView,"rotation",360.0f)


        bounceBtn.setOnClickListener {

            val animationBounce = AnimationUtils.loadAnimation(this,R.anim.bounce)
            imgView.startAnimation(animationBounce)
        }

        fadeBtn.setOnClickListener {


            val animationFadeIn = AnimationUtils.loadAnimation(this,R.anim.fade)
            imgView.startAnimation(animationFadeIn)
        }

        moveBtn.setOnClickListener {

            objectAnimator.duration = 4000
            objectAnimator.start()

        }

        rotateBtn.setOnClickListener {
            objectAnimator1.duration= 2000
            objectAnimator1.start()

        }

        singleTransitionBtn.setOnClickListener {



            val intent = Intent(this,AnimAct2::class.java)
            val imageViewPair = UtilPair.create<View?, String?>(imgView, "transition_picture")
            val options = ActivityOptions.makeSceneTransitionAnimation(this, imageViewPair)
            startActivity(intent, options.toBundle())
        }
        multiTransitionBtn.setOnClickListener {

            val intent = Intent(this, AnimAct2::class.java)
            val imageViewPair = UtilPair.create<View?, String?>(imgView, "transition_picture")
            val textViewPair = UtilPair.create<View, String>(textViewTitle, "transition_text")
            val options = ActivityOptions.makeSceneTransitionAnimation(this, imageViewPair, textViewPair)
            startActivity(intent, options.toBundle())
        }

        slideUpButton.setOnClickListener {

        }





    }
}