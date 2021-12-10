package com.capou.jordanm.api.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capou.jordanm.api.model.ChuckNorrisUi
import com.capou.jordanm.databinding.ItemRestaurantBinding

val diffUtils = object : DiffUtil.ItemCallback<ChuckNorrisUi>() {
    override fun areItemsTheSame(oldItem: ChuckNorrisUi, newItem: ChuckNorrisUi): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: ChuckNorrisUi, newItem: ChuckNorrisUi): Boolean {
        return oldItem == newItem
    }


}

class ChuckNorrisQuoteViewHolder(
    val binding: ItemRestaurantBinding,
    onItemClick: (objectDataSample: ChuckNorrisUi, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var ui: ChuckNorrisUi

    init {
        binding.root.setOnClickListener {
            onItemClick(ui, itemView)
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

class ChuckNorrisAdapter(private val onItemClick: (quoteUi: ChuckNorrisUi, view: View) -> Unit) : ListAdapter<ChuckNorrisUi, ChuckNorrisQuoteViewHolder>(diffUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChuckNorrisQuoteViewHolder {
        return ChuckNorrisQuoteViewHolder(
            ItemRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick
        )
    }


    override fun onBindViewHolder(holder: ChuckNorrisQuoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}



