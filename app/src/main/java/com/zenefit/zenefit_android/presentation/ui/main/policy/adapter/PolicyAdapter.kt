package com.zenefit.zenefit_android.presentation.ui.main.policy.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zenefit.zenefit_android.data.data.DummyPolicy
import com.zenefit.zenefit_android.databinding.ItemPolicyMainBinding

class PolicyAdapter(
    private val onCalendarClicked : () -> Unit,
    private val onListOpenClicked : (String) -> Unit,
    private val onDetailOpenClicked : (Int) -> Unit,
    private val policyDummy : List<DummyPolicy>) : RecyclerView.Adapter<PolicyAdapter.PolicyViewHolder>() {
    inner class PolicyViewHolder(private val binding : ItemPolicyMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : DummyPolicy) {
            binding.data = item

            binding.itemPolicyMainTvTitle.setOnClickListener { onListOpenClicked.invoke(item.supportPolicyType) }
            binding.itemPolicyMainBtnCalendar.setOnClickListener { onCalendarClicked.invoke() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PolicyViewHolder {
        return PolicyViewHolder(ItemPolicyMainBinding.inflate(LayoutInflater.from(parent.context),parent, false ))
    }

    override fun getItemCount(): Int = policyDummy.size

    override fun onBindViewHolder(holder: PolicyViewHolder, position: Int) {
        holder.bind(policyDummy[position])
    }
}