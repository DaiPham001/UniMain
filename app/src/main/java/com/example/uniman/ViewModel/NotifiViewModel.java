package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Class_Model;
import com.example.uniman.Model.Notifi_Model;
import com.example.uniman.Repository.ClassList_Repository;
import com.example.uniman.Repository.Noti_Repository;
import com.example.uniman.Repository.Notifi_Repository;

public class NotifiViewModel extends ViewModel {
    private Notifi_Repository noti_repository;

    public NotifiViewModel( ) {
        this.noti_repository = new Notifi_Repository();
    }
    public MutableLiveData<Notifi_Model> getnotifi(){
        return noti_repository.notifi_modelMutableLiveData();
    }

    public MutableLiveData<Notifi_Model> themnotifi(String title, String body){
        return noti_repository.themnotifi_modelMutableLiveData(title,body);
    }
}
