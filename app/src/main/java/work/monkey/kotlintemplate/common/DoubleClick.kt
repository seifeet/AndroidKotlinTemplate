package work.monkey.kotlintemplate.common

/*
 * https://gitlab.com/developerdeveloperdeveloper/androidutilslibrary/
 */

import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

open class DoubleClick(
    private val doubleClickListener: DoubleClickListener,
    private var interval: Long = 200L
) : View.OnClickListener {

    private var counterClicks = 0
    private var isBusy = false

    override fun onClick(view: View) {
        if (!isBusy) {
            isBusy = true

            counterClicks++

            Timer().schedule(interval) {
                GlobalScope.launch(Dispatchers.Main) {
                    if (counterClicks >= 2) {
                        doubleClickListener.onDoubleClickEvent(view)
                    }
                    if (counterClicks == 1) {
                        doubleClickListener.onSingleClickEvent(view)
                    }

                    counterClicks = 0
                }
            }

            isBusy = false
        }
    }
}
