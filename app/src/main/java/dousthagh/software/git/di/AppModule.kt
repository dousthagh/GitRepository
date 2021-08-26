package dousthagh.software.git.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dousthagh.software.git.data.repository.SearchRepository
import dousthagh.software.git.data.service.SearchInRepositoryService
import dousthagh.software.git.data.source.SearchInRepositorySource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(
                OkHttpClient.Builder().callTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS).build()
            )

        return retrofit.build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()


    @Provides
    fun provideSearchInRepositoryService(retrofit: Retrofit): SearchInRepositoryService =
        retrofit.create(SearchInRepositoryService::class.java)

    @Singleton
    @Provides
    fun provideSearchInRepositoryDataSource(searchInRepositoryService: SearchInRepositoryService) =
        SearchInRepositorySource(searchInRepositoryService)

    @Singleton
    @Provides
    fun provideSearchRepository(
        dataSource: SearchInRepositorySource,
    ) = SearchRepository(dataSource)

}
