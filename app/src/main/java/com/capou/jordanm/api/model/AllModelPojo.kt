package com.capou.jordanm.api.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


sealed class MyObjectForRecyclerView()

data class RestaurantHeader(
    val date:String
):MyObjectForRecyclerView()

/** Object use for Ui */
data class ChuckNorrisUi(
    val name: String,
    val type: String,
    val description: String,
    val logo: String,
    val address: String,
    val phone_number: String,
    val date:Long
): MyObjectForRecyclerView()



/** Object use for room */
@Entity(tableName = "restaurant")
data class ChuckNorrisRoom(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "logo")
    val logo: String,

    @ColumnInfo(name = "address")
    val address: String,

    @ColumnInfo(name = "phone_number")
    val phone_number: String,

    @ColumnInfo(name = "date")
    val date: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}


/** Object use for retrofit */
data class ChuckNorrisRetrofit(
    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("type")
    val type: String,

    @Expose
    @SerializedName( "description")
    val description: String,

    @Expose
    @SerializedName( "logo")
    val logo: String,

    @Expose
    @SerializedName( "address")
    val address: String,

    @Expose
    @SerializedName( "phone_number")
    val phone_number: String,


)
