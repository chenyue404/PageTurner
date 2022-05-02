package com.cy.page_turner

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

/**
 * Created by chenyue on 2022/5/2 0002.
 */
class ConfigActivity : Activity() {
    companion object {
        const val SP_FILE_KEY = "SP_FILE_KEY"
        const val SP_KEY_UP_CLICK = "SP_KEY_UP_CLICK"
        const val SP_KEY_UP_LONG_CLICK = "SP_KEY_UP_LONG_CLICK"
        const val SP_KEY_DOWN_CLICK = "SP_KEY_DOWN_CLICK"
        const val SP_KEY_DOWN_LONG_CLICK = "SP_KEY_DOWN_LONG_CLICK"
    }

    private val etUpClick: EditText by lazy { findViewById(R.id.etUpClick) }
    private val etUpLongClick: EditText by lazy { findViewById(R.id.etUpLongClick) }
    private val etDownClick: EditText by lazy { findViewById(R.id.etDownClick) }
    private val etDownLongClick: EditText by lazy { findViewById(R.id.etDownLongClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val sp = getSharedPreferences(SP_FILE_KEY, MODE_PRIVATE)

        etUpClick.setText(sp.getString(SP_KEY_UP_CLICK, ""))
        etUpLongClick.setText(sp.getString(SP_KEY_UP_LONG_CLICK, ""))
        etDownClick.setText(sp.getString(SP_KEY_DOWN_CLICK, ""))
        etDownLongClick.setText(sp.getString(SP_KEY_DOWN_LONG_CLICK, ""))

        findViewById<Button>(R.id.btSave).setOnClickListener {
            val upClickStr = etUpClick.text.toString()
            val upLongClickStr = etUpLongClick.text.toString()
            val downClickStr = etDownClick.text.toString()
            val downLongClickStr = etDownLongClick.text.toString()

            sp.edit()
                .putString(SP_KEY_UP_CLICK, upClickStr)
                .putString(SP_KEY_UP_LONG_CLICK, upLongClickStr)
                .putString(SP_KEY_DOWN_CLICK, downClickStr)
                .putString(SP_KEY_DOWN_LONG_CLICK, downLongClickStr)
                .apply()

            finish()
        }
    }
}