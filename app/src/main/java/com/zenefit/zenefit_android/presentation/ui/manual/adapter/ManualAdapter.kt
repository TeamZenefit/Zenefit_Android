package com.zenefit.zenefit_android.presentation.ui.manual.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zenefit.zenefit_android.data.data.Manual
import com.zenefit.zenefit_android.databinding.ItemManualWithSubtitleBinding
import com.zenefit.zenefit_android.databinding.ItemManualWithoutSubtitleBinding

class ManualAdapter(private val manualItems : List<Manual>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ManualWithoutSubtitleViewHolder(private val binding : ItemManualWithoutSubtitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Manual) {
            binding.manual = item
        }
    }

    inner class ManualWithSubtitleViewHolder(private val binding : ItemManualWithSubtitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Manual) {
            binding.manual = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> ManualWithoutSubtitleViewHolder(ItemManualWithoutSubtitleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> ManualWithSubtitleViewHolder(ItemManualWithSubtitleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int = position

    override fun getItemCount(): Int = manualItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ManualWithSubtitleViewHolder -> holder.bind(manualItems[position])
            is ManualWithoutSubtitleViewHolder -> holder.bind(manualItems[position])
        }
    }
}