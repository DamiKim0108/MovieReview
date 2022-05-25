package lilcode.aop.p3.c04.bookreview.api

import lilcode.aop.p3.c04.bookreview.model.BestSellerDto
import lilcode.aop.p3.c04.bookreview.model.SearchMovieDto

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    // get : 데이터 요청 시 반환 http
    // post : http body에 넣어 전달

    // 책 검색.
    @GET("\thttp://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=f5eef3421c602c6cb7ea224104795888")
    fun getBooksByName(
        @Query("key") apiKey: String,
        @Query("query") kweyWord: String
    ): Call<SearchMovieDto>

    // best seller 받아오기
    @GET("\thttp://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20120101")
    fun getBestSellerBooks(
        @Query("key") apiKey: String,
    ): Call<BestSellerDto>

}