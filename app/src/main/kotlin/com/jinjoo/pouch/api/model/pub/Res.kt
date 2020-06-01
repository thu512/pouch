package com.jinjoo.pouch.api.model.pub

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "response", strict = false)
data class Res (
        @field:Element(name = "header") var header: Header? = null,

        @field:Element(name = "body") var body: Body? = null
)