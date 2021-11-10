package work.monkey.kotlintemplate.state

import work.monkey.kotlintemplate.common.StateMachine
import java.util.logging.Logger

sealed class AuthenticateStateMachine {

    companion object {
        private val LOG = Logger.getLogger(AuthenticateStateMachine::class.java.name)

        fun create() : StateMachine<State, Event, SideEffect> {
            val stateMachine = StateMachine.create<State, Event, SideEffect> {
                initialState(State.Home)
                state<State.OperatorActivated> {
                    on<Event.OnHome> {
                        transitionTo(State.Home, SideEffect.LogHome)
                    }
                }
                onTransition {
                    AppState.stateMachineOnTransition(it)
                }
            }

            LOG.info("State machine with the initial state of ${stateMachine.state} was created")

            return stateMachine
        }
    }
}