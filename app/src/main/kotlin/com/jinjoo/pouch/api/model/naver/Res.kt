package com.jinjoo.pouch.api.model.naver

class Res (
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: MutableList<Item>
)