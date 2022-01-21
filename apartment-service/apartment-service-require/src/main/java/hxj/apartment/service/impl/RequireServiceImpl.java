package hxj.apartment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Require;
import hxj.apartment.dao.RequireMapper;
import hxj.apartment.service.RequireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:Require业务层接口实现类
 *****/
@Service
public class RequireServiceImpl implements RequireService {

    @Autowired
    private RequireMapper requireMapper;


    /**
     * Require条件+分页查询
     *
     * @param require 查询条件
     * @param page    页码
     * @param size    页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Require> findPage(Require require, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(require);
        //执行搜索
        return new PageInfo<Require>(requireMapper.selectByExample(example));
    }

    /**
     * Require分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Require> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<Require>(requireMapper.selectAll());
    }

    /**
     * Require条件查询
     *
     * @param require
     * @return
     */
    @Override
    public List<Require> findList(Require require) {
        //构建查询条件
        Example example = createExample(require);
        //根据构建的条件查询数据
        return requireMapper.selectByExample(example);
    }


    /**
     * Require构建查询对象
     *
     * @param require
     * @return
     */
    public Example createExample(Require require) {
        Example example = new Example(Require.class);
        Example.Criteria criteria = example.createCriteria();
        if (require != null) {
            // 订单id
            if (!StringUtils.isEmpty(require.getId())) {
                criteria.andEqualTo("id", require.getId());
            }
            // 用户id
            if (!StringUtils.isEmpty(require.getUserID())) {
                criteria.andEqualTo("userID", require.getUserID());
            }
            // 房间号
            if (!StringUtils.isEmpty(require.getRoomID())) {
                criteria.andEqualTo("roomID", require.getRoomID());
            }
            // 提交时间
            if (!StringUtils.isEmpty(require.getSubmitTime())) {
                criteria.andEqualTo("submitTime", require.getSubmitTime());
            }
            // 维修时间
            if (!StringUtils.isEmpty(require.getFinishTime())) {
                criteria.andEqualTo("finishTime", require.getFinishTime());
            }
            // 需要报修描述
            if (!StringUtils.isEmpty(require.getOtherDesc())) {
                criteria.andEqualTo("desc", require.getOtherDesc());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        requireMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Require
     *
     * @param require
     */
    @Override
    public void update(Require require) {
        requireMapper.updateByPrimaryKey(require);
    }

    /**
     * 增加Require
     *
     * @param require
     */
    @Override
    public void add(Require require) {
        requireMapper.insert(require);
    }

    /**
     * 根据ID查询Require
     *
     * @param id
     * @return
     */
    @Override
    public Require findById(Integer id) {
        return requireMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Require全部数据
     *
     * @return
     */
    @Override
    public List<Require> findAll() {
        return requireMapper.selectAll();
    }

    @Override
    public List<Require> getWaitRequires(Integer userID) {
        return requireMapper.getWaitRequire(userID);
    }

    @Override
    public List<Require> getFinishRequires(Integer userID) {
        return requireMapper.getFinishRequire(userID);
    }


}
