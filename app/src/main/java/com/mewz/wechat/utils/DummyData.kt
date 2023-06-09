package com.mewz.wechat.utils

import com.mewz.wechat.R

val months = listOf(
    "Month",
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
)

object DummyData{
    fun getAlphabetList(): List<Char> {
        val alphabet = ('A'..'Z').toList()
        return alphabet
    }
}