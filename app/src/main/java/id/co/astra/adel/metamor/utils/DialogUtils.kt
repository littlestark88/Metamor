package id.co.astra.adel.metamor.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.DialogCallback
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import id.co.astra.adel.metamor.R
import java.text.NumberFormat
import java.util.*

fun customDialogYesOrNo(
    context: Context,
    title: String? = null,
    message: String? = null,
    cancelable: Boolean = false,
    positiveButtonText: String? = null,
    negativeButtonText: String? = null,
    positiveButtonListener: (() -> Unit)? = null,
    negativeButtonListener: (() -> Unit)? = null
) {
    val dialog = MaterialAlertDialogBuilder(context)
        .setTitle(
            if(title.isNullOrBlank()) context.getString(R.string.label_information)
            else title
        )
        .setMessage(message.orEmpty())
        .setPositiveButton(
            if(positiveButtonText.isNullOrBlank()) context.getString(R.string.label_yes)
            else positiveButtonText
        ) { dialog, _ ->
            dialog.dismiss()
            positiveButtonListener?.invoke()
        }
        .setNegativeButton(
            if(negativeButtonText.isNullOrBlank()) context.getString(R.string.label_no)
            else negativeButtonText
        ) { dialog, _ ->
            dialog.dismiss()
            negativeButtonListener?.invoke()
        }
        .setCancelable(cancelable)
    dialog.show()
}

fun customDialogOk(
    context: Context,
    title: String? = null,
    message: String? = null,
    cancelable: Boolean = false,
    positiveButtonText: String? = null,
    positiveButtonListener: (() -> Unit)? = null
) {
    val dialog = MaterialAlertDialogBuilder(context)
        .setTitle(
            if(title.isNullOrBlank()) context.getString(R.string.label_information)
            else title
        )
        .setMessage(message.orEmpty())
        .setPositiveButton(
            if(positiveButtonText.isNullOrBlank()) context.getString(R.string.label_ok)
            else positiveButtonText
        ) { dialog, _ ->
            dialog.dismiss()
            positiveButtonListener?.invoke()
        }
        .setCancelable(cancelable)
    dialog.show()
}

fun showSimpleListDialog(
    context: Context,
    title: String,
    items: List<String>,
    onItemClicked: (Int) -> Unit
) {
    MaterialAlertDialogBuilder(context)
        .setTitle(title)
        .setItems(items.toTypedArray()) { dialog, selectedIndex ->
            dialog.dismiss()
            onItemClicked.invoke(selectedIndex)
        }
        .show()
}

