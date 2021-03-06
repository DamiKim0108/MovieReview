package lilcode.aop.p3.c04.bookreview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import lilcode.aop.p3.c04.bookreview.adapter.HistoryAdapter
import lilcode.aop.p3.c04.bookreview.adapter.MovieAdapter
import lilcode.aop.p3.c04.bookreview.adapter.ReviewAdapter
import lilcode.aop.p3.c04.bookreview.api.BookService
import lilcode.aop.p3.c04.bookreview.databinding.ActivityMainBinding
import lilcode.aop.p3.c04.bookreview.model.BestSellerDto
import lilcode.aop.p3.c04.bookreview.model.History
import lilcode.aop.p3.c04.bookreview.model.Review
import lilcode.aop.p3.c04.bookreview.model.SearchMovieDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Short.toString
import java.util.Arrays.toString
import java.util.Objects.toString
import kotlin.Unit.toString
import kotlin.coroutines.EmptyCoroutineContext.toString
import kotlin.time.TimeSource.Monotonic.toString


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var MovieRecyclerViewAdapter: MovieAdapter
    private lateinit var bookService: BookService
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var reviewAdapter: ReviewAdapter

    private val db: AppDatabase by lazy {
        getAppDatabase(this)
    }
    val datas = arrayListOf<Review>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        initBookRecyclerView()
        initHistoryRecyclerView()
        initSearchEditText()

        initBookService()
        bookServiceLoadBestSellers()


        val buttonView = findViewById<Button>(R.id.searchReview)

        buttonView.setOnClickListener{
            initRecycler()
        }
    }

    private fun initRecycler() {
        reviewAdapter = ReviewAdapter(itemClickedListener = {
            val intent = Intent(this, ShowReview::class.java)

            startActivity(intent)
        })

        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = MovieRecyclerViewAdapter
    }

    private fun bookServiceLoadBestSellers() {
        // ????????? ?????? ????????????;
        bookService.getBestSellerBooks(getString(R.string.interparkAPIKey))
            .enqueue(object : Callback<BestSellerDto> {
                // ????????? ??? ??????;
                override fun onResponse(
                    call: Call<BestSellerDto>,
                    response: Response<BestSellerDto>
                ) {
                    // ?????? ????????? ????????? ????????? ???;
                    if (response.isSuccessful.not()) {
                        Log.e(M_TAG, "NOT!! SUCCESS")
                        return
                    }

                    // ?????? ????????? ????????? ????????? ?????? ????????? ??????;
                    response.body()?.let {
                        Log.d(M_TAG, it.toString())

                        it.movies.forEach { Movie ->
                            Log.d(M_TAG, lilcode.aop.p3.c04.bookreview.model.Movie.toString())
                        }

                        // ??? ???????????? ??????;
                        MovieRecyclerViewAdapter.submitList(it.movies)
                    }
                }

                // ????????? ????????? ??????
                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                    Log.e(M_TAG, t.toString())
                }
            })
    }

    private fun initBookService() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.xml") // ???????????? ????????? ??????;
            .addConverterFactory(GsonConverterFactory.create()) // Gson ????????? ??????;
            .build()

        bookService = retrofit.create(BookService::class.java)
    }

    private fun initBookRecyclerView() {
        MovieRecyclerViewAdapter = MovieAdapter(itemClickedListener = {
            val intent = Intent(this, DetailActivity::class.java)

            // ????????? ?????? ?????? ???.
            intent.putExtra("bookModel", it)
            startActivity(intent)
        })

        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = MovieRecyclerViewAdapter
    }

    private fun initHistoryRecyclerView() {
        historyAdapter = HistoryAdapter(historyDeleteClickListener = {
            deleteSearchKeyword(it)
        }, this)

        binding.historyRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.historyRecyclerView.adapter = historyAdapter

        initSearchEditText()
    }


    fun bookServiceSearchBook(keyword: String) {

        bookService.getBooksByName(getString(R.string.interparkAPIKey), keyword)
            .enqueue(object : Callback<SearchMovieDto> {
                // ??????.

                override fun onResponse(
                    call: Call<SearchMovieDto>,
                    response: Response<SearchMovieDto>
                ) {
                    hideHistoryView()
                    saveSearchKeyword(keyword)

                    if (response.isSuccessful.not()) {
                        return
                    }

                    MovieRecyclerViewAdapter.submitList(response.body()?.movies.orEmpty()) // ??? ???????????? ??????
                }

                // ??????.
                override fun onFailure(call: Call<SearchMovieDto>, t: Throwable) {
                    hideHistoryView()
                    Log.e(M_TAG, t.toString())
                }
            })
    }

    private fun saveSearchKeyword(keyword: String) {
        Thread {
            db.historyDao().insertHistory(History(null, keyword))
        }.start()
    }

    private fun showHistoryView() {
        Thread {
            val keywords = db.historyDao().getAll().reversed()
            runOnUiThread {
                binding.historyRecyclerView.isVisible = true
                historyAdapter.submitList(keywords.orEmpty())
            }
        }.start()

    }

    private fun hideHistoryView() {
        binding.historyRecyclerView.isVisible = false
    }

    private fun deleteSearchKeyword(keyword: String) {
        Thread {
            db.historyDao().delete(keyword)
            showHistoryView()
        }.start()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initSearchEditText() {
        binding.searchEditText.setOnKeyListener { v, keyCode, event ->
            // ????????? ????????? ??????

            // ?????? ????????? ?????? (????????????, ?????? ??? -> ????????? ??? ???????????????.)
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN) {
                bookServiceSearchBook(binding.searchEditText.text.toString())
                return@setOnKeyListener true// ?????? ?????????.
            }
            return@setOnKeyListener false // ?????? ????????? ??? ?????????.
        }

        binding.searchEditText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                showHistoryView()
            }
            return@setOnTouchListener false
        }
    }



    companion object {
        private const val M_TAG = "MainActivity"
    }


}