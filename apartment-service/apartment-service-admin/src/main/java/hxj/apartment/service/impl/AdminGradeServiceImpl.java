package hxj.apartment.service.impl;
import hxj.apartment.dao.AdminGradeMapper;
import hxj.apartment.bean.AdminGrade;
import hxj.apartment.service.AdminGradeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:AdminGrade业务层接口实现类
 *****/
@Service
public class AdminGradeServiceImpl implements AdminGradeService {

    @Autowired
    private AdminGradeMapper adminGradeMapper;


    /**
     * AdminGrade条件+分页查询
     * @param adminGrade 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<AdminGrade> findPage(AdminGrade adminGrade, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(adminGrade);
        //执行搜索
        return new PageInfo<AdminGrade>(adminGradeMapper.selectByExample(example));
    }

    /**
     * AdminGrade分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<AdminGrade> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<AdminGrade>(adminGradeMapper.selectAll());
    }

    /**
     * AdminGrade条件查询
     * @param adminGrade
     * @return
     */
    @Override
    public List<AdminGrade> findList(AdminGrade adminGrade){
        //构建查询条件
        Example example = createExample(adminGrade);
        //根据构建的条件查询数据
        return adminGradeMapper.selectByExample(example);
    }


    /**
     * AdminGrade构建查询对象
     * @param adminGrade
     * @return
     */
    public Example createExample(AdminGrade adminGrade){
        Example example=new Example(AdminGrade.class);
        Example.Criteria criteria = example.createCriteria();
        if(adminGrade!=null){
            // 
            if(!StringUtils.isEmpty(adminGrade.getId())){
                    criteria.andEqualTo("id",adminGrade.getId());
            }
            // 身份
            if(!StringUtils.isEmpty(adminGrade.getIdentity())){
                    criteria.andEqualTo("identity",adminGrade.getIdentity());
            }
            // 等级
            if(!StringUtils.isEmpty(adminGrade.getGrade())){
                    criteria.andEqualTo("grade",adminGrade.getGrade());
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
        adminGradeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改AdminGrade
     * @param adminGrade
     */
    @Override
    public void update(AdminGrade adminGrade){
        adminGradeMapper.updateByPrimaryKey(adminGrade);
    }

    /**
     * 增加AdminGrade
     * @param adminGrade
     */
    @Override
    public void add(AdminGrade adminGrade){
        adminGradeMapper.insert(adminGrade);
    }

    /**
     * 根据ID查询AdminGrade
     * @param id
     * @return
     */
    @Override
    public AdminGrade findById(Integer id){
        return  adminGradeMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询AdminGrade全部数据
     * @return
     */
    @Override
    public List<AdminGrade> findAll() {
        return adminGradeMapper.selectAll();
    }
}
