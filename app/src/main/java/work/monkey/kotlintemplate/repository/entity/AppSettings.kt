package work.monkey.kotlintemplate.repository.entity

import work.monkey.kotlintemplate.BuildConfig

data class AppSettings(
    val appDebug: Boolean = false
) {
    companion object {
        fun default() : AppSettings {
            val appDebug = BuildConfig.APP_DEBUG.toBoolean()
            return AppSettings(
                appDebug = appDebug
            )
        }
    }
}

