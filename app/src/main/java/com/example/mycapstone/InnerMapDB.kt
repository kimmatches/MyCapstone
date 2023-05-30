package com.example.mycapstone

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileOutputStream
import java.io.IOException



class InnerMapDB (private val context: Context, private val dbName: String) :
    SQLiteOpenHelper(context, dbName, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
    }

    private val dbPath = context.applicationInfo.dataDir + "/databases/"

    init {
        if (!checkDatabase()) {
            copyDatabase()
        }
    }

    private fun checkDatabase(): Boolean {

        val dbFile = context.getDatabasePath(dbName)

        return dbFile.exists()
    }

    private fun copyDatabase() {
        try {
            val folder = context.getDatabasePath(dbName).parentFile

            if (!folder.exists()) {
                folder.mkdir()
            }

            val inputStream = context.assets.open(dbName)
            val outputStream = FileOutputStream(dbPath + dbName)
            val buffer = ByteArray(1024)
            var length: Int

            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }

            outputStream.flush()
            outputStream.close()
            inputStream.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

//    // liner_8 이미지에서 x와 y 좌표 값에 대해 해당하는 위치를 찾아내는 함수
//    fun findLocation(x: Int, y: Int ): String {
//        val db = readableDatabase
//        val cursor = db.rawQuery("SELECT * FROM liner_8 WHERE x = $x AND y = $y", null)
//        var result = ""
//        while (cursor.moveToNext()) {
//            result = cursor.getString(2)
//        }
//        cursor.close()
//        db.close()
//        listener.onLocationFound(result)
//    }
//
//

    fun findLocation(x: Int, y: Int, mainActivity2: MainActivity2) {
            val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM liner_8 WHERE x = $x AND y = $y", null)
        var result = ""
        while (cursor.moveToNext()) {
            result = cursor.getString(2)
        }
        cursor.close()
        db.close()
        //listener.onLocationFound(result) 오류안뜨게 바꿔줘
        mainActivity2.onLocationFound(result)
    }








    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }



}