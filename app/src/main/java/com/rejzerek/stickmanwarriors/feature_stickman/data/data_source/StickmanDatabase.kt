package com.rejzerek.stickmanwarriors.feature_stickman.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman

@Database(
    entities = [Stickman::class],
    version = 1
)
abstract class StickmanDatabase : RoomDatabase() {

    abstract val stickmanDao: StickmanDao

    companion object {

        const val DATABASE_NAME = "stickman_db"

//        @Synchronized
//        fun getDatabase(context: Context): StickmanDatabase {
//            if(DB_INSTANCE == null){
//                DB_INSTANCE = databaseBuilder(context.applicationContext, StickmanDatabase::class.java, "item_database").allowMainThreadQueries().build()
//            }
//            return DB_INSTANCE as StickmanDatabase
//        }
    }
}