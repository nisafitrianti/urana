package org.d3ifcool.urana.adapter

import android.content.Context
import android.content.LocusId
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import org.d3ifcool.urana.R
import org.d3ifcool.urana.data.Player
import org.d3ifcool.urana.databinding.FragmentPlayBinding

class PlayerAdapter(val mCtx: Context, val layoutResId: Int, val playerList : List<Player>)
    : ArrayAdapter<Player>(mCtx, layoutResId,playerList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflate: LayoutInflater = LayoutInflater.from(context);
        val view: View = layoutInflate.inflate(layoutResId, null);
        val textViewName = view.findViewById<TextView>(R.id.textViewName);
        val player = playerList[position];

        textViewName.text = player.name;
        return  view;
    }
}