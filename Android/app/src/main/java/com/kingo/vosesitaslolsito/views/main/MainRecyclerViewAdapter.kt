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
import com.kingo.vosesitaslolsito.R

class MainRecyclerViewAdapter(
    private val dataSet: Array<String>,
    private val applicationContext: Context
) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>(), View.OnClickListener, Filterable {

    private var listener: View.OnClickListener? = null
    var filteredChamps: MutableList<String> = dataSet.toMutableList()

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
        Log.d("CHAMP", filteredChamps[position])
        holder.textView.text = try {
            applicationContext.resources.getString(
                applicationContext.resources.getIdentifier(
                    filteredChamps[position].lowercase(),
                    "string",
                    applicationContext.packageName
                )
            )
        } catch (e: Exception) {
            "Lol no tiene bugs"
        }
        val imgId = applicationContext.resources.getIdentifier(filteredChamps[position], "drawable", applicationContext.packageName)
        holder.textView.setCompoundDrawablesWithIntrinsicBounds(
            null, applicationContext.resources.getDrawable(if(imgId != 0) imgId else R.drawable.ping, null), null, null
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
                                    champ.lowercase(),
                                    "string",
                                    applicationContext.packageName
                                )
                            )
                        } catch (ex: Exception) {
                            Log.e(
                                "PERFORM FILTERING",
                                "ERROR PERFORMING FILTERING WITH Champ: $champ"
                            )
                            champ
                        }
                        if (champ.lowercase()
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