package work.monkey.kotlintemplate.init

import android.app.Application
import work.monkey.kotlintemplate.di.AppComponent
import work.monkey.kotlintemplate.di.DaggerAppComponent
import work.monkey.kotlintemplate.repository.impl.SessionRepositoryImpl

class InitApp : Application() {
  
  lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent
      .factory()
      .repository(SessionRepositoryImpl())
  }

  fun appComp() = appComponent
}
