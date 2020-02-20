package com.hussein.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hussein.dogs.model.DogBreed

class DetailViewModel : ViewModel(){
    val dog = MutableLiveData<DogBreed>()

    fun showDog(selectedDog: DogBreed) {
        this.dog.value = selectedDog
    }
}