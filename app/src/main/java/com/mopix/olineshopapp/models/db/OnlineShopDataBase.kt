package com.mopix.olineshopapp.models.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mopix.olineshopapp.models.db.dao.BasketEntityDao
import com.mopix.olineshopapp.models.db.dao.UserEntityDao
import com.mopix.olineshopapp.models.db.models.BasketEntity
import com.mopix.olineshopapp.models.db.models.UserEntity

@Database(entities = [UserEntity::class, BasketEntity::class], version = 11)
abstract class OnlineShopDataBase : RoomDatabase() {

    abstract fun userDao(): UserEntityDao
    abstract fun basketDao(): BasketEntityDao

    companion object {
        private var instance: OnlineShopDataBase? = null
        fun getInstance(context: Context): OnlineShopDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    OnlineShopDataBase::class.java, "onlineshop.db"
                ).fallbackToDestructiveMigration().build()
            }
            return instance as OnlineShopDataBase
        }
    }
}