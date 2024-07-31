package com.mopix.olineshopapp.di

import com.mopix.olineshopapp.models.api.invoices.InvoiceApi
import com.mopix.olineshopapp.models.api.invoices.TransactionApi
import com.mopix.olineshopapp.models.api.products.ColorApi
import com.mopix.olineshopapp.models.api.products.ProductCategoryApi
import com.mopix.olineshopapp.models.api.products.ProductControllerApi
import com.mopix.olineshopapp.models.api.site.BlogApi
import com.mopix.olineshopapp.models.api.site.ContentApi
import com.mopix.olineshopapp.models.api.site.SliderApi
import com.mopix.olineshopapp.models.api.user.UserApi
import com.mopix.olineshopapp.models.repositories.invoices.InvoiceRepository
import com.mopix.olineshopapp.models.repositories.invoices.TransactionRepository
import com.mopix.olineshopapp.models.repositories.products.ColorRepository
import com.mopix.olineshopapp.models.repositories.products.ProductCategoryRepository
import com.mopix.olineshopapp.models.repositories.products.ProductRepository
import com.mopix.olineshopapp.models.repositories.site.BlogRepository
import com.mopix.olineshopapp.models.repositories.site.ContentRepository
import com.mopix.olineshopapp.models.repositories.site.SliderRepository
import com.mopix.olineshopapp.models.repositories.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesSliderRepository(api: SliderApi) = SliderRepository (api)

    @Provides
    @Singleton
    fun providesBlogRepository(api: BlogApi) = BlogRepository (api)

    @Provides
    @Singleton
    fun providesContentRepository(api: ContentApi) = ContentRepository (api)

    @Provides
    @Singleton
    fun providesInvoiceRepository(api: InvoiceApi) = InvoiceRepository (api)

    @Provides
    @Singleton
    fun providesTransactionRepository(api: TransactionApi) = TransactionRepository (api)

    @Provides
    @Singleton
    fun providesColorRepository(api: ColorApi) = ColorRepository (api)

    @Provides
    @Singleton
    fun providesProductCategoryRepository(api: ProductCategoryApi) = ProductCategoryRepository (api)

    @Provides
    @Singleton
    fun providesProductControllerRepository(api: ProductControllerApi) = ProductRepository (api)

    @Provides
    @Singleton
    fun providesUserRepository(api: UserApi) = UserRepository (api)










}