package work.monkey.kotlintemplate.repository

import work.monkey.kotlintemplate.repository.entity.AppSettings
import work.monkey.kotlintemplate.repository.entity.Supervisor
import work.monkey.kotlintemplate.repository.entity.MonkeySapiens
import work.monkey.kotlintemplate.state.AppState

/**
 * Repository for the session
 */
interface SessionRepository {

  /**
   * Someone needs to supervise the monkeys
   */
  var supervisor: Supervisor

  /**
   * Gets or sets a monkey sapien
   */
  var monkeySapien: MonkeySapiens

  /**
   * Gets or sets application settings
   */
  var settings: AppSettings

  /**
   * Gets or sets a state machine
   */
  var state: AppState
}