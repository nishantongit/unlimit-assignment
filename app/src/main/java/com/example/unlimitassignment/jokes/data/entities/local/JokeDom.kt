package com.example.unlimitassignment.jokes.data.entities.local

import com.google.gson.annotations.SerializedName

data class JokeDom(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("joke" ) var joke : String? = null,
    @SerializedName("created_at") var createdAt : Long = 0
)