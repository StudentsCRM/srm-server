package com.base.srm.dao;

import com.base.srm.model.SrmUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcRepository implements LoginRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<SrmUsers> findAll() {
        return jdbcTemplate.query("SELECT * from srmusers",
        BeanPropertyRowMapper.newInstance(SrmUsers.class));
    }

    @Override
    public List<SrmUsers> findByUserNamePassword(String userName, String password) {

        List<SrmUsers> srmUsersList = jdbcTemplate.query("SELECT * from srmusers where user_name=?",
                BeanPropertyRowMapper.newInstance(SrmUsers.class),userName);

        return srmUsersList;
    }

}
