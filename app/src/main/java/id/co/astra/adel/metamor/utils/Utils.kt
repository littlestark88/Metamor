package id.co.astra.adel.metamor.utils

import id.co.astra.adel.metamor.utils.Constants.COUNTRY_ID
import id.co.astra.adel.metamor.utils.Constants.EEEE_DD_MMMM_YYYY_HH_MM_SS
import id.co.astra.adel.metamor.utils.Constants.LANGUAGE_IN
import id.co.astra.adel.metamor.utils.Constants.YYYY_MM_DD_HH_MM_SS_SSS
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.*

fun convertCurrency (valueCurrency: Double?): String {
    val localeID =  Locale(LANGUAGE_IN, COUNTRY_ID)
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(valueCurrency)
}

fun convertPatternDate(dateOld: String?): String {
    val localeID = Locale(LANGUAGE_IN, COUNTRY_ID)
    val inputFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_SSS)
    val outputFormatter = DateTimeFormatter.ofPattern(EEEE_DD_MMMM_YYYY_HH_MM_SS, localeID)
    val date = inputFormatter.parse(dateOld)
    return outputFormatter.format(date)
}