package com.mishok.core_db_api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalBuildingFilters.TABLE_NAME)
data class LocalBuildingFilters(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,

    @ColumnInfo(name = COLUMN_TYPE)
    val type: String

) {
    companion object {
        const val TABLE_NAME = "table_building_filters"
        const val COLUMN_TYPE = "type"
        const val COLUMN_TITLE = "title"

        const val QUERY_GET_ALL = """
            SELECT * FROM $TABLE_NAME
        """
    }

}