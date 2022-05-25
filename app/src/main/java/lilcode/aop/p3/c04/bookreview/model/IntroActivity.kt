package lilcode.aop.p3.c04.bookreview.model

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import lilcode.aop.p3.c04.bookreview.MainActivity
import lilcode.aop.p3.c04.bookreview.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)

        var handler = Handler()
        handler.postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 3000)
    }

    override fun onPause(){
        super.onPause()
        finish()
    }
}