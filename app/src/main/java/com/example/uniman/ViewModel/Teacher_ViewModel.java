package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Schedule_Model;
import com.example.uniman.Model.Semester_Model;
import com.example.uniman.Model.Teacher_Model;
import com.example.uniman.Repository.Teacher_Repository;

public class Teacher_ViewModel extends ViewModel {

    private Teacher_Repository teacher_repository;

    public Teacher_ViewModel( ) {
        this.teacher_repository = new Teacher_Repository();
    }

    public MutableLiveData<Teacher_Model> getteacher(String ma){
        return teacher_repository.teacher_modelMutableLiveData(ma);
    }

    // lịch gảng
    public MutableLiveData<Schedule_Model> lectureschedule(String mgv, String thu){
        return teacher_repository.lecturescheduleMutableLiveData(mgv, thu);
    }

    public MutableLiveData<Teacher_Model> gettengv(String makhoa){
        return teacher_repository.gettengvtheomkhoa_modelMutableLiveData(makhoa);
    }
}
