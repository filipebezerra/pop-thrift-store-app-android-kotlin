/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.filipebezerra.android.popthriftstore.ui.util

/**
 * Extension functions and Binding Adapters.
 */

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.util.event.Event
import dev.filipebezerra.android.popthriftstore.util.event.EventObserver

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun View.showSnackbar(
    snackbarText: String,
    timeLength: Int,
    anchorView: View? = null,
    onDismiss: (() -> Unit)? = null,
) {
    Snackbar.make(this, snackbarText, timeLength)
        .apply {
            animationMode = ANIMATION_MODE_SLIDE
            setAnchorView(anchorView)
            setBackgroundTint(ContextCompat.getColor(context, R.color.snackbar_background))
            addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    onDismiss?.invoke()
                }
            })
        }
        .run { show() }
}

/**
 * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
 */
fun View.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<Event<Int>>,
    timeLength: Int,
    anchorView: View? = null,
    onDismiss: (() -> Unit)? = null
) = snackbarEvent.observe(lifecycleOwner, EventObserver {
    showSnackbar(context.getString(it), timeLength, anchorView, onDismiss)
})