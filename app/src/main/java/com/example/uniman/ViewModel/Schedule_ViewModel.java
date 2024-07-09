package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Schedule_Model;
import com.example.uniman.Model.Student_Model;
import com.example.uniman.Model.Uploadingavata_Model;
import com.example.uniman.Repository.Schedule_Repository;

public class Schedule_ViewModel extends ViewModel {
    private Schedule_Repository schedule_repository;

    public Schedule_ViewModel( ) {
        this.schedule_repository = new Schedule_Repository();
    }

    public MutableLiveData<Schedule_Model> schedule (String ma,String thu){
        return schedule_repository.schedule_modelMutableLiveData(ma,thu);
    }

    public MutableLiveData<Uploadingavata_Model> insertsv (String ma, String name, int gioitinh, String lop, String nganh,
                                                           String chuyennganh, String hinhanh){
        return schedule_repository.insert_modelMutableLiveData(ma,name,gioitinh,lop,nganh,chuyennganh,hinhanh);
    }
    // lop giang viên
    public MutableLiveData<Schedule_Model> classlistgv (String mgv){
        return schedule_repository.classlistgv_modelMutableLiveData(mgv);
    }
    // update tangthai lịch
    public MutableLiveData<Schedule_Model> updateschedule (String mgv,int id,int trangthai){
        return schedule_repository.updateschedule_modelMutableLiveData(mgv,id,trangthai);
    }
}
