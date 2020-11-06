package dev.filipebezerra.android.popthriftstore.ui.instructions

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class InstructionItem(
    @StringRes val titleResource: Int,
    @StringRes val descriptionResource: Int,
    @DrawableRes val imageResource: Int
)