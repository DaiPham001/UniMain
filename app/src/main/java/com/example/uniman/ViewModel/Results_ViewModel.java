package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Results_Model;
import com.example.uniman.Repository.Results_Repository;

public class Results_ViewModel extends ViewModel {
    private Results_Repository results_repository;

    public Results_ViewModel() {
        this.results_repository = new Results_Repository();
    }

    //
    public MutableLiveData<Results_Model> results(String msv){
        return results_repository.results_modelMutableLiveData(msv);
    }
    //
    public MutableLiveData<Results_Model> hienthikq(String msv,String lop){
        return results_repository.hienthikq_modelMutableLiveData(msv,lop);
    }
    //
    public MutableLiveData<Results_Model> nhapkq(String msv, String mamh, float diemcc, float diemhs1, float diemhs2, float diemhs3,int img,
                                                 float diemck1,float diemthi, float tongdiem, float tb4, String diemchu, String xeploai, String ghichu){
        return results_repository.nhapkq_modelMutableLiveData(msv,mamh,diemcc,diemhs1,diemhs2,diemhs3,img,diemck1,diemthi,tongdiem,tb4,diemchu,xeploai, ghichu);
    }
}
