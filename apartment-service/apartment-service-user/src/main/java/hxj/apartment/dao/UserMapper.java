package hxj.apartment.dao;

import hxj.apartment.bean.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:Users的Dao
 *****/
public interface UserMapper extends Mapper<User> {

    /***
     * 获取所有系统中有注册日期，且注销日期为空，状态为1（审核通过）的所有住户。
     * @return
     */
    @Select("SELECT id,`name`,`password`,`image`,`birthday`,`nativePlace`,`roomID`,`emergencyContactAddress`,`emergencyContactName`,`emergencyContactPhoneNo`,`emergencyContactRelationship`,`registDate`,`diseases`,`otherDesc`,`checkOutDate`,`status` \n" +
            "FROM `user` \n" +
            "WHERE `status`=1 AND `checkOutDate` is NULL AND `registDate` is NOT NULL GROUP BY roomID ORDER BY roomID;")
    List<User> getAllUsersInSystem();

}
