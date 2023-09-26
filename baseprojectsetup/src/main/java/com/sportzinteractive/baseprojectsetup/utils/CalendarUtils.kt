package com.sportzinteractive.baseprojectsetup.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

object CalendarUtils {

    const val PUBLISHED_DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss"
    const val PUBLISHED_ASSET_LIST = "yyyy-MM-dd'T'HH:mm:ss"
    const val PUBLISHED_ASSET_DETAILS = "yyyy-MM-dd HH:mm:ss"

    const val PUBLISHED_DISPLAY_DATE_FORMAT = "dd MMM, yyyy"

    const val FIXTURE_MATCH_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss"
    const val FIXTURE_MATCH_DATE_DISPLAY_FORMAT = "EEE dd MMM"

    const val PITCH_PLAYER_MATCH_DATE = "dd MMM"
    const val SCORE_CARD_MATCH_DATE_FORMAT = "yyyy-MM-dd'T'HH:mmZ"

    const val DOB_DATE_FORMAT = "yyyy-MM-dd"
    const val MATCH_DAY = "EEEE dd MMM yyyy"
    const val MATCH_TIME = "HH:mm"

    const val MATCH_DATE_FORMAT = "MM/dd/yyyy"
    const val MATCH_REQUIRED_DATE_FORMAT = "EEEE dd MMM yyyy"
    const val DOB_DATE_DISPLAY_FORMAT = "dd/MM/yyyy"

