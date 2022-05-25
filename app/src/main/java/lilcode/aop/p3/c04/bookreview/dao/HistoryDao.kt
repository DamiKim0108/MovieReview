package lilcode.aop.p3.c04.bookreview.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import lilcode.aop.p3.c04.bookreview.model.History

@Dao
interface HistoryDao {
    // 전부 가져오는 는
    @Query("SELECT * FROM history")
    fun getAll(): List<History>

    // 검색 작업이 일어날 때 insert
    @Insert
    fun insertHistory(history: History)

    // x 눌렀을 때 키워드 지워주
    @Query("DELETE FROM history WHERE keyword = :keyword")
    fun delete(keyword: String)
}