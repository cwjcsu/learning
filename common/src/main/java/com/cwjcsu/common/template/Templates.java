/*$$
 * author                          date   comment
 * cwjcsu@gmail.com       2014/8/20  Created
 */
package com.cwjcsu.common.template;

import com.cwjcsu.common.Dict;
import com.cwjcsu.common.DictImpl;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author atlas
 */
public class Templates {

    /**
     * 获取形如"template0{param1}-{param2}"中用"{}"包裹的模版变量param1、param2名称，注意，template里面不能包含孤立的"{"或"}"，即解析"template0{abc"会抛出异常。
     * 另外两个模版变量不能相连，即"template{param1}{param2}"不支持
     *
     * @param template
     * @return
     */
    public static Parts paraseTemplate(String template) {
        char s = '{';
        char e = '}';
        Parts vars = new Parts(template);
        //1变量开始，-1文本开始，0未知
        int state = 0;
        StringBuilder sb = null;
        for (int i = 0; i < template.length(); i++) {
            char c = template.charAt(i);
            if (c == s) {
                if (state == -1) {
                    vars.addPart(new Part(sb.toString(), false));
                    sb = null;
                }
                state = 1;
                sb = new StringBuilder();
                continue;
            } else if (c == e) {
                if (state != 1) {
                    throw new IllegalArgumentException("invalid template,no starting delimiter '{' found:" + template);
                }
                if (sb != null) {
                    vars.addPart(new Part(sb.toString(), true));
                    sb = null;
                }
                state = 0;
                continue;
            } else {
                if (state == 0) {
                    state = -1;
                    sb = new StringBuilder();
                }
            }
            sb.append(c);
        }
        if (state == 1) {
            throw new IllegalArgumentException("invalid template,no stopping delimiter '}' found:" + template);
        } else if (state == -1) {
            if (sb != null) {
                vars.addPart(new Part(sb.toString(), false));
            }
        }
        return vars;
    }

    /**
     * 用字符串去匹配模版template，如果匹配，则以Map的方式返回匹配后的键值对，如果没有匹配到则返回null。如果template格式不正确，会抛出异常
     *
     * @param s
     * @param template
     * @return
     * @see #paraseTemplate(String)
     */
    public static Map<String, String> matchTemplate(String s, String template) {
        return matchTemplate(s, paraseTemplate(template));
    }

    /**
     * 判断字符串s是否匹配parts，如果匹配，则以Map的方式返回匹配后的键值对，如果没有匹配到则返回null，如果parts不是template时，当s匹配parts所表示的字符串，则返回empty Map，不匹配是返回null
     *
     * @param s
     * @param parts
     * @return
     */
    public static Map<String, String> matchTemplate(String s, Parts parts) {
        Map<String, String> kv = new LinkedHashMap<String, String>();
        for (int a = 0; a < parts.getParts().size(); a++) {
            Part p = parts.getParts().get(a);
            if (!p.isVariable()) {//文本
                int i = 0;
                for (int j = 0; j < p.getPart().length(); j++) {
                    if (i >= s.length()) {
                        //not match
                        return null;
                    }
                    if (!(s.charAt(i++) == p.getPart().charAt(j))) {
                        //not match
                        return null;
                    }
                }
                s = s.substring(i);
                continue;
            }
            Part np = a < parts.getParts().size() - 1 ? parts.getParts().get(a + 1) : null;
            if (np == null) {//the last
                kv.put(p.getPart(), s);
                s = "";
            } else {//np must not be a var part
                int index = s.indexOf(np.getPart());
                if (index < 0) {
                    //not match
                    return null;
                }
                kv.put(p.getPart(), s.substring(0, index));
                s = s.substring(index);
            }
        }
        if (s.length() > 0) {// s 还有剩余，模版已经匹配完毕
            return null;
        }
        return kv;
    }


    /**
     * 根据模版参数和模版生成字符串，如果模版非法或者有遗漏的参数（not null）则抛出异常
     *
     * @param templateParameters
     * @param template
     * @return
     */
    public static String generateByTemplate(Map templateParameters, String template) {
        Parts parts = paraseTemplate(template);
        return generateByTemplate(templateParameters, parts);
    }

    /**
     * @param templateParameters
     * @param parts
     * @return
     * @see #generateByTemplate
     */
    public static String generateByTemplate(Map templateParameters, Parts parts) {
        StringBuilder sb = new StringBuilder();
        Dict dict = new DictImpl(templateParameters);
        for (Part part : parts.getParts()) {
            if (part.isVariable()) {
                String para = dict.getAsString(part.getPart());
                if (para == null) {
                    throw new IllegalArgumentException("template parameter " + part.getPart() + " not exists.");
                }
                sb.append(para);
            } else {
                sb.append(part.getPart());
            }
        }
        return sb.toString();
    }

}
