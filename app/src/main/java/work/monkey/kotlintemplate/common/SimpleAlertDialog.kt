package work.monkey.kotlintemplate.common

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import work.monkey.kotlintemplate.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

enum class SimpleAlertDialogType(
    val resourceId: Int,
    val positiveTextId: Int,
    val negativeTextId: Int,
    val titleId: Int,
    val messageId: Int
) {
    INFO(
        resourceId = R.drawable.ic_baseline_info_24,
        positiveTextId = R.string.text_ok,
        negativeTextId = -1,
        titleId = -1,
        messageId = -1
    ),
    WARNING(
        resourceId = R.drawable.ic_baseline_warning_24,
        positiveTextId = R.string.text_ok,
        negativeTextId = -1,
        titleId = -1,
        messageId = -1
    ),
    ERROR(
        resourceId = R.drawable.ic_baseline_error_24,
        positiveTextId = R.string.text_ok,
        negativeTextId = -1,
        titleId = -1,
        messageId = -1
    ),
    SUCCESS(
        resourceId = R.drawable.ic_baseline_success_24,
        positiveTextId = R.string.text_ok,
        negativeTextId = -1,
        titleId = -1,
        messageId = -1
    ),
    SERVER_ERROR(
        resourceId = R.drawable.ic_baseline_success_24,
        positiveTextId = R.string.text_ok,
        negativeTextId = -1,
        titleId = R.string.error_server,
        messageId = R.string.error_problem_request
    );
}

class SimpleAlertDialog(
    val context: Context,
    val type: SimpleAlertDialogType,
    val title: String?,
    val message: String?
) {

    fun show(callback: () -> Unit) {
        val builder = MaterialAlertDialogBuilder(context)

        if (type.titleId > 0 ) {
            builder.setTitle(context.getString(type.titleId))
        } else {
            builder.setTitle(title)
        }
        if (type.messageId > 0 ) {
            builder.setMessage(context.getString(type.messageId))
        } else {
            builder.setMessage(message)
        }
        builder.setIcon(type.resourceId)
        builder.background = ColorDrawable(
            Color.parseColor("#FFFFFF")
        )
        if (type.positiveTextId > 0 ) {
            builder.setPositiveButton(
                context.getString(type.positiveTextId)
            ) { _ /*dialog*/, _ /*which*/ ->
                callback.invoke()
            }
        }
        if (type.negativeTextId > 0 ) {
            builder.setNegativeButton(
                context.getString(type.negativeTextId)
            ) { _ /*dialog*/, _ /*which*/ ->
                callback.invoke()
            }
        }
        builder.setOnCancelListener {
            callback.invoke()
        }
        builder.setOnDismissListener {
            callback.invoke()
        }
        // set dialog non cancelable
        builder.setCancelable(false)

        val dialog = builder.create()
        dialog.show()
    }
}