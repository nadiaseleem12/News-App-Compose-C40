package com.example.news_compose_c40.data.model.source

import com.example.news_compose_c40.data.model.BaseResponse
import com.google.gson.annotations.SerializedName

 class SourcesResponse(

     @field:SerializedName("sources")
    val sources: List<Source>? = null,

     ): BaseResponse()