    fun convertDateStringToSpecifiedDateString(
        dateString: String?,
        dateFormat: String,
        inputLocale: Locale = Locale.ENGLISH,
        requiredDateFormat: String,
        outputLocale: Locale = Locale.ENGLISH,
    ): String? {
        try {
            if (dateString == null) {
                return null
            }
            val simpleDateFormat = SimpleDateFormat(dateFormat, inputLocale)
            val date = simpleDateFormat.parse(dateString)
            if (date != null) {
                val reqSimpleDateFormat = SimpleDateFormat(requiredDateFormat, outputLocale)
                return reqSimpleDateFormat.format(date)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getCurrentDate(): String {
        val simpleDateFormat = SimpleDateFormat(DOB_DATE_FORMAT)
        return simpleDateFormat.format(Date())
    }

    fun getDaysBetweenDates(currentDate: String, pastDate: String): Int {
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val date1 = sdf.parse(currentDate)
            val date2 = sdf.parse(pastDate)
            date1 ?: return 0
            date2 ?: return 0
            val diff: Long = date1.time - date2.time
            //val days = diff / 1000 * 60 * 60 * 24
            val days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt()
            return if (days >= 0) days else 0
        } catch (e: Exception) {
            0
        }
    }

    /**
     * Method to get display date/duration for Home page widgets, Listings and Detail pages
     * The duration calculation logic is commented
     * */
    fun getPublishedDuration(
        dateString: String?,
        dateFormat: String,
        inputLocale: Locale = Locale.ENGLISH,
        requiredDateFormat: String,
        outputLocale: Locale = Locale.ENGLISH
    ): String? {

        try {
            if (dateString == null) {
                return null
            }
            val simpleDateFormat = SimpleDateFormat(dateFormat, inputLocale)
            val date = simpleDateFormat.parse(dateString) ?: return null

            val reqSimpleDateFormat = SimpleDateFormat(requiredDateFormat, outputLocale)
            return reqSimpleDateFormat.format(date)


//            val publishedDuration = System.currentTimeMillis() - date.time
//
//            val days = (TimeUnit.MILLISECONDS.toDays(publishedDuration)).toInt()
//
//            if (days >= 1) {
//                val reqSimpleDateFormat = SimpleDateFormat(requiredDateFormat, outputLocale)
//                return reqSimpleDateFormat.format(date)
//            }

//            val hours = (TimeUnit.MILLISECONDS.toHours(publishedDuration)).toInt()
//            if (hours > 1)
//                return "$hours hours ago"
//            else if (hours == 1)
//                return "an hour ago"
//
//            val mins = (TimeUnit.MILLISECONDS.toMinutes(publishedDuration)).toInt()
//            return if (mins > 1)
//                "$mins mins ago"
//            else
//                "a min ago"


        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun convertDateStringToMillis(
        dateString: String?,
        dateFormat: String,
    ): Long {
        return try {
            if (dateString == null) {
                return -1
            }
            val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.US)
            val date = simpleDateFormat.parse(dateString)
            if (date != null) {
                return date.time
            } else -1L
        } catch (e: Exception) {
            -1L
        }
    }

    fun getPublishedDuration(
        dateString: String?,
        dateFormat: String,
        inputLocale: Locale = Locale.ENGLISH
    ): String? {

        try {
            if (dateString == null) {
                return null
            }
            val simpleDateFormat = SimpleDateFormat(dateFormat, inputLocale)
            val date = simpleDateFormat.parse(dateString) ?: return null


            val publishedDuration = System.currentTimeMillis() - date.time

            val days = (TimeUnit.MILLISECONDS.toDays(publishedDuration)).toInt()

            if(days >= 365)
                return (days/365).toString() + "y"

            if(days >= 30)
                return (days/30).toString() + "m"

            if(days >= 7)
                return (days/7).toString() + "w"

            if(days >= 1)
                return (days).toString() + "d"

            val hours = (TimeUnit.MILLISECONDS.toHours(publishedDuration)).toInt()
            if (hours >= 1)
                return hours.toString() + "h"

            val mins = (TimeUnit.MILLISECONDS.toMinutes(publishedDuration)).toInt()
            if (mins >= 1)
                return mins.toString() + "min"


            val seconds = (TimeUnit.MILLISECONDS.toSeconds(publishedDuration)).toInt()
            if (seconds >= 3)
                return seconds.toString() + "s"

            return "just now"
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun convertNumberToMMSS(seconds: Long): String {
        val s: Long = seconds % 60
        val m: Long = seconds / 60 % 60
        return String.format("%02d:%02d", m, s)
    }

    fun getConvertedDate(startDate: String): String {
        return convertDateStringToSpecifiedDateString(
            startDate, SCORE_CARD_MATCH_DATE_FORMAT,
            requiredDateFormat = "MMM d, yyyy"
        ) + " | " +
                convertDateStringToSpecifiedDateString(
                    startDate, SCORE_CARD_MATCH_DATE_FORMAT,
                    requiredDateFormat = "HH:mm"
                )
    }

    fun getCurrentTimeZone():String{
        val tz = TimeZone.getDefault()
        val isDaylight = tz.inDaylightTime(Date())
        val time = tz.getDisplayName(isDaylight, TimeZone.SHORT)
        val re = Regex("[^0-9+-]")

        if (time.equals("IST")) {
            return "+0530"
        }
        val timezone = re.replace(time, "")

        return timezone
    }

    fun getCurrentTimeStamp():String{
        val c: Calendar = Calendar.getInstance(TimeZone.getDefault())
        println("current:::->${c.get(Calendar.YEAR)}-${c.get(Calendar.MONTH)+1}-${c.get(Calendar.DAY_OF_MONTH)}")
        return "${c.get(Calendar.YEAR)}-${c.get(Calendar.MONTH)+1}-${c.get(Calendar.DAY_OF_MONTH)}"
    }

    fun getPreviousOrFutureDate(dateFormat: String,days:Int):String{
        val c: Calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat(dateFormat)
        c.add(Calendar.DAY_OF_YEAR,days)
        return simpleDateFormat.format(Date(c.timeInMillis))
    }


    fun isDatePast(dateFormat: String,date: String,currentDate:String):Boolean{
        return try {
            val sdf = SimpleDateFormat(dateFormat)
            val firstDate: Date = sdf.parse(date)
            val secondDate: Date = sdf.parse(currentDate)
            firstDate.before(secondDate)
        }catch (e:Exception){
            return false
        }
    }
}