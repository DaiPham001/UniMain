package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Class_Model;
import com.example.uniman.Model.Student_Model;
import com.example.uniman.Repository.Chat_Repository;
import com.example.uniman.Repository.ClassList_Repository;

public class Chat_ViewModel extends ViewModel {
    private Chat_Repository chat_repository;

    public Chat_ViewModel() {
        this.chat_repository = new Chat_Repository();
    }
    public MutableLiveData<Student_Model> getavatachat(String ma){
        return chat_repository.getavatachat_modelMutableLiveData(ma);
    }


}
