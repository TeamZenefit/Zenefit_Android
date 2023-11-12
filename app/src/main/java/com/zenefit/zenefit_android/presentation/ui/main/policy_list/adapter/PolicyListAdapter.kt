package com.zenefit.zenefit_android.presentation.ui.main.policy_list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zenefit.zenefit_android.data.data.DummyPolicy
import com.zenefit.zenefit_android.databinding.ItemPolicyMainBinding
import com.zenefit.zenefit_android.databinding.ItemRvPolicyDetailContentBinding

class PolicyListAdapter(
    private val onCalendarClicked : () -> Unit,
    private val policyDummy : List<DummyPolicy>) : RecyclerView.Adapter<PolicyListAdapter.PolicyViewHolder>() {
    inner class PolicyViewHolder(private val binding : ItemRvPolicyDetailContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : DummyPolicy) {
            binding.data = item

            binding.itemPolicyListBtnCalendar.setOnClickListener { onCalendarClicked.invoke() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PolicyViewHolder {
        return PolicyViewHolder(ItemRvPolicyDetailContentBinding.inflate(LayoutInflater.from(parent.context),parent, false ))
    }

    override fun getItemCount(): Int = policyDummy.size

    override fun onBindViewHolder(holder: PolicyViewHolder, position: Int) {
        holder.bind(policyDummy[position])
    }
}