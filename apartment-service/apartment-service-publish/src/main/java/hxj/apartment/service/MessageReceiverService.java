package hxj.apartment.service;


import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.MessageReceiver;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:MessageReceiver业务层接口
 *****/
public interface MessageReceiverService {

    /***
     * MessageReceiver多条件分页查询
     * @param messageReceiver
     * @param page
     * @param size
     * @return
     */
    PageInfo<MessageReceiver> findPage(MessageReceiver messageReceiver, int page, int size);

    /***
     * MessageReceiver分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<MessageReceiver> findPage(int page, int size);

    /***
     * MessageReceiver多条件搜索方法
     * @param messageReceiver
     * @return
     */
    List<MessageReceiver> findList(MessageReceiver messageReceiver);

    /***
     * 删除MessageReceiver
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改MessageReceiver数据
     * @param messageReceiver
     */
    void update(MessageReceiver messageReceiver);

    /***
     * 新增MessageReceiver
     * @param messageReceiver
     */
    void add(MessageReceiver messageReceiver);

    /**
     * 根据ID查询MessageReceiver
     *
     * @param id
     * @return
     */
    MessageReceiver findById(Integer id);

    /***
     * 查询所有MessageReceiver
     * @return
     */
    List<MessageReceiver> findAll();
}
