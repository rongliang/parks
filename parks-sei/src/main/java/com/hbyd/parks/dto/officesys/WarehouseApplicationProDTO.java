package com.hbyd.parks.dto.officesys;

import com.hbyd.parks.common.base.BaseDTO;

/**
 * Created by Zhao_d on 2017/1/22.
 */
public class WarehouseApplicationProDTO extends BaseDTO {
    private String parentIdFK;          //申请单ID
    private int SN;                  //序号
    private String productName;         //货品名称
    private String productModelNumber;   //货品型号
    private String productSpecifications;   //货品封装
    private String productBrand;           //品牌
    private String productUnit;          //货品单位
    private Double quantity;             //数量
    private Boolean isFinished;         //是否完成出入库操作
    private String note;                //备注

    public String getParentIdFK() {
        return parentIdFK;
    }

    public void setParentIdFK(String parentIdFK) {
        this.parentIdFK = parentIdFK;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductModelNumber() {
        return productModelNumber;
    }

    public void setProductModelNumber(String productModelNumber) {
        this.productModelNumber = productModelNumber;
    }

    public String getProductSpecifications() {
        return productSpecifications;
    }

    public void setProductSpecifications(String productSpecifications) {
        this.productSpecifications = productSpecifications;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public int getSN() {
        return SN;
    }

    public void setSN(int SN) {
        this.SN = SN;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }
}
