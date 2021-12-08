package com.capou.jordanm.api.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


//Model for the UI
data class RestaurantUi(
    val name:String,
    val logo:String,
    val address:String,
    val description:String,
    val type:String
)


//Model for the ROOM
@Entity(tableName = "restaurant")
data class RestaurantModel(

    @ColumnInfo(name="name")
    val name:String,

    @ColumnInfo(name="logo")
    val logo:String,

    @ColumnInfo(name="address")
    val address:String,

    @ColumnInfo(name="description")
    val description:String,

    @ColumnInfo(name="type")
    val type:String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


// Model for the RESTAURANT API
data class RestaurantModelAPI(
    @Expose
    @SerializedName("name")
    val name:String,

    @Expose
    @SerializedName("logo")
    val logo:String,

    @Expose
    @SerializedName("address")
    val address:String,

    @Expose
    @SerializedName("description")
    val description:String,

    @Expose
    @SerializedName("type")
    val type:String


)
