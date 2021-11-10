package work.monkey.kotlintemplate.repository.entity

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class MonkeySummary(
    override val id: String = UUID.randomUUID().toString(),
    override val date: Date = Date(),
    override val height: Double,
    var count: Int
) : Monkey {

    override fun toString(): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MMM-dd hh:mm:ss", Locale.US)

        return "Transaction{" +
                "Date = " + dateFormat.format(date) +
                ", amount = " + height.toString() +
                "}"
    }

    override fun equals(other: Any?): Boolean {
        return when(other){
            is MonkeySapiens -> {
                id == other.id
            }
            else -> false
        }
    }
}