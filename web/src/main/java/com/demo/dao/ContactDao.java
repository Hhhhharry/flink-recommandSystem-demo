package com.demo.dao;

import com.demo.domain.ContactEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
@Component(value = "ContactDao")
public interface ContactDao {

    ContactEntity selectById(@Param("id") int id);

    List<ContactEntity> selectByIds(@Param("ids") List<String> ids);
}
