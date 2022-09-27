 package com.redx.saync

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

 class MainActivity : AppCompatActivity() {
     private val REQUEST_CODE_SPEECH_INPUT = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listen.setOnClickListener {
            speak()

        }
    }
     private fun speak(){
         val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
         mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
         mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
         mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something")

         try {
             startActivityForResult(mIntent, REQUEST_CODE_SPEECH_INPUT)
         }catch (e:Exception){

             Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
         }
     }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         when(resultCode){
             REQUEST_CODE_SPEECH_INPUT -> {
                 if(resultCode == Activity.RESULT_OK && null != data){
//                     val result = data.getStringArrayExtra(RecognizerIntent.EXTRA_RESULTS)
//
//                     text1.text = result!![0]
                     val res: ArrayList<String> =
                         data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                     input.setText( Objects.requireNonNull(res)[0])

                 }
             }
         }
     }
 }