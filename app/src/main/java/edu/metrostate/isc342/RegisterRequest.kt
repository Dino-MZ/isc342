package edu.metrostate.isc342

import com.squareup.moshi.Json

data class RegisterRequest(
    @Json(name = "name") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)
