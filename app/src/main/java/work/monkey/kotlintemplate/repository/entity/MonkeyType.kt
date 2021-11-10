package work.monkey.kotlintemplate.repository.entity

import work.monkey.kotlintemplate.R

enum class MonkeyType(val text: String,
                      val resourceId: Int) {
    MANDRILL(
            text = "Mandrill",
            resourceId = R.drawable.ic_baseline_wifi_24
    ),
    MARMOSET(
            text = "Marmoset",
            resourceId = R.drawable.ic_baseline_add_24
    ),
    MACAQUE(
            text = "Macaque",
            resourceId = R.drawable.ic_baseline_info_24
    )
}