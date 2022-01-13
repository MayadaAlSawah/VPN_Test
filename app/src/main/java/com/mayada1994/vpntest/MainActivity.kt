package com.mayada1994.vpntest

import android.app.Activity
import android.content.Intent
import android.net.VpnService
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<AppCompatButton>(R.id.btnPermission).setOnClickListener {
            askVpnAccess(this)
        }

        findViewById<AppCompatButton>(R.id.btnStartVpn).setOnClickListener {
            startService(Intent(this, VpnTestService::class.java))
        }
    }

    private fun askVpnAccess(activity: Activity): Boolean = try {
        activity.startActivityForResult(VpnService.prepare(activity), VPN_ACCESS_REQUEST)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

    companion object {
        const val VPN_ACCESS_REQUEST = 1992
    }

}