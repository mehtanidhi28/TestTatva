package com.example.testtatva.ui.model


import com.google.gson.annotations.SerializedName

data class UserListResponseModel(
    @SerializedName("data") val userData: List<UserModel?>? = listOf(),
    @SerializedName("page") val page: Int? = 0,
    @SerializedName("per_page") val perPage: Int? = 0,
    @SerializedName("support") val support: Support? = Support(),
    @SerializedName("total") val total: Int? = 0,
    @SerializedName("total_pages") val totalPages: Int? = 0
) {
    data class UserModel(
        @SerializedName("avatar") val avatar: String? = "",
        @SerializedName("email") val email: String? = "",
        @SerializedName("first_name") val firstName: String? = "",
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("last_name") val lastName: String? = ""
    )

    data class Support(
        @SerializedName("text") val text: String? = "",
        @SerializedName("url") val url: String? = ""
    )
}