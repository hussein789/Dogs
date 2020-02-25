package com.hussein.dogs.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hussein.dogs.model.DogBreed
import com.hussein.dogs.model.DogDatabase
import com.hussein.dogs.model.DogsApiService
import com.hussein.dogs.util.SharedPreferencesHelper
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) :BaseViewModel(application){

    private val disposable = CompositeDisposable()
    private val dogsApi = DogsApiService()
    private val sharedPref = SharedPreferencesHelper(getApplication())
    private val refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        val updateTime = sharedPref.getUpdateTime()
        if(updateTime!=null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            getDataFromDatabase()
        }
        else
            getDataFromRemote()
    }

    fun refreshBypassCache(){
        getDataFromRemote()
    }

    private fun getDataFromDatabase(){
        launch {
            val dogList = DogDatabase(getApplication()).dogDao.getAllDogs()
            storeInLocalDatabase(dogList)
        }
    }

    private fun getDataFromRemote(){
        loading.value = true
        disposable.add(dogsApi.getDogs()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<DogBreed>>(){
                override fun onSuccess(dogsList: List<DogBreed>) {
                  storeInLocalDatabase(dogsList)
                }

                override fun onError(e: Throwable) {
                    loading.value = false
                    dogsLoadError.value = true
                }
            })
        )
    }

    private fun dogsRetrieved(dogsList:List<DogBreed>){
        dogs.value = dogsList
        loading.value = false
        dogsLoadError.value = false
    }

    private fun storeInLocalDatabase(dogsList: List<DogBreed>){
        launch {
            val dao = DogDatabase(getApplication()).dogDao
            dao.deleteAll()
            val result = dao.insertAll(*dogsList.toTypedArray())
            var i = 0
            while(i < dogsList.size){
                dogsList[i].uuid = result[i].toInt()
                i++
            }
            dogsRetrieved(dogsList)
            sharedPref.saveUpdateTime(System.nanoTime())
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}