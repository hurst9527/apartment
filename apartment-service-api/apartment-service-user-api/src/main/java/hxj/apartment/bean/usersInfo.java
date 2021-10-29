package hxj.apartment.bean;

import lombok.*;

import java.util.List;

/**
 * @author HXJ
 * @create 2021-11-01 15:53
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class usersInfo {
    private List<User> users;
    private List<Area> areas;
    private List<UserRelationship> relationships;
}
