package work.monkey.kotlintemplate.repository.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class MonkeyDetails(
    val status: MonkeyStatusType = MonkeyStatusType.HAPPY,
    val monkeyType: MonkeyType = MonkeyType.MARMOSET,
    val dob: Date = Date()
) : Comparable<MonkeyDetails>, Parcelable {

    companion object {}

    override fun toString(): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MMM-dd hh:mm:ss", Locale.US)

        return "TransactionDetails{" +
                ", status = " + status +
                ", monkeyType = " + monkeyType +
                ", dob = " + dateFormat.format(dob) +
                "}"
    }

    override fun compareTo(other: MonkeyDetails): Int {
        return status.compareTo(other.status)
    }

    override fun equals(other: Any?): Boolean {
        return when(other){
            is MonkeyDetails -> {
                status == other.status
            }
            else -> false
        }
    }
}