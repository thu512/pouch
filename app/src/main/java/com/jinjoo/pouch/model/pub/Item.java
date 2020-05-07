package com.jinjoo.pouch.model.pub;

public class Item {
    String ITEM_NAME;
    String COSMETIC_REPORT_SEQ;
    String ITEM_SEQ;
    String DEPT_RECEIPT_NO;
    String REPORT_FLAG_CODE;
    String REPORT_FLAG_NAME;
    String MANUF_NAME;
    String MANUF_COUNTRY_CODE;
    String MANUF_COUNTRY_NAME;
    String MANUF_PLACE;
    String ITEM_PH;
    String COSMETIC_TARGET_FLAG;
    String COSMETIC_TARGET_FLAG_NAME;
    String COSMETIC_STD_CODE;
    String COSMETIC_STD_NAME;
    String ENTP_NAME;
    String ENTP_SEQ;
    String REPORT_DATE;
    String CANCEL_REQ_DATE;
    String CANCEL_APPROVAL_DATE;
    String CANCEL_APPROVAL_YN;
    String ETHANOL_OVER_YN;
    String EE_CODE;
    String EE_NAME;
    String SPF;
    String PA;
    String USAGE_DOSAGE;
    String EFFECT_YN1;
    String EFFECT_YN2;
    String EFFECT_YN3;
    String WATER_PROOFING_FLAG;
    String WATER_PROOFING_NAME;
    String EE_DOC_DATA;
    String UD_DOC_DATA;
    String NB_DOC_DATA;

    public String getENTP_NAME() {
        return ENTP_NAME;
    }

    public void setENTP_NAME(String ENTP_NAME) {
        this.ENTP_NAME = ENTP_NAME;
    }

    public String getITEM_NAME() {
        return ITEM_NAME;
    }

    public void setITEM_NAME(String ITEM_NAME) {
        this.ITEM_NAME = ITEM_NAME;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ITEM_NAME='" + ITEM_NAME + '\'' +
                '}';
    }
}
