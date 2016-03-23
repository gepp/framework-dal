package com.jdk2010.framework.test.dal;

import java.io.Serializable;
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
public class InvoiceEntity extends Model<Model<?>> {
    /* 主键id */
    private Long id;
    /* 发票请求唯一流水号 */
    private String fpqqlsh;
    /* oms订单行号 */
    private String orderitemid;
    /* pos订单号 */
    private String posorderid;
    /* b2c订单号 */
    private String b2corderid;
    /* sap销售凭证号 */
    private String sapOrderId;
    /* 销售组织代码 */
    private String saleOrg;
    /* 销售组织名称 */
    private String saleOrgName;
    /* 门店号 */
    private String storeCode;
    /* 门店名称 */
    private String stourename;
    /* 原oms行号 */
    private String oldOrderitemid;
    /* 原pos单号 */
    private String oldPosOrderId;
    /* 原销售组织 */
    private String oldSaleOrg;
    /* 原门店号 */
    private String oldStoreCode;
    /* 订单时间 */
    private Date ordertime;
    /* 原销售订单日期 */
    private Date oldOrderTime;
    /* 出库单号 */
    private String stockNo;
    /* 正逆向标记 */
    private String znxbj;
    /* 部分退货标识 */
    private String bfthbs;
    /* 退货批次 */
    private String thpcs;
    /* 发票打印标示 */
    private String invoicesPrintlog;
    /* 特殊冲红标志 */
    private String tschbz;
    /* 操作代码 */
    private String czdm;
    /* 订单类型 */
    private String orderType;
    /* 分销渠道 */
    private String distChannel;
    /* 校验码 */
    private String verifyCode;
    /* 订单备注 */
    private String memo;
    /* 是否补开标示 */
    private String sfBk;
    /* 开具红字发票原因类型 */
    private String refundReasonType;
    /* 开具红字发票原因 */
    private String refundReason;
    /* 发票代码 */
    private String fpdm;
    /* 发票号码 */
    private String fphm;
    /* 纸质发票代码 */
    private String zzfpdm;
    /* 纸质发票号码 */
    private String zzfphm;
    /* 原发票代码 */
    private String yfpdm;
    /* 原发票号码 */
    private String yfphm;
    /* 发票类型 */
    private String invoiceType;
    /* 发票抬头 */
    private String invoicetitle;
    /* 发票内容 */
    private String invoicecontent;
    /* 开票金额合计 */
    private BigDecimal kpjehj;
    /* 合计不含税金额 */
    private BigDecimal hjbhsje;
    /* 开票金额合计大写 */
    private String sumAmountUppercase;
    /* 折扣金额合计 */
    private BigDecimal zkjehj;
    /* 税额合计 */
    private BigDecimal taxSumAmount;
    /* 运费服务费金额 */
    private BigDecimal transportFee;
    /* 会员id */
    private String memberId;
    /* 会员姓名 */
    private String memberName;
    /* 收票人名称 */
    private String accepterInvoiceName;
    /* 收票人固定电话 */
    private String phonenum;
    /* 收票人手机号码 */
    private String mobphonenum;
    /* 收票人邮箱地址 */
    private String emailinvoice;
    /* 收票方地址 */
    private String accepterInvoiceAddr;
    /* 增值税发票收件人纳税人识别号 */
    private String taxpayerNo;
    /* 增值税发票收件人姓名 */
    private String taxpayerName;
    /* 增值税发票收件人地址 */
    private String taxpayerAddress;
    /* 增值税发票收件人电话 */
    private String taxpayerPhone;
    /* 增值税发票收件人手机 */
    private String taxpayerMobile;
    /* 通用行业代码 */
    private String commonIndustryCode;
    /* 通用行业名称 */
    private String commonIndustryName;
    /* 开票方纳税人识别号 */
    private String makerTaxpayerno;
    /* 开票方纳税人名称 */
    private String makerTaxpayerName;
    /* 开票人纳税人地址 */
    private String makerTaxpayerAddr;
    /* 开票方开户银行 */
    private String makerInvoiceBank;
    /* 开票方银行账号 */
    private String makerInvoiceAccount;
    /* 开票方电话号码 */
    private String makerInvoicePhone;
    /* 税务机构代码 */
    private String taxOrgCode;
    /* 局端管理平台税务机关代码 */
    private String taxAuthoritiesCode;
    /* 局端管理平台税务机关名称 */
    private String taxAuthoritiesName;
    /* 服务平台编码 */
    private String servicePlatformCode;
    /* 服务平台名称 */
    private String servicePlatformName;
    /* 服务平台url */
    private String servicePlatformUrl;
    /* 电商平台编码 */
    private String businesPlatformCode;
    /* 电商平台名称 */
    private String businesPlatformName;
    /* 电商平台网址 */
    private String businesPlatformUrl;
    /* 票样代码 */
    private String ballotCode;
    /* 开票人名称 */
    private String makerInvoiceName;
    /* 收银员id */
    private String cashierId;
    /* 收银员名称 */
    private String cashierName;
    /* 收银台号码 */
    private String cashierTerminalId;
    /* 操作人工号 */
    private String operationCode;
    /* 操作人姓名 */
    private String operationName;
    /* 操作时间 */
    private Date operationDate;
    /* 处理状态 */
    private String status;
    /* 二维码信息 */
    private String twodimensionalCodeInfo;
    /* 数字签名信息 */
    private String digitalSignatureInfo;
    /* 打印次数 */
    private Integer printNum;
    /* 打印人工号 */
    private String printNo;
    /* 打印人名称 */
    private String printName;
    /* 打印时间 */
    private Date printDate;
    
