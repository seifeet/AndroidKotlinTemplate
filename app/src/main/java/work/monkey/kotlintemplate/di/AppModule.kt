package work.monkey.kotlintemplate.di

import work.monkey.kotlintemplate.state.AppSession
import work.monkey.kotlintemplate.state.AppSessionImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

  @Binds
  abstract fun provideAppSession(provider: AppSessionImpl): AppSession
}



