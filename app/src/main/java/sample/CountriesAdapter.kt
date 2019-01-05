package sample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sample.Language
import kotlinx.android.synthetic.main.countries_list_item.view.*

class CountriesAdapter(val items : ArrayList<Language>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.countries_list_item, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.languageFullName?.text = items.get(p1).fullName
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val languageFullName = view.tv_country_name
}