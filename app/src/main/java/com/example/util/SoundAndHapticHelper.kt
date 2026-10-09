package com.example.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

object SoundAndHapticHelper {

    fun vibrateTap(context: Context) {
        vibrate(context, 20, 80)
    }

    fun vibrateSuccess(context: Context) {
        vibrate(context, 80, 200)
    }

    fun vibrateError(context: Context) {
        vibrate(context, 120, 255)
    }

    fun vibrateHint(context: Context) {
        vibrate(context, 40, 150)
    }

    private fun vibrate(context: Context, durationMs: Long, amplitude: Int) {
        try {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vm = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vm?.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            }

            vibrator?.let { v ->
                if (v.hasVibrator()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(
                            VibrationEffect.createOneShot(
                                durationMs,
                                amplitude.coerceIn(1, 255)
                            )
                        )
                    } else {
                        @Suppress("DEPRECATION")
                        v.vibrate(durationMs)
                    }
                }
            }
        } catch (_: Exception) {
            // Gracefully ignore if vibrator not permitted or available
        }
    }
}
