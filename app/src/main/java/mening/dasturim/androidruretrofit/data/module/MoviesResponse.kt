package mening.dasturim.androidruretrofit.data.module

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoviesResponse (
        var page: Int,
        var results: List<Movie>,
        @SerializedName("total_results")
        var totalResults: Int,
        @SerializedName("total_pages")
        var totalPages: Int
)