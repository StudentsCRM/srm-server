package com.base.srm.dao;
import com.base.srm.model.SrmUsers;
import java.util.List;

public interface LoginRepository {

    List<SrmUsers> findAll();

    List<SrmUsers> findByUserNamePassword(String userName,String password);

}
