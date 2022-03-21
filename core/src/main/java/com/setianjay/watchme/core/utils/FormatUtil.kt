package com.setianjay.watchme.core.utils

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object FormatUtil {

    /* default date */
    private const val DEFAULT_DATE = "11 November 2000"

    /* default format of date */
    private const val DEFAULT_FORMAT = "yyyy-MM-dd"

    /* result format of date */
    private const val RESULT_FORMAT = "dd MMMM yyyy"


    /**
     * change date format from yyyy-MM-dd to dd MMMM yyyy (eg. 2022-03-17 change to 17 March 2022)
     *
     * @param   strDate         string of date in format yyyy-MM-dd
     * @return                  string of date in format dd MMMM yyyy
     * */
    fun changeDateFormat(strDate: String): String {

        val resultDate: String =  try {
            val oldFormat = SimpleDateFormat(DEFAULT_FORMAT, Locale.getDefault())
            val oldDate = oldFormat.parse(strDate)

            return if (oldDate != null) {
                val newFormat = SimpleDateFormat(RESULT_FORMAT, Locale.getDefault())
                newFormat.format(oldDate)
            } else {
                DEFAULT_DATE
            }

        } catch (e: Exception) {
            when (e) {
                is ParseException -> {
                    Timber.e("Error on parse date because ${e.message}")
                    e.printStackTrace()
                }
                else -> {
                    Timber.e("Error on format date because ${e.message}")
                    e.printStackTrace()
                }
            }
            DEFAULT_DATE
        }

        return resultDate
    }
}