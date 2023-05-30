package com.example.mycapstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)

        var innerMapDB = InnerMapDB(this, "cap.db")
        innerMapDB.findLocation(10, 20, this,)


    }

    fun onLocationFound(result: String) {
        resultTextView.text = result
        //activity_main2.xml의 TextView에 값 넣기
    }

}

//    private static class MyGraphicView extends View {
//        public MyGraphicView(Context context) {
//            super(context)
//        }
//    }
//
//}