package com.jdk2010.framework.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdk2010.framework.util.JsonUtil;
import com.jdk2010.framework.util.ModelInjector;
import com.jdk2010.framework.util.Page;

public class BaseController {

    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    public static final String REDIRECT = "redirect:";

    public static final String FORWARD = "forward:";

    public BaseController setAttr(String name, Object value) {
        request.setAttribute(name, value);
        return this;
    }

    public BaseController removeAttr(String name) {
        request.removeAttribute(name);
        return this;
    }

    public String getPara(String name) {
        return request.getParameter(name);
    }

    public String getPara(String name, String defaultValue) {
        String result = request.getParameter(name);
        return result != null && !"".equals(result) ? result : defaultValue;
    }

    public Map<String, String[]> getParaMap() {
        return request.getParameterMap();
    }

    public Enumeration<String> getParaNames() {
        return request.getParameterNames();
    }

    public String[] getParaValues(String name) {
        return request.getParameterValues(name);
    }

    public <T> T getAttr(String name) {
        return (T) request.getAttribute(name);
    }

    public String getAttrForStr(String name) {
        return (String) request.getAttribute(name);
    }

    public Integer getAttrForInt(String name) {
        return (Integer) request.getAttribute(name);
    }

    private Integer toInt(String value, Integer defaultValue) {
        if (value == null || "".equals(value.trim()))
            return defaultValue;
        if (value.startsWith("N") || value.startsWith("n"))
            return -Integer.parseInt(value.substring(1));
        return Integer.parseInt(value);
    }

    /**
     * Returns the value of a request parameter and convert to Integer.
     * 
     * @param name a String specifying the name of the parameter
     * @return a Integer representing the single value of the parameter
     */
    public Integer getParaToInt(String name) {
        return toInt(request.getParameter(name), null);
    }

    /**
     * Returns the value of a request parameter and convert to Integer with a default value if it is null.
     * 
     * @param name a String specifying the name of the parameter
     * @return a Integer representing the single value of the parameter
     */
    public Integer getParaToInt(String name, Integer defaultValue) {
        return toInt(request.getParameter(name), defaultValue);
    }

    private Long toLong(String value, Long defaultValue) {
        if (value == null || "".equals(value.trim()))
            return defaultValue;
        if (value.startsWith("N") || value.startsWith("n"))
            return -Long.parseLong(value.substring(1));
        return Long.parseLong(value);
    }

    /**
     * Returns the value of a request parameter and convert to Long.
     * 
     * @param name a String specifying the name of the parameter
     * @return a Integer representing the single value of the parameter
     */
    public Long getParaToLong(String name) {
        return toLong(request.getParameter(name), null);
    }

    public Double getParaToDouble(String name) {
        return Double.parseDouble(request.getParameter(name));
    }

    /**
     * Returns the value of a request parameter and convert to Long with a default value if it is null.
     * 
     * @param name a String specifying the name of the parameter
     * @return a Integer representing the single value of the parameter
     */
    public Long getParaToLong(String name, Long defaultValue) {
        return toLong(request.getParameter(name), defaultValue);
    }

    private Boolean toBoolean(String value, Boolean defaultValue) {
        if (value == null || "".equals(value.trim()))
            return defaultValue;
        value = value.trim().toLowerCase();
        if ("1".equals(value) || "true".equals(value))
            return Boolean.TRUE;
        else if ("0".equals(value) || "false".equals(value))
            return Boolean.FALSE;
        throw new RuntimeException("Can not parse the parameter \"" + value + "\" to boolean value.");
    }

    /**
     * Returns the value of a request parameter and convert to Boolean.
     * 
     * @param name a String specifying the name of the parameter
     * @return true if the value of the parameter is "true" or "1", false if it is "false" or "0", null if parameter is
     *         not exists
     */
    public Boolean getParaToBoolean(String name) {
        return toBoolean(request.getParameter(name), null);
    }

    /**
     * Returns the value of a request parameter and convert to Boolean with a default value if it is null.
     * 
     * @param name a String specifying the name of the parameter
     * @return true if the value of the parameter is "true" or "1", false if it is "false" or "0", default value if it
     *         is null
     */
    public Boolean getParaToBoolean(String name, Boolean defaultValue) {
        return toBoolean(request.getParameter(name), defaultValue);
    }

