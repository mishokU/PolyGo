package com.mishok.core_db_api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = LocalBuildingPath.TABLE_NAME)
data class LocalBuildingPath(

    @ColumnInfo(name = COLUMN_ID)
    @PrimaryKey
    val id: Long = 0,

    @ColumnInfo(name = COLUMN_BUILDING_ID)
    val buildingId: Long,

    @ColumnInfo(name = COLUMN_X_AXIS)
    val xCoordinate: Float,

    @ColumnInfo(name = COLUMN_Y_AXIS)
    val yCoordinate: Float
) {
    companion object {
        const val TABLE_NAME = "table_building_path"
        const val COLUMN_ID = "local_path_id"
        const val COLUMN_BUILDING_ID = "building_id"
        const val COLUMN_X_AXIS = "x_axis"
        const val COLUMN_Y_AXIS = "y_axis"

        const val QUERY_GET_BY_BUILDING_ID = """
            SELECT * FROM $TABLE_NAME WHERE $COLUMN_BUILDING_ID = :buildingId
        """

    }
}