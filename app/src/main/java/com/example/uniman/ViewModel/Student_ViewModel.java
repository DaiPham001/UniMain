package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Student_Model;
import com.example.uniman.Model.Uploadingavata_Model;
import com.example.uniman.Repository.Student_Repository;

import okhttp3.MultipartBody;

public class Student_ViewModel extends ViewModel {
    private Student_Repository student_repository;

    public Student_ViewModel() {
        this.student_repository = new Student_Repository();
    }

    public MutableLiveData<Student_Model> Student(String ma) {
        return student_repository.student_modelMutableLiveData(ma);
    }

    public MutableLiveData<Student_Model> Studentlist(String lop) {
        return student_repository.studentlist_modelMutableLiveData(lop);
    }
    // ảnh
    public MutableLiveData<Uploadingavata_Model> Uploadimg(MultipartBody.Part file){
        return student_repository.uploadingavata_modelMutableLiveData(file);
    }
    public MutableLiveData<Uploadingavata_Model> Updateavata(String ma,String hinhanh ){
        return student_repository.updateavata_modelMutableLiveData(ma,hinhanh);
    }

    // xoa sinh viên
    public MutableLiveData<Uploadingavata_Model> delete(int id ){
        return student_repository.delete_modelMutableLiveData(id);
    }

    // update sinh viên
    public MutableLiveData<Uploadingavata_Model> update(int id,String ma, String name, int gioitinh, String lop, String nganh,
                                                        String chuyennganh, String hinhanh ){
        return student_repository.update_modelMutableLiveData(id,ma, name, gioitinh, lop, nganh, chuyennganh, hinhanh);
    }
}
