package org.d3ifcool.urana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_pemain.view.*
import org.d3ifcool.urana.R
import org.d3ifcool.urana.data.Pemain
import org.d3ifcool.urana.ui.PlayFragment
import org.d3ifcool.urana.viewmodel.PemainViewModel

class PemainAdapter(private val handler: ClickHandler) : ListAdapter<Pemain, PemainAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var viewmodel: PemainViewModel

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pemain>()
        {
            override fun areItemsTheSame(oldData: Pemain,
                                         newData: Pemain): Boolean {
                return oldData.id == newData.id
            }
            override fun areContentsTheSame(oldData: Pemain,
                                            newData: Pemain): Boolean {
                return oldData == newData
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_pemain, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(pemain: Pemain){
            itemView.pemainTextView.text = pemain.name
            itemView.btn_delete.setOnClickListener { handler.onClick(adapterPosition, pemain) }
        }
    }

    interface ClickHandler {
        fun onClick(position: Int, pemain: Pemain)
    }

}