package cn.btttech.util;

public class ArrayUtil {
/*
 * 去掉数组中的空值
 */
	public static String[] escapeBlank(String[] str){
		//用StringBuffer来存放数组中的非空元素，用“;”分隔
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<str.length; i++) {
            if("".equals(str[i])) {
                continue;
            }
            sb.append(str[i]);
            if(i != str.length - 1) {
                sb.append(";");
            }
        }
        //用String的split方法分割，得到数组
        str = sb.toString().split(";");
		return str;
	}
}
