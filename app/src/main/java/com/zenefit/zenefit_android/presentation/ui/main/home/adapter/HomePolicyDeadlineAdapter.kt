package com.zenefit.zenefit_android.presentation.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zenefit.zenefit_android.data.remote.response.ResponseHomeData
import com.zenefit.zenefit_android.databinding.ItemRvHomeDeadlinePolicyListBinding
import com.zenefit.zenefit_android.databinding.ItemRvHomeRecommendPolicyListBinding

class HomePolicyDeadlineAdapter (
    private val onApplyClicked : (Int) -> Unit
): RecyclerView.Adapter<HomePolicyDeadlineAdapter.HomePolicyDeadlineViewHolder>() {
    var policyList = mutableListOf<ResponseHomeData.ResponseHomePolicyListData>()
    inner class HomePolicyDeadlineViewHolder(private val binding : ItemRvHomeDeadlinePolicyListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ResponseHomeData.ResponseHomePolicyListData) {
            binding.data = item.apply { item.endDate = "${item.endDate.split("-")[1]}/${item.endDate.split("-")[2]}" }
            binding.itemRvHomeDeadlinePolicyListBtnApply.setOnClickListener { onApplyClicked.invoke(absoluteAdapterPosition) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePolicyDeadlineViewHolder {
        return HomePolicyDeadlineViewHolder(ItemRvHomeDeadlinePolicyListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = policyList.size

    override fun onBindViewHolder(holder: HomePolicyDeadlineViewHolder, position: Int) {
        holder.bind(policyList[position])
    }
}