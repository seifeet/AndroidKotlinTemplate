package work.monkey.kotlintemplate.state

import work.monkey.kotlintemplate.common.StateMachine
import java.util.logging.Logger

enum class StateMachineType {
    AUTHENTICATE
}

class AppState(val type: StateMachineType,
               val stateMachine : StateMachine<State, Event, SideEffect>) {

    var updateRequired = false

    fun transition(event: Event) {
        stateMachine.transition(event)
    }

    override fun equals(other: Any?): Boolean {
        return when(other){
            is State -> {
                stateMachine.state == other
            }
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + stateMachine.hashCode()
        return result
    }

    companion object {
        private val LOG = Logger.getLogger(AppState::class.java.name)

        fun create(type: StateMachineType, state: State? = null) : AppState {
            fun createStateMachine(type: StateMachineType) : StateMachine<State, Event, SideEffect> {
                return when (type) {
                    StateMachineType.AUTHENTICATE ->
                        AuthenticateStateMachine.create()
                }
            }

            val stateMachine = createStateMachine(type)
            state?.let {
                return AppState(
                        type = type,
                        stateMachine = stateMachine.with { initialState(state) }
                )
            } ?: run {
                return AppState(type = type, stateMachine = stateMachine)
            }
        }

        fun stateMachineOnTransition(
                transition: StateMachine.Transition<State, Event, SideEffect>
        ) {
            val validTransition = transition as? StateMachine.Transition.Valid ?: return
            when (validTransition.sideEffect) {
                SideEffect.LogHome ->
                    LOG.info( "Transitioned to home")
                // Authenticate
                SideEffect.LogOperatorActivated ->
                    LOG.info( "Transitioned to activated")
                null ->
                    LOG.info( "Failed to transition to the next state")
            }
        }
    }
}

sealed class State {
    object Home : State()
    // Authenticate
    object OperatorActivated : State()
}

sealed class Event {
    object OnHome : Event()
    // Authenticate
    object OnOperatorActivated : Event()
}

sealed class SideEffect {
    object LogHome : SideEffect()
    // Authenticate
    object LogOperatorActivated : SideEffect()
}