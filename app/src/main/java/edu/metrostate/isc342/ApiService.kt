package edu.metrostate.isc342

import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("/api/users/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/api/users/register")
    suspend fun register(@Body request: RegisterRequest): Response<LoginResponse>
}
