package work.monkey.kotlintemplate.repository.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class MonkeySapiens(
    override val id: String = UUID.randomUUID().toString(),
    override val date: Date = Date(),
    override val height: Double = 0.0,
    val cash: Double = 0.0,
    val hair: Double = 0.0,
    val fingerCount: Double = 0.0,
    val type: SapiensType = SapiensType.ERECTUS,
    val details: MonkeyDetails = MonkeyDetails()
) : Monkey, Parcelable {

    // needed for the mock extension
    companion object {}

    override fun toString(): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MMM-dd hh:mm:ss", Locale.US)

        return "Transaction{" +
                "id = " + id +
                ", date = " + dateFormat.format(date) +
                ", type = " + type.text +
                ", amount = " + height.toString() +
                ", tip = " + cash.toString() +
                ", cashback = " + hair.toString() +
                ", totalCharge = " + fingerCount.toString() +
                ", type = " + type.toString() +
                ", details = " + details.toString() +
                "}"
    }
}