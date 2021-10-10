package com.jinjoo.pouch.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import com.jinjoo.pouch.R
import kotlinx.android.synthetic.main.item_add_dialog.*

class AddItemDialog(context: Context, themeResId: Int) : Dialog(context, themeResId) {
    lateinit var cosTitle:EditText
    lateinit var category:Spinner
    lateinit var expirationDate:Spinner
    lateinit var save:ConstraintLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 다이얼로그 외부 화면 흐리게 표현
        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.5f
        window?.attributes = lpWindow

        setContentView(R.layout.item_add_dialog)
        cosTitle = title
        save = save_btn

        save.setOnClickListener {

        }
    }

    constructor(context: Context) : this(context, android.R.style.Theme_Translucent_NoTitleBar)



}