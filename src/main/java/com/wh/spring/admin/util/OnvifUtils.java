package com.wh.spring.admin.util;

import lombok.Getter;
import lombok.Setter;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joy on 2019/10/15.
 */
public class OnvifUtils {

    @Getter
    @Setter
    public String actionURL;

    //将xml使用dom4j进行解析。传入参数为root元素，返回的类型为Map<String,List<Object>>，利用递归调用
    public static Map<String, List<Object>> xmlToMap(Element root) {
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        if (root == null) {
            return map;
        }
        List<Element> elements = root.elements();
        if (elements.size() > 0) {
            for (Element e : elements) {
                String key = e.getName();
                if (e.elements().size() > 0) {
                    if (map.containsKey(key)) {
                        map.get(key).add(xmlToMap(e));
                    } else {
                        List<Object> list = new ArrayList();
                        list.add(xmlToMap(e));
                        map.put(key, list);
                    }
                } else {
                    if (map.containsKey(key)) {
                        map.get(key).add(e.getData());
                    } else {
                        List<Object> list = new ArrayList();
                        list.add(e.getData());
                        map.put(key, list);
                    }

                }
            }
        } else {
            String key = root.getName();
            if (map.containsKey(key)) {
                map.get(key).add(root.getData());
            } else {
                List<Object> list = new ArrayList();
                list.add(root.getData());
                map.put(key, list);
            }
        }
        return map;
    }

    public Map<String, List<Object>> getDeviceInfomation() {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tds=\"http://www.onvif.org/ver10/device/wsdl\" xmlns:tt=\"http://www.onvif.org/ver10/schema\">\n" +
                "  <soap:Body>\n" +
                "    <tds:GetDeviceInformation />\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>");
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.post(actionURL)
                .paramXml(sb.toString())
                .setContentType(MediaType.APPLICATION_XML_VALUE);
        try {
            HttpUtils.HttpResult result = httpUtils.execute();
            if (result.statusCode != 200 || result.strResult == null)
                return null;
            Document doc = DocumentHelper.parseText(result.strResult); // 将字符串转为XML
            Element root = doc.getRootElement(); // 获取根节点
            Map<String, List<Object>> map = xmlToMap(root);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
