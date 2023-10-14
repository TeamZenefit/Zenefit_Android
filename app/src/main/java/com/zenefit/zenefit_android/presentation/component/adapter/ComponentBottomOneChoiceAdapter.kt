package com.zenefit.zenefit_android.presentation.component.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zenefit.zenefit_android.databinding.ItemComponentBottomRvContentBinding

class ComponentBottomOneChoiceAdapter(
    private val onItemClick :(String) -> Unit,
    private val items: List<String>,
    private val selected: String?
) : RecyclerView.Adapter<ComponentBottomOneChoiceAdapter.ComponentBottomOneChoiceViewHolder>() {

    inner class ComponentBottomOneChoiceViewHolder(private val binding: ItemComponentBottomRvContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String) {
            binding.content = title
            binding.selected = title == selected

            binding.root.setOnClickListener {
                onItemClick.invoke(title)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComponentBottomOneChoiceViewHolder {
        return ComponentBottomOneChoiceViewHolder(
            ItemComponentBottomRvContentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ComponentBottomOneChoiceViewHolder, position: Int) {
        holder.bind(items[position])
    }
}