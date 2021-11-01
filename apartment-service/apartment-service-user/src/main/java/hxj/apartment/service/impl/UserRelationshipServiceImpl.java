package hxj.apartment.service.impl;
import hxj.apartment.dao.UserRelationshipMapper;
import hxj.apartment.bean.UserRelationship;
import hxj.apartment.service.UserRelationshipService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:UserRelationships业务层接口实现类
 *****/
@Service
public class UserRelationshipServiceImpl implements UserRelationshipService {

    @Autowired
    private UserRelationshipMapper userRelationshipMapper;


    /**
     * UserRelationships条件+分页查询
     * @param userRelationship 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<UserRelationship> findPage(UserRelationship userRelationship, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(userRelationship);
        //执行搜索
        return new PageInfo<UserRelationship>(userRelationshipMapper.selectByExample(example));
    }

    /**
     * UserRelationships分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<UserRelationship> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<UserRelationship>(userRelationshipMapper.selectAll());
    }

    /**
     * UserRelationships条件查询
     * @param userRelationship
     * @return
     */
    @Override
    public List<UserRelationship> findList(UserRelationship userRelationship){
        //构建查询条件
        Example example = createExample(userRelationship);
        //根据构建的条件查询数据
        return userRelationshipMapper.selectByExample(example);
    }


    /**
     * UserRelationships构建查询对象
     * @param userRelationship
     * @return
     */
    public Example createExample(UserRelationship userRelationship){
        Example example=new Example(UserRelationship.class);
        Example.Criteria criteria = example.createCriteria();
        if(userRelationship !=null){
            // 
            if(!StringUtils.isEmpty(userRelationship.getId())){
                    criteria.andEqualTo("id", userRelationship.getId());
            }
            // 关系名
            if(!StringUtils.isEmpty(userRelationship.getRelationshipName())){
                    criteria.andEqualTo("relationshipName", userRelationship.getRelationshipName());
            }
            // 关系等级 1：直系亲属 2：旁系  3：无血缘关系
            if(!StringUtils.isEmpty(userRelationship.getGrade())){
                    criteria.andEqualTo("grade", userRelationship.getGrade());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        userRelationshipMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改UserRelationships
     * @param userRelationship
     */
    @Override
    public void update(UserRelationship userRelationship){
        userRelationshipMapper.updateByPrimaryKey(userRelationship);
    }

    /**
     * 增加UserRelationships
     * @param userRelationship
     */
    @Override
    public void add(UserRelationship userRelationship){
        userRelationshipMapper.insert(userRelationship);
    }

    /**
     * 根据ID查询UserRelationships
     * @param id
     * @return
     */
    @Override
    public UserRelationship findById(Integer id){
        return  userRelationshipMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询UserRelationships全部数据
     * @return
     */
    @Override
    public List<UserRelationship> findAll() {
        return userRelationshipMapper.selectAll();
    }
}
