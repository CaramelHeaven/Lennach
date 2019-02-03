package com.caramelheaven.lennach.utils.animations.util_helpers

import android.content.Context
import android.view.View

/**
 * Created by CaramelHeaven on 23:43, 03/02/2019.
 * Helper interfaces for control animation moving view
 */
interface Expandable {
    fun expandFromZeroToHalfOfPartScreen(view: View, context: Context)

    fun expandFromHalfOfPartToAllScreen(view: View, screenHeight: Int, context: Context)
}

interface Collapsible {
    fun collapseFromAllScreenHalfOfPart(view: View, context: Context)

    fun collapseFromHalfOfPartToZero(view: View, context: Context)
}
