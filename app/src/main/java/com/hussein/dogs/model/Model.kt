package com.hussein.dogs.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DogBreed(

    @ColumnInfo(name = "breed_id")
    @SerializedName("id")
    val breedId:String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val dogBreed:String?,

    @ColumnInfo(name = "life_span")
    @SerializedName("life_span")
    val lifeSpan:String?,

    @ColumnInfo(name = "breed_group")
    @SerializedName("breed_group")
    val breedGroup:String?,

    @ColumnInfo(name = "bred_for")
    @SerializedName("bred_for")
    val bredFor:String?,


    @SerializedName("temperament")
    val temperament:String?,

    @ColumnInfo(name = "dog_url")
    @SerializedName("url")
    val imageUrl:String?
):Parcelable{
    @PrimaryKey
    var uuid:Int = 0
}