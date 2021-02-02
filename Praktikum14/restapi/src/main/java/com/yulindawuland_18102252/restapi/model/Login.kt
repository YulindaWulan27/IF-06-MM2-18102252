package com.yulindawuland_18102252.restapi.model

data class Login(
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("message")
    var message: String? = null
)
