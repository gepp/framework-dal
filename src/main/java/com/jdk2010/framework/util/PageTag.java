package com.jdk2010.framework.util;


import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport
{
  private String data = "pageList";
  private String href;
  private StringBuffer output;

  public String getData()
  {
    return this.data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getHref() {
    return this.href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public StringBuffer getOutput() {
    return this.output;
  }

  public void setOutput(StringBuffer output) {
    this.output = output;
  }

  public int doEndTag() throws JspException
  {
    return super.doEndTag();
  }

  public int doStartTag() throws JspException
  {
    try {
      this.output = new StringBuffer();
      hander();
      this.pageContext.getOut().write(this.output.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  private void hander() {
    if (this.href.indexOf("?") < 0) {
      this.href += "?";
    }
    if ((this.href.endsWith("?")) || (this.href.endsWith("&amp;")))
      this.href += "pageIndex=";
    else {
      this.href += "&amp;pageIndex=";
    }
    Page pageList = (Page)this.pageContext.getRequest().getAttribute(this.data);
    if (pageList != null) {
      StringBuffer page = new StringBuffer();
      int pageNumber = Integer.valueOf(pageList.getPageIndex()).intValue();
      int totalPage = Integer.valueOf(pageList.getTotalPage()).intValue();
      int totalRow = Integer.valueOf(pageList.getTotalCount()).intValue();
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

      page.append("<div class=\"pagin  \"><div class=\"message\">共<i class=\"blue\">"+totalRow+"</i>条记录，当前显示第&nbsp;<i class=\"blue\">"+pageNumber+"&nbsp;</i>页</div><ul class=\"paginList\">");

      if (pageNumber <= 1) {
        page.append("<li class=\"paginItem\"><a href=\"javascript:;\"><span  class=\"pagepre\"></span></a></li>");
      }
      if (pageNumber > 1) {
        page.append("<li class=\"paginItem\" ><a href=\"" + this.href + (pageNumber - 1) + " \"><span  class=\"pagepre\"></span></a></li>");
      }
      if (pageNumber > 8) {
        page.append("<li class=\"paginItem\" ><a href=\"" + this.href + "1 \">1</a></li>")
          .append("<li class=\"paginItem\" ><a href=\"" + this.href + "2 \">2</a></li>")
          .append("<li class=\"paginItem more\"> <a href='#'>...</a></li>");
      }

      for (int i = startPage; i <= endPage; i++) {
        if (pageNumber == i)
          page.append("<li class=\"paginItem current\"><a href='#'>" + i + "</a></li>");
        else {
          page.append("<li class=\"paginItem\"><a href=\"" + this.href + i + " \">" + i + "</a></li>");
        }
      }
      if (totalPage - pageNumber >= 8) {
        page.append("<li class=\"paginItem more\"> <a href='#'>...</a></li>")
          .append("<li class=\"paginItem\"><a href=\"" + this.href + (totalPage - 1) + " \">" + (totalPage - 1) + "</a></li>")
          .append("<li class=\"paginItem\"><a href=\"" + this.href + totalPage + " \">" + totalPage + "</a></li>");
      }

      if (pageNumber == totalPage)
        page.append("<li class=\"paginItem\"><a href='#'><span class=\"pagenxt\"></span></a></li>");
      else {
        page.append("<li class=\"paginItem\"><a href=\"" + this.href + (pageNumber + 1) + " \"><span  class=\"pagenxt\"></span></a></li>");
      }

      page.append("</ul></div>");
      this.output.append(page);
    }
  }
}
