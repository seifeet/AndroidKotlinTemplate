package work.monkey.kotlintemplate.repository.entity

import work.monkey.kotlintemplate.R

enum class MonkeyStatusType(val text: String,
                            val title: String) {
    SMILING(
            text = "Status: Smiling",
            title = "Smiling"
    ),
    HAPPY(
            text = "Status: HAPPY",
            title = "HAPPY"
    ),
    SAD(
        text = "Status: SAD",
        title = "SAD"
    )
}