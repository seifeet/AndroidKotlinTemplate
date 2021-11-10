package work.monkey.kotlintemplate.state

import work.monkey.kotlintemplate.repository.SessionRepository
import work.monkey.kotlintemplate.repository.entity.*
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class AppSessionImpl @Inject constructor(
        private val repository: SessionRepository
) : AppSession {
    override val supervisor: Supervisor
        get() = repository.supervisor

    override val supervisorPublisher: PublishSubject<Supervisor>
        get() = PublishSubject.create()

    override val updateRequired: Boolean
        get() {
            if (appState.updateRequired) {
                appState.updateRequired = false
                return true
            }
            return false
        }

    override val monkeySapien: MonkeySapiens
        get() = repository.monkeySapien

    override val settings: AppSettings
        get() = repository.settings

    override val appState: AppState
        get() = repository.state

    override fun updateSupervisorCountry(country: String) {
        if (country == supervisor.country) return
        repository.supervisor = supervisor.copy(
            country = country
        )
        appState.updateRequired = true
        supervisorPublisher.onNext(supervisor)
    }

    override fun createMockSupervisor() {
        repository.supervisor = Supervisor.mock()
    }

    override fun setMonkeySapien(monkey: MonkeySapiens) {
        repository.monkeySapien = monkey
    }

    override fun createMockMonkey() {
        repository.monkeySapien = MonkeySapiens.mock()
    }

    override val isDebug: Boolean
        get() = repository.settings.appDebug

    override fun create(type: StateMachineType, state: State?)  {
        repository.state = AppState.create(type, state)
    }
}