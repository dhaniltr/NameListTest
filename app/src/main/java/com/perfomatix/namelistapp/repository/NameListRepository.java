package com.perfomatix.namelistapp.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import com.perfomatix.namelistapp.model.Person;
import com.perfomatix.namelistapp.repository.api.AppRestApi;
import com.perfomatix.namelistapp.repository.api.NameListInterface;
import com.perfomatix.namelistapp.utility.api.ApiResponse;
import com.perfomatix.namelistapp.utility.api.NetworkOnlyResource;
import com.perfomatix.namelistapp.utility.api.Resource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Repository class which defines the API call
 */
public class NameListRepository {

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build();


    public LiveData<Resource<List<Person>>> loadData(){

        return new NetworkOnlyResource<List<Person>>() {
            @Override
            protected LiveData<ApiResponse<List<Person>>> createCall() {
                return AppRestApi.getAppRestApiInstant(okHttpClient).create(NameListInterface.class).getListOfPersons();
            }

            @Override
            protected void saveCallResult(@Nullable List<Person> item) {

            }
        }.asLiveData();
    }
}
