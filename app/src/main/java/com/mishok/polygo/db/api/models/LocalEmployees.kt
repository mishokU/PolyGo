package com.mishok.polygo.db.api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.PropertyKey

@Entity(tableName = LocalEmployees.TABLE_NAME)
data class LocalEmployees(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = COLUMN_ID)
        val id: Long,

        @ColumnInfo(name = COLUMN_NAME)
        val name: String,

        @ColumnInfo(name = COLUMN_AVATAR_URL)
        val avatar: String,

        @ColumnInfo(name = COLUMN_CONTACTS)
        val contacts: String,

        @ColumnInfo(name = COLUMN_POSITION)
        val position: String,

        @ColumnInfo(name = COLUMN_SCHEDULE_URL)
        val scheduleUrl: String,

        @ColumnInfo(name = COLUMN_BOOKMARK)
        val saved: Boolean

) {
    companion object {

        const val TABLE_NAME = "table_employees"
        const val COLUMN_ID = "id"
        const val COLUMN_CONTACTS = "contacts"
        const val COLUMN_NAME = "full_name"
        const val COLUMN_AVATAR_URL = "avatar_url"
        const val COLUMN_POSITION = "position"
        const val COLUMN_SCHEDULE_URL = "schedule_url"
        const val COLUMN_BOOKMARK = "saved"

        const val QUERY_GET_ALL = """
            SELECT * FROM $TABLE_NAME
        """

        const val QUERY_GET_SAVED = """
            SELECT * FROM $TABLE_NAME WHERE $COLUMN_BOOKMARK = true
        """

    }
}