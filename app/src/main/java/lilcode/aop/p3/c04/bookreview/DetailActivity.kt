package lilcode.aop.p3.c04.bookreview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import lilcode.aop.p3.c04.bookreview.databinding.ActivityDetailBinding
import lilcode.aop.p3.c04.bookreview.model.Book
import lilcode.aop.p3.c04.bookreview.model.Movie
import lilcode.aop.p3.c04.bookreview.model.Review

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var db: AppDatabase

    private var model: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = getAppDatabase(this)

        model = intent.getParcelableExtra("bookModel")

        renderView()

        initSaveButton()
    }

    private fun initSaveButton() {
        binding.saveButton.setOnClickListener {
            Thread {
                db.reviewDao().saveReview(
                    Review(
                        model?.id?.toInt() ?: 0,
                        binding.reviewEditText.text.toString()
                    )
                )
            }.start()
        }
    }

    private fun renderView() {

        binding.titleTextView.text = model?.title.orEmpty()

        binding.descriptionTextView.text = model?.description.orEmpty()

        Glide.with(binding.coverImageView.context)
            .load(model?.coverLargeUrl.orEmpty())
            .into(binding.coverImageView)


        // 저장된 리뷰 데이터 가져오기;
        Thread {
            val review = db.reviewDao().getOneReview(model?.id?.toInt() ?: 0)
            review?.let {
                runOnUiThread {
                    binding.reviewEditText.setText(it.review)
                }
            }
        }.start()
    }
}