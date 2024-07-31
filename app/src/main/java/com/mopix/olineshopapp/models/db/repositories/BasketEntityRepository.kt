package com.mopix.olineshopapp.models.db.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.mopix.olineshopapp.models.db.OnlineShopDataBase
import com.mopix.olineshopapp.models.db.dao.BasketEntityDao
import com.mopix.olineshopapp.models.db.models.BasketEntity

class BasketEntityRepository(application: Application) {

    private var basketDao: BasketEntityDao
    private var BasketLiveData:LiveData<List<BasketEntity>>

    init {
        val database = OnlineShopDataBase.getInstance(application)
        basketDao = database.basketDao()
        BasketLiveData = basketDao.getAllLIVE()
    }

    suspend fun insert(basket: BasketEntity) {
        basketDao.add(basket)
    }

    suspend fun update(basket: BasketEntity) {
        basketDao.update(basket)
    }

    suspend fun delete(basket: BasketEntity) {
        basketDao.delete(basket)
    }

    suspend fun incrementalQuantity(basket: BasketEntity) {
        basket.quantity++
        update(basket)
    }

    suspend fun decrementalQuantity(basket: BasketEntity) {
        basket.quantity--
        if (basket.quantity <= 0)delete(basket)
        update(basket)
    }

    suspend fun deleteAll() {
        return basketDao.deleteAll()
    }

    suspend fun getAllBasketList(): List<BasketEntity> {
        return basketDao.getAll()
    }
     fun getAllBasketLive():LiveData< List<BasketEntity>> {
        return BasketLiveData
    }


}