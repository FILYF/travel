package com.itcast.ssm.dao;

import com.itcst.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{orderId})")
    List<Traveller> findByOrdersId(String orderId) throws Exception;
}
