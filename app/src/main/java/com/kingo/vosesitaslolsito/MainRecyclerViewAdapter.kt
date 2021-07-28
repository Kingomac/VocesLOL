package com.kingo.vosesitaslolsito

import android.content.Context
import android.content.res.Resources
import android.util.Log
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
        Log.d("CHAMP", dataSet[position].name)
        holder.textView.text = try {
            applicationContext.resources.getString(
                applicationContext.resources.getIdentifier(
                    dataSet[position].name.lowercase(),
                    "string",
                    applicationContext.packageName
                )
            )
        } catch(e: Exception) {
            "Lol no tiene bugs"
        }
        holder.textView.setCompoundDrawablesWithIntrinsicBounds(0, when(dataSet[position]) {
            Champ.GWEN -> R.drawable.gwen_circle_0_gwen
            Champ.ZOE -> R.drawable.zoe_circle
            Champ.ZOE_SKIN_1 -> R.drawable.zoe_circle_1
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
            Champ.ANNIE -> R.drawable.annie_circle
            Champ.ASHE -> R.drawable.ashe_circle
            Champ.ASHE_SKIN_8 -> R.drawable.ashe_circle_8
            Champ.ASHE_SKIN_11 -> R.drawable.ashe_circle_11
            Champ.ASHE_SKIN_17 -> R.drawable.ashe_circle_17_chroma_ashe_highnoon
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
            Champ.LUX -> R.drawable.lux_circle_0
            Champ.LUX_SKIN_7 -> arrayOf(R.drawable.lux_circle_7_light, R.drawable.lux_circle_7_air, R.drawable.lux_circle_7_dark, R.drawable.lux_circle_7_fire, R.drawable.lux_circle_7_ice, R.drawable.lux_circle_7_magma, R.drawable.lux_circle_7_mystic, R.drawable.lux_circle_7_nature, R.drawable.lux_circle_7_storm, R.drawable.lux_circle_7_water).random()
            Champ.LUX_SKIN_15 -> R.drawable.lux_circle_15
            Champ.LUX_SKIN_17 -> R.drawable.lux_circle_17
            Champ.LUX_SKIN_18 -> R.drawable.lux_circle_18
            Champ.AKSHAN -> R.drawable.akshan_circle_akshan
            Champ.RAKAN -> R.drawable.rakan_circle
            Champ.RAKAN_SKIN_5 -> R.drawable.rakan_circle_5
            Champ.XAYAH -> R.drawable.xayah_circle
            Champ.XAYAH_SKIN_3 -> R.drawable.xayah_circle_3
            Champ.XAYAH_SKIN_4 -> R.drawable.xayah_circle_4
            Champ.MASTERYI -> R.drawable.masteryi_circle_0
            Champ.MASTERYI_SKIN_9 -> R.drawable.masteryi_circle_9
            Champ.MASTERYI_SKIN_10 -> R.drawable.masteryi_circle_10
            Champ.IRELIA -> R.drawable.irelia_circle_0
            Champ.IRELIA_SKIN_1 -> R.drawable.irelia_circle_1
            Champ.IRELIA_SKIN_2 -> R.drawable.irelia_circle_2
            Champ.IRELIA_SKIN_3 -> R.drawable.irelia_circle_3
            Champ.IRELIA_SKIN_4 -> R.drawable.irelia_circle_4
            Champ.IRELIA_SKIN_5 -> R.drawable.irelia_circle_5
            Champ.IRELIA_SKIN_16 -> R.drawable.irelia_circle_16
            Champ.JINX -> R.drawable.jinx_circle
            Champ.JINX_SKIN_1 -> R.drawable.jinx_circle_1
            Champ.JINX_SKIN_4 -> R.drawable.jinx_circle_4
            Champ.JINX_SKIN_20 -> R.drawable.jinx_circle_20
            Champ.KAISA -> R.drawable.kaisa_circle
            Champ.KAISA_SKIN_1 -> R.drawable.kaisa_circle_1
            Champ.KAISA_SKIN_14 -> R.drawable.kaisa_circle_14
            Champ.KAISA_SKIN_17 -> R.drawable.kaisa_circle_17
            Champ.KAISA_SKIN_26 -> R.drawable.kaisa_circle_26_pie_c_10_22
            Champ.KATARINA -> R.drawable.katarina_circle
            Champ.KATARINA_SKIN_9 -> R.drawable.katarina_circle_9
            Champ.KATARINA_SKIN_10 -> R.drawable.katarina_circle_10
            Champ.KATARINA_SKIN_12 -> R.drawable.katarina_circle_12
            Champ.KATARINA_SKIN_29 -> R.drawable.katarina_circle_29_pie_c_10_25
            Champ.BLITZCRANK -> R.drawable.steamgolem_circle
            Champ.BLITZCRANK_SKIN_20 -> R.drawable.blitzcrank_circle_20
            Champ.BLITZCRANK_SKIN_21 -> R.drawable.blitzcrank_circle_21
            Champ.BLITZCRANK_SKIN_29 -> R.drawable.blitzcrank_circle_29_pie_c_11_7
            Champ.CAITLYN -> R.drawable.caitlyn_circle
            Champ.CAITLYN_SKIN_11 -> R.drawable.caitlyn_circle_11
            Champ.CAITLYN_SKIN_22 -> R.drawable.caitlyn_circle_22_pie_c_11_6
            Champ.CHOGATH -> R.drawable.greenterror_circle
            Champ.CHOGATH_SKIN_2 -> R.drawable.chogath_circle_2
            Champ.CHOGATH_SKIN_5 -> R.drawable.chogath_circle_5
            Champ.SONA -> R.drawable.sona_circle
            Champ.SONA_SKIN_3 ->R.drawable.sona_circle_3
            Champ.SONA_SKIN_4 -> R.drawable.sona_circle_4
            Champ.SONA_SKIN_5 -> R.drawable.sona_circle_5
            Champ.SONA_SKIN_6 -> R.drawable.sona_circle_6
            Champ.SONA_SKIN_17 -> R.drawable.sona_circle_17_pie_c_10_18
            Champ.SETT -> R.drawable.sett_circle_0
            Champ.SETT_SKIN_1 -> R.drawable.sett_circle_1
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