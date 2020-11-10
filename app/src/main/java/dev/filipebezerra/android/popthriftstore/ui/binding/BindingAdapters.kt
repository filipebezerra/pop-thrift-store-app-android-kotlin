package dev.filipebezerra.android.popthriftstore.ui.binding

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter

@BindingAdapter("toText")
fun bindToText(textView: TextView, @StringRes stringRes: Int) {
    textView.text = textView.context.getString(stringRes)
}