package work.monkey.kotlintemplate.state

import work.monkey.kotlintemplate.repository.entity.*
import com.github.javafaker.Faker

internal fun Supervisor.Companion.mock() : Supervisor {
    return Supervisor(
        id = faker.random().hex(11),
        displayName = faker.company().name(),
        addressLine1 = faker.address().streetAddress(),
        addressLine2 = faker.address().secondaryAddress(),
        city = faker.address().city(),
        status = faker.random().nextInt(4).toString(),
        state = faker.address().state(),
        country = faker.address().country(),
        postalCode = faker.address().zipCode(),
        phone = faker.phoneNumber().phoneNumber()
    )
}

private val faker: Faker by lazy { Faker() }
