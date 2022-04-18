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
            String host = "10.10.41.242";  //要连接的服务端IP地址
            int port = 9990;   //要连接的服务端对应的监听端口
            //与服务端建立连接
            Socket client = new Socket(host, port);
            //建立连接后就可以往服务端写数据了
            Writer writer = new OutputStreamWriter(client.getOutputStream(), "GBK");
            while (true) {
                //writer.write("你好，服务端。");
                //writer.write("eof\n");
//                writer.write("111111111\n");
                writer.write("{\"messageProtocolVersion\":\"1\",\"messageDeviceTypeId\":1,\"messageProductId\":0,\"messageDeviceDescribe\":\"0\",\"messageEmbeddedSoftwareVersion\":\"0\",\"messageChipVersion\":\"0\",\"messageDeviceSerialId\":\"JAJX01710001C100\",\"messagePackageId\":0,\"messageLoadLength\":0,\"messageNumber\":1,\"messageSplitSerialId\":0,\"verifyCode\":0,\"reserved\":0,\"basicMessageBasicList\":[{\"srcMac\":\"11-22-33-aa-44-55\",\"destMac\":\"10-20-30-40-50-60\",\"srcIp\":\"10.10.10.100\",\"destIp\":\"10.10.10.110\",\"srcPort\":8080,\"destPort\":8090,\"protocolType\":0,\"byteNumber\":-31395,\"packageNumber\":21773,\"verifyMessageBodyType\":1,\"verifyTypeId\":101,\"loadLength\":31,\"verifyFunctionModuleCode\":0}],\"tempVerifyCode\":0,\"messageSplit\":false}\n");
                //{"messageProtocolVersion":"0","messageDeviceTypeId":0,"messageProductId":0,"messageDeviceDescribe":"0","messageEmbeddedSoftwareVersion":"0","messageChipVersion":"0","messageDeviceSerialId":"JAJX01710001C100","messagePackageId":0,"messageLoadLength":0,"messageNumber":1,"messageSplitSerialId":0,"verifyCode":0,"reserved":0,"basicMessageBasicList":[{"srcMac":"11-22-33-aa-44-55","destMac":"10-20-30-40-50-60","srcIp":"10.10.10.100","destIp":"10.10.10.110","srcPort":8080,"destPort":8090,"protocolType":0,"byteNumber":-31395,"packageNumber":21773,"verifyMessageBodyType":1,"verifyTypeId":101,"loadLength":31,"verifyFunctionModuleCode":0}],"tempVerifyCode":0,"messageSplit":false}
                //writer.write("{\"messageProtocolVersion\":0\"\",\"messageDeviceTypeId\":0,\"messageProductId\":0,\"messageDeviceDescribe\":\"\",\"messageEmbeddedSoftwareVersion\":\"\",\"messageChipVersion\":\"\",\"messageDeviceSerialId\":\"JAJX01710001C100\",\"messagePackageId\":0,\"messageLoadLength\":0,\"messageNumber\":1,\"messageSplitSerialId\":0,\"verifyCode\":0,\"reserved\":0,\"basicMessageBasicList\":[{\"destMac\":\"10-20-30-40-50-60\",\"srcIp\":\"10.10.10.100\",\"destIp\":\"10.10.10.110\",\"srcPort\":8080,\"destPort\":8090,\"protocolType\":0,\"byteNumber\":-31395,\"packageNumber\":21773,\"verifyMessageBodyType\":1,\"verifyTypeId\":101,\"loadLength\":31,\"verifyFunctionModuleCode\":0}],\"tempVerifyCode\":0,\"messageSplit\":false}\n");
                //"srcMac":"11-22-33-aa-44-55",
                //writer.write("{\"userId\":\"1\",\"day\":\"2020-01-05\",\"data\":[{\"package\":\"com.zyd\",\"activetime\":\"2311\"}]}\n");
                Thread.sleep(5000);
                writer.flush();
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
