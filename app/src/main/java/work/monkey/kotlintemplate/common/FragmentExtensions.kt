package work.monkey.kotlintemplate.common

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

internal fun Fragment.setTitle(@StringRes resId: Int) {
    (activity as AppCompatActivity).supportActionBar?.setTitle(resId)
}

internal fun Fragment.showActionBar() {
    (activity as AppCompatActivity).supportActionBar?.show()
}

internal fun Fragment.hideActionBar() {
    (activity as AppCompatActivity).supportActionBar?.hide()
}

internal fun AppCompatActivity.showActionBar() {
    supportActionBar?.show()
}

internal fun AppCompatActivity.hideActionBar() {
    supportActionBar?.hide()
}

/**
 * halcyonmobile
 *
 * Returns a [CurrentDestinationCheckingNavController] which before delegating to [androidx.navigation.NavController.navigate] checks the currentDestination.
 *
 * Note: The saved DestinationId to check against is gathered from the first [CurrentDestinationCheckingNavController.navigate] call,
 * if it wasn't correct then the app will crash as normally.
 */
internal fun Fragment.findSafeNavController() : CurrentDestinationCheckingNavController =
    CurrentDestinationCheckingNavController(findNavController(), ViewModelProvider(this)[CurrentDestinationHoldingStore::class.java])