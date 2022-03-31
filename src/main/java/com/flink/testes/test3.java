package com.flink.testes;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class test3 {
    public static void main(String[] args) {
        String url="http://10.10.41.207:8181/restconf/operations/sal-strategy:set-remote-respond-host-template";
        Map<String,Object> map =new HashMap<>();
        Map<String,Object> input=new HashMap<>();
        /*map.put("device_serial_id",resources.getDeviceId());
        map.put("respond-vxlan-index",resources.getIndexId());
        map.put("src-mac",resources.getSourceMac());
        map.put("dst-mac",resources.getAimMac());
        map.put("network-port",resources.getPhysicalPort());
        map.put("src-ip",resources.getSourceIp());
        map.put("src-port",resources.getSourcePort());
        map.put("dest-ip",resources.getAimIp());
        map.put("dest-port",resources.getAimPort());
        input.put("input",map);*/
        /*HttpClientContext httpClientContext = HttpClientUtils.addUserOAuth("admin", "admin");
        HttpClientResult httpClientResult = HttpClientUtils.doPost(url, input, false,httpClientContext);
        String getContent = httpClientResult.getContent();
        Map<String,Map<String,Object>> output= JSON.parseObject(getContent,Map.class);
        Map<String,Object>outvalue=output.get("output");
        if(outvalue.get("code").equals(0)){
            return busVxlanMapper.toDto(busVxlanRepository.save(resources));*/

    }
}
