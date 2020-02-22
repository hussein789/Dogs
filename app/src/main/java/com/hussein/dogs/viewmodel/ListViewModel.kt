package com.hussein.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hussein.dogs.model.DogBreed
import com.hussein.dogs.model.DogsApiService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel :ViewModel(){

    private val disposable = CompositeDisposable()
    private val dogsApi = DogsApiService()

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        getDataFromRemote()
    }

    private fun getDataFromRemote(){
        loading.value = true
        disposable.add(dogsApi.getDogs()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<DogBreed>>(){
                override fun onSuccess(dogsList: List<DogBreed>) {
                    dogs.value = dogsList
                    loading.value = false
                    dogsLoadError.value = false
                }

                override fun onError(e: Throwable) {
                    loading.value = false
                    dogsLoadError.value = true
                }
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}