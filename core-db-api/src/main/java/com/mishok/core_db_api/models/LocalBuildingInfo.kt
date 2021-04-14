package com.mishok.core_db_api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalBuildingInfo.TABLE_NAME)
data class LocalBuildingInfo(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = COLUMN_BUILDING_ID)
    val buildingId: Long,

    @ColumnInfo(name = COLUMN_CATEGORY)
    val category: String,

    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,

    @ColumnInfo(name = COLUMN_LOCATION)
    val location: String

) {
    companion object {
        const val TABLE_NAME = "table_building_info"
        const val COLUMN_BUILDING_ID = "building_id"
        const val COLUMN_CATEGORY = "category"
        const val COLUMN_TITLE = "title"
        const val COLUMN_LOCATION = "location"

        const val QUERY_GET_BY_BUILDING_ID = """
            SELECT * FROM $TABLE_NAME WHERE $COLUMN_BUILDING_ID = :buildingId
        """
    }
}