package com.petapp.home.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.petapp.R
import com.petapp.data.remote.PetsRemoteDataSource
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    @Inject
    lateinit var petsRemoteDataStore: PetsRemoteDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        petsRemoteDataStore.getToken()
//            .flatMap { petsRemoteDataStore.getAnimals("${it.tokenType} ${it.accessToken}", 1) }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { Log.d("SAM", "getAnimals success: $it") },
//                { Log.e("SAM", "getToken failed: ${it.stackTraceToString()}") }
//            )
    }
}