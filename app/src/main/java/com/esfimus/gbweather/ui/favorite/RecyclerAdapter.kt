package com.esfimus.gbweather.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.esfimus.gbweather.R
import com.esfimus.gbweather.data.room.WeatherEntity
import com.esfimus.gbweather.databinding.RecyclerviewItemBinding

class RecyclerAdapter(private val itemsList: List<WeatherEntity>) :
    RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>() {

    private var itemCLickListener: OnListItemCLick? = null
    private var itemLongClickListener: OnListItemLongClick? = null

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RecyclerviewItemBinding.bind(itemView)
        fun bind(favouriteLocation: WeatherEntity) {
            with (binding) {
                cardLocationName.text = favouriteLocation.locationName
                cardTemperature.text = favouriteLocation.temperature
            }
        }

        init {
            val cardView: CardView = binding.weatherListCard
            cardView.setOnClickListener {
                itemCLickListener?.onClick(adapterPosition)
            }
            cardView.setOnLongClickListener {
                itemLongClickListener?.onLongCLick(adapterPosition, cardView)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return  CustomViewHolder(itemView)
    }

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    fun setListItemClickListener(clickListener: OnListItemCLick) {
        itemCLickListener = clickListener
    }

    fun setListItemLongClickListener(longClickListener: OnListItemLongClick) {
        itemLongClickListener = longClickListener
    }

}

interface OnListItemCLick {
    fun onClick(position: Int)
}

interface OnListItemLongClick {
    fun onLongCLick(position: Int, itemView: View)
}