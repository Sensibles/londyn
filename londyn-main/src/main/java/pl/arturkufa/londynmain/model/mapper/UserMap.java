package pl.arturkufa.londynmain.model.mapper;

import org.apache.ibatis.annotations.Select;
import pl.arturkufa.londynmain.model.entity.User;


import java.util.List;

public interface UserMap {

    @Select("SELECT USERNAME as username, PASSWORD as password, ENABLED as enabled " +
            "FROM USERS")
    public List<User> getAllUsers();
}
