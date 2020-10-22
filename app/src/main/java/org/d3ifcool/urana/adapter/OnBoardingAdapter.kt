package org.d3ifcool.urana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.d3ifcool.urana.R
import org.d3ifcool.urana.data.OnBoarding

class OnBoardingAdapter (private val onBoarding: List<OnBoarding>) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return onBoarding.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoarding[position])
    }

    inner class OnBoardingViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val imageIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)

        fun bind(onBoarding : OnBoarding){
            textTitle.text = onBoarding.title
            textDescription.text = onBoarding.description
            imageIcon.setImageResource(onBoarding.icon)
        }

    }
}