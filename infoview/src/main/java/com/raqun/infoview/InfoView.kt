package com.raqun.infoview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.support.annotation.DrawableRes
import android.support.annotation.ColorRes


@SuppressLint("ViewConstructor")
class InfoView constructor(context: Context,
                           attrs: AttributeSet? = null,
                           defStyle: Int = 0,
                           defStyleRes: Int = 0) : LinearLayout(context, attrs) {
    // emty info view

    // INFO TYPES
    enum class InfoType private constructor(private val value: Int,
                                            @param:DrawableRes @field:DrawableRes
                                            private val backgroundRes: Int,
                                            @param:ColorRes @field:ColorRes
                                            private val titleColorRes: Int,
                                            @param:ColorRes @field:ColorRes
                                            private val infoColorRes: Int,
                                            @param:DrawableRes @field:DrawableRes
                                            private val iconRes: Int) {

        // TODO Uncomment when icons available
        //INFO(0, R.drawable.background_info_view_info, R.color.infoTextInfoColor, R.color.infoTextInfoColor, 0),
        //WARNING(1, R.drawable.background_info_view_warning, R.color.infoTextWarningColor, R.color.infoTextInfoColor, 0),
        //SUCCESS(2, R.drawable.background_info_view_success, R.color.infoTextSuccessColor, R.color.infoTextSuccessColor, 0);

        companion object {

            fun fromValue(value: Int): InfoType? {
                for (type in values()) {
                    if (type.value == value) {
                        return type
                    }
                }

                return null
            }
        }
    }
}