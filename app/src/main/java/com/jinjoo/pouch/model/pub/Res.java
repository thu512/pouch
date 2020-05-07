package com.jinjoo.pouch.model.pub;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Res {
    @Element
    Header header;

    @Element
    Body body;


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Res{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
