package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.User_Model;
import com.example.uniman.Repository.User_Repository;

public class User_ViewModel extends ViewModel {
    private User_Repository user_repository;

    public User_ViewModel() {
        this.user_repository = new User_Repository();
    }

    public MutableLiveData<User_Model> Login(String ma,String password){
        return user_repository.user_modelMutableLiveData(ma,password);
    }

    public MutableLiveData<User_Model> Change(String ma,String password){
        return user_repository.changeMutableLiveData(ma,password);
    }

    public MutableLiveData<User_Model> registter(String ma,String password,int role,String uid){
        return user_repository.registter_modelMutableLiveData(ma,password,role,uid);
    }

    public MutableLiveData<User_Model> themtoken(int id,String token){
        return user_repository.themtoken_modelMutableLiveData(id,token);
    }
}
