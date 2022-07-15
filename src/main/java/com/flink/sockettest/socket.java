package com.flink.sockettest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class socket {

        public static void main(String args[]) throws Exception {
            //为了简单起见，所有的异常都直接往外抛
            String host = "127.0.0.1";  //要连接的服务端IP地址192.168.110.245
            int port = 20189;   //要连接的服务端对应的监听端口
            //与服务端建立连接
            //建立连接后就可以往服务端写数据了
            while (true) {
                Socket client = new Socket(host, port);
                Writer writer = new OutputStreamWriter(client.getOutputStream(), "GBK");
                //writer.write("你好，服务端。");
                //writer.write("你好 服务端。\n");

                //writer.write("eof\n");
//                writer.write("111111111\n");
                //
                writer.write("{\"messageProtocolVersion\":\"1\",\"messageDeviceTypeId\":1,\"messageProductId\":0,\"messageDeviceDescribe\":\"0\",\"messageEmbeddedSoftwareVersion\":\"0\",\"messageChipVersion\":\"0\",\"messageDeviceSerialId\":\"JAJX01710001C100\",\"messagePackageId\":0,\"messageLoadLength\":0,\"messageNumber\":1,\"messageSplitSerialId\":0,\"verifyCode\":0,\"reserved\":0,\"basicMessageBasicList\":[{\"srcMac\":\"11-22-33-aa-44-55\",\"destMac\":\"10-20-30-40-50-60\",\"srcIp\":\"10.10.10.100\",\"destIp\":\"10.10.10.110\",\"srcPort\":8080,\"destPort\":8090,\"protocolType\":0,\"byteNumber\":-31395,\"packageNumber\":21773,\"verifyMessageBodyType\":1,\"verifyTypeId\":101,\"loadLength\":31,\"verifyFunctionModuleCode\":0}],\"tempVerifyCode\":0,\"messageSplit\":false}\n");
                //{"messageProtocolVersion":"0","messageDeviceTypeId":0,"messageProductId":0,"messageDeviceDescribe":"0","messageEmbeddedSoftwareVersion":"0","messageChipVersion":"0","messageDeviceSerialId":"JAJX01710001C100","messagePackageId":0,"messageLoadLength":0,"messageNumber":1,"messageSplitSerialId":0,"verifyCode":0,"reserved":0,"basicMessageBasicList":[{"srcMac":"11-22-33-aa-44-55","destMac":"10-20-30-40-50-60","srcIp":"10.10.10.100","destIp":"10.10.10.110","srcPort":8080,"destPort":8090,"protocolType":0,"byteNumber":-31395,"packageNumber":21773,"verifyMessageBodyType":1,"verifyTypeId":101,"loadLength":31,"verifyFunctionModuleCode":0}],"tempVerifyCode":0,"messageSplit":false}
                //writer.write("{\"device_id\": \"222222222222\",\"device_ip\": \"10.45.69.3\",\"interface_icon\": \"核心交换\"," +
//                        "\"data_type\": 1," +
//                        "\"time\": 1581927006," +
//                        "\"sip\": \"1.2.170.2\"," +
//                        "\"sipv6\": \"\"," +
//                        "\"smac\": \"00:50:56:9C:18:D4\"," +
//                        "\"sport\": 51140," +
//                        "\"dip\": \"1.2.170.5\"," +
//                        "\"dipv6\": \"\",\n" +
//                        "\"dmac\": \"00:16:31:F9:C0:17\"," +
//                        "\"dport\": 443,\n" +
//                        "\"network_protocol\": 1," +
//                        "\"transport_protocol\": 4," +
//                        "\"session_protocol\": 1," +
//                        "\"app_protocol\": 80," +
//                        "\"sess_id\": \"1111111\"," +
//                        "\"log_type \": 5," +
//                        "\"mail_time\": \"Tue, 22 Oct 2019 16:54:43 +0800\"," +
//                        "\"from\": \"1@1128.com\"," +
//                        "\"to\": \"2@1128.com\"," +
//                        "\"cc\": \"3@1128.com\"," +
//                        "\"subject\": \"subject\"," +
//                        "\"bcc\": \"xxxxxxxxxxx@1128.com\"," +
//                        "\"returnpath\": \"2<2@1128.com>\"," +
//                        "\"received\": \"xxxxxxxxxxxxx\"," +
//                        "\"send_server_domain\": \"xxxx\"," +
//                        "\"send_server_ip\": \"10.10.41.195\"," +
//                        "\"content_encoding\": \"base64\"," +
//                        "\"content_length\": 256," +
//                        "\"mail_size\": 1234," +
                        //"\"file_list\": [“123.exe”]}\n");
                //"srcMac":"11-22-33-aa-44-55",
                //writer.write("{\"userId\":\"1\",\"day\":\"2020-01-05\",\"data\":[{\"package\":\"com.zyd\",\"activetime\":\"2311\"}]}\n");
                Thread.sleep(5000);
                writer.flush();
                writer.close();

                System.out.println("数据发送");
            }
            //写完以后进行读操作
            /*BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
            //设置超时间为10秒
            client.setSoTimeout(10*1000);
            StringBuffer sb = new StringBuffer();
            String temp;
            int index;
            try {
                while ((temp=br.readLine()) != null) {
                    if ((index = temp.indexOf("eof")) != -1) {
                        sb.append(temp.substring(0, index));
                        break;
                    }
                    sb.append(temp);
                }
            } catch (SocketTimeoutException e) {
                System.out.println("数据读取超时。");
            }
            System.out.println("服务端: " + sb);
            writer.close();
            br.close();
            client.close();*/
        }
    }
