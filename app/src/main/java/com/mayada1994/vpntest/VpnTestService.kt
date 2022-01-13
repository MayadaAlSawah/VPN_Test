package com.mayada1994.vpntest

import android.content.Intent
import android.net.VpnService
import java.net.InetSocketAddress
import java.nio.channels.DatagramChannel

class VpnTestService: VpnService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            this.Builder()
                .setSession(getString(R.string.app_name))
                .addAddress("192.168.0.1", 24)
                .addDnsServer(getString(R.string.custom_dns))
//                .addRoute("0.0.0.0", 0)
                .establish()

            val tunnel = DatagramChannel.open()
            tunnel.connect(InetSocketAddress("127.0.0.1", 8087))
            protect(tunnel.socket())
        }.start()

        return START_STICKY
    }

}