package com.mewz.wechat.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class GroceryVO (
    var name: String? = "",
    var description: String? = "",
    var amount: Int? = 0,
    var image: String? = ""
    )