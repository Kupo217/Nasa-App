package com.example.nasaapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.nasaapp.models.DBModel

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_TITLE + " TEXT," +
                KEY_URL + " TEXT," +
                KEY_EXPLANATION + " TEXT," +
                KEY_DATE + " TEXT" + ")")

        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addData(data: DBModel): Long{
        val values = ContentValues()

        values.put(KEY_TITLE, data.title)
        values.put(KEY_URL, data.url)
        values.put(KEY_EXPLANATION, data.explanation)
        values.put(KEY_DATE, data.date)

        val db = this.writableDatabase
        val success = db.insertOrThrow(TABLE_NAME, null, values)

        db.close()
        return success
    }


    fun viewData(): ArrayList<DBModel>
    {
        val dataList = ArrayList<DBModel>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase

        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var title: String
        var url: String
        var explanation: String
        var date: String

        if (cursor.moveToFirst())
        {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID))
                title = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TITLE))
                url = cursor.getString(cursor.getColumnIndexOrThrow(KEY_URL))
                explanation = cursor.getString(cursor.getColumnIndexOrThrow(KEY_EXPLANATION))
                date = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DATE))

                val data = DBModel(id, title, url, explanation, date)
                dataList.add(data)
            }while (cursor.moveToNext())
        }

        return dataList
    }


    fun deleteData(data: DBModel): Int {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(KEY_ID, data.id)

        val success = db.delete(TABLE_NAME, "$KEY_ID = ${data.id}", null)
        db.close()

        return success
    }

    companion object {
        const val DATABASE_VERSION = 1

        const val DATABASE_NAME = "NASA-APP"

        const val TABLE_NAME = "apod_data"

        const val KEY_ID = "id"
        const val KEY_TITLE = "title"
        const val KEY_URL = "url"
        const val KEY_EXPLANATION = "desc"
        const val KEY_DATE = "time"


    }
}