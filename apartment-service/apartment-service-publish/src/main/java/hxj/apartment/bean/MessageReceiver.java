package hxj.apartment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/****
 * @Author:HXJ
 * @Description:MessageReceiver构建
 *****/
@ApiModel(description = "MessageReceiver", value = "MessageReceiver")
@Table(name = "messageReceiver")
public class MessageReceiver implements Serializable {

    @ApiModelProperty(value = "", required = false)
    @Id
    @Column(name = "id")
    private Integer id;//

    @ApiModelProperty(value = "消息接收者", required = false)
    @Column(name = "messageReceiver")
    private String messageReceiver;//消息接收者


    //get方法
    public Integer getId() {
        return id;
    }

    //set方法
    public void setId(Integer id) {
        this.id = id;
    }

    //get方法
    public String getMessageReceiver() {
        return messageReceiver;
    }

    //set方法
    public void setMessageReceiver(String messageReceiver) {
        this.messageReceiver = messageReceiver;
    }


}
