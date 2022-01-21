package hxj.apartment.dao;

import hxj.apartment.bean.Require;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:Require的Dao
 *****/
public interface RequireMapper extends Mapper<Require> {

    @Select("select id,userID,roomID,phoneNo,submitTime,finishTime,otherDesc ,image" +
            " from UserRequire where userID = #{userID} and finishTime is null")
    List<Require> getWaitRequire(@Param("userID") Integer userID);

    @Select("select id,userID,roomID,phoneNo,submitTime,finishTime,otherDesc ,image" +
            " from UserRequire where userID = #{userID} and finishTime is not null")
    List<Require> getFinishRequire(@Param("userID") Integer userID);
}
