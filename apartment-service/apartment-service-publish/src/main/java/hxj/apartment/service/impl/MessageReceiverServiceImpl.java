package hxj.apartment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hxj.apartment.bean.MessageReceiver;
import hxj.apartment.dao.MessageReceiverMapper;
import hxj.apartment.service.MessageReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:HXJ
 * @Description:MessageReceiver业务层接口实现类
 *****/
@Service
public class MessageReceiverServiceImpl implements MessageReceiverService {

    @Autowired
    private MessageReceiverMapper messageReceiverMapper;


    /**
     * MessageReceiver条件+分页查询
     *
     * @param messageReceiver 查询条件
     * @param page            页码
     * @param size            页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<MessageReceiver> findPage(MessageReceiver messageReceiver, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(messageReceiver);
        //执行搜索
        return new PageInfo<MessageReceiver>(messageReceiverMapper.selectByExample(example));
    }

    /**
     * MessageReceiver分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<MessageReceiver> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<MessageReceiver>(messageReceiverMapper.selectAll());
    }

    /**
     * MessageReceiver条件查询
     *
     * @param messageReceiver
     * @return
     */
    @Override
    public List<MessageReceiver> findList(MessageReceiver messageReceiver) {
        //构建查询条件
        Example example = createExample(messageReceiver);
        //根据构建的条件查询数据
        return messageReceiverMapper.selectByExample(example);
    }


    /**
     * MessageReceiver构建查询对象
     *
     * @param messageReceiver
     * @return
     */
    public Example createExample(MessageReceiver messageReceiver) {
        Example example = new Example(MessageReceiver.class);
        Example.Criteria criteria = example.createCriteria();
        if (messageReceiver != null) {
            // 
            if (!StringUtils.isEmpty(messageReceiver.getId())) {
                criteria.andEqualTo("id", messageReceiver.getId());
            }
            // 消息接收者
            if (!StringUtils.isEmpty(messageReceiver.getMessageReceiver())) {
                criteria.andEqualTo("messageReceiver", messageReceiver.getMessageReceiver());
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
        messageReceiverMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改MessageReceiver
     *
     * @param messageReceiver
     */
    @Override
    public void update(MessageReceiver messageReceiver) {
        messageReceiverMapper.updateByPrimaryKey(messageReceiver);
    }

    /**
     * 增加MessageReceiver
     *
     * @param messageReceiver
     */
    @Override
    public void add(MessageReceiver messageReceiver) {
        messageReceiverMapper.insert(messageReceiver);
    }

    /**
     * 根据ID查询MessageReceiver
     *
     * @param id
     * @return
     */
    @Override
    public MessageReceiver findById(Integer id) {
        return messageReceiverMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询MessageReceiver全部数据
     *
     * @return
     */
    @Override
    public List<MessageReceiver> findAll() {
        return messageReceiverMapper.selectAll();
    }
}
