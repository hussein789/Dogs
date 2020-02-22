package com.hussein.dogs.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DogBreed(

    @SerializedName("id")
    val breedId:String?,

    @SerializedName("name")
    val dogBreed:String?,

    @SerializedName("life_span")
    val lifeSpan:String?,

    @SerializedName("breed_group")
    val breedGroup:String?,

    @SerializedName("bred_for")
    val bredFor:String?,

    @SerializedName("temperament")
    val temperament:String?,

    @SerializedName("url")
    val imageUrl:String?
):Parcelable