    private String printPassword;
    
    
    /* 打印标识 */
    private String printFlag;
    /* 发票状态 01 蓝票已被冲红 ， 99 蓝票已被换开 */
    private String invStatus;
    /* inv_desc */
    private String invDesc;
    /* 开票日期 */
    private Date kprq;
    /* 开票流水号（航信返回） */
    private String hxReturnKplsh;
    /* 开票项目 */
    private String kpxm;
    /* 购货方名称 */
    private String ghfmc;
    /* 开票类型 */
    private Integer kplx;
    /* 防伪码 */
    private String securityCode;
    /* 发票票种编码 */
    private String invoiceTicketCode;
    /* 最后接收日期 */
    private Date lastAcceptDate;
    /* 接收发票次数 */
    private Integer acceptTime;
    /* pdf地址 */
    private String pdfUrl;
    /* pdf文件服务器地址 */
    private String pdfFile;
    /* 备用字段1 */
    private String backupFieldFir;
    /* 备用字段2 */
    private String backupFieldSec;

    private Date ctime;
    /*原开票日期*/
    private Date ykprq;
    
    private String cmmdtyname;

    private BigDecimal sl;
    /* 单价 */
    private BigDecimal dj;

    private BigDecimal taxRate;

    private BigDecimal taxSum;

    private BigDecimal je;

    
   @TableField
    public Date getYkprq() {
        return ykprq;
    }

    public void setYkprq(Date ykprq) {
        this.ykprq = ykprq;
    }

    @TableField
    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
    
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
    
    @TableField
    public String getPrintPassword() {
        return printPassword;
    }

    public void setPrintPassword(String printPassword) {
        this.printPassword = printPassword;
    }

