package com.mewz.wechat.data.vos

data class MomentVO(

    var name: String?,
    var lastOnline: String?,
    var profilePicture: String?,
    var postPicture: String?,
    var numberOfReacts: Int?,
    var numberOfComments: Int?,
    var isBookMarked: Boolean?
)
