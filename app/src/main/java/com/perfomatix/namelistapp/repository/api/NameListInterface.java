package com.perfomatix.namelistapp.repository.api;

import android.arch.lifecycle.LiveData;

import com.perfomatix.namelistapp.model.Person;
import com.perfomatix.namelistapp.utility.api.ApiResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface to define the API calls
 */
public interface NameListInterface {

    /**
     * GET API call to fetch the Person details
     * @return List of person details parsed from the JSON
     */
    @GET("?rows=100&fname=%7BfirstName%7D&lname=%7BlastName%7D&city=%7Bcity%7D")
    public LiveData<ApiResponse<List<Person>>> getListOfPersons();
}



