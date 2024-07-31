package com.mopix.olineshopapp.di

import com.mopix.olineshopapp.models.api.invoices.InvoiceApi
import com.mopix.olineshopapp.models.api.invoices.TransactionApi
import com.mopix.olineshopapp.models.api.products.ColorApi
import com.mopix.olineshopapp.models.api.products.ProductCategoryApi
import com.mopix.olineshopapp.models.api.products.ProductControllerApi
import com.mopix.olineshopapp.models.api.site.BlogApi
import com.mopix.olineshopapp.models.api.site.ContentApi
import com.mopix.olineshopapp.models.api.site.SliderApi
import com.mopix.olineshopapp.models.api.user.*
import com.mopix.olineshopapp.models.config.UnsafeSSLConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
     fun provideApi():Retrofit{
        return Retrofit.Builder()
//            .baseUrl("https://10.0.3.2:8080")
            .baseUrl("https://onlineshop.holosen.net:9090/swagger-ui/")
            .client(UnsafeSSLConfig.unsafeOkHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun providesSliderApi(): SliderApi {
        return provideApi().create(SliderApi::class.java)
    }
    @Provides
    @Singleton
    fun providesContentApi(): ContentApi {
        return provideApi().create(ContentApi::class.java)
    }
    @Provides
    @Singleton
    fun providesBlogApi(): BlogApi {
        return provideApi().create(BlogApi::class.java)
    }

    @Provides
    @Singleton
    fun providesColorApi(): ColorApi {
        return provideApi().create(ColorApi::class.java)
    }
    @Provides
    @Singleton
    fun providesProductCategoryApi(): ProductCategoryApi {
        return provideApi().create(ProductCategoryApi::class.java)
    }
    @Provides
    @Singleton
    fun providesProductControllerApi(): ProductControllerApi {
        return provideApi().create(ProductControllerApi::class.java)
    }
    @Provides
    @Singleton
    fun providesInvoiceApi(): InvoiceApi {
        return provideApi().create(InvoiceApi::class.java)
    }
    @Provides
    @Singleton
    fun providesTransactionApi(): TransactionApi {
        return provideApi().create(TransactionApi::class.java)
    }
    @Provides
    @Singleton
    fun providesUserApi(): UserApi {
        return provideApi().create(UserApi::class.java)
    }
}