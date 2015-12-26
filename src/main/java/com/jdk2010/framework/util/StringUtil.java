package com.jdk2010.framework.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;

/**
 * 
 */
public class StringUtil {
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0) ? true : false;
    }

    public static boolean isBlank(Double str) {
        return (str == null) ? true : false;
    }
    
    public static boolean isBlank(Integer str) {
        return (str == null) ? true : false;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isNotBlank(Object[] array) {
        return (array != null) && (array.length > 0);
    }

    public static boolean isNotBlank(Collection<?> collection) {
        return (collection != null) && (!collection.isEmpty());
    }

    public static boolean isBlank(Collection<?> collection) {
        return !isNotBlank(collection);
    }

    public static boolean isNotBlank(Map<?, ?> map) {
        return (map != null) && (!map.isEmpty());
    }

    public static boolean isBlank(Map<?, ?> map) {
        return !isNotBlank(map);
    }

    /**
     * 删除字符串最后一位
     * 
     * @param sb
     * @param c
     * @return
     */

    public static StringBuilder compareAndDeleteLastChar(StringBuilder sb, char c) {
        if ((sb.length() > 0) && (sb.charAt(sb.length() - 1) == c)) {
            sb = sb.deleteCharAt(sb.length() - 1);
        }
        return sb;
    }

    /**
     * 字符串转16进制字符串
     * 
     * @param string
     * @return
     */
    public static String toHexString(String string, String charset) {
        try {
            return DatatypeConverter.printHexBinary(string.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将16进制转换为字符串
     * 
     * @param hexString
     * @param charset
     * @return
     */
    public static String fromHexString(String hexString, String charset) {
        try {
            return new String(toByteArray(hexString), charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] toByteArray(String hexadecimalString) {
        return DatatypeConverter.parseHexBinary(hexadecimalString);
    }

    /**
     * 格式化浮点数
     * 
     * @param f Float
     * @param pattern String 模式。例如".00"表示小数点后保留两位
     * @return
     */
    public static String formatFloat(Float f, String pattern) {
        return new DecimalFormat(pattern).format(f);
    }

    /**
     * 格式化浮点数
     * 
     * @param f Double
     * @param pattern String 模式。例如".00"表示小数点后保留两位
     * @return
     */
    public static String formatFloat(Double f, String pattern) {
        return new DecimalFormat(pattern).format(f);
    }

    /**
     * 使用简明的方式表示大的数量
     * 
     * @param number long
     * @return String
     */

    public static String parseMetricUnits(long number) {

        long ahundredmillion = 0L;
        long tenthousand = 0L;
        long entries = 0L;
        long tmp = 0L;

        if (number < 10) {
            return number + "条";
        }

        tmp = number;
        entries = tmp % 10000L;
        tenthousand = tmp / 10000L;
        if (tenthousand < 10000) {
            return tenthousand + "万" + entries + "条";
        }

        tmp = tenthousand;
        tenthousand = tmp % 10000L;
        ahundredmillion = tmp / 10000L;

        return ahundredmillion + "亿" + tenthousand + "万" + entries + "条";
    }

    /**
     * 给定字符串转换字符编码<br/>
     * 如果参数为空，则返回原字符串，不报错。
     * 
     * @param str 被转码的字符串
     * @param sourceCharset 原字符集
     * @param destCharset 目标字符集
     * @return 转换后的字符串
     */
    public static String transCharset(String str, String sourceCharset, String destCharset) {
        if (StringUtil.isBlank(str) || StringUtil.isBlank(sourceCharset) || StringUtil.isBlank(destCharset)) {
            return str;
        }
        try {
            return new String(str.getBytes(sourceCharset), destCharset);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * 金额大小写转换
     */
    public static String digitUppercase(double n) {
        String fraction[] = { "角", "分" };
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };
        String head = n < 0 ? "负" : "";
        n = Math.abs(n);
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if (s.length() < 1) {
            s = "整";
        }
        int integerPart = (int) Math.floor(n);
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p = "";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart % 10] + unit[1][j] + p;
                integerPart = integerPart / 10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;

        }
        return head
                + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零")
                        .replaceAll("^整$", "零元整");
    }

    /**
     * 判断是否汉字
     * 
     * @param c char 要判断的字符
     * @return boolean
     */
    public static boolean isChinese(char c) {
        String tmp = String.valueOf(c);
        return tmp.matches("[\u4e00-\u9fa5]+");
    }

    /**
     * 判断整个字符串是否都是汉字
     * 
     * @param str String
     * @return boolean
     */
    public static boolean isChinese(String str) {
        return str.matches("[\u4e00-\u9fa5]+");
    }

    /**
     * 判断字符串是否包含汉字
     * 
     * @param str String
     * @return boolean
     */
    public static boolean containsChinese(String str) {
        if (!hasText(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是超链接
     * 
     * @param a String
     * @return boolean
     */
    public static boolean isA(String a) {
        if (!hasText(a)) {
            return false;
        }

        return a.matches("(<a\\s*href=[^>]*>)");
    }

    /**
     * 判断是否URL
     * 
     * @param url String
     * @return boolean
     */
    public static boolean isURL(String url) {
        if (!hasText(url)) {
            return false;
        }
        return url.matches("http://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
    }

    /**
     * 判断是否Email字符串
     * 
     * @param email String
     * @return boolean
     */
    public static boolean isEmail(String email) {
        if (!hasText(email)) {
            return false;
        }
        return email.matches("[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*");
    }

    /**
     * 判断是否是一个数字串
     * 
     * @param value
     * @return
     */
    public static boolean isNumber(String value) {
        if (!hasText(value)) {
            return false;
        }
        boolean isInt = true;
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (!Character.isDigit(ch)) {
                isInt = false;
                break;
            }// if
        }// for
        return isInt;
    }

    /**
     * text 不能为 null 且必须至少包含一个非空格的字符
     * 
     * @param text String
     * @return boolean
     */
    public static boolean hasText(String text) {
        return hasLength(text) && Pattern.matches(".*\\S.*", text);
    }

    /**
     * 当 text 不为 null 且长度不为 0
     * 
     * @param text String
     * @return boolean
     */
    public static boolean hasLength(String text) {
        return (text != null) && (text.length() > 0);
    }

    /**
     * 判断是否合法的手机号码. 手机号码为11位数字。 国家号码段分配如下： 　　 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 　　 *
     * 联通：130、131、132、152、155、156、185、186 　　 * 电信：133、153、180、189、（1349卫通）
     * 
     * @param mobiles 手机号码
     * @return boolean
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isMobileNO2(String mobiles) {
        String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobiles);
        return m.find();
    }

    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    public static boolean isLongInteger(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    /**
     * 补充num位,前置补充
     * 
     * @param str
     * @param num
     * @return
     */
    public static String charFront(String str, int num, String ch) {
        String rt = str;
        if (str != null && !"".equals(str)) {
            for (int i = str.length(); i < num; i++) {
                rt = ch + rt;
            }
        }
        return rt;
    }

    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        Character firstChar = str.charAt(0);
        String tail = str.substring(1);
        str = Character.toLowerCase(firstChar) + tail;
        return str;
    }

    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase(String str) {
        Character firstChar = str.charAt(0);
        String tail = str.substring(1);
        str = Character.toUpperCase(firstChar) + tail;
        return str;
    }

    /**
     * 根据指定的key value 判断list中是否存在相同的记录
     * 
     * @param args
     */

    public static boolean listIsExistSameKeyValue(List<Map<String, Object>> maplist, String key, String value) {
        boolean isExist = false;
        for (Map<String, Object> map : maplist) {
            if (map.get(key).equals(value)) {
                isExist = true;
            } else {
                continue;
            }

        }
        return isExist;
    }

    public static String substr(String text, int length) throws UnsupportedEncodingException {
        if (text == null) {
            return null;
        }
        String encode = "GB2312";
        StringBuilder sb = new StringBuilder();
        int currentLength = 0;
        for (char c : text.toCharArray()) {
            currentLength += String.valueOf(c).getBytes(encode).length;
            if (currentLength <= length) {
                sb.append(c);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 })).byteValue();
        _b0 <<= 4;
        byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    public static byte[] HexString2Bytes(String src) {
        src = src.toUpperCase();
        int length = src.length() / 2;
        byte ret[] = new byte[length];
        try {
            byte tmp[] = src.getBytes("GBK");
            for (int i = 0; i < length; i++)
                ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static String toHexString(byte value[])
    {
        String newString = "";
        for(int i = 0; i < value.length; i++)
        {
            byte b = value[i];
            String str = Integer.toHexString(b);
            if(str.length() > 2)
                str = str.substring(str.length() - 2);
            if(str.length() < 2)
                str = "0" + str;
            newString = newString + str;
        }

        return newString.toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(isChinese(","));
    }
}
