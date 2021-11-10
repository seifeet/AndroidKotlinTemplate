package work.monkey.kotlintemplate.repository.impl

import work.monkey.kotlintemplate.repository.SessionRepository
import work.monkey.kotlintemplate.repository.entity.AppSettings
import work.monkey.kotlintemplate.repository.entity.Supervisor
import work.monkey.kotlintemplate.repository.entity.MonkeySapiens
import work.monkey.kotlintemplate.repository.entity.empty
import work.monkey.kotlintemplate.state.AppState
import work.monkey.kotlintemplate.state.StateMachineType

class SessionRepositoryImpl constructor(
    override var supervisor: Supervisor = Supervisor.empty(),
    override var monkeySapien: MonkeySapiens = MonkeySapiens(),
    override var settings: AppSettings = AppSettings.default(),
    override var state: AppState = AppState.create(type = StateMachineType.AUTHENTICATE)
) : SessionRepository