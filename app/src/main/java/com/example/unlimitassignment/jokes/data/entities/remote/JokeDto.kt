package com.example.unlimitassignment.jokes.data.entities.remote

import com.google.gson.annotations.SerializedName

data class JokeDto (
    @SerializedName("joke")
    var joke : String? = null
)