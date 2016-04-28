package com.jdk2010.framework.test.dal;

import java.math.BigDecimal;
import java.util.Date;

import com.jdk2010.framework.dal.model.Model;
import com.jdk2010.framework.dal.parse.annotation.TableField;

/**
 * 
 * 预开 实体类<br>
 * 〈功能详细描述〉
 * 
 * @author
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class InvoiceItemEntity extends Model<Model> {

    /* 主键id */
    private Long id;
    /* fk */
    private String fpqqlsh;
    /* 销售组织代码 */
    private String saleOrg;
    /* 门店号 */
    private String storeCode;
    /* 订单时间 */
    private Date ordertime;
    /* 发票代码 */
    private String fpdm;
    /* 发票号码 */
    private String fphm;
    /* 行项目序号 */
    private String seqno;
    /* 商品编码 */
    private String cmmdtycode;
    /* 商品名称 */
    private String cmmdtyname;
    /* 单位 */
    private String jldw;
    /* 规格 */
    private String ggxh;
    /* 销售数量 */
    private BigDecimal sl;
    /* 单价 */
    private BigDecimal dj;
    /* 销售金额 */
    private BigDecimal je;
    /* 运费 */
    private BigDecimal ysfy;
    /* 服务费 */
    private BigDecimal fwfy;
    /* 优惠单总金额 */
    private BigDecimal yhdzje;
    /* 参考价 */
    private BigDecimal ckjg;
    /* 税率 */
    private BigDecimal taxRate;
    /* 税额 */
    private BigDecimal taxSum;
    /* 折扣金额 */
    private BigDecimal discountSum;
    /* 销售价 */
    private BigDecimal price;
    /* 商品类目 */
    private String sapProductCode;
    /* 产品层次 */
    private String cmmdtyBand;
    /* 串码 */
    private String phoneCode;
    /* 行项目类别 */
    private String orderItemType;
    /* 是否为实时延保 */
    private String isExtendFlag;
    /* 延保商品类型 */
    private String extendType;

    /**
     * 
     * 功能描述: 获取 主键<br>
     * 〈功能详细描述〉
     * 
     * @return：Long,主键
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public Long getId() {
        return id;
    }

    /**
     * 
     * 功能描述: 设置 主键<br>
     * 〈功能详细描述〉
     * 
     * @param id,主键
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * 功能描述: 获取 fk<br>
     * 〈功能详细描述〉
     * 
     * @return：String,fk
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getFpqqlsh() {
        return fpqqlsh;
    }

    /**
     * 
     * 功能描述: 设置 fk<br>
     * 〈功能详细描述〉
     * 
     * @return：String,fk
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh;
    }

    /**
     * 
     * 功能描述: 获取 销售组织代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,销售组织代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getSaleOrg() {
        return saleOrg;
    }

    /**
     * 
     * 功能描述: 设置 销售组织代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,销售组织代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSaleOrg(String saleOrg) {
        this.saleOrg = saleOrg;
    }

    /**
     * 
     * 功能描述: 获取 门店号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,门店号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * 
     * 功能描述: 设置 门店号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,门店号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    /**
     * 
     * 功能描述: 获取 订单时间<br>
     * 〈功能详细描述〉
     * 
     * @return：Date,订单时间
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public Date getOrdertime() {
        return ordertime;
    }

    /**
     * 
     * 功能描述: 设置 订单时间<br>
     * 〈功能详细描述〉
     * 
     * @return：Date,订单时间
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    /**
     * 
     * 功能描述: 获取 发票代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getFpdm() {
        return fpdm;
    }

    /**
     * 
     * 功能描述: 设置 发票代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setFpdm(String fpdm) {
        this.fpdm = fpdm;
    }

    /**
     * 
     * 功能描述: 获取 发票号码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票号码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getFphm() {
        return fphm;
    }

    /**
     * 
     * 功能描述: 设置 发票号码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票号码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    /**
     * 
     * 功能描述: 获取 行项目序号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,行项目序号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getSeqno() {
        return seqno;
    }

    /**
     * 
     * 功能描述: 设置 行项目序号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,行项目序号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    /**
     * 
     * 功能描述: 获取 商品编码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,商品编码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getCmmdtycode() {
        return cmmdtycode;
    }

    /**
     * 
     * 功能描述: 设置 商品编码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,商品编码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCmmdtycode(String cmmdtycode) {
        this.cmmdtycode = cmmdtycode;
    }

    /**
     * 
     * 功能描述: 获取 商品名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,商品名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getCmmdtyname() {
        return cmmdtyname;
    }

    /**
     * 
     * 功能描述: 设置 商品名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,商品名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCmmdtyname(String cmmdtyname) {
        this.cmmdtyname = cmmdtyname;
    }

    /**
     * 
     * 功能描述: 获取 单位<br>
     * 〈功能详细描述〉
     * 
     * @return：String,单位
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getJldw() {
        return jldw;
    }

    /**
     * 
     * 功能描述: 设置 单位<br>
     * 〈功能详细描述〉
     * 
     * @return：String,单位
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    /**
     * 
     * 功能描述: 获取 规格<br>
     * 〈功能详细描述〉
     * 
     * @return：String,规格
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getGgxh() {
        return ggxh;
    }

    /**
     * 
     * 功能描述: 设置 规格<br>
     * 〈功能详细描述〉
     * 
     * @return：String,规格
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setGgxh(String ggxh) {
        this.ggxh = ggxh;
    }

    /**
     * 
     * 功能描述: 获取 销售数量<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,销售数量
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getSl() {
        return sl;
    }

    /**
     * 
     * 功能描述: 设置 销售数量<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,销售数量
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    /**
     * 
     * 功能描述: 获取 单价<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,单价
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getDj() {
        return dj;
    }

    /**
     * 
     * 功能描述: 设置 单价<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,单价
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setDj(BigDecimal dj) {
        this.dj = dj;
    }

    /**
     * 
     * 功能描述: 获取 销售金额<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,销售金额
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getJe() {
        return je;
    }

    /**
     * 
     * 功能描述: 设置 销售金额<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,销售金额
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setJe(BigDecimal je) {
        this.je = je;
    }

    /**
     * 
     * 功能描述: 获取 运费<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,运费
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getYsfy() {
        return ysfy;
    }

    /**
     * 
     * 功能描述: 设置 运费<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,运费
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setYsfy(BigDecimal ysfy) {
        this.ysfy = ysfy;
    }

    /**
     * 
     * 功能描述: 获取 服务费<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,服务费
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getFwfy() {
        return fwfy;
    }

    /**
     * 
     * 功能描述: 设置 服务费<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,服务费
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setFwfy(BigDecimal fwfy) {
        this.fwfy = fwfy;
    }

    /**
     * 
     * 功能描述: 获取 优惠单总金额<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,优惠单总金额
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getYhdzje() {
        return yhdzje;
    }

    /**
     * 
     * 功能描述: 设置 优惠单总金额<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,优惠单总金额
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setYhdzje(BigDecimal yhdzje) {
        this.yhdzje = yhdzje;
    }

    /**
     * 
     * 功能描述: 获取 参考价<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,参考价
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getCkjg() {
        return ckjg;
    }

    /**
     * 
     * 功能描述: 设置 参考价<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,参考价
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCkjg(BigDecimal ckjg) {
        this.ckjg = ckjg;
    }

    /**
     * 
     * 功能描述: 获取 税率<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,税率
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 
     * 功能描述: 设置 税率<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,税率
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 
     * 功能描述: 获取 税额<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,税额
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getTaxSum() {
        return taxSum;
    }

    /**
     * 
     * 功能描述: 设置 税额<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,税额
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTaxSum(BigDecimal taxSum) {
        this.taxSum = taxSum;
    }

    /**
     * 
     * 功能描述: 获取 折扣金额<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,折扣金额
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getDiscountSum() {
        return discountSum;
    }

    /**
     * 
     * 功能描述: 设置 折扣金额<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,折扣金额
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setDiscountSum(BigDecimal discountSum) {
        this.discountSum = discountSum;
    }

    /**
     * 
     * 功能描述: 获取 销售价<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,销售价
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 
     * 功能描述: 设置 销售价<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,销售价
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 
     * 功能描述: 获取 商品类目<br>
     * 〈功能详细描述〉
     * 
     * @return：String,商品类目
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getSapProductCode() {
        return sapProductCode;
    }

    /**
     * 
     * 功能描述: 设置 商品类目<br>
     * 〈功能详细描述〉
     * 
     * @return：String,商品类目
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSapProductCode(String sapProductCode) {
        this.sapProductCode = sapProductCode;
    }

    /**
     * 
     * 功能描述: 获取 产品层次<br>
     * 〈功能详细描述〉
     * 
     * @return：String,产品层次
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getCmmdtyBand() {
        return cmmdtyBand;
    }

    /**
     * 
     * 功能描述: 设置 产品层次<br>
     * 〈功能详细描述〉
     * 
     * @return：String,产品层次
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCmmdtyBand(String cmmdtyBand) {
        this.cmmdtyBand = cmmdtyBand;
    }

    /**
     * 
     * 功能描述: 获取 串码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,串码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getPhoneCode() {
        return phoneCode;
    }

    /**
     * 
     * 功能描述: 设置 串码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,串码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    /**
     * 
     * 功能描述: 获取 行项目类别<br>
     * 〈功能详细描述〉
     * 
     * @return：String,行项目类别
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getOrderItemType() {
        return orderItemType;
    }

    /**
     * 
     * 功能描述: 设置 行项目类别<br>
     * 〈功能详细描述〉
     * 
     * @return：String,行项目类别
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOrderItemType(String orderItemType) {
        this.orderItemType = orderItemType;
    }

    /**
     * 
     * 功能描述: 获取 是否为实时延保<br>
     * 〈功能详细描述〉
     * 
     * @return：String,是否为实时延保
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getIsExtendFlag() {
        return isExtendFlag;
    }

    /**
     * 
     * 功能描述: 设置 是否为实时延保<br>
     * 〈功能详细描述〉
     * 
     * @return：String,是否为实时延保
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setIsExtendFlag(String isExtendFlag) {
        this.isExtendFlag = isExtendFlag;
    }

    /**
     * 
     * 功能描述: 获取 延保商品类型<br>
     * 〈功能详细描述〉
     * 
     * @return：String,延保商品类型
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getExtendType() {
        return extendType;
    }

    /**
     * 
     * 功能描述: 设置 延保商品类型<br>
     * 〈功能详细描述〉
     * 
     * @return：String,延保商品类型
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setExtendType(String extendType) {
        this.extendType = extendType;
    }

}