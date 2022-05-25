package lilcode.aop.p3.c04.bookreview

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ShowReview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.review_list)

        val backButton = findViewById<Button>(R.id.returnToMain)
        backButton.setOnClickListener{
            finish()
        }
    }
}