package com.example.mycapstone

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.FileOutputStream
import java.io.IOException


class InnerMapDB (private val context: Context, private val dbName: String) :
    SQLiteOpenHelper(context, dbName, null, DATABASE_VERSION,) {

    companion object {
        private const val DATABASE_NAME = "cap.db"
        private const val DATABASE_VERSION = 1

    }
//    init {
//        val dbExist = checkDatabase()
//        if (dbExist) {
//            Log.d("dbCheck", "Database exist")
//        } else {
//            Log.d("dbCheck", "Database doesn't exist")
//            createDatabase()
//        }
//    }
//
//    private fun checkDatabase(): Boolean {
//
//    }

    //cap.db 경로 설정
    private val dbPath = context.applicationInfo.dataDir + "/databases/"


    data class Classroom(
        val id: Int,
        val x: Int,
        val y: Int
    )



    fun getClassroom(): List<Classroom> {
        val classroomList = mutableListOf<Classroom>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM nodes_place", null)

        cursor?.let {
            while (it.moveToNext()) {
                val id = it.getInt(0)
                val x = it.getInt(1)
                val y = it.getInt(2)

                val classroom = Classroom( id, x, y)
                classroomList.add(classroom)
            }
            it.close()
        }

        return classroomList
    }

    fun getAllTableNames(): List<String> {
        val tableNames = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null)

        cursor?.let {
            while (it.moveToNext()) {
                val tableName = it.getString(0)
                tableNames.add(tableName)
            }
            it.close()
        }

        return tableNames
    }


    fun getClassroom2() {
        val classroomList = mutableListOf<Classroom>()
        val db = readableDatabase

        val tableNames = getAllTableNames()
        Log.d("TableNames", tableNames.toString())

    }





    override fun onCreate(p0: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE IF NOT EXISTS nodes_place (id INTEGER PRIMARY KEY, name TEXT)"

        val db = writableDatabase
        db.execSQL(createTableQuery)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }




}



