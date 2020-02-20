package com.hussein.dogs.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.hussein.dogs.R
import com.hussein.dogs.model.DogBreed
import com.hussein.dogs.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private var dogUuid = 0
    private lateinit var dog:DogBreed
    private lateinit var viewModel: DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
             dog = DetailFragmentArgs.fromBundle(it).dog
        }

        viewModel =  ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.showDog(dog)

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.dog.observe(this, Observer { dog ->
            dog?.let {
                showDog(dog)
            }
        })
    }

    private fun showDog(dog: DogBreed?) {
        dog?.let {
            dogName.text = dog.dogBreed
            dogLifespan.text = dog.lifeSpan
            dogTemperament.text = dog.temperament
        }
    }


}
