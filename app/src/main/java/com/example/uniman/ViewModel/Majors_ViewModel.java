package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Majors_Model;
import com.example.uniman.Repository.Majors_Repository;

public class Majors_ViewModel extends ViewModel {

    private Majors_Repository majors_repository;

    public Majors_ViewModel() {
        this.majors_repository = new Majors_Repository();
    }

    public MutableLiveData<Majors_Model> gettennganh(){
        return majors_repository.gettennganh_modelMutableLiveData();
    }

    public MutableLiveData<Majors_Model> themnganh(String manganh,String tennganh,String chuyennganh){
        return majors_repository.themnganh_modelMutableLiveData(manganh,tennganh,chuyennganh);
    }

    public MutableLiveData<Majors_Model> updatenganh(int id,String manganh,String tennganh){
        return majors_repository.update_modelMutableLiveData(id,manganh,tennganh);
    }
}
