package hxj.apartment.dao;
import hxj.apartment.bean.Admin;
import hxj.apartment.bean.AdminInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:Admin的Dao
 *****/
public interface AdminMapper extends Mapper<Admin> {

    /**
     * 根据在职状态查找员工
     * @param status
     * @return
     */
    @Select("SELECT\n" +
            "\ta.`id`,\n" +
            "\ta.`name`,\n" +
            "\ta.`image`,\n" +
            "\tag.`identity`,\n" +
            "\ta.`introduce`,\n" +
            "\tp.`position` ,\n" +
            "\ta.`status`\n" +
            "FROM\n" +
            "\t`admin`AS a\n" +
            "\tLEFT JOIN `admin_grade` AS ag ON ag.`grade` = a.`identity`\n" +
            "\tLEFT JOIN `position` AS p ON a.`position` = p.`id` \n" +
            "WHERE\n" +
            "\ta.`status` = #{status}")
    List<AdminInfo> queryAdmin(@Param("status") String status);

}
