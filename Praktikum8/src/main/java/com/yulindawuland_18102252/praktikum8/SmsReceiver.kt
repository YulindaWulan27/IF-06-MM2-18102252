package com.yulindawuland_18102252.praktikum8

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log

class SmsReceiver : BroadcastReceiver() {
    private val TAG = SmsReceiver::class.java.simpleName
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        try {
            if (bundle != null) {
                val pdusObj = bundle.get("pdus") as Array<Any>
                for (aPdusObj in pdusObj) {
                    val currentMessage = getIncomingMessage(aPdusObj, bundle)
                    val senderNum = currentMessage.displayOriginatingAddress.toString()
                    val message = currentMessage.displayMessageBody
                    Log.d(TAG, "senderNum: $senderNum; message: $message")
                    val showSmsIntent = Intent(context, SmsReceiverActivity::class.java)
                    showSmsIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_NO, senderNum)
                    showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_MESSAGE, message)
                    context.startActivity(showSmsIntent)
                }
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        TODO("SmsReceiver.onReceive() is not implemented")
    }
}



        private fun getIncomingMessage(aObject: Any, bundle: Bundle): SmsMessage {
            val currentSMS: SmsMessage
            if (Build.VERSION.SDK_INT >= 23) {
                val format = bundle.getString("format")
                currentSMS = SmsMessage.createFromPdu(aObject as ByteArray, format)
            } else currentSMS = SmsMessage.createFromPdu(aObject as ByteArray)
            return currentSMS
        }