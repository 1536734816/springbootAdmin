package com.wh.spring.admin.util;

/**
 * Created by Joy on 2019/10/12.
 */

//import com.sun.xml.internal.ws.client.BindingProviderProperties;
//import com.sun.xml.internal.ws.developer.JAXWSProperties;


//import javax.xml.soap.SOAPHeader;

public class SoapUtils {

//
//    String nameSpace = "";
//    String wsdlUrl = "";
//    String serviceName = "";
//    String portName = "";
//    String responseName = "";
//    String elementName = "";
//    int timeout = 20000;
//
//    /**
//     *
//     * @param nameSpace
//     * @param wsdlUrl
//     * @param serviceName
//     * @param portName
//     * @param element
//     * @param responseName
//     */
//
//    public SoapUtils(String nameSpace, String wsdlUrl,
//                     String serviceName, String portName, String element,
//                     String responseName) {
//        this.nameSpace = nameSpace;
//        this.wsdlUrl = wsdlUrl;
//        this.serviceName = serviceName;
//        this.portName = portName;
//        this.elementName = element;
//        this.responseName = responseName;
//    }
//
//    /**
//     *
//     * @param nameSpace
//     * @param wsdlUrl
//     * @param serviceName
//     * @param portName
//     * @param element
//     * @param responseName
//     * @param timeOut
//     *            毫秒
//     */
//
//    public SoapUtils(String nameSpace, String wsdlUrl,
//                     String serviceName, String portName, String element,
//                     String responseName, int timeOut) {
//        this.nameSpace = nameSpace;
//        this.wsdlUrl = wsdlUrl;
//        this.serviceName = serviceName;
//        this.portName = portName;
//        this.elementName = element;
//        this.responseName = responseName;
//        this.timeout = timeOut;
//    }
//
//    public String sendMessage(HashMap<String, String> inMsg) throws Exception {
//        // 创建URL对象
//        URL url = null;
//        try {
//            url = new URL(wsdlUrl);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "创建URL对象异常";
//        }
//        System.out.println("url " + url);
//        // 创建服务(Service)
//        QName sname = new QName(nameSpace, serviceName);
//        Service service = Service.create(url, sname);
//
//        // 创建Dispatch对象
//        Dispatch<SOAPMessage> dispatch = null;
//        try {
//            dispatch = service.createDispatch(new QName(nameSpace, portName),
//                    SOAPMessage.class, Service.Mode.MESSAGE);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "创建Dispatch对象异常";
//        }
//
//        // 创建SOAPMessage
//        try {
//            SOAPMessage msg = MessageFactory.newInstance(
//                    SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();
//            msg.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
//
//            SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
//
//            // 创建SOAPHeader(不是必需)
//            // SOAPHeader header = envelope.getHeader();
//            // if (header == null)
//            // header = envelope.addHeader();
//            // QName hname = new QName(nameSpace, "username", "nn");
//            // header.addHeaderElement(hname).setValue("huoyangege");
//
//            // 创建SOAPBody
//            SOAPBody body = envelope.getBody();
//            QName ename = new QName(nameSpace, elementName, "q0");
//            SOAPBodyElement ele = body.addBodyElement(ename);
//            // 增加Body元素和值
//            for (Map.Entry<String, String> entry : inMsg.entrySet()) {
//                ele.addChildElement(new QName(nameSpace, entry.getKey()))
//                        .setValue(entry.getValue());
//            }
//
//            // 超时设置
//            dispatch.getRequestContext().put(
//                    BindingProviderProperties.CONNECT_TIMEOUT, timeout);
//            dispatch.getRequestContext().put(JAXWSProperties.REQUEST_TIMEOUT,
//                    timeout);
//            dispatch.getRequestContext().put(JAXWSProperties.HTTP_REQUEST_URL, "http://10.211.55.3/onvif/device_service");
//
//            // 通过Dispatch传递消息,会返回响应消息
//            SOAPMessage response = dispatch.invoke(msg);
//
//            response.writeTo(System.out);
//
//            SOAPBody b = response.getSOAPBody();
//            Iterator<SOAPElement> iterator = b.getChildElements();
//            PrintBody(iterator, "");
//
//            // 响应消息处理,将响应的消息转换为doc对象
//            Document doc = response.getSOAPPart().getEnvelope().getBody()
//                    .extractContentAsDocument();
//
//            String ret = doc.getElementsByTagName(responseName).item(0)
//                    .getTextContent();
//            return ret;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
////    //将响应的SOAPMessage解析成xml的代码片段 response为响应的SOAPMessage
////    Transformer transformer = transformerFactory.newTransformer();
////    Document doc = response.getSOAPPart().getEnvelope().getOwnerDocument();
////    StreamResult streamResult = new StreamResult(output);
////            transformer.transform(new DOMSource(doc),streamResult);
////    result = output.toString();
//
//
//    private static void PrintBody(Iterator<SOAPElement> iterator, String side) {
//        while (iterator.hasNext()) {
//            System.out.println(side + "+++++++++++++");
//            SOAPElement element = (SOAPElement) iterator.next();
//            System.out.println(side+"Local Name : " + element.getLocalName());
//            System.out.println(side+"Node Name  : " + element.getNodeName());
//            System.out.println(side+"Tag Name   : " + element.getTagName());
//            System.out.println(side+"Value      : " + element.getValue());
//            if (null == element.getValue()
//                    && element.getChildElements().hasNext()) {
//                PrintBody(element.getChildElements(), "    " + side );
//            }
//        }
//    }
}

