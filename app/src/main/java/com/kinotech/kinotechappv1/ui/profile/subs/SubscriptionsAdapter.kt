package com.kinotech.kinotechappv1.ui.profile.subs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kinotech.kinotechappv1.databinding.SubscriptionItemBinding
import com.kinotech.kinotechappv1.ui.profile.SubsInfo

class SubscriptionsAdapter :
ListAdapter<SubsInfo, SubscriptionsViewHolder>(SubsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionsViewHolder {
        val binding = SubscriptionItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SubscriptionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubscriptionsViewHolder, position: Int) {
        val subscription = getItem(position)
        holder.bind(subscription)
    }
}