package work.monkey.kotlintemplate.state

import work.monkey.kotlintemplate.repository.entity.*
import io.reactivex.rxjava3.subjects.PublishSubject

interface AppSession {
    val supervisor: Supervisor
    val supervisorPublisher: PublishSubject<Supervisor>
    val updateRequired: Boolean
    fun updateSupervisorCountry(country: String)
    fun createMockSupervisor()

    val monkeySapien: MonkeySapiens
    fun setMonkeySapien(monkey: MonkeySapiens)
    fun createMockMonkey()

    val settings: AppSettings
    val isDebug: Boolean

    val appState: AppState
    fun create(type: StateMachineType, state: State? = null)
}