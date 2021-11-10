package work.monkey.kotlintemplate.state

import work.monkey.kotlintemplate.common.RandomDate
import work.monkey.kotlintemplate.repository.entity.*
import com.github.javafaker.Faker
import java.util.*
import java.util.concurrent.TimeUnit

internal fun MonkeySapiens.Companion.mock(
        randomDate: RandomDate = RandomDate()
) : MonkeySapiens {
    val number = faker.number()
    val amount = number.randomDouble(2, 100, 2000)
    var type = randomSapiensType()
    val tipOrCashback = amount * (number.randomDigit() / 100)
    var cash = 0.0
    var hair = 0.0

    when (type) {
        SapiensType.ERECTUS -> {
            cash = tipOrCashback
            hair = 0.0
        }
        SapiensType.FLORESIENSIS -> {
            cash = 0.0
            hair = cash
        }
        SapiensType.HABILIS -> {
            cash = 0.0
            hair = 0.0
        }
    }

    return MonkeySapiens(
            date = randomDate.nextDate(),
            height = amount,
            cash = cash,
            hair = hair,
            fingerCount = amount + cash + hair,
            type = randomSapiensType(),
            details = MonkeyDetails.mock()
    )
}

internal fun MonkeyDetails.Companion.mock() : MonkeyDetails {
    val number = faker.number()
    val date = faker.date()
    val business = faker.business()

    val digit = number.randomDigit()

    val status = when (digit) {
        in 1..2 -> {
            MonkeyStatusType.SAD
        }
        in 3..4 -> {
            MonkeyStatusType.HAPPY
        }
        else -> {
            MonkeyStatusType.SMILING
        }
    }

    var creditCardType = when (digit) {
        0 -> {
            MonkeyType.MARMOSET
        }
        1 -> {
            MonkeyType.MACAQUE
        }
        2 -> {
            MonkeyType.MANDRILL
        }
        else -> MonkeyType.MARMOSET
    }

    return MonkeyDetails(
            status = status,
            monkeyType = creditCardType,
            dob = date.past(1200, TimeUnit.DAYS)
    )
}

/**
 * Generates a random transaction type
 */
private fun randomSapiensType(): SapiensType {
    val pick = Random().nextInt(SapiensType.values().count())
    return SapiensType.values()[pick]
}

private val faker: Faker by lazy { Faker() }
