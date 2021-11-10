package work.monkey.kotlintemplate.api.model

import com.google.gson.annotations.SerializedName

data class Monkey(
    @SerializedName("monkey-dob")
    var date: String,
    @SerializedName("monkey-height")
    var height: Int
)
