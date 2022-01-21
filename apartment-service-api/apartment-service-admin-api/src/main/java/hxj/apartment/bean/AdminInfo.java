package hxj.apartment.bean;

import lombok.*;

/**
 * @author HXJ
 * @create 2021-10-27 10:53
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AdminInfo {
    private Long id;
    private String name;   //姓名
    private String identity;//身份
    private String position;//职位
    private String image;//头像
    private Long phoneNo;
    private String email;
    private String introduce;//描述
    private String status;
}
