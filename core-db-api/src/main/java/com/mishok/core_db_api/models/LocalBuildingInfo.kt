package com.mishok.core_db_api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalBuildingInfo.TABLE_NAME)
data class LocalBuildingInfo(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Long = 0,

    @ColumnInfo(name = COLUMN_BUILDING_ID)
    val buildingId: Long,

    @ColumnInfo(name = COLUMN_CATEGORY)
    val category: String,

    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,

    @ColumnInfo(name = COLUMN_LOCATION)
    val location: String,

    @ColumnInfo(name = COLUMN_BOOKMARK)
    val saved: Boolean

) {
    companion object {
        const val TABLE_NAME = "table_building_info"
        const val COLUMN_BUILDING_ID = "building_id"
        const val COLUMN_CATEGORY = "category"
        const val COLUMN_TITLE = "title"
        const val COLUMN_ID = "id"
        const val COLUMN_LOCATION = "location"
        const val COLUMN_BOOKMARK = "saved"

        const val QUERY_GET_BY_BUILDING_ID = """
            SELECT * FROM $TABLE_NAME WHERE $COLUMN_BUILDING_ID = :buildingId
        """

        const val QUERY_GET_BY_CATEGORY = """
            SELECT * FROM $TABLE_NAME WHERE
            $COLUMN_BUILDING_ID = :buildingId
            AND $COLUMN_CATEGORY = :category
        """

        const val QUERY_UPDATE_BOOKMARK = """
            UPDATE $TABLE_NAME SET $COLUMN_BOOKMARK = :state WHERE $COLUMN_ID = :buildingInfoId
        """
    }
}