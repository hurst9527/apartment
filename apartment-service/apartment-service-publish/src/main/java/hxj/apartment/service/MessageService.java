package hxj.apartment.service;


import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Message;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:Message业务层接口
 *****/
public interface MessageService {

    /***
     * Message多条件分页查询
     * @param message
     * @param page
     * @param size
     * @return
     */
    PageInfo<Message> findPage(Message message, int page, int size);

    /***
     * Message分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Message> findPage(int page, int size);

    /***
     * Message多条件搜索方法
     * @param message
     * @return
     */
    List<Message> findList(Message message);

    /***
     * 删除Message
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Message数据
     * @param message
     */
    void update(Message message);

    /***
     * 新增Message
     * @param message
     */
    void add(Message message);

    /**
     * 根据ID查询Message
     *
     * @param id
     * @return
     */
    Message findById(Integer id);

    /***
     * 查询所有Message
     * @return
     */
    List<Message> findAll();
}
