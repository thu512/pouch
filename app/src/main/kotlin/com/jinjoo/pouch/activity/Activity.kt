package com.jinjoo.pouch.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity

open class Activity : AppCompatActivity() {
    val pd: ProgressDialog by lazy { ProgressDialog(this) }
    fun showPd() {
        with(pd){
            setCancelable(true)
            setMessage("잠시만 기다려주세요...")
            show()
        }
    }

    fun stopPd() {

        if (pd.isShowing) {
            pd.dismiss()
        }
    }
}