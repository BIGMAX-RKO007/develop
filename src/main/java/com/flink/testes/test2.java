package com.flink.testes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
        /*private String whereStrategySql (StrategyListBean strategyListBean){
            StringBuilder str =new StringBuilder();
            String strategyJson = strategyListBean.getStrategyJson();
            if(strategyJson!=null && !strategyJson.equals("")){
                str.append(" and strategy_json =’"+strategyJson+"'");
            }
            String strategyName = strategyListBean.getStrategyName();
            if(strategyName!=null && !strategyName.equals("")){
                str.append(" and strategy_name='"+strategyName+"'");
            }
            String strategyType = strategyListBean.getStrategyType();
            if(strategyType!=null && !strategyType.equals("")){
                str.append(" and strategy_type='"+strategyType+"'");
            }
            String describe = strategyListBean.getDescribe();
            if(describe!=null && !describe.equals("")){
                str.append(" and describe1='"+describe+"'");
            }
            String devName = strategyListBean.getDevName();
            if(devName!=null && !devName.equals("")){
                str.append(" and dev_name='"+devName+"'");
            }
            return str.toString();
        }

        public Map<String, Object> queryStrategy(StrategyListBean strategyListBean, Pageable pageable) {  //  查询所有列表
            //增加查询条件
            String whereSql = whereStrategySql(strategyListBean);
            SqlParameterSource ps=new BeanPropertySqlParameterSource(StrategyListBean.class);//插入数据时 javabean转驼峰
//        namedjdbctemp.update(sql, ps,keyholder);
            String sql  ="select id,strategy_id as strategyId,dev_name as devName,strategy_name as strategyName,strategy_type as strategyType,strategy_json as strategyJson from bus_strategy_list where 1=1 "+whereSql+" limit "+pageable.getPageNumber()*pageable.getPageSize()+","+pageable.getPageSize();
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            List<StrategyListBean> strategyListBeans = namedParameterJdbcTemplate.query(sql, parameters,new BeanPropertyRowMapper<>(StrategyListBean.class));
            Map<String, Object> stringObjectMap = PageUtil.toPage(strategyListBeans, strategyListBeans.size());
            return stringObjectMap;
        }


        Date now = new Date(); // 创建一个Date对象，获取当前时间
        System.out.println(now);
        // 指定格式化格式
        SimpleDateFormat f = new SimpleDateFormat("今天是 " + "yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒");
        System.out.println(f.format(now)); // 将当前时间袼式化为指定的格式
    }*/
    }
}
