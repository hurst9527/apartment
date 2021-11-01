package hxj.apartment.service.impl;
import hxj.apartment.dao.RepairMapper;
import hxj.apartment.bean.Repair;
import hxj.apartment.service.RepairService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Repair业务层接口实现类
 *****/
@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairMapper repairMapper;


    /**
     * Repair条件+分页查询
     * @param repair 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Repair> findPage(Repair repair, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(repair);
        //执行搜索
        return new PageInfo<Repair>(repairMapper.selectByExample(example));
    }

    /**
     * Repair分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Repair> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Repair>(repairMapper.selectAll());
    }

    /**
     * Repair条件查询
     * @param repair
     * @return
     */
    @Override
    public List<Repair> findList(Repair repair){
        //构建查询条件
        Example example = createExample(repair);
        //根据构建的条件查询数据
        return repairMapper.selectByExample(example);
    }


    /**
     * Repair构建查询对象
     * @param repair
     * @return
     */
    public Example createExample(Repair repair){
        Example example=new Example(Repair.class);
        Example.Criteria criteria = example.createCriteria();
        if(repair!=null){
            // 
            if(!StringUtils.isEmpty(repair.getId())){
                    criteria.andEqualTo("id",repair.getId());
            }
            // 房间号
            if(!StringUtils.isEmpty(repair.getRoomID())){
                    criteria.andEqualTo("roomID",repair.getRoomID());
            }
            // 提交时间
            if(!StringUtils.isEmpty(repair.getSubmitTime())){
                    criteria.andEqualTo("submitTime",repair.getSubmitTime());
            }
            // 维修时间
            if(!StringUtils.isEmpty(repair.getFinishTime())){
                    criteria.andEqualTo("finishTime",repair.getFinishTime());
            }
            // 需要报修描述
            if(!StringUtils.isEmpty(repair.getDesc())){
                    criteria.andEqualTo("desc",repair.getDesc());
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
        repairMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Repair
     * @param repair
     */
    @Override
    public void update(Repair repair){
        repairMapper.updateByPrimaryKey(repair);
    }

    /**
     * 增加Repair
     * @param repair
     */
    @Override
    public void add(Repair repair){
        repairMapper.insert(repair);
    }

    /**
     * 根据ID查询Repair
     * @param id
     * @return
     */
    @Override
    public Repair findById(Integer id){
        return  repairMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Repair全部数据
     * @return
     */
    @Override
    public List<Repair> findAll() {
        return repairMapper.selectAll();
    }
}
