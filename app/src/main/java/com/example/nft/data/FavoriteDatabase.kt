package com.example.nft.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteItem::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao():FavoritesDao

    companion object{
        private var INSTANCE : FavoriteDatabase? = null
        fun getDatabase(context: Context) : FavoriteDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "favorites_table"
                ).build()
                INSTANCE =instance
                return instance
            }

        }
    }
}