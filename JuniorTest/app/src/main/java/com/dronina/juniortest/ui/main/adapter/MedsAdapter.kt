package com.dronina.juniortest.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dronina.juniortest.R
import com.dronina.juniortest.data.model.Medication
import com.dronina.juniortest.utils.ui.setInvisible
import kotlinx.android.synthetic.main.medication_item.view.*

class MedsAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Medication, MedsAdapter.DataViewHolder>(MedsDiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(currentMedication: Medication?)
        fun onSootherIconClick()
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(medication: Medication) {
            itemView.apply {
                tvTitle.text = medication.title
                ivIcon.setImageURI(medication.icon)
                setInvisible(invisible = !medication.isReadyForKids, ivKidsFriendly)
                ivKidsFriendly.setOnClickListener {
                    listener.onSootherIconClick()
                }
                setOnClickListener {
                    listener.onItemClick(medication)
                }
            }
        }
    }

    class MedsDiffCallback : DiffUtil.ItemCallback<Medication>() {
        override fun areItemsTheSame(oldItem: Medication, newItem: Medication): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Medication, newItem: Medication): Boolean =
            oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.medication_item, parent, false)
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}