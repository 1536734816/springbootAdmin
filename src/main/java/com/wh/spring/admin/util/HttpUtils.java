package com.wh.spring.admin.util;


import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by devy
 * since 2015/9/22 0022.
 */
public class HttpUtils {
    String targetUrl = "";
    String contentType = "application/json";
    String methodType = "post";
    String jsonString = null;
    String xmlString = null;
    Map<String, String> reqParams = new HashMap<String, String>();
    RequestConfig requestConfig = null;

    public HttpUtils() {
    }

    public static String parseUrl(String urlString, String enc) {
        int questIndex = urlString.indexOf('?');
        if (questIndex == -1) {
            return urlString;
        }
        String url = urlString.substring(0, questIndex);
        return url;
    }

    private static Map<String, String[]> getParamsMap(String queryString, String enc) {
        Map<String, String[]> paramsMap = new HashMap<String, String[]>();
        if (queryString != null && queryString.length() > 0) {
            int ampersandIndex, lastAmpersandIndex = 0;
            String subStr, param, value;
            String[] paramPair, values, newValues;
            do {
                ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;
                if (ampersandIndex > 0) {
                    subStr = queryString.substring(lastAmpersandIndex, ampersandIndex - 1);
                    lastAmpersandIndex = ampersandIndex;
                } else {
                    subStr = queryString.substring(lastAmpersandIndex);
                }
                paramPair = subStr.split("=");
                param = paramPair[0];
                value = paramPair.length == 1 ? "" : paramPair[1];
                try {
                    value = URLDecoder.decode(value, enc);
                } catch (UnsupportedEncodingException ignored) {
                }
                if (paramsMap.containsKey(param)) {
                    values = paramsMap.get(param);
                    int len = values.length;
                    newValues = new String[len + 1];
                    System.arraycopy(values, 0, newValues, 0, len);
                    newValues[len] = value;
                } else {
                    newValues = new String[]{value};
                }
                paramsMap.put(param, newValues);
            } while (ampersandIndex > 0);
        }
        return paramsMap;
    }

    public HttpUtils param(String key, String value) {
        reqParams.put(key, value);
        return this;
    }

    public HttpUtils paramJson(String jsonValue) {
        jsonString = jsonValue;
        return this;
    }

    public HttpUtils paramXml(String xmlValue) {
        xmlString = xmlValue;
        return this;
    }

    public HttpUtils post(String url) {
        methodType = "post";
        targetUrl = url;
        return this;
    }

    public HttpUtils get(String url) {
        methodType = "get";
        targetUrl = url;
        return this;
    }

    public HttpUtils setContentType(String cct) {
        contentType = cct;
        return this;
    }

    public HttpUtils enableCookie() {
        if (requestConfig == null) {
            requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        }
        return this;
    }

    HttpClient createHttpClientWithSSL() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {//信任所有
                return true;
            }
        }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        return httpclient;
    }

    HttpClient createHttpClientNoSSL() {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        return httpClient;
    }

    public void setParams(HttpEntityEnclosingRequestBase request) throws UnsupportedEncodingException {
        if (request.getMethod().equalsIgnoreCase("POST")) {
            if (contentType.equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
                if (jsonString != null && jsonString.length() > 0) {
                    request.setEntity(new StringEntity(jsonString, StandardCharsets.UTF_8));
                }
                String params = buildQueryString();
                if (params != null && params.length() > 0) {
                    request.setEntity(new StringEntity(params, StandardCharsets.UTF_8));
                }
            } else if (contentType.equalsIgnoreCase(MediaType.APPLICATION_XML_VALUE)) {
                StringEntity stringEntity = new StringEntity(xmlString, StandardCharsets.UTF_8);
                stringEntity.setContentEncoding("UTF-8");
                request.setEntity(stringEntity);

                String params = buildQueryString();
                if (params != null && params.length() > 0) {
                    request.setEntity(new StringEntity(params, StandardCharsets.UTF_8));
                }
            } else {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (String key : reqParams.keySet()) {
                    String value = reqParams.get(key);
                    params.add(new BasicNameValuePair(key, value));
                }

                UrlEncodedFormEntity httpEntity = new UrlEncodedFormEntity(params, Consts.UTF_8);
                request.setEntity(httpEntity);
            }
        } else if (request.getMethod().equalsIgnoreCase("GET")) {
            String params = buildQueryString();
            request.setEntity(new StringEntity(params, StandardCharsets.UTF_8));
        }
    }

    String buildQueryString() {
        if (reqParams.size() < 1) {
            return "";
        } else {
            QueryString qs = new QueryString();
            for (String key : reqParams.keySet()) {
                String value = reqParams.get(key);
                qs.add(key, value);
            }
            return qs.toString();
        }
    }

    String buildQueryString(Map<String, String[]> params) {
        if (reqParams.size() < 1) {
            return "";
        } else {
            QueryString qs = new QueryString();
            for (String key : reqParams.keySet()) {
                String value = reqParams.get(key);
                qs.add(key, value);
            }
            if (params != null) {
                for (String key : params.keySet()) {
                    String[] values = params.get(key);
                    for (String v : values) {
                        qs.add(key, v);
                    }
                }
            }
            return qs.toString();
        }
    }

    boolean isSSL() {
        String preProxy = targetUrl.substring(0, 6);
        return preProxy.equalsIgnoreCase("https:");
    }

    HttpClient createHttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return isSSL() ? createHttpClientWithSSL() : createHttpClientNoSSL();
    }

    public HttpResult execute() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpClient httpClient = createHttpClient();
        HttpResponse response = null;
        if (methodType.equalsIgnoreCase("post")) {
            HttpPost httpPost = new HttpPost(targetUrl);
            setParams(httpPost);
            httpPost.setHeader("Content-Type", this.contentType);
            httpPost.setHeader("Accept-Language", "zh-cn");
            response = httpClient.execute(httpPost);
        } else {
            Map<String, String[]> params = getParamsMap(targetUrl, "utf-8");
            String url = targetUrl;
            if (params.size() > 0) {
                url = parseUrl(targetUrl, "utf-8");
            }
            String queryString = buildQueryString(params);
            if (queryString.length() > 0) {
                url = targetUrl + "?" + queryString;
            }
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Accept-Language", "zh-cn");
            response = httpClient.execute(httpGet);
        }
        HttpResult httpResult = new HttpResult();
        httpResult.statusCode = response.getStatusLine().getStatusCode();
        httpResult.strResult = EntityUtils.toString(response.getEntity());
        return httpResult;
    }

    static class QueryString {
        private String query = "";

        public QueryString() {
        }

        public QueryString(String name, String value) {
            encode(name, value);
        }

        public QueryString add(String name, String value) {
            if (query.length() < 1) {
                encode(name, value);
                return this;
            } else {
                query += "&";
                encode(name, value);
                return this;
            }
        }

        private void encode(String name, String value) {
            try {
                query += URLEncoder.encode(name, "UTF-8");
                query += "=";
                query += URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException("Broken VM does not support UTF-8");
            }
        }

        public String getQuery() {
            return query;
        }

        @Override
        public String toString() {
            return getQuery();
        }

    }

    public static class HttpResult {
        public int statusCode;
        public String strResult;
    }
}
