package com.example.swissquotetest.data.di

import android.content.Context
import com.example.swissquotetest.data.mapper.CardDtoToCardDomainMapper
import com.example.swissquotetest.data.mapper.CardDtoToCardDomainMapperImpl
import com.example.swissquotetest.data.mapper.TransactionDtoToTransactionDomainMapper
import com.example.swissquotetest.data.mapper.TransactionDtoToTransactionDomainMapperImpl
import com.example.swissquotetest.data.remote.BankApi
import com.example.swissquotetest.data.repository.BankRepositoryImpl
import com.example.swissquotetest.domain.BankRepository
import com.example.swissquotetest.presentation.util.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): BankApi {
        return retrofit.create(BankApi::class.java)
    }

    @Provides
    fun provideCalendarInstance(): Calendar {
        return Calendar.getInstance()
    }

    @Provides
    @Singleton
    fun provideCardMapper(): CardDtoToCardDomainMapper {
        return CardDtoToCardDomainMapperImpl()
    }

    @Provides
    @Singleton
    fun provideTransactionMapper(
        calendar: Calendar
    ): TransactionDtoToTransactionDomainMapper {
        return TransactionDtoToTransactionDomainMapperImpl(calendar)
    }

    @Provides
    @Singleton
    fun provideBankRepository(
        bankApi: BankApi,
        cardDomainMapper: CardDtoToCardDomainMapper,
        transactionDomainMapper: TransactionDtoToTransactionDomainMapper,
        @ApplicationContext context: Context
    ): BankRepository {
        return BankRepositoryImpl(bankApi, cardDomainMapper, transactionDomainMapper, context)
    }
}