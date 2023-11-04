package com.zenefit.zenefit_android.presentation.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zenefit.zenefit_android.data.remote.response.ResponseHomeData
import com.zenefit.zenefit_android.databinding.ItemRvHomeRecommendPolicyListBinding

class HomePolicyRecommendAdapter (
    private val onApplyClicked : (Int) -> Unit
): RecyclerView.Adapter<HomePolicyRecommendAdapter.HomePolicyRecommendViewHolder>() {
    var policyList = mutableListOf<ResponseHomeData.ResponseHomePolicyListData>()
    inner class HomePolicyRecommendViewHolder(private val binding : ItemRvHomeRecommendPolicyListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ResponseHomeData.ResponseHomePolicyListData) {
            binding.data = item
            binding.itemRvHomeRecommendPolicyListBtnApply.setOnClickListener { onApplyClicked.invoke(absoluteAdapterPosition) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePolicyRecommendViewHolder {
        return HomePolicyRecommendViewHolder(ItemRvHomeRecommendPolicyListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = policyList.size

    override fun onBindViewHolder(holder: HomePolicyRecommendViewHolder, position: Int) {
        holder.bind(policyList[position])
    }
}