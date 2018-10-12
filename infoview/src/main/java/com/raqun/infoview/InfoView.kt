package com.raqun.infoview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
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
import android.support.v4.content.ContextCompat
import android.content.res.Resources.NotFoundException
import android.view.View
import android.text.TextUtils


@SuppressLint("ViewConstructor")
class InfoView @JvmOverloads constructor(context: Context,
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
            mIconRes = fields.getInt(R.styleable.CustomInfoView_infoIcon, NO_RES)
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

    /**
     * Setter Getter Methods
     */
    fun setInfo(message: CharSequence?) {
        this.mInfoMessage = message
        initMessage()
    }

    fun getInfo(): CharSequence? {
        return mInfoMessage
    }

    fun setTitle(title: CharSequence?) {
        this.mInfoTitle = title
        initTitle()
    }

    fun getTitle(): CharSequence? {
        return mInfoTitle
    }

    fun setIcon(@DrawableRes iconRes: Int) {
        this.mIconRes = iconRes
        initIconRes()
    }

    @DrawableRes
    fun getIconRes(): Int {
        return mIconRes
    }

    fun setInfoType(infoType: InfoType?) {
        if (infoType != null) {
            this.mInfoType = infoType
            initType()
        }
    }

    fun getInfoType(): InfoType? {
        return mInfoType
    }

    /**
     * Init Methods
     */
    private fun initView() {
        initType()
        initIconRes()
        initTitle()
        initMessage()
    }

    private fun initType() {
        if (mInfoType == null) {
            mInfoType = InfoType.INFO
        }

        mTextViewTitle.setTextColor(ContextCompat.getColor(context, mInfoType!!.titleColorRes))
        mTextViewMessage.setTextColor(ContextCompat.getColor(context, mInfoType!!.infoColorRes))
        mImageViewIcon.setColorFilter(ContextCompat.getColor(mImageViewIcon.context, mInfoType!!.titleColorRes))
        mContainer.background = ContextCompat.getDrawable(context, mInfoType!!.backgroundRes)
    }

    private fun initIconRes() =
            try {
                mImageViewIcon.setImageResource(mIconRes)
            } catch (ex: Resources.NotFoundException) {
                mImageViewIcon.visibility = View.GONE
            }

    private fun initTitle() {
        mTextViewTitle.text = mInfoTitle
        if (TextUtils.isEmpty(mInfoTitle)) {
            mTextViewTitle.visibility = View.GONE
        }
    }

    private fun initMessage() {
        mTextViewMessage.text = mInfoMessage
        if (TextUtils.isEmpty(mInfoMessage)) {
            mTextViewMessage.visibility = View.GONE
        }
    }

    companion object {
        private const val NO_RES = 0
    }
}