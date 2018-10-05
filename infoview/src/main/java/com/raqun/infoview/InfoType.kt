package com.raqun.infoview

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes

enum class InfoType constructor(val value: Int,
                                @param:DrawableRes @field:DrawableRes
                                val backgroundRes: Int,
                                @param:ColorRes @field:ColorRes
                                val titleColorRes: Int,
                                @param:ColorRes @field:ColorRes
                                val infoColorRes: Int,
                                @param:DrawableRes @field:DrawableRes
                                val iconRes: Int) {

    INFO(0, R.drawable.background_info_view_info, R.color.infoViewInfo, R.color.infoViewInfo, R.drawable.ic_baseline_info_24px),
    WARNING(1, R.drawable.background_info_view_warning, R.color.infoViewWarning, R.color.infoViewWarning, R.drawable.ic_baseline_error_24px),
    SUCCESS(2, R.drawable.background_info_view_success, R.color.infoViewSuccess, R.color.infoViewSuccess, R.drawable.ic_baseline_done_24px);

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