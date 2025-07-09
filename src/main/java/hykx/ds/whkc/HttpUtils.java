package hykx.ds.whkc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * 通用HTTP发送工具类
 */
public class HttpUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    private static RestTemplate restTemplate;

    static {
        // 初始化RestTemplate并配置
        restTemplate = new RestTemplate();
        // 设置默认字符集为UTF-8
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        // 配置超时时间
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000); // 连接超时时间5秒
        requestFactory.setReadTimeout(600000);    // 读取超时时间600秒
        restTemplate.setRequestFactory(requestFactory);

        // 信任所有HTTPS证书
        trustAllHttpsCertificates();
        HostnameVerifier hv = (urlHostName, session) -> true;
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url 发送请求的URL
     * @return 响应结果
     */
    public static String sendGet(String url) {
        return sendGet(url, null);
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url 发送请求的URL
     * @param params 请求参数
     * @return 响应结果
     */
    public static String sendGet(String url, Map<String, String> params) {
        log.info("sendGet - {}", url);

        // 构建带参数的URL
        if (params != null && !params.isEmpty()) {
            StringBuilder paramBuilder = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paramBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            String paramStr = paramBuilder.toString();
            if (paramStr.endsWith("&")) {
                paramStr = paramStr.substring(0, paramStr.length() - 1);
            }
            url = url + "?" + paramStr;
        }

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String result = response.getBody();
            log.info("recv - {}", result);
            return result;
        } catch (RestClientException e) {
            log.error("调用HttpUtils.sendGet RestClientException, url=" + url, e);
            return null;
        } catch (Exception e) {
            log.error("调用HttpUtils.sendGet Exception, url=" + url, e);
            return null;
        }
    }

    /**
     * 向指定URL发送POST方法的请求，使用JSON格式
     *
     * @param url 发送请求的URL
     * @param json 请求的JSON数据
     * @return 响应结果
     */
    public static String sendPostJson(String url, String json) {
        log.info("sendPostJson - {}, json={}", url, json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            String result = response.getBody();
            log.info("recv - {}", result);
            return result;
        } catch (RestClientException e) {
            log.error("调用HttpUtils.sendPostJson RestClientException, url=" + url + ", json=" + json, e);
            return null;
        } catch (Exception e) {
            log.error("调用HttpUtils.sendPostJson Exception, url=" + url + ", json=" + json, e);
            return null;
        }
    }

    /**
     * 向指定URL发送POST方法的请求，使用表单格式
     *
     * @param url 发送请求的URL
     * @param params 请求参数
     * @return 响应结果
     */
    public static String sendPostForm(String url, MultiValueMap<String, String> params) {
        log.info("sendPostForm - {}, params={}", url, params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            String result = response.getBody();
            log.info("recv - {}", result);
            return result;
        } catch (RestClientException e) {
            log.error("调用HttpUtils.sendPostForm RestClientException, url=" + url + ", params=" + params, e);
            return null;
        } catch (Exception e) {
            log.error("调用HttpUtils.sendPostForm Exception, url=" + url + ", params=" + params, e);
            return null;
        }
    }

    /**
     * 信任所有HTTPS证书
     */
    private static void trustAllHttpsCertificates() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[1];
            trustAllCerts[0] = new TrustAllManager();
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            log.error("信任HTTPS证书失败", e);
        }
    }

    /**
     * 信任所有证书的管理器
     */
    private static class TrustAllManager implements X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType) {
            // 信任所有证书
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType) {
            // 信任所有证书
        }
    }
}