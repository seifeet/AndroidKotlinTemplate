package work.monkey.kotlintemplate.repository.entity

import java.util.*

interface Monkey : Comparable<Monkey> {
    val id: String
    val date: Date
    val height: Double

    override fun compareTo(other: Monkey): Int {
        return date.compareTo(other.date)
    }
}

