package work.monkey.kotlintemplate.repository.entity

data class Supervisor(
    val id: String,
    val displayName: String,
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val status: String,
    val state: String,
    val country: String,
    val postalCode: String,
    val phone: String
) : Comparable<Supervisor> {

    override fun toString(): String {
        return "Supervisor{" +
                "name = " + displayName +
                ", postalCode = " + postalCode +
                ", id = " + id +
                "}"
    }

    override fun compareTo(other: Supervisor): Int {
        return id.compareTo(other.id)
    }

    override fun equals(other: Any?): Boolean {
        return when(other){
            is Supervisor -> {
                displayName == other.displayName
            }
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + country.hashCode()
        return result
    }

    companion object
}

internal fun Supervisor.Companion.empty() : Supervisor {
    return Supervisor(
        id = "",
        displayName = "",
        addressLine1 = "",
        addressLine2 = "",
        city = "",
        status = "",
        state = "",
        country = "",
        postalCode = "",
        phone = ""
    )
}