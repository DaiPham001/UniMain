package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Student_Model;
import com.example.uniman.Repository.Search_Repository;

public class Search_ViewModel extends ViewModel {

    private Search_Repository search_repository;

    public Search_ViewModel( ) {
        this.search_repository = new Search_Repository();
    }

    public MutableLiveData<Student_Model> Search(String search,String lop){
        return search_repository.searchProduct_modelMutableLiveData(search,lop);
    }
}
