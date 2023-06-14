package com.mewz.wechat.data.vos

data class GroupVO(
    var id: Long = 0L,
    var name: String = "",
    var userIdList: List<String> = listOf(),
    var messageList: List<PrivateMessageVO> = listOf()
)
