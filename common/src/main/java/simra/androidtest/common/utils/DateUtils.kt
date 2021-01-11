package simra.androidtest.common.utils

import java.util.*

object DateUtils {
    fun getTimeGreetingMessage(): String = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
        in 0..11 -> "Good Morning!"
        in 12..15 -> "Good Afternoon!"
        in 16..20 -> "Good Evening!"
        in 21..23 -> "Good Night!"
        else -> "Hello!"
    }
}