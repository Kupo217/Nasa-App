package com.example.nasaapp.screens.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaapp.R
import com.example.nasaapp.models.ApodModel
import com.example.nasaapp.screens.MainScreen
import com.example.nasaapp.screens.onboarding.ViewPagerFragmentDirections
import com.squareup.picasso.Picasso
import retrofit2.Response

class NasaAdapter(private val nasaData: List<ApodModel>, private val context: Context) : RecyclerView.Adapter<NasaAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val copyright: TextView = itemView.findViewById(R.id.tvCopyright)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = nasaData[position]
        holder.title.text = "\"".plus(data.title).plus("\"")
        if (data.copyright != null)
        {
            holder.copyright.text = "Copyright: ".plus(data.copyright)
        }
        Picasso.get().load(Uri.parse(data.url)).into(holder.image)

        holder.cardView.setOnClickListener {
            val action = ViewPagerFragmentDirections.actionViewPagerFragmentToDetailsScreen(
                data.title,
                data.url,
                data.explanation,
                data.date
            )
            holder.cardView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return nasaData.size
    }
}