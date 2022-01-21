package hxj.apartment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.Message;
import hxj.apartment.dao.MessageMapper;
import hxj.apartment.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:Message业务层接口实现类
 *****/
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;


    /**
     * Message条件+分页查询
     *
     * @param message 查询条件
     * @param page    页码
     * @param size    页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Message> findPage(Message message, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(message);
        //执行搜索
        return new PageInfo<Message>(messageMapper.selectByExample(example));
    }

    /**
     * Message分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Message> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<Message>(messageMapper.selectAll());
    }

    /**
     * Message条件查询
     *
     * @param message
     * @return
     */
    @Override
    public List<Message> findList(Message message) {
        //构建查询条件
        Example example = createExample(message);
        //根据构建的条件查询数据
        return messageMapper.selectByExample(example);
    }


    /**
     * Message构建查询对象
     *
     * @param message
     * @return
     */
    public Example createExample(Message message) {
        Example example = new Example(Message.class);
        Example.Criteria criteria = example.createCriteria();
        if (message != null) {
            // 
            if (!StringUtils.isEmpty(message.getId())) {
                criteria.andEqualTo("id", message.getId());
            }
            // 消息标题
            if (!StringUtils.isEmpty(message.getTitle())) {
                criteria.andLike("title", "%" + message.getTitle() + "%");
            }
            // 消息内容
            if (!StringUtils.isEmpty(message.getContent())) {
                criteria.andEqualTo("content", message.getContent());
            }
            // 消息类型
            if (!StringUtils.isEmpty(message.getMessageFor())) {
                criteria.andEqualTo("messageFor", message.getMessageFor());
            }
            // 消息发送者
            if (!StringUtils.isEmpty(message.getMessageFrom())) {
                criteria.andEqualTo("messageFrom", message.getMessageFrom());
            }
            // 过期时间
            if (!StringUtils.isEmpty(message.getFailureTime())) {
                criteria.andEqualTo("failureTime", message.getFailureTime());
            }
            // 发布时间
            if (!StringUtils.isEmpty(message.getPublishTime())) {
                criteria.andEqualTo("publishTime", message.getPublishTime());
            }
        }
        return example;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        messageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Message
     *
     * @param message
     */
    @Override
    public void update(Message message) {
        messageMapper.updateByPrimaryKey(message);
    }

    /**
     * 增加Message
     *
     * @param message
     */
    @Override
    public void add(Message message) {
        messageMapper.insert(message);
    }

    /**
     * 根据ID查询Message
     *
     * @param id
     * @return
     */
    @Override
    public Message findById(Integer id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Message全部数据
     *
     * @return
     */
    @Override
    public List<Message> findAll() {
        return messageMapper.selectAll();
    }
}
