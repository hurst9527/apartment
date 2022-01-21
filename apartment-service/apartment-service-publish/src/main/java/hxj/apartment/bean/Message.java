package hxj.apartment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:HXJ
 * @Description:Message构建
 *****/
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

	@ApiModelProperty(value = "消息类型", required = false)
	@Column(name = "messageFor")
	private String messageFor;//消息类型

	@ApiModelProperty(value = "消息发送者", required = false)
	@Column(name = "messageFrom")
	private String messageFrom;//消息发送者

	@ApiModelProperty(value = "过期时间", required = false)
	@Column(name = "failureTime")
	private Date failureTime;//过期时间

	@ApiModelProperty(value = "发布时间", required = false)
	@Column(name = "publishTime")
	private Date publishTime;//发布时间


}
