package com.shenhesoft.logistics.business.pojo.stock.adjusthistory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TbStockAdjustHistory implements Serializable{
    /** */
    private Integer id;

    /** */
    private Integer stockId;

    /** 调整库存*/
    private BigDecimal adjustStock;

    /** */
    private Date adjustDate;

    /** */
    private String adjustor;

    /** */
    private Integer projectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public BigDecimal getAdjustStock() {
        return adjustStock;
    }

    public void setAdjustStock(BigDecimal adjustStock) {
        this.adjustStock = adjustStock;
    }

    public Date getAdjustDate() {
        return adjustDate;
    }

    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
    }

    public String getAdjustor() {
        return adjustor;
    }

    public void setAdjustor(String adjustor) {
        this.adjustor = adjustor == null ? null : adjustor.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}