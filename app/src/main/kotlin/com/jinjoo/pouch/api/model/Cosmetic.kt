package com.jinjoo.pouch.api.model

class Cosmetic(var name: String, var brand: String) {
    var img: String? = null
    override fun toString(): String {
        return "Cosmetic{" +
                "img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                '}'
    }

}