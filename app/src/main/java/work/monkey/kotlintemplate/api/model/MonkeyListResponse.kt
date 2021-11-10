package work.monkey.kotlintemplate.api.model

import com.google.gson.annotations.SerializedName

data class MonkeyListResponse(
    val status : Int,
    @SerializedName("monkeys") val monkeys : List<Monkey>
)
