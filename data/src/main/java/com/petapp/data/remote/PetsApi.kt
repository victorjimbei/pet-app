package com.petapp.data.remote

import com.petapp.data.remote.model.AnimalsDto
import com.petapp.data.remote.model.LoginDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


interface PetsApi {

    @FormUrlEncoded
    @POST("v2/oauth2/token")
    fun fetchToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Single<LoginDto>

    @GET("v2/animals")
    fun fetchAnimals(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Single<AnimalsDto>

    @GET("pet.get")
    fun fetchAnimal(
        @Query("key") key: String,
        @Query("id") id: String
    ): Single<String>
}