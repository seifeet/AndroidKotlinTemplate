package work.monkey.kotlintemplate.api.model

data class FindMonkeyResponse(
    val status : String,
    val dob : String,
    val monkeyType : String,
    val fingerCount : Int
)
