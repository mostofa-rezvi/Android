package com.example.foodzilla3.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "favorite_restaurants.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "favorite_restaurants"
        const val COLUMN_NAME = "name"
        const val COLUMN_RATING = "rating"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_NAME TEXT PRIMARY KEY, $COLUMN_RATING TEXT)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun addFavoriteRestaurant(name: String, rating: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_RATING, rating)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun removeFavoriteRestaurant(name: String) {
        val db = writableDatabase
        val whereClause = "$COLUMN_NAME = ?"
        val whereArgs = arrayOf(name)
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }
}
