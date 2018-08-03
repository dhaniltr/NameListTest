package com.perfomatix.namelistapp.utility.api;

// ResultType: Type for the Resource data
// RequestType: Type for the API response

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

public abstract class NetworkOnlyResource<RequestType> {
    public NetworkOnlyResource() {
        result.setValue(Resource.loading(null));
        fetchFromNetwork();
    }

    private MediatorLiveData<Resource<RequestType>> result = new MediatorLiveData<Resource<RequestType>>();

    // returns a LiveData that represents the resource, implemented
    // in the base class.
    public LiveData<Resource<RequestType>> asLiveData() {
        return result;
    }

    // Called to create the API call.
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    // Called to save the result of the API response into the database
    @WorkerThread
    protected abstract void saveCallResult(@Nullable RequestType item);

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    @MainThread
    private void onFetchFailed() {
    }


    private void fetchFromNetwork() {
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();

        result.addSource(apiResponse, response ->{
            result.removeSource(apiResponse);
            if (response.isSuccessful() == true){
                saveResultAndReInit(response);
            } else{
                onFetchFailed();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    public void saveResultAndReInit(ApiResponse<RequestType> response) {
        new AsyncTask<Void,Void,Void >() {
            @Override
            protected Void doInBackground(Void... voids) {
                saveCallResult(response.body);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                result.setValue(Resource.success(response.body));
            }
        }.execute();
    }
}

