package work.monkey.kotlintemplate.common

import work.monkey.kotlintemplate.common.DateUtil.Companion.addDays
import java.util.*
import java.util.concurrent.ThreadLocalRandom

internal class RandomDate(
        private val minDate: Date = Date().addDays(-360),
        private val maxDate: Date = Date()
) {

    fun nextDate(): Date {
        val startMillis = minDate.time
        val endMillis = maxDate.time
        val randomMillisSinceEpoch: Long = ThreadLocalRandom
            .current()
            .nextLong(startMillis, endMillis)
        return Date(randomMillisSinceEpoch)
    }

    override fun toString(): String {
        return "RandomDate{maxDate=$maxDate, minDate=$minDate}"
    }
}