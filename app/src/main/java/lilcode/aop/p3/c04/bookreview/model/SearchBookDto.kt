package lilcode.aop.p3.c04.bookreview.model

import com.google.gson.annotations.SerializedName

data class SearchMovieDto(
    @SerializedName("title") val title: String,
    @SerializedName("movieCd") val movies: List<Movie>,
)