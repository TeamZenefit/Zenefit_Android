package com.zenefit.zenefit_android.presentation.ui.interest_benefit_policy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zenefit.zenefit_android.data.remote.response.ResponseUserPolicyData
import com.zenefit.zenefit_android.databinding.ItemRvInterestBenefitContentBinding

class InterestBenefitPolicyAdapter(private val type : String,
    private val onDeleteClicked : (String, Int) -> Unit) :
    PagingDataAdapter<ResponseUserPolicyData.ResultUserPolicyData, InterestBenefitPolicyAdapter.InterestBenefitPolicyViewHolder>(
        diffCallback) {
    var deleteStatus = false
    inner class InterestBenefitPolicyViewHolder(private val binding : ItemRvInterestBenefitContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ResponseUserPolicyData.ResultUserPolicyData) {
            binding.type = type
            binding.data = item
            binding.deleteStatus = deleteStatus

            binding.deadline = item.applyEndDate?.mapEndDate()
            binding.benefit = item.benefit?.let { it / 10000 }

            binding.itemRvInterestBenefitContentTvAdditional.setOnClickListener {
                if (deleteStatus) onDeleteClicked.invoke(type, item.policyId)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InterestBenefitPolicyViewHolder {
        return InterestBenefitPolicyViewHolder(ItemRvInterestBenefitContentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: InterestBenefitPolicyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    private fun String.mapEndDate() : String {
        val arr = this.split("-")
        return "${arr[1]}/${arr[2]}"
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ResponseUserPolicyData.ResultUserPolicyData>() {

            override fun areItemsTheSame(oldItem: ResponseUserPolicyData.ResultUserPolicyData, newItem: ResponseUserPolicyData.ResultUserPolicyData): Boolean {
                return oldItem.policyId == newItem.policyId
            }

            override fun areContentsTheSame(oldItem: ResponseUserPolicyData.ResultUserPolicyData, newItem: ResponseUserPolicyData.ResultUserPolicyData): Boolean {
                return oldItem == newItem
            }
        }
    }
}