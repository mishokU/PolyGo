package com.mishok.core_db_api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalBuildings.TABLE_NAME)
data class LocalBuildings(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Long = 0,

    @ColumnInfo(name = COLUMN_LONGITUDE)
    val longitude: Double,

    @ColumnInfo(name = COLUMN_LATITUDE)
    val latitude: Double,

    @ColumnInfo(name = COLUMN_CORP)
    val corpId: Long,

    @ColumnInfo(name = COLUMN_FLOOR)
    val floorId: Long,

    @ColumnInfo(name = COLUMN_AUDIENCE_ID)
    val audienceId: Long,

    @ColumnInfo(name = COLUMN_TIME)
    val time: String,

    @ColumnInfo(name = COLUMN_DISTANCE)
    val distance: Long,

    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,

    @ColumnInfo(name = COLUMN_BOOKMARK)
    val saved: Boolean

) {
    companion object {
        const val TABLE_NAME = "table_buildings"
        const val COLUMN_LONGITUDE = "longitude"
        const val COLUMN_LATITUDE = "latitude"
        const val COLUMN_CORP = "corp"
        const val COLUMN_TITLE = "title"
        const val COLUMN_FLOOR = "floor"
        const val COLUMN_ID = "id"
        const val COLUMN_AUDIENCE_ID = "audience_id"
        const val COLUMN_TIME = "time"
        const val COLUMN_DISTANCE = "distance"
        const val COLUMN_BOOKMARK = "saved"

        const val DELTA_DISTANCE = 5

        const val QUERY_GET_ALL = """
            SELECT * FROM $TABLE_NAME
        """

        const val QUERY_GET_BY_COORDINATES = """
            SELECT * FROM $TABLE_NAME WHERE 
            $COLUMN_LATITUDE BETWEEN :latitudeFrom AND :latitudeTo AND
            $COLUMN_LONGITUDE BETWEEN :longitudeFrom AND :longitudeTo
        """

        const val QUERY_UPDATE_BOOKMARK = """
            UPDATE $TABLE_NAME SET $COLUMN_BOOKMARK = :state WHERE $COLUMN_ID = :buildingId
        """

        const val QUERY_GET_SAVED = """
            SELECT * FROM $TABLE_NAME WHERE $COLUMN_BOOKMARK = 1
        """

        const val QUERY_GET_BY_QUERY_WITH_BOOKMARKS = """
            SELECT * FROM $TABLE_NAME WHERE $COLUMN_BOOKMARK = 1 AND $COLUMN_TITLE like (:query)
        """

        const val QUERY_GET_BY_QUERY = """
            SELECT * FROM $TABLE_NAME WHERE $COLUMN_TITLE like (:query)
        """

    }
}