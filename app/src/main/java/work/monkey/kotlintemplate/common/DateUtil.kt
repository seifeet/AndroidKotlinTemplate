package work.monkey.kotlintemplate.common

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DateUtil {
    companion object {
        // https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
        fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }

        fun String.toDate(format: String, locale: Locale = Locale.getDefault()): Date? {
            return try {
                SimpleDateFormat(format, locale).parse(this)
            } catch (exception: Exception) {
                null
            }
        }

        /**
         * Get a diff between two dates
         * @param date the date
         * @param timeUnit the unit in which you want the diff
         * @return the diff value, in the provided unit
         */
        fun Date.getDateDiffFrom(date: Date, timeUnit: TimeUnit): Long {
            val diffInMillies = this.time - date.time
            return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS)
        }

        fun Date.addDays(days: Int): Date {
            val calendar = Calendar.getInstance()
            calendar.time = this
            calendar.add(Calendar.DAY_OF_YEAR, days);

            return calendar.time
        }

        fun Date.getCalendarMonth(): Int {
            val calendar = Calendar.getInstance()
            calendar.time = this
            return calendar.get(Calendar.MONTH) + 1 // Crazy Sun developers
        }

        fun Date.getCalendarYear(): Int {
            val calendar = Calendar.getInstance()
            calendar.time = this
            return calendar.get(Calendar.YEAR)
        }
    }
}