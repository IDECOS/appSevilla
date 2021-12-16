package com.example.appsevilla.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appsevilla.model.SitePoi

class SiteViewModel: ViewModel() {

    private val selected = MutableLiveData<SitePoi>()

    fun getSelected(): LiveData<SitePoi> = selected

    fun select(sitePoi: SitePoi){
        selected.value = sitePoi
    }
}