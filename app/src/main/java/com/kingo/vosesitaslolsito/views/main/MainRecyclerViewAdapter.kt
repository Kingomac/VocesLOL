package com.kingo.vosesitaslolsito.views.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kingo.vosesitaslolsito.util.Champ
import com.kingo.vosesitaslolsito.R

class MainRecyclerViewAdapter(private val dataSet: Array<Champ>, private val applicationContext: Context) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>(), View.OnClickListener, Filterable {

    private var listener: View.OnClickListener? = null
    public var filteredChamps: ArrayList<Champ> = ArrayList(dataSet.toMutableList())

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
        Log.d("CHAMP", filteredChamps[position].name)
        holder.textView.text = try {
            applicationContext.resources.getString(
                applicationContext.resources.getIdentifier(
                    filteredChamps[position].name.lowercase(),
                    "string",
                    applicationContext.packageName
                )
            )
        } catch (e: Exception) {
            "Lol no tiene bugs"
        }
        holder.textView.setCompoundDrawablesWithIntrinsicBounds(
            0, when (filteredChamps[position]) {
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
                Champ.LUX_SKIN_7 -> arrayOf(
                    R.drawable.lux_circle_7_light,
                    R.drawable.lux_circle_7_air,
                    R.drawable.lux_circle_7_dark,
                    R.drawable.lux_circle_7_fire,
                    R.drawable.lux_circle_7_ice,
                    R.drawable.lux_circle_7_magma,
                    R.drawable.lux_circle_7_mystic,
                    R.drawable.lux_circle_7_nature,
                    R.drawable.lux_circle_7_storm,
                    R.drawable.lux_circle_7_water
                ).random()
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
                Champ.SONA_SKIN_3 -> R.drawable.sona_circle_3
                Champ.SONA_SKIN_4 -> R.drawable.sona_circle_4
                Champ.SONA_SKIN_5 -> R.drawable.sona_circle_5
                Champ.SONA_SKIN_6 -> R.drawable.sona_circle_6
                Champ.SONA_SKIN_17 -> R.drawable.sona_circle_17_pie_c_10_18
                Champ.SETT -> R.drawable.sett_circle_0
                Champ.SETT_SKIN_1 -> R.drawable.sett_circle_1
                Champ.BARD -> R.drawable.bard_circle
                Champ.AZIR -> R.drawable.azir_circle
                Champ.AZIR_SKIN_2 -> R.drawable.azir_circle_2
                Champ.APHELIOS -> R.drawable.aphelios_circle_0_aphelios
                Champ.APHELIOS_SKIN_1 -> R.drawable.aphelios_circle_1_aphelios
                Champ.VARUS -> R.drawable.varus_circle_0
                Champ.VARUS_SKIN_2 -> R.drawable.varus_circle_2
                Champ.VARUS_SKIN_4 -> R.drawable.varus_circle_4
                Champ.VARUS_SKIN_16 -> R.drawable.varus_circle_16_pie_c_11_11
                Champ.VARUS_SKIN_17 -> R.drawable.varus_circle_17_pie_c_10_24
                Champ.VIEGO -> R.drawable.viego_circle_ruinedking
                Champ.VOLIBEAR -> R.drawable.volibear_circle_0_voli
                Champ.WARWICK -> R.drawable.warwick_circle_0
                Champ.WARWICK_SKIN_7 -> R.drawable.warwick_circle_7
                Champ.WARWICK_SKIN_16 -> R.drawable.warwick_circle_16
                Champ.SORAKA -> R.drawable.soraka_circle
                Champ.SORAKA_SKIN_4 -> R.drawable.soraka_circle_4
                Champ.SORAKA_SKIN_6 -> R.drawable.soraka_circle_6
                Champ.SORAKA_SKIN_15 -> R.drawable.soraka_circle_15_pie_c_9_24
                Champ.SORAKA_SKIN_16 -> R.drawable.soraka_circle_16_pie_c_9_24
                Champ.TEEMO -> R.drawable.teemo_circle_0
                Champ.TEEMO_SKIN_4 -> R.drawable.teemo_circle_4
                Champ.TEEMO_SKIN_8 -> R.drawable.teemo_circle_8
                Champ.TWITCH -> R.drawable.twitch_circle
                Champ.TWITCH_SKIN_8 -> R.drawable.twitch_circle_8
                Champ.TWITCH_SKIN_12 -> R.drawable.twitch_circle_12
                Champ.TWITCH_SKIN_27 -> R.drawable.twitch_circle_27
                Champ.VAYNE -> R.drawable.vayne_circle
                Champ.VAYNE_SKIN_11 -> R.drawable.vayne_circle_11
                Champ.VAYNE_SKIN_25 -> R.drawable.vayne_circle_25_pie_c_11_14
                Champ.VEIGAR -> R.drawable.veigar_circle
                Champ.VEIGAR_SKIN_8 -> R.drawable.veigar_circle_8
                Champ.VEIGAR_SKIN_9 -> R.drawable.veigar_circle_9
                Champ.VEIGAR_SKIN_32 -> R.drawable.veigar_circle_32_pie_c_11_13
                Champ.YONE -> R.drawable.yone_circle_0_yone
                Champ.YONE_SKIN_1 -> R.drawable.yone_circle_1_yone
                Champ.YONE_SKIN_10 -> R.drawable.yone_circle_10_pie_c_11_6
                Champ.FIDDLESTICKS -> R.drawable.fiddlesticks_circle_0_fiddlesticksrework
                Champ.FIDDLESTICKS_SKIN_6 -> R.drawable.fiddlesticks_circle_6_fiddlesticksrework
                Champ.FIDDLESTICKS_SKIN_9 -> R.drawable.fiddlesticks_circle_9_fiddlesticksrework
                Champ.FIORA -> R.drawable.fiora_circle
                Champ.FIORA_SKIN_4 -> R.drawable.fiora_circle_4
                Champ.GAREN -> R.drawable.garen_circle
                Champ.GAREN_SKIN_13 -> R.drawable.garen_circle_13
                Champ.GAREN_SKIN_22 -> R.drawable.garen_circle_22
                Champ.GAREN_SKIN_24 -> R.drawable.garen_circle_24_pie_c_11_6
                Champ.GRAVES -> R.drawable.graves_circle
                Champ.GRAVES_SKIN_18 -> R.drawable.graves_circle_18
                Champ.GRAVES_SKIN_35 -> R.drawable.graves_circle_35_pie_c_11_15
                Champ.GRAVES_SKIN_25 -> R.drawable.graves_circle_25
                Champ.LEESIN -> R.drawable.leesin_circle
                Champ.LEESIN_SKIN_12 -> R.drawable.leesin_circle_12
                Champ.LEESIN_SKIN_31 -> R.drawable.leesin_circle_31_pie_c_10_20
                Champ.LEESIN_SKIN_11 -> R.drawable.leesin_circle_11
                Champ.LEONA -> R.drawable.leona_circle
                Champ.LEONA_SKIN_8 -> R.drawable.leona_circle_8
                Champ.LEONA_SKIN_10 -> R.drawable.leona_circle_10
                Champ.LEONA_SKIN_11 -> R.drawable.leona_circle_11
                Champ.LEONA_SKIN_12 -> R.drawable.leona_circle_12
                Champ.LEONA_SKIN_21 -> R.drawable.leona_circle_21_pie_c_11_6
                Champ.LILLIA -> R.drawable.lillia_circle_0_lillia
                Champ.LILLIA_SKIN_1 -> R.drawable.lillia_circle_1_lillia
                Champ.LUCIAN -> R.drawable.lucian_circle
                Champ.LUCIAN_SKIN_6 -> R.drawable.lucian_circle_6
                Champ.LUCIAN_SKIN_8 -> R.drawable.lucian_circle_8
                Champ.LULU -> R.drawable.lulu_circle_0
                Champ.LULU_SKIN_14 -> R.drawable.lulu_circle_14
                Champ.LULU_SKIN_26 -> R.drawable.lulu_circle_26_pie_c_11_7
                Champ.LISSANDRA -> R.drawable.lissandra_circle
                Champ.LISSANDRA_SKIN_3 -> R.drawable.lissandra_circle_3
                Champ.LISSANDRA_SKIN_12 -> R.drawable.lissandra_circle_12_pie_c_10_24
                else -> R.drawable.ic_launcher_foreground
            }, 0, 0
        )
    }

    override fun onClick(v: View?) {
        listener?.onClick(v)
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int = filteredChamps.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filteredChamps = if(charSearch.isEmpty()) {
                    ArrayList(dataSet.toMutableList())
                } else {
                    val resultList = ArrayList<Champ>()
                    dataSet.forEach { champ ->
                        if(champ.name.lowercase().startsWith(charSearch.lowercase())) {
                            resultList.add(champ)
                        }
                    }
                    resultList
                }
                filteredChamps.clear()
                if(charSearch.isEmpty()) filteredChamps.addAll(dataSet)
                else
                    dataSet.forEach { champ ->
                        if(champ.name.lowercase().startsWith(charSearch.lowercase())) {
                            filteredChamps.add(champ)
                        }
                    }
                Log.e("FILTER", filteredChamps.toString())
                return FilterResults().apply { count = filteredChamps.size; values = filteredChamps }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                //filteredChamps = results?.values as ArrayList<Champ>
                notifyDataSetChanged()
            }
        }
    }
}