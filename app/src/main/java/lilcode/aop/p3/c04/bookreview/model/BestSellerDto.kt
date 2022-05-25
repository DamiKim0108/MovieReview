package lilcode.aop.p3.c04.bookreview.model

import com.google.gson.annotations.SerializedName

// 전체 api 응답을 받아 올 데이터 클래스
data class BestSellerDto (
    @SerializedName("movieNm") val title: String,
    @SerializedName("item") val movies: List<Movie>,

)