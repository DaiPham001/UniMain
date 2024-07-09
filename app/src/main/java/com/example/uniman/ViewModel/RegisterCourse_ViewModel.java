package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Semester_Model;
import com.example.uniman.Repository.RegisterCourse_Repository;

public class RegisterCourse_ViewModel extends ViewModel {

    private RegisterCourse_Repository registerCourse_repository;

    public RegisterCourse_ViewModel() {
        this.registerCourse_repository = new RegisterCourse_Repository();
    }
    //
    public MutableLiveData<Semester_Model> getsemesteradmin(int khoa,String namhoc,String hocki){
        return registerCourse_repository.getsemester_modelMutableLiveData(khoa,namhoc,hocki);
    }
    //
    public MutableLiveData<Semester_Model> getkhoahoc(String manganh){
        return registerCourse_repository.getkhoahoc_modelMutableLiveData(manganh);
    }

    //
    public MutableLiveData<Semester_Model> getnamhoc(int khoa){
        return registerCourse_repository.getnamhoc_modelMutableLiveData(khoa);
    }
    //
    public MutableLiveData<Semester_Model> themmonadmin(int khoa, String manganh, String namhoc, String hocki, String mamh,
                                                        String tenmh, String tengv, int tinchi,double hocphi){
        return registerCourse_repository.themmonadmin_modelMutableLiveData(khoa,manganh,namhoc,hocki,mamh,tenmh,tengv,tinchi,hocphi);
    }
    //
    public MutableLiveData<Semester_Model> updatemonadmin(int id, int khoa, String manganh, String namhoc, String hocki, String mamh,
                                                        String tenmh, String tengv, int tinchi,double hocphi){
        return registerCourse_repository.updatemonadmin_modelMutableLiveData(id,khoa,manganh,namhoc,hocki,mamh,tenmh,tengv,tinchi,hocphi);
    }
}
