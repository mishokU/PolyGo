package com.mishok.core_db_api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalSearching.TABLE_NAME)
data class LocalSearching(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = LocalEmployees.COLUMN_ID)
        val id: Long,

        @ColumnInfo(name = COLUMN_TYPE)
        val type: String,

        @ColumnInfo(name = COLUMN_TITLE)
        val title: String,

        @ColumnInfo(name = COLUMN_TIME)
        val time: Long
) {
    companion object {
        const val TABLE_NAME = "table_searching"
        const val COLUMN_TYPE = "type"
        const val COLUMN_TITLE = "title"
        const val COLUMN_TIME = "time"

        const val QUERY_GET_ALL = """
            SELECT * FROM $TABLE_NAME
        """
    }
}