package lilcode.aop.p3.c04.bookreview.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import lilcode.aop.p3.c04.bookreview.model.Review

@Dao
interface ReviewDao {

    @Query("SELECT * FROM review WHERE id = :id")
    fun getOneReview(id: Int): Review

    // 같은 값이오면 새로운 거로 대체.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveReview(review: Review)
}