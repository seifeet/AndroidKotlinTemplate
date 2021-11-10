package work.monkey.kotlintemplate.di

import work.monkey.kotlintemplate.ui.MainActivity
import work.monkey.kotlintemplate.repository.SessionRepository
import work.monkey.kotlintemplate.ui.common.AppFragment
import work.monkey.kotlintemplate.ui.home.MainMenuFragment
import dagger.BindsInstance
import dagger.Component
import work.monkey.kotlintemplate.ui.AppSettingsActivity

@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun repository(
            @BindsInstance session: SessionRepository
        ): AppComponent
    }

    fun inject(fragment: AppFragment)
    fun inject(fragment: MainMenuFragment)

    fun inject(activity: MainActivity)
    fun inject(activity: AppSettingsActivity)
}