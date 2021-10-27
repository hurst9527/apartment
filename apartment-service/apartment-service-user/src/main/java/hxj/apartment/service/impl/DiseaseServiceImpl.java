package hxj.apartment.service.impl;
import hxj.apartment.dao.DiseaseMapper;
import hxj.apartment.bean.Disease;
import hxj.apartment.service.DiseaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:HXJ
 * @Description:Disease业务层接口实现类
 *****/
@Service
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    private DiseaseMapper diseaseMapper;


    /**
     * Disease条件+分页查询
     * @param disease 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Disease> findPage(Disease disease, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(disease);
        //执行搜索
        return new PageInfo<Disease>(diseaseMapper.selectByExample(example));
    }

    /**
     * Disease分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Disease> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Disease>(diseaseMapper.selectAll());
    }

    /**
     * Disease条件查询
     * @param disease
     * @return
     */
    @Override
    public List<Disease> findList(Disease disease){
        //构建查询条件
        Example example = createExample(disease);
        //根据构建的条件查询数据
        return diseaseMapper.selectByExample(example);
    }


    /**
     * Disease构建查询对象
     * @param disease
     * @return
     */
    public Example createExample(Disease disease){
        Example example=new Example(Disease.class);
        Example.Criteria criteria = example.createCriteria();
        if(disease!=null){
            // 
            if(!StringUtils.isEmpty(disease.getId())){
                    criteria.andEqualTo("id",disease.getId());
            }
            // 疾病名称
            if(!StringUtils.isEmpty(disease.getDisease())){
                    criteria.andEqualTo("disease",disease.getDisease());
            }
            // 发病描述
            if(!StringUtils.isEmpty(disease.getDesc())){
                    criteria.andEqualTo("desc",disease.getDesc());
            }
            // 救治药物
            if(!StringUtils.isEmpty(disease.getTherapeuticDrugs())){
                    criteria.andEqualTo("therapeuticDrugs",disease.getTherapeuticDrugs());
            }
            // 禁忌
            if(!StringUtils.isEmpty(disease.getTaboo())){
                    criteria.andEqualTo("taboo",disease.getTaboo());
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
        diseaseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Disease
     * @param disease
     */
    @Override
    public void update(Disease disease){
        diseaseMapper.updateByPrimaryKey(disease);
    }

    /**
     * 增加Disease
     * @param disease
     */
    @Override
    public void add(Disease disease){
        diseaseMapper.insert(disease);
    }

    /**
     * 根据ID查询Disease
     * @param id
     * @return
     */
    @Override
    public Disease findById(Integer id){
        return  diseaseMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Disease全部数据
     * @return
     */
    @Override
    public List<Disease> findAll() {
        return diseaseMapper.selectAll();
    }
}
