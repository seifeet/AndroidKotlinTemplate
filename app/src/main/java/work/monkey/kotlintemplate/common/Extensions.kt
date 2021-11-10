package work.monkey.kotlintemplate.common

import android.content.Context
import androidx.annotation.DimenRes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

/**
 * Returns a *typed* array containing all of the elements of this collection.
 *
 * Allocates an array of runtime type `T` having its size equal to the size of this collection
 * and populates the array with the elements of this collection.
 * @sample samples.collections.Collections.Collections.collectionToTypedArray
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> Collection<T>.toTypedArray(): Array<T> {
    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    val thisCollection = this as java.util.Collection<T>
    return thisCollection.toArray(arrayOfNulls<T>(0)) as Array<T>
}

internal fun Context.px(@DimenRes dimen: Int): Int = resources.getDimension(dimen).toInt()

internal fun Context.dp(@DimenRes dimen: Int): Float = px(dimen) / resources.displayMetrics.density