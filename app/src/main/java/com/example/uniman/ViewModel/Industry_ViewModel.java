package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Majors_Model;
import com.example.uniman.Repository.Industry_Repository;

public class Industry_ViewModel extends ViewModel {
    private Industry_Repository industry_repository;

    public Industry_ViewModel() {
        this.industry_repository = new Industry_Repository();
    }

    public MutableLiveData<Majors_Model> industry(){
        return industry_repository.majors_modelMutableLiveData();
    }

    // spinner ngành
    public MutableLiveData<Majors_Model> chnganh(String manganh){
        return industry_repository.chnganh_modelMutableLiveData(manganh);
    }
}
