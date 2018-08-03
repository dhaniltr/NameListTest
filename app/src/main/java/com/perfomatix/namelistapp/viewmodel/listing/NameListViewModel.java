package com.perfomatix.namelistapp.viewmodel.listing;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.perfomatix.namelistapp.model.Person;
import com.perfomatix.namelistapp.repository.NameListRepository;
import com.perfomatix.namelistapp.utility.api.Resource;

import java.util.List;

/**
 * ViewModel class for NameList
 */
public class NameListViewModel extends ViewModel {

    NameListRepository nameListRepository = new NameListRepository();

    /**
     * Method to fetch and convert person details from API response to LiveData
     * @return The converted Livedata list
     */
    public LiveData<Resource<List<Person>>> getPersons(){
        LiveData<Resource<List<Person>>> t = null;
        MutableLiveData<String[]> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(new String[]{""});
        return Transformations.switchMap(mutableLiveData, input -> nameListRepository.loadData());
    }
}
