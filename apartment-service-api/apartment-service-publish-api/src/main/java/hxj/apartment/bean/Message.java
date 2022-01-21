package hxj.apartment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:HXJ
 * @Description:Message构建
 *****/
@ApiModel(description = "Message", value = "Message")
@Table(name = "message")
public class Message implements Serializable {

    @ApiModelProperty(value = "", required = false)
    @Id
    @Column(name = "id")
    private Integer id;//

    @ApiModelProperty(value = "消息标题", required = false)
    @Column(name = "title")
    private String title;//消息标题

    @ApiModelProperty(value = "消息内容", required = false)
    @Column(name = "content")
    private String content;//消息内容

    @ApiModelProperty(value = "消息发送者", required = false)
    @Column(name = "messageFrom")
    private String messageFrom;//消息发送者

    @ApiModelProperty(value = "消息类型", required = false)
    @Column(name = "messageFor")
    private String messageFor;//消息类型

    @ApiModelProperty(value = "过期时间", required = false)
    @Column(name = "failureTime")
    private Date failureTime;//过期时间

    @ApiModelProperty(value = "发布时间", required = false)
    @Column(name = "publishTime")
    private Date publishTime;//发布时间


    //get方法
    public Integer getId() {
        return id;
    }

    //set方法
    public void setId(Integer id) {
        this.id = id;
    }

    //get方法
    public String getTitle() {
        return title;
    }

    //set方法
    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    //get方法
    public String getContent() {
        return content;
    }

    //set方法
    public void setContent(String content) {
        this.content = content;
    }

    //get方法
    public String getMessageFor() {
        return messageFor;
    }

    //set方法
    public void setMessageFor(String messageFor) {
        this.messageFor = messageFor;
    }

    //get方法
    public Date getFailureTime() {
        return failureTime;
    }

    //set方法
    public void setFailureTime(Date failureTime) {
        this.failureTime = failureTime;
    }

    //get方法
    public Date getPublishTime() {
        return publishTime;
    }

    //set方法
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }


}
