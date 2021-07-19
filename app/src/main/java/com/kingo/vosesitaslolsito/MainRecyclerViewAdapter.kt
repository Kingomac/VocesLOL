package com.kingo.vosesitaslolsito

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext

class MainRecyclerViewAdapter(private val dataSet: Array<Champ>, private val applicationContext: Context) :
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
        holder.textView.text = applicationContext.resources.getString(applicationContext.resources.getIdentifier(dataSet[position].name.lowercase(), "string", applicationContext.packageName))
        holder.textView.setCompoundDrawablesWithIntrinsicBounds(0, when(dataSet[position]) {
            Champ.GWEN -> R.drawable.gwen_circle_0_gwen
            Champ.ZOE -> R.drawable.zoe_circle
            Champ.DRMUNDO -> R.drawable.drmundo_circle_0_dr_mundo_vgu
            Champ.YASUO -> R.drawable.yasuo_circle
            Champ.NEEKO -> R.drawable.neeko_circle_0
            Champ.JHIN -> R.drawable.jhin_circle_0
            Champ.SERAPHINE -> R.drawable.seraphine_circle_0_ellipsismage
            Champ.SERAPHINE_SKIN_1 -> R.drawable.seraphine_circle_1_ellipsismage
            Champ.SERAPHINE_SKIN_2 -> R.drawable.seraphine_circle_2_ellipsismage
            Champ.SERAPHINE_SKIN_3 -> R.drawable.seraphine_circle_3_ellipsismage
            Champ.AATROX -> R.drawable.aatrox_circle
            Champ.AHRI -> R.drawable.ahri_circle
            Champ.AHRI_SKIN_14 -> R.drawable.ahri_circle_14
            Champ.AHRI_SKIN_27 -> R.drawable.ahri_circle_27_pie_c_10_16
            Champ.AHRI_SKIN_28 -> R.drawable.ahri_circle_28_pie_c_10_22
            Champ.AKALI -> R.drawable.akali_circle
            Champ.AKALI_SKIN_9 -> R.drawable.akali_circle_9
            Champ.AKALI_SKIN_14 -> R.drawable.akali_circle_14
            Champ.AKALI_SKIN_15 -> R.drawable.akali_circle_15_pie_c_9_22
            Champ.AKALI_SKIN_32 -> R.drawable.akali_circle_32_pie_c_10_22
            Champ.JHIN_SKIN_5 -> R.drawable.jhin_circle_5
            Champ.JHIN_SKIN_1 -> R.drawable.jhin_circle_1
            Champ.JHIN_SKIN_4 -> R.drawable.jhin_circle_4
            Champ.SENNA -> R.drawable.senna_circle_0_senna
            Champ.SENNA_SKIN_1 -> R.drawable.senna_circle_1_senna
            Champ.SENNA_SKIN_10 -> R.drawable.senna_circle_10_pie_c_10_12
            Champ.SENNA_SKIN_16 -> R.drawable.senna_circle_16_pie_c_legacy_bugs_2021
            Champ.EVELYNN -> R.drawable.evelynn_circle
            Champ.EVELYNN_SKIN_6 -> R.drawable.evelynn_circle_6
            Champ.EVELYNN_SKIN_15 -> R.drawable.evelynn_circle_15_pie_c_10_22
            Champ.EZREAL -> R.drawable.ezreal_circle
            Champ.EZREAL_SKIN_5 -> R.drawable.ezreal_circle_5
            Champ.EZREAL_SKIN_9 -> R.drawable.ezreal_circle_9
            Champ.EZREAL_SKIN_18 -> R.drawable.ezreal_circle_18
            Champ.EZREAL_SKIN_21 -> R.drawable.ezreal_circle_21
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