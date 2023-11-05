package com.zenefit.zenefit_android.presentation.ui.interest_benefit_policy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zenefit.zenefit_android.databinding.ItemRvInterestBenefitContentBinding

class InterestBenefitPolicyAdapter(private val type : String) : RecyclerView.Adapter<InterestBenefitPolicyAdapter.InterestBenefitPolicyViewHolder>() {
    var deleteStatus = false
    inner class InterestBenefitPolicyViewHolder(private val binding : ItemRvInterestBenefitContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.type = type
            binding.deleteStatus = deleteStatus
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InterestBenefitPolicyViewHolder {
        return InterestBenefitPolicyViewHolder(ItemRvInterestBenefitContentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: InterestBenefitPolicyViewHolder, position: Int) {
        holder.bind()
    }
}