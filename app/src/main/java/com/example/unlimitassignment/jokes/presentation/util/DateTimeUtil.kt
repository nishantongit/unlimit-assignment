package com.example.unlimitassignment.jokes.presentation.util

import android.text.format.DateFormat
import java.util.*

object DateTimeUtil {
    fun getFormatedDate(timestamp:Long): String {
        val cal: Calendar = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = timestamp
        return DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString()
    }
}