    private Date toDate(String value, Date defaultValue) {
        if (value == null || "".equals(value.trim()))
            return defaultValue;
        try {
            return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the value of a request parameter and convert to Date.
     * 
     * @param name a String specifying the name of the parameter
     * @return a Date representing the single value of the parameter
     */
    public Date getParaToDate(String name) {
        return toDate(request.getParameter(name), null);
    }

    /**
     * Returns the value of a request parameter and convert to Date with a default value if it is null.
     * 
     * @param name a String specifying the name of the parameter
     * @return a Date representing the single value of the parameter
     */
    public Date getParaToDate(String name, Date defaultValue) {
        return toDate(request.getParameter(name), defaultValue);
    }

    /**
     * Return HttpServletRequest. Do not use HttpServletRequest Object in constructor of Controller
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * Return HttpServletResponse. Do not use HttpServletResponse Object in constructor of Controller
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * Return HttpSession.
     */
    public HttpSession getSession() {
        return request.getSession();
    }

    /**
     * Return HttpSession.
     * 
     * @param create a boolean specifying create HttpSession if it not exists
     */
    public HttpSession getSession(boolean create) {
        return request.getSession(create);
    }

    /**
     * Return a Object from session.
     * 
     * @param key a String specifying the key of the Object stored in session
     */
    public <T> T getSessionAttr(String key) {
        HttpSession session = request.getSession(false);
        return session != null ? (T) session.getAttribute(key) : null;
    }

    /**
     * Store Object to session.
     * 
     * @param key a String specifying the key of the Object stored in session
     * @param value a Object specifying the value stored in session
     */
    public BaseController setSessionAttr(String key, Object value) {
        request.getSession().setAttribute(key, value);
        return this;
    }

    /**
     * Remove Object in session.
     * 
     * @param key a String specifying the key of the Object stored in session
     */
    public BaseController removeSessionAttr(String key) {
        HttpSession session = request.getSession(false);
        if (session != null)
            session.removeAttribute(key);
        return this;
    }

    /**
     * Get cookie value by cookie name.
     */
    public String getCookie(String name, String defaultValue) {
        Cookie cookie = getCookieObject(name);
        return cookie != null ? cookie.getValue() : defaultValue;
    }

    /**
     * Get cookie value by cookie name.
     */
    public String getCookie(String name) {
        return getCookie(name, null);
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     */
    public Integer getCookieToInt(String name) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     */
    public Integer getCookieToInt(String name, Integer defaultValue) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }

    /**
     * Get cookie value by cookie name and convert to Long.
     */
    public Long getCookieToLong(String name) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Long.
     */
    public Long getCookieToLong(String name, Long defaultValue) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : defaultValue;
    }

    /**
     * Get cookie object by cookie name.
     */
    public Cookie getCookieObject(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals(name))
                    return cookie;
        return null;
    }

    /**
     * Get all cookie objects.
     */
    public Cookie[] getCookieObjects() {
        Cookie[] result = request.getCookies();
        return result != null ? result : new Cookie[0];
    }

    /**
     * Set Cookie to response.
     */
    public BaseController setCookie(Cookie cookie) {
        response.addCookie(cookie);
        return this;
    }

    /**
     * Set Cookie to response.
     * 
     * @param name cookie name
     * @param value cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately. n>0 : max age in n
     *            seconds.
     * @param path see Cookie.setPath(String)
     */
    public BaseController setCookie(String name, String value, int maxAgeInSeconds, String path) {
        setCookie(name, value, maxAgeInSeconds, path, null);
        return this;
    }

    /**
     * Set Cookie to response.
     * 
     * @param name cookie name
     * @param value cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately. n>0 : max age in n
     *            seconds.
     * @param path see Cookie.setPath(String)
     * @param domain the domain name within which this cookie is visible; form is according to RFC 2109
     */
    public BaseController setCookie(String name, String value, int maxAgeInSeconds, String path, String domain) {
        Cookie cookie = new Cookie(name, value);
        if (domain != null)
            cookie.setDomain(domain);
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setPath(path);
        response.addCookie(cookie);
        return this;
    }

    /**
     * Set Cookie with path = "/".
     */
    public BaseController setCookie(String name, String value, int maxAgeInSeconds) {
        setCookie(name, value, maxAgeInSeconds, "/", null);
        return this;
    }

    /**
     * Remove Cookie with path = "/".
     */
    public BaseController removeCookie(String name) {
        setCookie(name, null, 0, "/", null);
        return this;
    }

    /**
     * Remove Cookie.
     */
    public BaseController removeCookie(String name, String path) {
        setCookie(name, null, 0, path, null);
        return this;
    }

    /**
     * Remove Cookie.
     */
    public BaseController removeCookie(String name, String path, String domain) {
        setCookie(name, null, 0, path, domain);
        return this;
    }

    public <T> T getModel(Class<T> modelClass) throws Exception {
        return (T) ModelInjector.inject(modelClass, request, false);
    }

    public void renderJson(String jsonText) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(jsonText);

    }

    public void renderJson(Object obj) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        response.getWriter().print(JsonUtil.toJson(obj));
    }

    public Page getPage() {
        int pageIndex = 1;
        int pageSize = 10;
        String orderBy = getPara("orderBy");
        String sort = getPara("sort");

        if (getPara("pageIndex") != null) {
            pageIndex = getParaToInt("pageIndex");
        }
        if (getPara("pageSize") != null) {
            pageSize = getParaToInt("pageSize");
        }

        Page page = new Page();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);

        return page;
    }

}
