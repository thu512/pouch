package com.jinjoo.pouch.api.model.pub

data class Body(
        var numOfRows: String = "",
        var pageNo: String = "",
        var totalCount: String = "",
        var items: MutableList<Item>? = null
)
