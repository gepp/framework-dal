package com.jdk2010.framework.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.jdk2010.framework.util.Page;

public class PageTag extends TagSupport {

    private String data = "pageList";// 数据集合名字
    private String href; // 连接地址
    private StringBuffer output; // 页面输出

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public StringBuffer getOutput() {
        return output;
    }

    public void setOutput(StringBuffer output) {
        this.output = output;
    }

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            output = new StringBuffer();
            hander();
            this.pageContext.getOut().write(output.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    private void hander() {
        // 如果URL不包�? 则添�?
        if (href.indexOf("?") < 0) {
            href += "?";
        }
        if (href.endsWith("?") || href.endsWith("&amp;")) {
            href += "pageIndex=";
        } else {
            href += "&amp;pageIndex=";
        }
        Page pageList = (Page) this.pageContext.getRequest().getAttribute(data);
        if (pageList != null) {
            StringBuffer page = new StringBuffer();
            int pageNumber = Integer.valueOf(pageList.getPageIndex());
            int totalPage = Integer.valueOf(pageList.getTotalPage());
            int totalRow = Integer.valueOf(pageList.getTotalCount());
            int startPage = pageNumber - 4;
            int endPage = pageNumber + 4;

            if (totalRow == 0) {
                pageNumber = 0;
            }

            if (startPage < 1) {
                startPage = 1;
            }

            if (endPage > totalPage) {
                endPage = totalPage;
            }

            if (pageNumber <= 8) {
                startPage = 1;
            }
            if (totalPage - pageNumber < 8) {
                endPage = totalPage;
            }

            page.append("<div class=\"pagination pull-right \"><ul>");

            if (pageNumber <= 1) {
                page.append("<li><a href='#'>上一�?/a></li>");
            }
            if (pageNumber > 1) {
                page.append("<li><a href=\"" + href + (pageNumber - 1) + " \">上一�?/a></li>");
            }
            if (pageNumber > 8) {
                page.append("<li><a href=\"" + href + "1 \">1</a></li>")
                        .append("<li><a href=\"" + href + "2 \">2</a></li>").append("<a href='#'>...</a>");
            }

            for (int i = startPage; i <= endPage; i++) {
                if (pageNumber == i) {
                    page.append("<li class=\"active\"><a href='#'>" + i + "</a></li>");
                } else {
                    page.append("<li><a href=\"" + href + i + " \">" + i + "</a></li>");
                }
            }
            if (totalPage - pageNumber >= 8) {
                page.append("<a href='#'>...</a>")
                        .append("<li><a href=\"" + href + (totalPage - 1) + " \">" + (totalPage - 1) + "</a></li>")
                        .append("<li><a href=\"" + href + (totalPage) + " \">" + (totalPage) + "</a></li>");
            }

            if (pageNumber == totalPage) {
                page.append("<li><a href='#'>下一�?/a></li>");
            } else {
                page.append("<li><a href=\"" + href + (pageNumber + 1) + " \">下一�?/a></li>");

            }

            page.append("</ul></div>");
            output.append(page);
        }
    }

}
