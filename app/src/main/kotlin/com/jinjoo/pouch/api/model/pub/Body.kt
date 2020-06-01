package com.jinjoo.pouch.api.model.pub

data class Body (
    var numOfRows: String? = null,
    var pageNo: String? = null,
    var totalCount: String? = null,
    var items: MutableList<Item>? = null
)
