package com.example.mornhousett.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mornhousett.databinding.HistoryItemBinding
import com.example.mornhousett.model.Fact

class HistoryRVAdapter: RecyclerView.Adapter<HistoryRVAdapter.HistoryItemViewHolder>() {

    private val diffUtilCallback = object: DiffUtil.ItemCallback<Fact>() {
        override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtilCallback)

    private var onItemClickListener: ((Fact) -> Unit)? = null

    fun setOnItemClickListener(listener: (Fact) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    inner class HistoryItemViewHolder(
        private val binding: HistoryItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(fact: Fact) {
            binding.apply {
                tvNumber.text = fact.number.toString()
                tvText.text = fact.text
            }
            itemView.setOnClickListener {
                onItemClickListener?.let { it(fact) }
            }
        }
    }
}