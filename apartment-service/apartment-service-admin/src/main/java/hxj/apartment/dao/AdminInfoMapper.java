package hxj.apartment.dao;

import hxj.apartment.bean.AdminInfo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author HXJ
 * @create 2022-01-02 11:05
 */
public interface AdminInfoMapper extends Mapper<AdminInfo> {
    /***
     * 找到所有身份不为system administrator的管理员
     * @return
     */
    @Select("SELECT admin.id,admin.`name`,admin.`password`,admin.image,admin.introduce,admin.`status`,admin.phoneNo,admin.email,admin_grade.identity,position.position FROM admin \n" +
            "\tLEFT JOIN admin_grade\n" +
            "\ton admin.grade = admin_grade.grade\n" +
            "\tLEFT JOIN position \n" +
            "\tON admin.position=position.positionID WHERE admin.grade!=1 and admin.`status`='1'")
    List<AdminInfo> getNomalAdminInfos();
}
