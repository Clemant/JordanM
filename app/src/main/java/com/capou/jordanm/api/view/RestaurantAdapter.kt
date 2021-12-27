package com.capou.jordanm.api.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capou.jordanm.api.model.ChuckNorrisUi
import com.capou.jordanm.api.model.MyObjectForRecyclerView
import com.capou.jordanm.api.model.RestaurantHeader
import com.capou.jordanm.api.viewModel.ChuckNorrisViewModel
import com.capou.jordanm.databinding.ItemRestaurantBinding
import com.capou.jordanm.databinding.ItemRestaurantHeaderBinding

val diffUtils = object : DiffUtil.ItemCallback<MyObjectForRecyclerView>() {
    override fun areItemsTheSame(oldItem: MyObjectForRecyclerView, newItem: MyObjectForRecyclerView): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: MyObjectForRecyclerView, newItem: MyObjectForRecyclerView): Boolean {
        return oldItem == newItem
    }


}

class ChuckNorrisQuoteViewHolder(
    val binding: ItemRestaurantBinding,
    onItemClick: (objectDataSample: ChuckNorrisUi, view: View, type:String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var ui: ChuckNorrisUi

    init {
        binding.root.setOnClickListener {
            onItemClick(ui, itemView,"display")
        }
        binding.deleteItem.setOnClickListener {
            onItemClick(ui,itemView,"delete")
        }
    }


    fun bind(chuckNorrisUi: ChuckNorrisUi) {
        ui = chuckNorrisUi
        Glide.with(itemView.context)
            .load(chuckNorrisUi.logo)
            .into(binding.itemChuckNorrisIcon)


        binding.itemChuckNorrisQuote.text = "Nom: "+chuckNorrisUi.name
        binding.type.text = "Type: "+chuckNorrisUi.type
        binding.address.text = chuckNorrisUi.address
        binding.description.text = chuckNorrisUi.description
    }
}




class HeaderViewHolder(
    val binding: ItemRestaurantHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var header: RestaurantHeader



    fun bind(restaurantHeader: RestaurantHeader) {
        header = restaurantHeader
        binding.date.text = header.date
    }
}

class ChuckNorrisAdapter(private val onItemClick: (quoteUi: ChuckNorrisUi, view: View,type:String) -> Unit) : ListAdapter<MyObjectForRecyclerView, RecyclerView.ViewHolder>(diffUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        when (viewType) {

        MyItemType.ROW.type -> {
            ChuckNorrisQuoteViewHolder(
                ItemRestaurantBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onItemClick
            )
        }


        MyItemType.HEADER.type -> {
            HeaderViewHolder(
                ItemRestaurantHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }



        else -> throw RuntimeException("Wrong view type received $viewType")
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ChuckNorrisUi -> MyItemType.ROW.type
            is RestaurantHeader -> MyItemType.HEADER.type
            else -> MyItemType.ROW.type
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       var type = (holder::class.java).simpleName.toString()
       when (holder.itemViewType){
           MyItemType.ROW.type -> (holder as ChuckNorrisQuoteViewHolder).bind(getItem(position) as ChuckNorrisUi)
           MyItemType.HEADER.type -> (holder as HeaderViewHolder).bind(getItem(position) as RestaurantHeader)
           else -> throw java.lang.RuntimeException("Can't display the right view ${holder.itemViewType} ${holder.itemView}")
       }
    }

    enum class MyItemType(val type: Int) {
        HEADER(0),
        ROW(1)
    }
}



