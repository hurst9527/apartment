package hxj.apartment.service;

import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Require;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:Require业务层接口
 *****/
public interface RequireService {

    /***
     * Require多条件分页查询
     * @param require
     * @param page
     * @param size
     * @return
     */
    PageInfo<Require> findPage(Require require, int page, int size);

    /***
     * Require分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Require> findPage(int page, int size);

    /***
     * Require多条件搜索方法
     * @param require
     * @return
     */
    List<Require> findList(Require require);

    /***
     * 删除Require
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Require数据
     * @param require
     */
    void update(Require require);

    /***
     * 新增Require
     * @param require
     */
    void add(Require require);

    /**
     * 根据ID查询Require
     *
     * @param id
     * @return
     */
    Require findById(Integer id);

    /***
     * 查询所有Require
     * @return
     */
    List<Require> findAll();

    /***
     * 获取所有待处理的Require
     * @param userID
     * @param roomID
     * @return
     */
    List<Require> getWaitRequires(Integer userID);

    /***
     * 获取所有处理完成的Require
     * @param userID
     * @param roomID
     * @return
     */
    List<Require> getFinishRequires(Integer userID);
}
