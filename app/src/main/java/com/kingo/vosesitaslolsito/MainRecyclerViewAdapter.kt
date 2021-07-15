package com.kingo.vosesitaslolsito

import Champ
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext

class MainRecyclerViewAdapter(private val dataSet: Array<Champ>) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>(), View.OnClickListener {

    private var listener: View.OnClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_row_item, parent, false)
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val formattedChamp = dataSet[position].name[0].uppercase() + dataSet[position].name.substring(1)
        holder.textView.text = formattedChamp
        holder.textView.setCompoundDrawablesWithIntrinsicBounds(0, when(dataSet[position]) {
            Champ.GWEN -> R.drawable.gwen_circle_0_gwen
            Champ.ZOE -> R.drawable.zoe_circle
            Champ.DRMUNDO -> R.drawable.drmundo_circle_0_dr_mundo_vgu
            Champ.YASUO -> R.drawable.yasuo_circle
            Champ.NEEKO -> R.drawable.neeko_circle_0
            Champ.JHIN -> R.drawable.jhin_circle_0
            Champ.SERAPHINE -> R.drawable.seraphine_circle_0_ellipsismage
            else -> R.drawable.ic_launcher_foreground
        }, 0, 0)
    }

    override fun onClick(v: View?) {
        listener?.onClick(v)
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int = dataSet.size
}