package com.wh.spring.admin.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class RetUtils {

    /**
     * 成功状态
     */
    public static final int status_success = 200;

    /**
     * 请求失败状态
     */
    public static final int status_failed = 400;

    /**
     * 数据为空状态
     */
    public static final int status_null = 300;

    /**
     * 异常状态
     */
    public static final int status_error = 500;


    /**
     * 没有授权
     */
    public static final int status_no_authorization = 403;


    public static Map ret(int status, Object o) {
        Map<String, Object> t_map = new HashMap<String, Object>();
        t_map.put("status", status);
        t_map.put("ret", o);
        return t_map;
    }

    public static Map retSuccess(Object o) {
        return ret(status_success, o);
    }

    public static Map retError(Object o) {
        return ret(status_error, o);
    }

    public static Map retFailed(Object o) {
        return ret(status_failed, o);
    }

    public static Map retstatNotAuthorization(Object o) {
        return ret(status_no_authorization, o);
    }

    public static Map retNull(Object o) {
        return ret(status_null, o);
    }

    public static void retJsonWithResponse(HttpServletResponse response, Object o) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(o));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public static void retHtmlWithResponse(HttpServletResponse response, Object o) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = null;

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello World!</h1>");
        out.println("</body>");
        out.println("</html>");

    }


}
