package com.optiva.videoplayer.network

import com.optiva.videoplayer.data.DataList
import retrofit2.Call
import retrofit2.http.GET

interface GetData {

    @GET("uc?id=1ldaCVV28Xtk-EVXhv7xh8ebR9kzfha0v")
    fun getAll(): Call<DataList>
}