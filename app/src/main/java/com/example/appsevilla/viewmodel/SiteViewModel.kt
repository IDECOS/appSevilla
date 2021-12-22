package com.example.appsevilla.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appsevilla.api.ApiService
import com.example.appsevilla.api.RetrofitFactory
import com.example.appsevilla.model.SitePoi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SiteViewModel: ViewModel() {

   private var listSiteViewModel: MutableLiveData<List<SitePoi>> = MutableLiveData()

    fun getSiteObserver(): LiveData<List<SitePoi>>{
        return listSiteViewModel
    }

    fun getApiPoi(){
        viewModelScope.launch (Dispatchers.IO){
            val retrofitFactory = RetrofitFactory.getRetrofitFactory()
            val retroService = retrofitFactory.create(ApiService::class.java)
            val response = retroService.requestPoi()
            listSiteViewModel.postValue(response)
        }
    }
}


