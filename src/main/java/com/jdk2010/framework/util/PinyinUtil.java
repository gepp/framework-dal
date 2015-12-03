package com.jdk2010.framework.util;

public class PinyinUtil
{
	private static int BEGIN = 45217;
	
	private static int END = 63486;
	
	private static char[] chartable =
	{ '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', };
	
	private static int[] table = new int[27];
	
	private static char[] initialtable =
	{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 't', 't', 'w', 'x', 'y', 'z', };
	
	// 初始化
	static
	{
		for (int i = 0; i < 26; i++)
		{
			table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。
		}
		table[26] = END;// 区间表结尾
	}
	
	/**
	 * 获取字符串第一个字符首字母
	 * @param str
	 * @return
	 */
	public static String firstLetterOfString(String str)
	{
		 
		String first = str.substring(0,1);
		return cn2py(first).toUpperCase();
	}
	
	/**
	 * 获取字符串拼音首字母
	 * @param SourceStr
	 * @return
	 */
	public static String cn2py(String SourceStr)
	{
		String Result = "";
		int StrLength = SourceStr.length();
		int i;
		try
		{
			for (i = 0; i < StrLength; i++)
			{
				Result += Char2Initial(SourceStr.charAt(i));
			}
		}
		catch (Exception e)
		{
			Result = "";
		}
		return Result;
	}
	
	private static char Char2Initial(char ch)
	{
		if (ch >= 'a' && ch <= 'z')
			return (char) (ch - 'a' + 'A');
		if (ch >= 'A' && ch <= 'Z')
			return ch;
		int gb = gbValue(ch);// 汉字转换首字母
		if ((gb < BEGIN) || (gb > END))// 在码表区间之前，直接返回
			return ch;
		int i;
		for (i = 0; i < 26; i++)
		{
			if ((gb >= table[i]) && (gb < table[i + 1]))
				break;
		}
		
		if (gb == END)
		{
			i = 25;
		}
		return initialtable[i]; // 在码表区间中，返回首字母
	}
	
	private static int gbValue(char ch)
	{
		String str = new String();
		str += ch;
		try
		{
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2)
				return 0;
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		}
		catch (Exception e)
		{
			return 0;
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		System.out.println(cn2py("唐山重视发展IT行业，大多数外企，如，IBM等进驻山城"));
	}
	
}