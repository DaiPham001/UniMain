package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Results_Model;
import com.example.uniman.Model.Retest_Model;
import com.example.uniman.Repository.Retest_Repository;

public class Retest_ViewModel extends ViewModel {

    private Retest_Repository retest_repository;

    public Retest_ViewModel() {
        this.retest_repository = new Retest_Repository();
    }

    public MutableLiveData<Retest_Model> getthilai(String msv){
        return retest_repository.getthilai_modelMutableLiveData(msv);
    }

    public MutableLiveData<Retest_Model> getthicaithien(String msv){
        return retest_repository.getthicaithien_modelMutableLiveData(msv);
    }

    public MutableLiveData<Retest_Model> themthilai(String msv,String mamh,String lophp,String tenmh, int tc,String diemck1,String ghichu){
        return retest_repository.themmonthilai_modelMutableLiveData(msv,mamh,lophp,tenmh,tc,diemck1,ghichu);
    }

    public MutableLiveData<Retest_Model> themthilai_sv(String msv,String lophp,String tenmh, int tc,float tienthi,String ngaydangky){
        return retest_repository.themmonthilaisv_modelMutableLiveData(msv,lophp,tenmh,tc,tienthi,ngaydangky);
    }

    public MutableLiveData<Retest_Model> getmonthilai(String msv){
        return retest_repository.getmonthilai_modelMutableLiveData(msv);
    }
}
