package com.raqun.infoview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.support.annotation.DrawableRes
import android.support.annotation.ColorRes
import com.raqun.infoview.InfoView.InfoType
import android.view.ViewGroup
import android.support.annotation.NonNull
import android.widget.ImageView
import android.widget.TextView
import android.view.LayoutInflater
import com.raqun.infoview.InfoView.InfoType
import android.content.res.TypedArray






@SuppressLint("ViewConstructor")
class InfoView constructor(context: Context,
                           attrs: AttributeSet? = null,
                           defStyle: Int = 0,
                           defStyleRes: Int = 0) : LinearLayout(context, attrs) {
    /**
     * Vars
     */

    private var mInfoType = InfoType.INFO

    @DrawableRes
    private var mIconRes: Int = NO_RES

    private var mInfoTitle: CharSequence? = null

    private var mInfoMessage: CharSequence? = null

    companion object {
        private const val NO_RES = 0
    }

    /**
     * Views
     */

    private val mImageViewIcon: ImageView

    private val mTextViewTitle: TextView

    private val mTextViewMessage: TextView

    private val mContainer: ViewGroup

    init {
        val fields = context.theme
                .obtainStyledAttributes(attrs, R.styleable.CustomInfoView, 0, 0)

        try {
            mInfoType = InfoType.fromValue(fields.getInt(R.styleable.CustomInfoView_infoType,
                    InfoType.INFO.value))
            mIconRes = fields.getInt(R.styleable.CustomInfoView_infoIcon, 0)
            mInfoTitle = fields.getString(R.styleable.CustomInfoView_infoTitle)
            mInfoMessage = fields.getString(R.styleable.CustomInfoView_infoText)
        } finally {
            fields.recycle()
        }

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_info_view, this, true)

        mImageViewIcon = view.findViewById(R.id.icon)
        mTextViewTitle = view.findViewById(R.id.title)
        mTextViewMessage = view.findViewById(R.id.message)
        mContainer = view.findViewById(R.id.container)

        initView()
    }

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