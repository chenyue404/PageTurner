package com.cy.page_turner

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.net.toUri

class MainActivity : Activity() {

    private val btConfig: Button by lazy { findViewById(R.id.btConfig) }
    private val btUp: Button by lazy { findViewById(R.id.btUp) }
    private val btDown: Button by lazy { findViewById(R.id.btDown) }

    private var upClickStr: String? = ""
    private var upLongClickStr: String? = ""
    private var downClickStr: String? = ""
    private var downLongClickStr: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btConfig.apply {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, ConfigActivity::class.java))
            }
            setOnLongClickListener {
                finish()
                true
            }
        }
        btUp.apply {
            setOnClickListener {
                runCommand(upClickStr)
            }
            setOnLongClickListener {
                runCommand(upLongClickStr)
                true
            }
        }
        btDown.apply {
            setOnClickListener {
                runCommand(downClickStr)
            }
            setOnLongClickListener {
                runCommand(downLongClickStr)
                true
            }
        }
    }

    override fun onResume() {
        super.onResume()
        readConfig()
    }

    private fun readConfig() {
        getSharedPreferences(ConfigActivity.SP_FILE_KEY, MODE_PRIVATE).let { sp ->
            upClickStr = sp.getString(ConfigActivity.SP_KEY_UP_CLICK, "")
            upLongClickStr = sp.getString(ConfigActivity.SP_KEY_UP_LONG_CLICK, "")
            downClickStr = sp.getString(ConfigActivity.SP_KEY_DOWN_CLICK, "")
            downLongClickStr = sp.getString(ConfigActivity.SP_KEY_DOWN_LONG_CLICK, "")
        }
    }

    private fun runCommand(command: String?) {
        if (command.isNullOrEmpty()) return
        try {
            startActivity(Intent(Intent.ACTION_VIEW, command.toUri()))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "未找到Activity", Toast.LENGTH_SHORT).show()
        }
    }
}