    /**
     * 
     * 功能描述: 获取 发票请求唯一流水号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票请求唯一流水号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getFpqqlsh() {
        return fpqqlsh;
    }

    /**
     * 
     * 功能描述: 设置 发票请求唯一流水号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票请求唯一流水号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh;
    }

    /**
     * 
     * 功能描述: 获取 oms订单行号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,oms订单行号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getOrderitemid() {
        return orderitemid;
    }

    /**
     * 
     * 功能描述: 设置 oms订单行号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,oms订单行号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOrderitemid(String orderitemid) {
        this.orderitemid = orderitemid;
    }

    /**
     * 
     * 功能描述: 获取 pos订单号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,pos订单号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getPosorderid() {
        return posorderid;
    }

    /**
     * 
     * 功能描述: 设置 pos订单号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,pos订单号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setPosorderid(String posorderid) {
        this.posorderid = posorderid;
    }

    /**
     * 
     * 功能描述: 获取 b2c订单号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,b2c订单号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getB2corderid() {
        return b2corderid;
    }

    /**
     * 
     * 功能描述: 设置 b2c订单号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,b2c订单号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setB2corderid(String b2corderid) {
        this.b2corderid = b2corderid;
    }

    /**
     * 
     * 功能描述: 获取 sap销售凭证号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,sap销售凭证号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getSapOrderId() {
        return sapOrderId;
    }

    /**
     * 
     * 功能描述: 设置 sap销售凭证号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,sap销售凭证号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSapOrderId(String sapOrderId) {
        this.sapOrderId = sapOrderId;
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
     * 功能描述: 获取 销售组织名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,销售组织名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getSaleOrgName() {
        return saleOrgName;
    }

    /**
     * 
     * 功能描述: 设置 销售组织名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,销售组织名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSaleOrgName(String saleOrgName) {
        this.saleOrgName = saleOrgName;
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
     * 功能描述: 获取 门店名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,门店名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getStourename() {
        return stourename;
    }

    /**
     * 
     * 功能描述: 设置 门店名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,门店名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setStourename(String stourename) {
        this.stourename = stourename;
    }

    /**
     * 
     * 功能描述: 获取 原oms行号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,原oms行号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getOldOrderitemid() {
        return oldOrderitemid;
    }

    /**
     * 
     * 功能描述: 设置 原oms行号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,原oms行号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOldOrderitemid(String oldOrderitemid) {
        this.oldOrderitemid = oldOrderitemid;
    }

    /**
     * 
     * 功能描述: 获取 原pos单号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,原pos单号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getOldPosOrderId() {
        return oldPosOrderId;
    }

    /**
     * 
     * 功能描述: 设置 原pos单号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,原pos单号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOldPosOrderId(String oldPosOrderId) {
        this.oldPosOrderId = oldPosOrderId;
    }

    /**
     * 
     * 功能描述: 获取 原销售组织<br>
     * 〈功能详细描述〉
     * 
     * @return：String,原销售组织
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getOldSaleOrg() {
        return oldSaleOrg;
    }

    /**
     * 
     * 功能描述: 设置 原销售组织<br>
     * 〈功能详细描述〉
     * 
     * @return：String,原销售组织
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOldSaleOrg(String oldSaleOrg) {
        this.oldSaleOrg = oldSaleOrg;
    }

    /**
     * 
     * 功能描述: 获取 原门店号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,原门店号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getOldStoreCode() {
        return oldStoreCode;
    }

    /**
     * 
     * 功能描述: 设置 原门店号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,原门店号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOldStoreCode(String oldStoreCode) {
        this.oldStoreCode = oldStoreCode;
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
     * 功能描述: 获取 原销售订单日期<br>
     * 〈功能详细描述〉
     * 
     * @return：Date,原销售订单日期
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public Date getOldOrderTime() {
        return oldOrderTime;
    }

    /**
     * 
     * 功能描述: 设置 原销售订单日期<br>
     * 〈功能详细描述〉
     * 
     * @return：Date,原销售订单日期
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOldOrderTime(Date oldOrderTime) {
        this.oldOrderTime = oldOrderTime;
    }

    /**
     * 
     * 功能描述: 获取 出库单号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,出库单号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getStockNo() {
        return stockNo;
    }

    /**
     * 
     * 功能描述: 设置 出库单号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,出库单号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    /**
     * 
     * 功能描述: 获取 正逆向标记<br>
     * 〈功能详细描述〉
     * 
     * @return：String,正逆向标记
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getZnxbj() {
        return znxbj;
    }

    /**
     * 
     * 功能描述: 设置 正逆向标记<br>
     * 〈功能详细描述〉
     * 
     * @return：String,正逆向标记
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setZnxbj(String znxbj) {
        this.znxbj = znxbj;
    }

    /**
     * 
     * 功能描述: 获取 部分退货标识<br>
     * 〈功能详细描述〉
     * 
     * @return：String,部分退货标识
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getBfthbs() {
        return bfthbs;
    }

    /**
     * 
     * 功能描述: 设置 部分退货标识<br>
     * 〈功能详细描述〉
     * 
     * @return：String,部分退货标识
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setBfthbs(String bfthbs) {
        this.bfthbs = bfthbs;
    }

    /**
     * 
     * 功能描述: 获取 退货批次<br>
     * 〈功能详细描述〉
     * 
     * @return：String,退货批次
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getThpcs() {
        return thpcs;
    }

    /**
     * 
     * 功能描述: 设置 退货批次<br>
     * 〈功能详细描述〉
     * 
     * @return：String,退货批次
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setThpcs(String thpcs) {
        this.thpcs = thpcs;
    }

    /**
     * 
     * 功能描述: 获取 发票打印标示<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票打印标示
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getInvoicesPrintlog() {
        return invoicesPrintlog;
    }

    /**
     * 
     * 功能描述: 设置 发票打印标示<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票打印标示
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setInvoicesPrintlog(String invoicesPrintlog) {
        this.invoicesPrintlog = invoicesPrintlog;
    }

    /**
     * 
     * 功能描述: 获取 特殊冲红标志<br>
     * 〈功能详细描述〉
     * 
     * @return：String,特殊冲红标志
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getTschbz() {
        return tschbz;
    }

    /**
     * 
     * 功能描述: 设置 特殊冲红标志<br>
     * 〈功能详细描述〉
     * 
     * @return：String,特殊冲红标志
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTschbz(String tschbz) {
        this.tschbz = tschbz;
    }

    /**
     * 
     * 功能描述: 获取 操作代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,操作代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getCzdm() {
        return czdm;
    }

    /**
     * 
     * 功能描述: 设置 操作代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,操作代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCzdm(String czdm) {
        this.czdm = czdm;
    }

    /**
     * 
     * 功能描述: 获取 订单类型<br>
     * 〈功能详细描述〉
     * 
     * @return：String,订单类型
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getOrderType() {
        return orderType;
    }

    /**
     * 
     * 功能描述: 设置 订单类型<br>
     * 〈功能详细描述〉
     * 
     * @return：String,订单类型
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 
     * 功能描述: 获取 分销渠道<br>
     * 〈功能详细描述〉
     * 
     * @return：String,分销渠道
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getDistChannel() {
        return distChannel;
    }

    /**
     * 
     * 功能描述: 设置 分销渠道<br>
     * 〈功能详细描述〉
     * 
     * @return：String,分销渠道
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setDistChannel(String distChannel) {
        this.distChannel = distChannel;
    }

    /**
     * 
     * 功能描述: 获取 校验码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,校验码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getVerifyCode() {
        return verifyCode;
    }

    /**
     * 
     * 功能描述: 设置 校验码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,校验码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * 
     * 功能描述: 获取 订单备注<br>
     * 〈功能详细描述〉
     * 
     * @return：String,订单备注
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getMemo() {
        return memo;
    }

    /**
     * 
     * 功能描述: 设置 订单备注<br>
     * 〈功能详细描述〉
     * 
     * @return：String,订单备注
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 
     * 功能描述: 获取 是否补开标示<br>
     * 〈功能详细描述〉
     * 
     * @return：String,是否补开标示
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getSfBk() {
        return sfBk;
    }

    /**
     * 
     * 功能描述: 设置 是否补开标示<br>
     * 〈功能详细描述〉
     * 
     * @return：String,是否补开标示
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSfBk(String sfBk) {
        this.sfBk = sfBk;
    }

    /**
     * 
     * 功能描述: 获取 开具红字发票原因类型<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开具红字发票原因类型
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getRefundReasonType() {
        return refundReasonType;
    }

    /**
     * 
     * 功能描述: 设置 开具红字发票原因类型<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开具红字发票原因类型
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setRefundReasonType(String refundReasonType) {
        this.refundReasonType = refundReasonType;
    }

    /**
     * 
     * 功能描述: 获取 开具红字发票原因<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开具红字发票原因
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getRefundReason() {
        return refundReason;
    }

    /**
     * 
     * 功能描述: 设置 开具红字发票原因<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开具红字发票原因
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
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
     * 功能描述: 获取 纸质发票代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,纸质发票代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getZzfpdm() {
        return zzfpdm;
    }

    /**
     * 
     * 功能描述: 设置 纸质发票代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,纸质发票代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setZzfpdm(String zzfpdm) {
        this.zzfpdm = zzfpdm;
    }

    /**
     * 
     * 功能描述: 获取 纸质发票号码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,纸质发票号码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getZzfphm() {
        return zzfphm;
    }

    /**
     * 
     * 功能描述: 设置 纸质发票号码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,纸质发票号码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setZzfphm(String zzfphm) {
        this.zzfphm = zzfphm;
    }

    /**
     * 
     * 功能描述: 获取 原发票代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,原发票代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getYfpdm() {
        return yfpdm;
    }

    /**
     * 
     * 功能描述: 设置 原发票代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,原发票代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setYfpdm(String yfpdm) {
        this.yfpdm = yfpdm;
    }

    /**
     * 
     * 功能描述: 获取 原发票号码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,原发票号码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getYfphm() {
        return yfphm;
    }

    /**
     * 
     * 功能描述: 设置 原发票号码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,原发票号码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setYfphm(String yfphm) {
        this.yfphm = yfphm;
    }

    /**
     * 
     * 功能描述: 获取 发票类型<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票类型
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getInvoiceType() {
        return invoiceType;
    }

    /**
     * 
     * 功能描述: 设置 发票类型<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票类型
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 
     * 功能描述: 获取 发票抬头<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票抬头
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getInvoicetitle() {
        return invoicetitle;
    }

    /**
     * 
     * 功能描述: 设置 发票抬头<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票抬头
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setInvoicetitle(String invoicetitle) {
        this.invoicetitle = invoicetitle;
    }

    /**
     * 
     * 功能描述: 获取 发票内容<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票内容
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getInvoicecontent() {
        return invoicecontent;
    }

    /**
     * 
     * 功能描述: 设置 发票内容<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票内容
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setInvoicecontent(String invoicecontent) {
        this.invoicecontent = invoicecontent;
    }

    /**
     * 
     * 功能描述: 获取 开票金额合计<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,开票金额合计
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getKpjehj() {
        return kpjehj;
    }

    /**
     * 
     * 功能描述: 设置 开票金额合计<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,开票金额合计
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setKpjehj(BigDecimal kpjehj) {
        this.kpjehj = kpjehj;
    }

    /**
     * 
     * 功能描述: 获取 合计不含税金额<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,合计不含税金额
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getHjbhsje() {
        return hjbhsje;
    }

    /**
     * 
     * 功能描述: 设置 合计不含税金额<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,合计不含税金额
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setHjbhsje(BigDecimal hjbhsje) {
        this.hjbhsje = hjbhsje;
    }

    /**
     * 
     * 功能描述: 获取 开票金额合计大写<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票金额合计大写
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getSumAmountUppercase() {
        return sumAmountUppercase;
    }

    /**
     * 
     * 功能描述: 设置 开票金额合计大写<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票金额合计大写
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSumAmountUppercase(String sumAmountUppercase) {
        this.sumAmountUppercase = sumAmountUppercase;
    }

    /**
     * 
     * 功能描述: 获取 折扣金额合计<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,折扣金额合计
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getZkjehj() {
        return zkjehj;
    }

    /**
     * 
     * 功能描述: 设置 折扣金额合计<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,折扣金额合计
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setZkjehj(BigDecimal zkjehj) {
        this.zkjehj = zkjehj;
    }

    /**
     * 
     * 功能描述: 获取 税额合计<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,税额合计
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getTaxSumAmount() {
        return taxSumAmount;
    }

    /**
     * 
     * 功能描述: 设置 税额合计<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,税额合计
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTaxSumAmount(BigDecimal taxSumAmount) {
        this.taxSumAmount = taxSumAmount;
    }

    /**
     * 
     * 功能描述: 获取 运费服务费金额<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,运费服务费金额
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public BigDecimal getTransportFee() {
        return transportFee;
    }

    /**
     * 
     * 功能描述: 设置 运费服务费金额<br>
     * 〈功能详细描述〉
     * 
     * @return：BigDecimal,运费服务费金额
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTransportFee(BigDecimal transportFee) {
        this.transportFee = transportFee;
    }

    /**
     * 
     * 功能描述: 获取 会员id<br>
     * 〈功能详细描述〉
     * 
     * @return：String,会员id
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getMemberId() {
        return memberId;
    }

    /**
     * 
     * 功能描述: 设置 会员id<br>
     * 〈功能详细描述〉
     * 
     * @return：String,会员id
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * 
     * 功能描述: 获取 会员姓名<br>
     * 〈功能详细描述〉
     * 
     * @return：String,会员姓名
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getMemberName() {
        return memberName;
    }

    /**
     * 
     * 功能描述: 设置 会员姓名<br>
     * 〈功能详细描述〉
     * 
     * @return：String,会员姓名
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * 
     * 功能描述: 获取 收票人名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收票人名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getAccepterInvoiceName() {
        return accepterInvoiceName;
    }

    /**
     * 
     * 功能描述: 设置 收票人名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收票人名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setAccepterInvoiceName(String accepterInvoiceName) {
        this.accepterInvoiceName = accepterInvoiceName;
    }

    /**
     * 
     * 功能描述: 获取 收票人固定电话<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收票人固定电话
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getPhonenum() {
        return phonenum;
    }

    /**
     * 
     * 功能描述: 设置 收票人固定电话<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收票人固定电话
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    /**
     * 
     * 功能描述: 获取 收票人手机号码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收票人手机号码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getMobphonenum() {
        return mobphonenum;
    }

    /**
     * 
     * 功能描述: 设置 收票人手机号码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收票人手机号码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMobphonenum(String mobphonenum) {
        this.mobphonenum = mobphonenum;
    }

    /**
     * 
     * 功能描述: 获取 收票人邮箱地址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收票人邮箱地址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getEmailinvoice() {
        return emailinvoice;
    }

    /**
     * 
     * 功能描述: 设置 收票人邮箱地址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收票人邮箱地址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setEmailinvoice(String emailinvoice) {
        this.emailinvoice = emailinvoice;
    }

    /**
     * 
     * 功能描述: 获取 收票方地址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收票方地址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getAccepterInvoiceAddr() {
        return accepterInvoiceAddr;
    }

    /**
     * 
     * 功能描述: 设置 收票方地址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收票方地址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setAccepterInvoiceAddr(String accepterInvoiceAddr) {
        this.accepterInvoiceAddr = accepterInvoiceAddr;
    }

    /**
     * 
     * 功能描述: 获取 增值税发票收件人纳税人识别号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,增值税发票收件人纳税人识别号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getTaxpayerNo() {
        return taxpayerNo;
    }

    /**
     * 
     * 功能描述: 设置 增值税发票收件人纳税人识别号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,增值税发票收件人纳税人识别号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTaxpayerNo(String taxpayerNo) {
        this.taxpayerNo = taxpayerNo;
    }

    /**
     * 
     * 功能描述: 获取 增值税发票收件人姓名<br>
     * 〈功能详细描述〉
     * 
     * @return：String,增值税发票收件人姓名
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getTaxpayerName() {
        return taxpayerName;
    }

    /**
     * 
     * 功能描述: 设置 增值税发票收件人姓名<br>
     * 〈功能详细描述〉
     * 
     * @return：String,增值税发票收件人姓名
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTaxpayerName(String taxpayerName) {
        this.taxpayerName = taxpayerName;
    }

    /**
     * 
     * 功能描述: 获取 增值税发票收件人地址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,增值税发票收件人地址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getTaxpayerAddress() {
        return taxpayerAddress;
    }

    /**
     * 
     * 功能描述: 设置 增值税发票收件人地址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,增值税发票收件人地址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTaxpayerAddress(String taxpayerAddress) {
        this.taxpayerAddress = taxpayerAddress;
    }

    /**
     * 
     * 功能描述: 获取 增值税发票收件人电话<br>
     * 〈功能详细描述〉
     * 
     * @return：String,增值税发票收件人电话
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getTaxpayerPhone() {
        return taxpayerPhone;
    }

    /**
     * 
     * 功能描述: 设置 增值税发票收件人电话<br>
     * 〈功能详细描述〉
     * 
     * @return：String,增值税发票收件人电话
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTaxpayerPhone(String taxpayerPhone) {
        this.taxpayerPhone = taxpayerPhone;
    }

    /**
     * 
     * 功能描述: 获取 增值税发票收件人手机<br>
     * 〈功能详细描述〉
     * 
     * @return：String,增值税发票收件人手机
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getTaxpayerMobile() {
        return taxpayerMobile;
    }

    /**
     * 
     * 功能描述: 设置 增值税发票收件人手机<br>
     * 〈功能详细描述〉
     * 
     * @return：String,增值税发票收件人手机
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTaxpayerMobile(String taxpayerMobile) {
        this.taxpayerMobile = taxpayerMobile;
    }

    /**
     * 
     * 功能描述: 获取 通用行业代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,通用行业代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getCommonIndustryCode() {
        return commonIndustryCode;
    }

    /**
     * 
     * 功能描述: 设置 通用行业代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,通用行业代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCommonIndustryCode(String commonIndustryCode) {
        this.commonIndustryCode = commonIndustryCode;
    }

    /**
     * 
     * 功能描述: 获取 通用行业名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,通用行业名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getCommonIndustryName() {
        return commonIndustryName;
    }

    /**
     * 
     * 功能描述: 设置 通用行业名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,通用行业名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCommonIndustryName(String commonIndustryName) {
        this.commonIndustryName = commonIndustryName;
    }

    /**
     * 
     * 功能描述: 获取 开票方纳税人识别号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票方纳税人识别号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getMakerTaxpayerno() {
        return makerTaxpayerno;
    }

    /**
     * 
     * 功能描述: 设置 开票方纳税人识别号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票方纳税人识别号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMakerTaxpayerno(String makerTaxpayerno) {
        this.makerTaxpayerno = makerTaxpayerno;
    }

    /**
     * 
     * 功能描述: 获取 开票方纳税人名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票方纳税人名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getMakerTaxpayerName() {
        return makerTaxpayerName;
    }

    /**
     * 
     * 功能描述: 设置 开票方纳税人名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票方纳税人名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMakerTaxpayerName(String makerTaxpayerName) {
        this.makerTaxpayerName = makerTaxpayerName;
    }

    /**
     * 
     * 功能描述: 获取 开票人纳税人地址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票人纳税人地址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getMakerTaxpayerAddr() {
        return makerTaxpayerAddr;
    }

    /**
     * 
     * 功能描述: 设置 开票人纳税人地址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票人纳税人地址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMakerTaxpayerAddr(String makerTaxpayerAddr) {
        this.makerTaxpayerAddr = makerTaxpayerAddr;
    }

    /**
     * 
     * 功能描述: 获取 开票方开户银行<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票方开户银行
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getMakerInvoiceBank() {
        return makerInvoiceBank;
    }

    /**
     * 
     * 功能描述: 设置 开票方开户银行<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票方开户银行
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMakerInvoiceBank(String makerInvoiceBank) {
        this.makerInvoiceBank = makerInvoiceBank;
    }

    /**
     * 
     * 功能描述: 获取 开票方银行账号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票方银行账号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getMakerInvoiceAccount() {
        return makerInvoiceAccount;
    }

    /**
     * 
     * 功能描述: 设置 开票方银行账号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票方银行账号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMakerInvoiceAccount(String makerInvoiceAccount) {
        this.makerInvoiceAccount = makerInvoiceAccount;
    }

    /**
     * 
     * 功能描述: 获取 开票方电话号码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票方电话号码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getMakerInvoicePhone() {
        return makerInvoicePhone;
    }

    /**
     * 
     * 功能描述: 设置 开票方电话号码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票方电话号码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMakerInvoicePhone(String makerInvoicePhone) {
        this.makerInvoicePhone = makerInvoicePhone;
    }

    /**
     * 
     * 功能描述: 获取 税务机构代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,税务机构代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getTaxOrgCode() {
        return taxOrgCode;
    }

    /**
     * 
     * 功能描述: 设置 税务机构代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,税务机构代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTaxOrgCode(String taxOrgCode) {
        this.taxOrgCode = taxOrgCode;
    }

    /**
     * 
     * 功能描述: 获取 局端管理平台税务机关代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,局端管理平台税务机关代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getTaxAuthoritiesCode() {
        return taxAuthoritiesCode;
    }

    /**
     * 
     * 功能描述: 设置 局端管理平台税务机关代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,局端管理平台税务机关代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTaxAuthoritiesCode(String taxAuthoritiesCode) {
        this.taxAuthoritiesCode = taxAuthoritiesCode;
    }

    /**
     * 
     * 功能描述: 获取 局端管理平台税务机关名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,局端管理平台税务机关名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getTaxAuthoritiesName() {
        return taxAuthoritiesName;
    }

    /**
     * 
     * 功能描述: 设置 局端管理平台税务机关名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,局端管理平台税务机关名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTaxAuthoritiesName(String taxAuthoritiesName) {
        this.taxAuthoritiesName = taxAuthoritiesName;
    }

    /**
     * 
     * 功能描述: 获取 服务平台编码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,服务平台编码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getServicePlatformCode() {
        return servicePlatformCode;
    }

    /**
     * 
     * 功能描述: 设置 服务平台编码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,服务平台编码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setServicePlatformCode(String servicePlatformCode) {
        this.servicePlatformCode = servicePlatformCode;
    }

    /**
     * 
     * 功能描述: 获取 服务平台名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,服务平台名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getServicePlatformName() {
        return servicePlatformName;
    }

    /**
     * 
     * 功能描述: 设置 服务平台名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,服务平台名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setServicePlatformName(String servicePlatformName) {
        this.servicePlatformName = servicePlatformName;
    }

    /**
     * 
     * 功能描述: 获取 服务平台url<br>
     * 〈功能详细描述〉
     * 
     * @return：String,服务平台url
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getServicePlatformUrl() {
        return servicePlatformUrl;
    }

    /**
     * 
     * 功能描述: 设置 服务平台url<br>
     * 〈功能详细描述〉
     * 
     * @return：String,服务平台url
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setServicePlatformUrl(String servicePlatformUrl) {
        this.servicePlatformUrl = servicePlatformUrl;
    }

    /**
     * 
     * 功能描述: 获取 电商平台编码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,电商平台编码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getBusinesPlatformCode() {
        return businesPlatformCode;
    }

    /**
     * 
     * 功能描述: 设置 电商平台编码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,电商平台编码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setBusinesPlatformCode(String businesPlatformCode) {
        this.businesPlatformCode = businesPlatformCode;
    }

    /**
     * 
     * 功能描述: 获取 电商平台名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,电商平台名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getBusinesPlatformName() {
        return businesPlatformName;
    }

    /**
     * 
     * 功能描述: 设置 电商平台名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,电商平台名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setBusinesPlatformName(String businesPlatformName) {
        this.businesPlatformName = businesPlatformName;
    }

    /**
     * 
     * 功能描述: 获取 电商平台网址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,电商平台网址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getBusinesPlatformUrl() {
        return businesPlatformUrl;
    }

    /**
     * 
     * 功能描述: 设置 电商平台网址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,电商平台网址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setBusinesPlatformUrl(String businesPlatformUrl) {
        this.businesPlatformUrl = businesPlatformUrl;
    }

    /**
     * 
     * 功能描述: 获取 票样代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,票样代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getBallotCode() {
        return ballotCode;
    }

    /**
     * 
     * 功能描述: 设置 票样代码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,票样代码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setBallotCode(String ballotCode) {
        this.ballotCode = ballotCode;
    }

    /**
     * 
     * 功能描述: 获取 开票人名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票人名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getMakerInvoiceName() {
        return makerInvoiceName;
    }

    /**
     * 
     * 功能描述: 设置 开票人名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票人名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMakerInvoiceName(String makerInvoiceName) {
        this.makerInvoiceName = makerInvoiceName;
    }

    /**
     * 
     * 功能描述: 获取 收银员id<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收银员id
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getCashierId() {
        return cashierId;
    }

    /**
     * 
     * 功能描述: 设置 收银员id<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收银员id
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }

    /**
     * 
     * 功能描述: 获取 收银员名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收银员名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getCashierName() {
        return cashierName;
    }

    /**
     * 
     * 功能描述: 设置 收银员名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收银员名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    /**
     * 
     * 功能描述: 获取 收银台号码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收银台号码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getCashierTerminalId() {
        return cashierTerminalId;
    }

    /**
     * 
     * 功能描述: 设置 收银台号码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,收银台号码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCashierTerminalId(String cashierTerminalId) {
        this.cashierTerminalId = cashierTerminalId;
    }

    /**
     * 
     * 功能描述: 获取 操作人工号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,操作人工号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getOperationCode() {
        return operationCode;
    }

    /**
     * 
     * 功能描述: 设置 操作人工号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,操作人工号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    /**
     * 
     * 功能描述: 获取 操作人姓名<br>
     * 〈功能详细描述〉
     * 
     * @return：String,操作人姓名
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getOperationName() {
        return operationName;
    }

    /**
     * 
     * 功能描述: 设置 操作人姓名<br>
     * 〈功能详细描述〉
     * 
     * @return：String,操作人姓名
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * 
     * 功能描述: 获取 操作时间<br>
     * 〈功能详细描述〉
     * 
     * @return：Date,操作时间
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public Date getOperationDate() {
        return operationDate;
    }

    /**
     * 
     * 功能描述: 设置 操作时间<br>
     * 〈功能详细描述〉
     * 
     * @return：Date,操作时间
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    /**
     * 
     * 功能描述: 获取 处理状态<br>
     * 〈功能详细描述〉
     * 
     * @return：String,处理状态
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getStatus() {
        return status;
    }

    /**
     * 
     * 功能描述: 设置 处理状态<br>
     * 〈功能详细描述〉
     * 
     * @return：String,处理状态
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * 功能描述: 获取 二维码信息<br>
     * 〈功能详细描述〉
     * 
     * @return：String,二维码信息
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getTwodimensionalCodeInfo() {
        return twodimensionalCodeInfo;
    }

    /**
     * 
     * 功能描述: 设置 二维码信息<br>
     * 〈功能详细描述〉
     * 
     * @return：String,二维码信息
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setTwodimensionalCodeInfo(String twodimensionalCodeInfo) {
        this.twodimensionalCodeInfo = twodimensionalCodeInfo;
    }

    /**
     * 
     * 功能描述: 获取 数字签名信息<br>
     * 〈功能详细描述〉
     * 
     * @return：String,数字签名信息
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getDigitalSignatureInfo() {
        return digitalSignatureInfo;
    }

    /**
     * 
     * 功能描述: 设置 数字签名信息<br>
     * 〈功能详细描述〉
     * 
     * @return：String,数字签名信息
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setDigitalSignatureInfo(String digitalSignatureInfo) {
        this.digitalSignatureInfo = digitalSignatureInfo;
    }

    /**
     * 
     * 功能描述: 获取 打印次数<br>
     * 〈功能详细描述〉
     * 
     * @return：Integer,打印次数
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public Integer getPrintNum() {
        return printNum;
    }

    /**
     * 
     * 功能描述: 设置 打印次数<br>
     * 〈功能详细描述〉
     * 
     * @return：Integer,打印次数
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setPrintNum(Integer printNum) {
        this.printNum = printNum;
    }

    /**
     * 
     * 功能描述: 获取 打印人工号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,打印人工号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getPrintNo() {
        return printNo;
    }

    /**
     * 
     * 功能描述: 设置 打印人工号<br>
     * 〈功能详细描述〉
     * 
     * @return：String,打印人工号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setPrintNo(String printNo) {
        this.printNo = printNo;
    }

    /**
     * 
     * 功能描述: 获取 打印人名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,打印人名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getPrintName() {
        return printName;
    }

    /**
     * 
     * 功能描述: 设置 打印人名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,打印人名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setPrintName(String printName) {
        this.printName = printName;
    }

    /**
     * 
     * 功能描述: 获取 打印时间<br>
     * 〈功能详细描述〉
     * 
     * @return：Date,打印时间
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public Date getPrintDate() {
        return printDate;
    }

    /**
     * 
     * 功能描述: 设置 打印时间<br>
     * 〈功能详细描述〉
     * 
     * @return：Date,打印时间
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }

    /**
     * 
     * 功能描述: 获取 打印标识<br>
     * 〈功能详细描述〉
     * 
     * @return：String,打印标识
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getPrintFlag() {
        return printFlag;
    }

    /**
     * 
     * 功能描述: 设置 打印标识<br>
     * 〈功能详细描述〉
     * 
     * @return：String,打印标识
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
    }

    /**
     * 
     * 功能描述: 获取 发票状态 01 蓝票已被冲红 ， 99 蓝票已被换开<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票状态 01 蓝票已被冲红 ， 99 蓝票已被换开
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getInvStatus() {
        return invStatus;
    }

    /**
     * 
     * 功能描述: 设置 发票状态 01 蓝票已被冲红 ， 99 蓝票已被换开<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票状态 01 蓝票已被冲红 ， 99 蓝票已被换开
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setInvStatus(String invStatus) {
        this.invStatus = invStatus;
    }

    /**
     * 
     * 功能描述: 获取 inv_desc<br>
     * 〈功能详细描述〉
     * 
     * @return：String,inv_desc
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getInvDesc() {
        return invDesc;
    }

    /**
     * 
     * 功能描述: 设置 inv_desc<br>
     * 〈功能详细描述〉
     * 
     * @return：String,inv_desc
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setInvDesc(String invDesc) {
        this.invDesc = invDesc;
    }

    /**
     * 
     * 功能描述: 获取 开票日期<br>
     * 〈功能详细描述〉
     * 
     * @return：Date,开票日期
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public Date getKprq() {
        return kprq;
    }

    /**
     * 
     * 功能描述: 设置 开票日期<br>
     * 〈功能详细描述〉
     * 
     * @return：Date,开票日期
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setKprq(Date kprq) {
        this.kprq = kprq;
    }

    /**
     * 
     * 功能描述: 获取 开票流水号（航信返回）<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票流水号（航信返回）
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getHxReturnKplsh() {
        return hxReturnKplsh;
    }

    /**
     * 
     * 功能描述: 设置 开票流水号（航信返回）<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票流水号（航信返回）
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setHxReturnKplsh(String hxReturnKplsh) {
        this.hxReturnKplsh = hxReturnKplsh;
    }

    /**
     * 
     * 功能描述: 获取 开票项目<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票项目
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getKpxm() {
        return kpxm;
    }

    /**
     * 
     * 功能描述: 设置 开票项目<br>
     * 〈功能详细描述〉
     * 
     * @return：String,开票项目
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setKpxm(String kpxm) {
        this.kpxm = kpxm;
    }

    /**
     * 
     * 功能描述: 获取 购货方名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,购货方名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getGhfmc() {
        return ghfmc;
    }

    /**
     * 
     * 功能描述: 设置 购货方名称<br>
     * 〈功能详细描述〉
     * 
     * @return：String,购货方名称
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setGhfmc(String ghfmc) {
        this.ghfmc = ghfmc;
    }

    /**
     * 
     * 功能描述: 获取 开票类型<br>
     * 〈功能详细描述〉
     * 
     * @return：Integer,开票类型
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public Integer getKplx() {
        return kplx;
    }

    /**
     * 
     * 功能描述: 设置 开票类型<br>
     * 〈功能详细描述〉
     * 
     * @return：Integer,开票类型
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setKplx(Integer kplx) {
        this.kplx = kplx;
    }

    /**
     * 
     * 功能描述: 获取 防伪码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,防伪码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getSecurityCode() {
        return securityCode;
    }

    /**
     * 
     * 功能描述: 设置 防伪码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,防伪码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * 
     * 功能描述: 获取 发票票种编码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票票种编码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getInvoiceTicketCode() {
        return invoiceTicketCode;
    }

    /**
     * 
     * 功能描述: 设置 发票票种编码<br>
     * 〈功能详细描述〉
     * 
     * @return：String,发票票种编码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setInvoiceTicketCode(String invoiceTicketCode) {
        this.invoiceTicketCode = invoiceTicketCode;
    }

    /**
     * 
     * 功能描述: 获取 最后接收日期<br>
     * 〈功能详细描述〉
     * 
     * @return：Date,最后接收日期
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public Date getLastAcceptDate() {
        return lastAcceptDate;
    }

    /**
     * 
     * 功能描述: 设置 最后接收日期<br>
     * 〈功能详细描述〉
     * 
     * @return：Date,最后接收日期
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setLastAcceptDate(Date lastAcceptDate) {
        this.lastAcceptDate = lastAcceptDate;
    }

    /**
     * 
     * 功能描述: 获取 接收发票次数<br>
     * 〈功能详细描述〉
     * 
     * @return：Integer,接收发票次数
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public Integer getAcceptTime() {
        return acceptTime;
    }

    /**
     * 
     * 功能描述: 设置 接收发票次数<br>
     * 〈功能详细描述〉
     * 
     * @return：Integer,接收发票次数
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setAcceptTime(Integer acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     * 
     * 功能描述: 获取 pdf地址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,pdf地址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getPdfUrl() {
        return pdfUrl;
    }

    /**
     * 
     * 功能描述: 设置 pdf地址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,pdf地址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    /**
     * 
     * 功能描述: 获取 pdf文件服务器地址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,pdf文件服务器地址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getPdfFile() {
        return pdfFile;
    }

    /**
     * 
     * 功能描述: 设置 pdf文件服务器地址<br>
     * 〈功能详细描述〉
     * 
     * @return：String,pdf文件服务器地址
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setPdfFile(String pdfFile) {
        this.pdfFile = pdfFile;
    }

    /**
     * 
     * 功能描述: 获取 备用字段1<br>
     * 〈功能详细描述〉
     * 
     * @return：String,备用字段1
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getBackupFieldFir() {
        return backupFieldFir;
    }

    /**
     * 
     * 功能描述: 设置 备用字段1<br>
     * 〈功能详细描述〉
     * 
     * @return：String,备用字段1
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setBackupFieldFir(String backupFieldFir) {
        this.backupFieldFir = backupFieldFir;
    }

    /**
     * 
     * 功能描述: 获取 备用字段2<br>
     * 〈功能详细描述〉
     * 
     * @return：String,备用字段2
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @TableField
    public String getBackupFieldSec() {
        return backupFieldSec;
    }

    /**
     * 
     * 功能描述: 设置 备用字段2<br>
     * 〈功能详细描述〉
     * 
     * @return：String,备用字段2
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setBackupFieldSec(String backupFieldSec) {
        this.backupFieldSec = backupFieldSec;
    }

    public String getCmmdtyname() {
        return cmmdtyname;
    }

    public void setCmmdtyname(String cmmdtyname) {
        this.cmmdtyname = cmmdtyname;
    }

    public BigDecimal getSl() {
        return sl;
    }

    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    public BigDecimal getDj() {
        return dj;
    }

    public void setDj(BigDecimal dj) {
        this.dj = dj;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxSum() {
        return taxSum;
    }

    public void setTaxSum(BigDecimal taxSum) {
        this.taxSum = taxSum;
    }

     
    /**
     * @return the je
     */
    public BigDecimal getJe() {
        return je;
    }

    /**
     * @param je the je to set
     */
    public void setJe(BigDecimal je) {
        this.je = je;
    }
}