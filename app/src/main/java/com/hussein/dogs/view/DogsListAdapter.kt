package com.hussein.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hussein.dogs.R
import com.hussein.dogs.model.DogBreed
import com.hussein.dogs.util.getProgressDrawable
import com.hussein.dogs.util.loadImage
import kotlinx.android.synthetic.main.item_dog.view.*

class DogsListAdapter(var dogsList:ArrayList<DogBreed>) : RecyclerView.Adapter<DogsListAdapter.DogsViewHolder>() {

    fun updateDogList(newDogList:List<DogBreed>){
        dogsList.clear()
        dogsList.addAll(newDogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_dog,parent,false)
        return DogsViewHolder(view)
    }

    override fun getItemCount(): Int = dogsList.size

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val dog = dogsList[position]
        holder.view.name.text = dog.dogBreed
        holder.view.lifespan.text = dog.lifeSpan
        holder.view.setOnClickListener {
            val action = ListFragmentDirections.actionDetailFragment(dogsList[position])
            it.findNavController().navigate(action)
        }
        holder.view.imageView.loadImage(dog.imageUrl, getProgressDrawable(holder.view.imageView.context))
    }

    class DogsViewHolder(val view: View):RecyclerView.ViewHolder(view)
}