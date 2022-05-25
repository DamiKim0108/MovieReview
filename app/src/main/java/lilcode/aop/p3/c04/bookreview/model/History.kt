package lilcode.aop.p3.c04.bookreview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History (
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name="keyword") val keyword: String?
)