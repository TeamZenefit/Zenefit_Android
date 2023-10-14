package com.zenefit.zenefit_android.presentation.component.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zenefit.zenefit_android.databinding.ItemComponentBottomRvContentBinding
import com.zenefit.zenefit_android.databinding.ItemComponentBottomRvContentMultipleBinding

class ComponentBottomMultiChoiceAdapter(
    private val onItemClick :(String) -> Unit,
    private val items: List<String>
) : RecyclerView.Adapter<ComponentBottomMultiChoiceAdapter.ComponentBottomMultiChoiceViewHolder>() {
    var selected = mutableListOf<String>()
    inner class ComponentBottomMultiChoiceViewHolder(private val binding: ItemComponentBottomRvContentMultipleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String) {
            binding.content = title
            binding.selected = title in selected
            binding.root.setOnClickListener {
                binding.componentBottomRvContentMultipleIvCheck.visibility = if(binding.componentBottomRvContentMultipleIvCheck.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                onItemClick.invoke(title)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComponentBottomMultiChoiceViewHolder {
        return ComponentBottomMultiChoiceViewHolder(
            ItemComponentBottomRvContentMultipleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ComponentBottomMultiChoiceViewHolder, position: Int) {
        holder.bind(items[position])
    }
}