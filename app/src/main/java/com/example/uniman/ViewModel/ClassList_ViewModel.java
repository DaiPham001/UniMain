package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Class_Model;
import com.example.uniman.Repository.ClassList_Repository;

public class ClassList_ViewModel extends ViewModel {
    private ClassList_Repository classList_repository;

    public ClassList_ViewModel( ) {
        this.classList_repository = new ClassList_Repository();
    }
    public MutableLiveData<Class_Model> classlist(String manganh){
        return classList_repository.class_modelMutableLiveData(manganh);
    }

    // spinner sửa
    public MutableLiveData<Class_Model> getclass(String manganh){
        return classList_repository.getclass_modelMutableLiveData(manganh);
    }
}
