package com.raqun.infoview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.support.annotation.DrawableRes
import android.support.annotation.ColorRes
import android.view.ViewGroup
import android.support.annotation.NonNull
import android.widget.ImageView
import android.widget.TextView
import android.view.LayoutInflater
import android.content.res.TypedArray


@SuppressLint("ViewConstructor")
class InfoView constructor(context: Context,
                           attrs: AttributeSet? = null,
                           defStyle: Int = 0,
                           defStyleRes: Int = 0) : LinearLayout(context, attrs) {
    /**
     * Vars
     */

    private var mInfoType: InfoType?

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
    }
}