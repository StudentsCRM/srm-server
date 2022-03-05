package com.base.srm.dao;
import com.base.srm.model.SrmUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LoginRepository {

    List<SrmUsers> findAll();

    List<SrmUsers> findByUserNamePassword(String userName,String password);

}
