package com.jdk2010.framework.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jdk2010.framework.util.JsonUtil;
import com.jdk2010.framework.util.ModelInjector;
import com.jdk2010.framework.util.Page;

public abstract class BaseController {

    public static final String REDIRECT = "redirect:";

    public static final String FORWARD = "forward:";

    private HttpServletRequest request;

    public BaseController setAttr(String name, Object value) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute(name, value);
        return this;
    }

    public BaseController removeAttr(String name) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.removeAttribute(name);
        return this;
    }

    public String getPara(String name) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getParameter(name);
    }

    public String getPara(String name, String defaultValue) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String result = request.getParameter(name);
        return result != null && !"".equals(result) ? result : defaultValue;
    }

    public Map<String, String[]> getParaMap() {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getParameterMap();
    }

    public Enumeration<String> getParaNames() {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getParameterNames();
    }

    public String[] getParaValues(String name) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getParameterValues(name);
    }

    public <T> T getAttr(String name) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (T) request.getAttribute(name);
    }

    public String getAttrForStr(String name) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (String) request.getAttribute(name);
    }

    public Integer getAttrForInt(String name) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (Integer) request.getAttribute(name);
    }

    private Integer toInt(String value, Integer defaultValue) {
        if (value == null || "".equals(value.trim()))
            return defaultValue;
        if (value.startsWith("N") || value.startsWith("n"))
            return -Integer.parseInt(value.substring(1));
        return Integer.parseInt(value);
    }

    public Integer getParaToInt(String name) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return toInt(request.getParameter(name), null);
    }

    public Integer getParaToInt(String name, Integer defaultValue) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return toInt(request.getParameter(name), defaultValue);
    }

    private Long toLong(String value, Long defaultValue) {
        if (value == null || "".equals(value.trim()))
            return defaultValue;
        if (value.startsWith("N") || value.startsWith("n"))
            return -Long.parseLong(value.substring(1));
        return Long.parseLong(value);
    }

    public Long getParaToLong(String name) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return toLong(request.getParameter(name), null);
    }

    public Double getParaToDouble(String name) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return Double.parseDouble(request.getParameter(name));
    }

    public Long getParaToLong(String name, Long defaultValue) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
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

    public Boolean getParaToBoolean(String name) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return toBoolean(request.getParameter(name), null);
    }

    public Boolean getParaToBoolean(String name, Boolean defaultValue) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
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

    public Date getParaToDate(String name) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return toDate(request.getParameter(name), null);
    }

    public Date getParaToDate(String name, Date defaultValue) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return toDate(request.getParameter(name), defaultValue);
    }

    public HttpServletRequest getRequest() {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * Return HttpSession.
     */
    public HttpSession getSession() {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession();
    }

    /**
     * Return HttpSession.
     * 
     * @param create a boolean specifying create HttpSession if it not exists
     */
    public HttpSession getSession(boolean create) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession(create);
    }

    /**
     * Return a Object from session.
     * 
     * @param key a String specifying the key of the Object stored in session
     */
    public <T> T getSessionAttr(String key) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
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
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(key, value);
        return this;
    }

    /**
     * Remove Object in session.
     * 
     * @param key a String specifying the key of the Object stored in session
     */
    public BaseController removeSessionAttr(String key) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
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

    public Long getCookieToLong(String name) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : null;
    }

    public Long getCookieToLong(String name, Long defaultValue) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : defaultValue;
    }

    public Cookie getCookieObject(String name) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
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
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] result = request.getCookies();
        return result != null ? result : new Cookie[0];
    }

    public BaseController setCookie(HttpServletResponse response, Cookie cookie) {
        response.addCookie(cookie);
        return this;
    }

    public BaseController setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds,
            String path) {
        setCookie(response, name, value, maxAgeInSeconds, path, null);
        return this;
    }

    public BaseController setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds,
            String path, String domain) {
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
    public BaseController setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        setCookie(response, name, value, maxAgeInSeconds, "/", null);
        return this;
    }

    /**
     * Remove Cookie with path = "/".
     */
    public BaseController removeCookie(HttpServletResponse response, String name) {
        setCookie(response, name, null, 0, "/", null);
        return this;
    }

    /**
     * Remove Cookie.
     */
    public BaseController removeCookie(HttpServletResponse response, String name, String path) {
        setCookie(response, name, null, 0, path, null);
        return this;
    }

    /**
     * Remove Cookie.
     */
    public BaseController removeCookie(HttpServletResponse response, String name, String path, String domain) {
        setCookie(response, name, null, 0, path, domain);
        return this;
    }

    public <T> T getModel(Class<T> modelClass) throws Exception {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (T) ModelInjector.inject(modelClass, request, false);
    }

    public void renderText(HttpServletResponse response, String jsonText) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(jsonText);
    }

    public void renderJson(HttpServletResponse response, Object obj) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(JsonUtil.toJson(obj));
    }

    public Page getPage() {
        int pageIndex = 1;
        int pageSize = 20;
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
