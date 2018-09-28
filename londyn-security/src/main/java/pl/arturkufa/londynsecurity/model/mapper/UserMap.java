package pl.arturkufa.londynsecurity.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import pl.arturkufa.londynsecurity.model.entity.User;


import java.util.List;

public interface UserMap {

    @Select("SELECT u.USERNAME as username, u.PASSWORD as password, u.ENABLED as enabled, r.ROLENAME as role " +
            "FROM USERS u " +
            "LEFT JOIN ROLES r ON u.ROLEID = r.ROLEID")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "enabled", column = "enabled", javaType = java.lang.Boolean.class, typeHandler = org.apache.ibatis.type.BooleanTypeHandler.class),
            @Result(property = "role", column = "role")
    })
    List<User> getAllUsers();

    @Select("SELECT u.USERNAME as username, u.PASSWORD as password, u.ENABLED as enabled, r.ROLENAME as role " +
            "FROM USERS u " +
            "LEFT JOIN ROLES r ON u.ROLEID = r.ROLEID " +
            "WHERE u.USERNAME = #{name}")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "enabled", column = "enabled", javaType = java.lang.Boolean.class, typeHandler = org.apache.ibatis.type.BooleanTypeHandler.class),
            @Result(property = "role", column = "role")
    })
    User getUserByUsername(@Param("name")String username);
}
