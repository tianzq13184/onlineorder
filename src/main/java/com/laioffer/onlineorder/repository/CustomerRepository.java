package com.laioffer.onlineorder.repository;

import com.laioffer.onlineorder.entity.CustomerEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
//它扩展了 ListCrudRepository<CustomerEntity, Long>，表明它使用通用的CRUD（创建、读取、更新、删除）操作来处理由Long类型ID标识的CustomerEntity对象
public interface CustomerRepository extends ListCrudRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByFirstName(String firstName);
    List<CustomerEntity> findByLastName(String lastName);
    CustomerEntity findByEmail(String email);

//    @Modifying 和 @Query 注解通常一起使用来定义自定义修改查询
    @Modifying
//    这个注解用于标记一个查询方法，表明该方法将执行一个修改操作，比如更新或删除。
    @Query("UPDATE customers SET first_name = :firstName, last_name = :lastName WHERE email = :email")
//    这个注解允许你定义一个自定义的SQL或JPQL（Java Persistence Query Language）查询。
    void updateNameByEmail(String email, String firstName, String lastName);
}
