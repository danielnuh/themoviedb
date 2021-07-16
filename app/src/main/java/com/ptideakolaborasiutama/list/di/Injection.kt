package com.ptideakolaborasiutama.list.di

import android.content.Context
import com.ptideakolaborasiutama.list.data.Repository
import com.ptideakolaborasiutama.list.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): Repository {
        val remoteDataSource = RemoteDataSource.getInstance()

        return Repository.getInstance(remoteDataSource)
    }
}