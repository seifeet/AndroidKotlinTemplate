package work.monkey.kotlintemplate.common

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * halcyonmobile
 *
 * Holds a destinationId saved by [CurrentDestinationCheckingNavController].
 *
 * Saves and Restores that state into [savedStateHandle].
 */
internal class CurrentDestinationHoldingStore(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    var savedCurrentDestinationId: Int?
        get() = savedStateHandle.get(CURRENT_DESTINATION_ID_STATE)
        set(value) = savedStateHandle.set(CURRENT_DESTINATION_ID_STATE, value)

    companion object {
        const val CURRENT_DESTINATION_ID_STATE = "current_destination_holding_store_current_destination_id_state"
    }

}