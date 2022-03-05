package com.base.srm.controller;

import com.base.srm.dao.LoginRepository;
import com.base.srm.model.SrmUsers;
import com.base.srm.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SrmUsersController {
    @Autowired()
    LoginRepository loginRepository;

    @GetMapping("/srmAllUsers")
    public ResponseEntity<List<SrmUsers>> getAllUsers() {
        try {
            List<SrmUsers> srmUsersList = new ArrayList<>();

            srmUsersList.addAll(loginRepository.findAll());
            return new ResponseEntity<>(srmUsersList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/login")
    public ResponseEntity<ResponseModel> getSrmLoginUser(@RequestParam("user_name") String user_name,
                                                         @RequestParam("password") String password) {
        try {
            ResponseModel responseModel = new ResponseModel();
            List<SrmUsers> srmUsersList = loginRepository.findByUserNamePassword(user_name, password);
            if (srmUsersList != null && srmUsersList.size() > 0) {
                if (srmUsersList.get(0).password.equals(password)) {
                    responseModel.result = srmUsersList;
                    responseModel.errorMessage = "Success";
                    return new ResponseEntity<>(responseModel, HttpStatus.OK);
                } else {
                    responseModel.errorMessage = "Invalid Password";
                    return new ResponseEntity<>(responseModel, HttpStatus.OK);
                }
            } else {
                responseModel.errorMessage = "Invalid UseName";
                responseModel.result = srmUsersList;
                return new ResponseEntity<>(responseModel, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
