package work.monkey.kotlintemplate.common

fun <T> ArrayList<T>.pop(): T? {
    val element = lastOrNull()
    if (!isEmpty()){
        removeAt(size -1)
    }
    return element
}

fun <T> ArrayList<T>.push(element: T) {
    add(element)
}

fun <T> ArrayList<T>.peek() : T? = lastOrNull()
