package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Class_Model;
import com.example.uniman.Model.NotiResponse;
import com.example.uniman.Model.NotisenData;
import com.example.uniman.Repository.ClassList_Repository;
import com.example.uniman.Repository.Noti_Repository;

public class Noti_ViewModel extends ViewModel {
    private Noti_Repository noti_repository;

    public Noti_ViewModel( ) {
        this.noti_repository = new Noti_Repository();
    }
    public MutableLiveData<NotiResponse> data(NotisenData notisenData){
        return noti_repository.notisenDataMutableLiveData(notisenData);
    }
}
