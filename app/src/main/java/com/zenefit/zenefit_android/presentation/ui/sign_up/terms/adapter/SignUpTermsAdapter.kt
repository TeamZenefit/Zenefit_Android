package com.zenefit.zenefit_android.presentation.ui.sign_up.terms.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zenefit.zenefit_android.data.data.Terms
import com.zenefit.zenefit_android.databinding.ItemFgSignUpTermsRvTermsBinding

class SignUpTermsAdapter (private val onClicked : () -> Unit): RecyclerView.Adapter<SignUpTermsAdapter.AgreementViewHolder>() {
    var terms = mutableListOf<Terms>()

    inner class AgreementViewHolder(private val binding : ItemFgSignUpTermsRvTermsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Terms) {
            binding.terms = item
            binding.itemFgSignUpTermsRvTermsCheck.setOnClickListener { onClicked.invoke() }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgreementViewHolder {
        return AgreementViewHolder(ItemFgSignUpTermsRvTermsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = terms.size

    override fun onBindViewHolder(holder: AgreementViewHolder, position: Int) {
        holder.bind(terms[position])
    }

    fun setAllClicked(status : Boolean) {
        for (i in 0 until terms.size) {
            terms[i].selected = status
        }
        notifyItemRangeChanged(0, terms.size)
    }
}