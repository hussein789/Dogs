package com.hussein.dogs.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DogBreed::class),version = 2)
abstract class DogDatabase :RoomDatabase() {
    abstract val dogDao:DogDao
    companion object
    {
        @Volatile private var instance:DogDatabase? = null
        private val Lock = Any()
        operator fun invoke(context:Context):DogDatabase{
            if(instance != null)
                return instance!!
            else {
                synchronized(Lock){
                    val database = Room.databaseBuilder(context.applicationContext,DogDatabase::class.java,"dog_database").build()
                    instance = database
                    return instance!!
                }
            }
        }
    }

}