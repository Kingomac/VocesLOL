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

class MainRecyclerViewAdapter(
    private val dataSet: Array<Champ>,
    private val applicationContext: Context
) :
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
                Champ.MISSFORTUNE -> R.drawable.missfortune_circle_0
                Champ.MISSFORTUNE_SKIN_16 -> arrayOf(
                    R.drawable.missfortune_circle_16_royalarms,
                    R.drawable.missfortune_circle_16_scarletfair,
                    R.drawable.missfortune_circle_16_zerohour,
                    R.drawable.missfortune_circle_16_starswarm
                ).random()
                Champ.MISSFORTUNE_SKIN_21 -> R.drawable.missfortune_circle_21_pie_c_11_15
                Champ.MONKEYKING -> R.drawable.monkeyking_circle
                Champ.MONKEYKING_SKIN_4 -> R.drawable.monkeyking_circle_4
                Champ.MONKEYKING_SKIN_5 -> R.drawable.monkeyking_circle_5
                Champ.MONKEYKING_SKIN_6 -> R.drawable.monkeyking_circle_6
                Champ.MONKEYKING_SKIN_7 -> R.drawable.monkeyking_circle_7_pie_c_11_6
                Champ.MORDEKAISER -> R.drawable.mordekaiser_circle_0
                Champ.MORDEKAISER_SKIN_6 -> R.drawable.mordekaiser_circle_6
                Champ.MORDEKAISER_SKIN_13 -> R.drawable.mordekaiser_circle_13_pie_c_11_11
                Champ.MORGANA -> R.drawable.morgana_circle_0
                Champ.MORGANA_SKIN_5 -> R.drawable.morgana_circle_5
                Champ.MORGANA_SKIN_26 -> R.drawable.morgana_circle_26
                Champ.NAMI -> R.drawable.nami_circle
                Champ.NAMI_SKIN_2 -> R.drawable.nami_circle_2
                Champ.NAMI_SKIN_7 -> R.drawable.nami_circle_7
                Champ.NAMI_SKIN_9 -> R.drawable.nami_circle_9
                Champ.NAMI_SKIN_24 -> R.drawable.nami_circle_24_pie_c_10_24
                Champ.NAUTILUS -> R.drawable.nautilus_circle_0
                Champ.NASUS -> R.drawable.nasus_circle
                Champ.NASUS_SKIN_5 -> R.drawable.nasus_circle_5
                Champ.NASUS_SKIN_16 -> R.drawable.nasus_circle_16_pie_c_10_23
                Champ.NASUS_SKIN_25 -> R.drawable.nasus_circle_25_pie_c_11_7
                Champ.NIDALEE -> R.drawable.nidalee_circle
                Champ.NIDALEE_SKIN_18 -> R.drawable.nidalee_circle_18_pie_c_10_24
                Champ.NOCTURNE -> R.drawable.nocturne_circle_0
                Champ.NOCTURNE_SKIN_5 -> R.drawable.nocturne_circle_5
                Champ.NUNU -> R.drawable.nunu_circle_0
                Champ.NUNU_SKIN_2 -> R.drawable.nunu_circle_2
                Champ.NUNU_SKIN_4 -> R.drawable.nunu_circle_4
                Champ.NUNU_SKIN_5 -> R.drawable.nunu_circle_5
                Champ.NUNU_SKIN_6 -> R.drawable.nunu_circle_6
                Champ.NUNU_SKIN_7 -> R.drawable.nunu_circle_7
                Champ.NUNU_SKIN_8 -> R.drawable.nunu_circle_8
                Champ.NUNU_SKIN_16 -> R.drawable.nunu_circle_16_pie_c_11_7
                Champ.POPPY -> R.drawable.poppy_circle
                Champ.POPPY_SKIN_16 -> R.drawable.poppy_circle_16_pie_c_10_11
                Champ.PYKE -> R.drawable.pyke_circle_0
                Champ.PYKE_SKIN_1 -> R.drawable.pyke_circle_1
                Champ.PYKE_SKIN_16 -> R.drawable.pyke_circle_16
                Champ.PYKE_SKIN_25 -> R.drawable.pyke_circle_25_pie_c_10_19
                Champ.AHRI_SKIN_4 -> R.drawable.ahri_circle_4
                Champ.AHRI_SKIN_42 -> R.drawable.ahri_circle_42_pie_c_11_16
                Champ.ASHE_SKIN_32 -> R.drawable.ashe_circle_32_pie_c_11_16
                Champ.CASSIOPEIA -> R.drawable.cassiopeia_circle_0
                Champ.CASSIOPEIA_SKIN_8 -> R.drawable.cassiopeia_circle_8
                Champ.CASSIOPEIA_SKIN_18 -> R.drawable.cassiopeia_circle_18_pie_c_11_16
                Champ.EVELYNN_SKIN_24 -> R.drawable.evelynn_circle_24_pie_c_11_16
                Champ.MALPHITE -> R.drawable.malphite_circle
                Champ.MALPHITE_SKIN_6 -> R.drawable.malphite_circle_6
                Champ.MALPHITE_SKIN_16 -> R.drawable.malphite_circle_16
                Champ.MALPHITE_SKIN_23 -> R.drawable.malphite_circle_23_skins_malphite_skin23
                Champ.MALPHITE_SKIN_27 -> R.drawable.malphite_circle_27_pie_c_11_16
                Champ.WARWICK_SKIN_35 -> R.drawable.warwick_circle_35_pie_c_11_16
                Champ.GALIO -> R.drawable.galio_circle
                Champ.GALIO_SKIN_4 -> R.drawable.galio_circle_4
                Champ.RENEKTON -> R.drawable.renekton_circle_0
                Champ.RENEKTON_SKIN_5 -> R.drawable.renekton_circle_5
                Champ.RENEKTON_SKIN_26 -> R.drawable.renekton_circle_26_pie_c_11_11
                Champ.RENGAR -> R.drawable.rengar_circle_0
                Champ.RENGAR_SKIN_8 -> R.drawable.rengar_circle_8
                Champ.RIVEN -> R.drawable.riven_circle
                Champ.RIVEN_SKIN_6 -> R.drawable.riven_circle_6
                Champ.RIVEN_SKIN_16 -> R.drawable.riven_circle_16
                Champ.RIVEN_SKIN_18 -> R.drawable.riven_circle_18
                Champ.EKKO -> R.drawable.ekko_circle
                Champ.EKKO_SKIN_3 -> R.drawable.ekko_circle_3
                Champ.EKKO_SKIN_19 -> R.drawable.ekko_circle_19
                Champ.ELISE -> R.drawable.elise_circle
                Champ.FIZZ -> R.drawable.fizz_circle_0
                Champ.FIZZ_SKIN_4 -> R.drawable.fizz_circle_4
                Champ.FIZZ_SKIN_10 -> R.drawable.fizz_circle_10
                Champ.GANGPLANK -> R.drawable.gangplank_circle
                Champ.GANGPLANK_SKIN_5 -> R.drawable.gangplank_circle_5
                Champ.GANGPLANK_SKIN_6 -> R.drawable.gangplank_circle_6
                Champ.GANGPLANK_SKIN_7 -> R.drawable.gangplank_circle_7
                Champ.GNAR -> R.drawable.gnar_circle
                Champ.GNAR_SKIN_13 -> R.drawable.gnar_circle_13
                Champ.GNAR_SKIN_14 -> R.drawable.gnar_circle_14
                Champ.GNAR_SKIN_15 -> R.drawable.gnar_circle_15_pie_c_10_11
                Champ.JANNA -> R.drawable.janna_circle
                Champ.JANNA_SKIN_5 -> R.drawable.janna_circle_5
                Champ.ALISTAR -> R.drawable.alistar_circle
                Champ.AMUMU -> R.drawable.amumu_circle_0
                Champ.AMUMU_SKIN_7 -> R.drawable.amumu_circle_7
                Champ.ANIVIA -> R.drawable.cryophoenix_circle
                Champ.ANIVIA_SKIN_2 -> R.drawable.anivia_circle_2
                Champ.ANIVIA_SKIN_5 -> R.drawable.anivia_circle_5
                Champ.ANIVIA_SKIN_17 -> R.drawable.anivia_circle_17_pie_c_10_24
                Champ.AURELIONSOL -> R.drawable.aurelionsol_circle
                Champ.AURELIONSOL_SKIN_2 -> R.drawable.aurelionsol_circle_2
                Champ.BRAND -> R.drawable.brand_circle_0
                Champ.BRAND_SKIN_4 -> R.drawable.brand_circle_4
                Champ.BRAND_SKIN_6 -> R.drawable.brand_circle_6
                Champ.BRAND_SKIN_7 -> R.drawable.brand_circle_7
                Champ.BRAUM -> R.drawable.braum_circle
                Champ.BRAUM_SKIN_2 -> R.drawable.braum_circle_2
                Champ.BRAUM_SKIN_24 -> R.drawable.braum_circle_24
                Champ.CAMILLE -> R.drawable.camille_circle
                Champ.CAMILLE_SKIN_1 -> R.drawable.camille_circle_1
                Champ.CAMILLE_SKIN_2 -> R.drawable.camille_circle_2
                Champ.CORKI -> R.drawable.corki_circle_0
                Champ.CORKI_SKIN_26 -> R.drawable.corki_circle_26_pie_c_11_13
                Champ.DARIUS -> R.drawable.darius_circle_0
                Champ.DARIUS_SKIN_4 -> R.drawable.darius_circle_4
                Champ.DARIUS_SKIN_15 -> R.drawable.darius_circle_15
                Champ.DIANA -> R.drawable.diana_circle_0
                Champ.DIANA_SKIN_12 -> R.drawable.diana_circle_12
                Champ.DRAVEN -> R.drawable.draven_circle
                Champ.DRAVEN_SKIN_1 -> R.drawable.draven_circle_1
                Champ.DRAVEN_SKIN_2 -> R.drawable.draven_circle_2
                Champ.DRAVEN_SKIN_3 -> R.drawable.draven_circle_3
                Champ.DRAVEN_SKIN_13 -> R.drawable.draven_circle_13
                Champ.DRAVEN_SKIN_20 -> R.drawable.draven_circle_20_pie_c_11_2
                Champ.HECARIM -> R.drawable.hecarim_circle_0
                Champ.HECARIM_SKIN_4 -> R.drawable.hecarim_circle_4
                Champ.HECARIM_SKIN_7 -> R.drawable.hecarim_circle_7
                Champ.HECARIM_SKIN_14 -> R.drawable.hecarim_circle_14_pie_c_10_24
                Champ.HEIMERDINGER -> R.drawable.heimerdinger_circle
                Champ.HEIMERDINGER_SKIN_6 -> R.drawable.heimerdinger_circle_6
                Champ.ILLAOI -> R.drawable.illaoi_circle
                Champ.ILLAOI_SKIN_1 -> R.drawable.illaoi_circle_1
                Champ.ILLAOI_SKIN_2 -> R.drawable.illaoi_circle_2
                Champ.ILLAOI_SKIN_10 -> R.drawable.illaoi_circle_10_pie_c_10_24
                Champ.IVERN -> R.drawable.ivern_circle
                Champ.IVERN_SKIN_1 -> R.drawable.ivern_circle_1
                Champ.IVERN_SKIN_11 -> R.drawable.ivern_circle_11_pie_c_10_25
                Champ.JARVANIV -> R.drawable.jarvaniv_circle_0
                Champ.JARVANIV_SKIN_5 -> R.drawable.jarvaniv_circle_5
                Champ.JARVANIV_SKIN_7 -> R.drawable.jarvaniv_circle_7
                Champ.JAX -> R.drawable.jax_circle_0
                Champ.JAX_SKIN_5 -> R.drawable.jax_circle_5
                Champ.JAX_SKIN_13 -> R.drawable.jax_circle_13
                Champ.JAX_SKIN_14 -> R.drawable.jax_circle_14
                Champ.JAYCE -> R.drawable.jayce_circle
                Champ.JAYCE_SKIN_1 -> R.drawable.jayce_circle_1
                Champ.JAYCE_SKIN_3 -> R.drawable.jayce_circle_3
                Champ.JAYCE_SKIN_5 -> R.drawable.jayce_circle_5
                Champ.KALISTA -> R.drawable.kalista_circle_0
                Champ.KALISTA_SKIN_5 -> R.drawable.kalista_circle_5_skins_kalista_skin05
                Champ.KARMA -> R.drawable.karma_circle
                Champ.KARMA_SKIN_3 -> R.drawable.karma_circle_3
                Champ.KARMA_SKIN_8 -> R.drawable.karma_circle_8
                Champ.KARMA_SKIN_27 -> R.drawable.karma_circle_27_pie_c_11_2
                Champ.KARTHUS -> R.drawable.karthus_circle
                Champ.KARTHUS_SKIN_4 -> R.drawable.karthus_circle_4
                Champ.KASSADIN -> R.drawable.kassadin_circle_0
                Champ.KASSADIN_SKIN_5 -> R.drawable.kassadin_circle_5
                Champ.KAYLE -> arrayOf(
                    R.drawable.kayle_circle_0,
                    R.drawable.kayle_circle_0_lvl11
                ).random()
                Champ.KAYLE_SKIN_6 -> arrayOf(
                    R.drawable.kayle_circle_6,
                    R.drawable.kayle_circle_6_lvl11
                ).random()
                Champ.KAYN -> R.drawable.kayn_circle
                Champ.KAYN_SKIN_2 -> R.drawable.kayn_circle_2
                Champ.KENNEN -> R.drawable.kennen_circle
                Champ.KHAZIX -> R.drawable.khazix_circle
                Champ.KHAZIX_SKIN_1 -> R.drawable.khazix_circle_1
                Champ.KHAZIX_SKIN_60 -> R.drawable.khazix_circle_60_pie_c_legacy_bugs_10_22
                Champ.KINDRED -> R.drawable.kindred_circle
                Champ.KLED -> R.drawable.kled_circle
                Champ.KLED_SKIN_9 -> R.drawable.kled_circle_9_skins_kled_skin09
                Champ.KOGMAW -> R.drawable.kogmaw_circle_0
                Champ.KOGMAW_SKIN_4 -> R.drawable.kogmaw_circle_4
                Champ.KOGMAW_SKIN_8 -> R.drawable.kogmaw_circle_8
                Champ.LEBLANC -> R.drawable.leblanc_circle_0
                Champ.LEBLANC_SKIN_12 -> R.drawable.leblanc_circle_12
                Champ.LEBLANC_SKIN_20 -> R.drawable.leblanc_circle_20
                Champ.MALZAHAR -> R.drawable.malzahar_circle
                Champ.MALZAHAR_SKIN_6 -> R.drawable.malzahar_circle_6
                Champ.MAOKAI -> R.drawable.maokai_circle
                Champ.MAOKAI_SKIN_24 -> R.drawable.maokai_circle_24_pie_c_11_13
                Champ.OLAF -> R.drawable.olaf_circle
                Champ.OLAF_SKIN_3 -> R.drawable.olaf_circle_3
                Champ.ORIANNA -> R.drawable.oriana_circle
                Champ.ORNN -> R.drawable.ornn_circle
                Champ.PANTHEON -> R.drawable.pantheon_circle_0
                Champ.PANTHEON_SKIN_25 -> R.drawable.pantheon_circle_25_pie_c_11_14
                Champ.PANTHEON_SKIN_6 -> R.drawable.pantheon_circle_6
                Champ.QIYANA -> R.drawable.qiyana_circle_0
                Champ.QIYANA_SKIN_1 -> R.drawable.qiyana_circle_1
                Champ.QUINN -> R.drawable.quinn_circle
                Champ.QUINN_SKIN_5 -> R.drawable.quinn_circle_5_pie_c_11_1
                Champ.RAMMUS -> R.drawable.armordillo_circle
                Champ.RAMMUS_SKIN_17 -> R.drawable.rammus_circle_17_pie_c_11_13
                Champ.RAMMUS_SKIN_8 -> R.drawable.rammus_circle_8
                Champ.REKSAI -> R.drawable.reksai_circle
                Champ.REKSAI_SKIN_1 -> R.drawable.reksai_circle_1
                Champ.RELL -> R.drawable.rell_circle_0_darksupport
                Champ.RUMBLE -> R.drawable.rumble_circle
                Champ.RUMBLE_SKIN_13 -> R.drawable.rumble_circle_13_pie_c_11_7
                Champ.RUMBLE_SKIN_3 -> R.drawable.rumble_circle_3
                Champ.RYZE -> R.drawable.ryze_circle_0
                Champ.SAMIRA -> R.drawable.samira_circle_0_samira
                Champ.SAMIRA_SKIN_1 -> R.drawable.samira_circle_1_samira
                Champ.SEJUANI -> R.drawable.sejuani_circle_0
                Champ.SEJUANI_SKIN_16 -> R.drawable.sejuani_circle_16_pie_c_11_11
                Champ.SEJUANI_SKIN_3 -> R.drawable.sejuani_circle_3
                Champ.SHACO -> R.drawable.jester_circle
                Champ.SHACO_SKIN_8 -> R.drawable.shaco_circle_8
                Champ.SHACO_SKIN_23 -> R.drawable.shaco_circle_23_pie_c_11_17
                Champ.SHEN -> R.drawable.shen_circle
                Champ.SHEN_SKIN_15 -> R.drawable.shen_circle_15
                Champ.SHEN_SKIN_22 -> R.drawable.shen_circle_22_pie_c_10_18
                Champ.SHYVANA -> R.drawable.shyvana_circle_0
                Champ.SHYVANA_SKIN_8 -> R.drawable.shyvana_circle_8_pie_c_11_2
                Champ.SINGED -> R.drawable.singed_circle_0
                Champ.SINGED_SKIN_10 -> R.drawable.singed_circle_10_pie_c_10_23
                Champ.SION -> R.drawable.sion_circle
                Champ.SION_SKIN_5 -> R.drawable.sion_circle_5
                Champ.SIVIR -> R.drawable.sivir_circle
                Champ.SKARNER -> R.drawable.skarner_circle
                Champ.SKARNER_SKIN_3 -> R.drawable.skarner_circle_3
                Champ.SKARNER_SKIN_5 -> R.drawable.skarner_circle_5_pie_c_10_24
                Champ.SWAIN -> R.drawable.swain_circle_0
                Champ.SWAIN_SKIN_1 -> R.drawable.swain_circle_1
                Champ.SWAIN_SKIN_2 -> R.drawable.swain_circle_2
                Champ.SWAIN_SKIN_3 -> R.drawable.swain_circle_3
                Champ.SWAIN_SKIN_4 -> R.drawable.swain_circle_4
                Champ.SYLAS -> R.drawable.sylas_circle_0
                Champ.SYLAS_SKIN_13 -> R.drawable.sylas_circle_13_pie_c_11_11
                Champ.SYNDRA -> R.drawable.syndra_circle_0
                Champ.TAHMKENCH -> R.drawable.tahmkench_circle
                Champ.TALIYAH -> R.drawable.taliyah_circle
                Champ.TALIYAH_SKIN_1 -> R.drawable.taliyah_circle_1
                Champ.TALON -> R.drawable.talon_circle_0
                Champ.TARIC -> R.drawable.taric_circle
                Champ.TARIC_SKIN_4 -> R.drawable.taric_circle_4
                Champ.THRESH -> R.drawable.thresh_circle
                Champ.THRESH_SKIN_5 -> R.drawable.thresh_circle_5
                Champ.THRESH_SKIN_17 -> R.drawable.thresh_circle_17_pie_c_10_15
                Champ.THRESH_SKIN_27 -> R.drawable.thresh_circle_27_skins_thresh_skin27
                Champ.TRISTANA -> R.drawable.tristana_circle
                Champ.TRISTANA_SKIN_10 -> R.drawable.tristana_circle_10
                Champ.TRISTANA_SKIN_24 -> R.drawable.tristana_circle_24
                Champ.TRUNDLE -> R.drawable.trundle_circle
                Champ.TRUNDLE_SKIN_3 -> R.drawable.trundle_circle_3
                Champ.TRYNDAMERE -> R.drawable.tryndamere_circle
                Champ.TRYNDAMERE_SKIN_4 -> R.drawable.tryndamere_circle_4
                Champ.TRYNDAMERE_SKIN_5 -> R.drawable.tryndamere_circle_5
                Champ.TRYNDAMERE_SKIN_9 -> R.drawable.tryndamere_circle_9
                Champ.TWISTEDFATE -> R.drawable.twistedfate_circle_0
                Champ.TWISTEDFATE_SKIN_11 -> R.drawable.twistedfate_circle_11
                Champ.TWISTEDFATE_SKIN_25 -> R.drawable.twistedfate_circle_25_pie_c_11_17
                Champ.AATROX_SKIN_1 -> R.drawable.aatrox_circle_1
                Champ.AATROX_SKIN_11 -> R.drawable.aatrox_circle_11_pie_c_10_21
                Champ.AATROX_SKIN_2 -> R.drawable.aatrox_circle_2
                Champ.AKALI_SKIN_50 -> R.drawable.akali_circle_50_pie_c_11_17
                Champ.AMUMU_SKIN_34 -> R.drawable.amumu_circle_34_pie_c_12_2
                Champ.CHOGATH_SKIN_7 -> R.drawable.chogath_circle_7
                Champ.DARIUS_SKIN_33 -> R.drawable.darius_circle_33_pie_c_11_17
                Champ.DRMUNDO_SKIN_3 -> R.drawable.drmundo_circle_3_dr_mundo_vgu
                Champ.EZREAL_SKIN_25 -> R.drawable.ezreal_circle_25_pie_c_12_2
                Champ.GRAGAS -> R.drawable.gragas
                Champ.GRAGAS_SKIN_10 -> R.drawable.gragas_circle_10
                Champ.GRAGAS_SKIN_11 -> R.drawable.gragas_circle_11_pie_c_11_1
                Champ.UDYR -> R.drawable.udyr
                Champ.UDYR_SKIN_3 -> R.drawable.udyr_circle_3
                Champ.URGOT -> R.drawable.urgot
                Champ.URGOT_SKIN_1 -> R.drawable.urgot_circle_1
                Champ.URGOT_SKIN_3 -> R.drawable.urgot_circle_3
                Champ.VELKOZ -> R.drawable.velkoz
                Champ.VELKOZ_SKIN_1 -> R.drawable.velkoz_circle_1
                Champ.VELKOZ_SKIN_2 -> R.drawable.velkoz
                Champ.VEX -> R.drawable.vex
                Champ.VI -> R.drawable.vi
                Champ.VIKTOR -> R.drawable.viktor
                Champ.VIKTOR_SKIN_5 -> R.drawable.viktor_circle_5_pie_c_10_19
                Champ.VI_SKIN_11 -> R.drawable.vi_circle_11
                Champ.VI_SKIN_4 -> R.drawable.vi_circle_4
                Champ.VLADIMIR -> R.drawable.vladimir
                Champ.VLADIMIR_SKIN_21 -> R.drawable.vladimir_circle_21_pie_c_10_24
                Champ.VLADIMIR_SKIN_5 -> R.drawable.vladimir_circle_5
                Champ.VLADIMIR_SKIN_8 -> R.drawable.vladimir_circle_8
                Champ.XERATH -> R.drawable.xerath
                Champ.XERATH_SKIN_5 -> R.drawable.xerath_circle_5_skins_xerath_skin05
                Champ.XINZHAO -> R.drawable.xinzhao
                Champ.XINZHAO_SKIN_20 -> R.drawable.xinzhao_circle_20
                Champ.XINZHAO_SKIN_27 -> R.drawable.xinzhao_circle_27_skins_xinzhao_skin27
                Champ.XINZHAO_SKIN_5 -> R.drawable.xinzhao_circle_5
                Champ.YASUO_SKIN_17 -> R.drawable.yasuo_circle_17
                Champ.YASUO_SKIN_2 -> R.drawable.yasuo_circle_2
                Champ.YASUO_SKIN_54 -> R.drawable.yasuo_circle_54_pie_c_11_21
                Champ.YASUO_SKIN_55 -> R.drawable.yasuo_circle_55_pie_c_11_21
                Champ.YASUO_SKIN_9 -> R.drawable.yasuo_circle_9
                Champ.YONE_SKIN_19 -> R.drawable.yone_circle_19_pie_c_11_19
                Champ.YORICK -> R.drawable.yorick
                Champ.YUUMI -> R.drawable.yuumi_circle_0
                Champ.ZAC -> R.drawable.zac
                Champ.ZAC_SKIN_7 -> R.drawable.zac_circle_7_pie_c_10_23
                Champ.ZED -> R.drawable.zed
                Champ.ZED_SKIN_13 -> R.drawable.zed_circle_13
                Champ.ZED_SKIN_15 -> R.drawable.zed_circle_15_pie_c_10_19
                Champ.ZED_SKIN_3 -> R.drawable.zed_circle_3
                Champ.ZED_SKIN_31 -> R.drawable.zed_circle_31_pie_c_11_24
                Champ.ZERI -> R.drawable.zeri_circle_0_zeri
                Champ.ZIGGS -> R.drawable.ziggs
                Champ.ZIGGS_SKIN_6 -> R.drawable.ziggs_circle_6
                Champ.ZILEAN -> R.drawable.zilean
                Champ.ZOE_SKIN_9 -> R.drawable.zoe_circle_9
                Champ.ZYRA -> R.drawable.zyra
                Champ.ZYRA_SKIN_16 -> R.drawable.zyra_circle_16_pie_c_11_17
                Champ.ZYRA_SKIN_5 -> R.drawable.zyra_circle_5
                Champ.RENATA -> R.drawable.renata_cicle_0
                Champ.RENATA_SKIN_1 -> R.drawable.renata_circle_1_renata
                Champ.MISSFORTUNE_SKIN_31 -> R.drawable.missfortune_circle_31_pie_c_12_6
                Champ.PYKE_SKIN_44 -> R.drawable.pyke_circle_44_pie_c_12_6
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
                /*filteredChamps = if(charSearch.isEmpty()) {
                    ArrayList(dataSet.toMutableList())
                } else {
                    val resultList = ArrayList<Champ>()
                    dataSet.forEach { champ ->
                        if(champ.name.lowercase().startsWith(charSearch.lowercase())) {
                            resultList.add(champ)
                        }
                    }
                    resultList
                }*/
                filteredChamps.clear()
                if (charSearch.isEmpty()) filteredChamps.addAll(dataSet)
                else
                    dataSet.forEach { champ ->
                        val champName = try {
                            applicationContext.resources.getString(
                                applicationContext.resources.getIdentifier(
                                    champ.name.lowercase(),
                                    "string",
                                    applicationContext.packageName
                                )
                            )
                        } catch (ex: Exception) {
                            Log.e(
                                "PERFORM FILTERING",
                                "ERROR PERFORMING FILTERING WITH Champ: ${champ.name}"
                            )
                            champ.name
                        }
                        if (champ.name.lowercase()
                                .contains(charSearch.lowercase()) || champName.lowercase()
                                .contains(charSearch.lowercase())
                        ) {
                            filteredChamps.add(champ)
                        }
                    }
                Log.e("FILTER", filteredChamps.toString())
                return FilterResults().apply {
                    count = filteredChamps.size; values = filteredChamps
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                //filteredChamps = results?.values as ArrayList<Champ>
                notifyDataSetChanged()
            }
        }
    }
}