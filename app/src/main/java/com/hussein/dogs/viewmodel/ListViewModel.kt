package com.hussein.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hussein.dogs.model.DogBreed

class ListViewModel :ViewModel(){
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        val corgi = DogBreed("1","Corgi","15 years","breedGroup","bredFor","temperament","")
        val lambador = DogBreed("2","Lambador","20 years","breedGroup","bredFor","temperament","")
        val policy = DogBreed("3","Policy","25 years","breedGroup","bredFor","temperament","")

        val dogsList = arrayListOf<DogBreed>(corgi,lambador,policy)
        dogs.value =  dogsList
        dogsLoadError.value = false
        loading.value = false
    }
}