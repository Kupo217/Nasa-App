package com.example.nasaapp.screens.adapters

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaapp.R
import com.example.nasaapp.database.DBHelper
import com.example.nasaapp.models.DBModel
import com.example.nasaapp.screens.onboarding.ViewPagerFragmentDirections
import com.squareup.picasso.Picasso

class FavoriteAdapter(private val dataList: ArrayList<DBModel>, private val context: Context): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        Picasso.get().load(Uri.parse(data.url)).into(holder.image)
        holder.title.text = data.title

        holder.cardView.setOnClickListener {
            val action = ViewPagerFragmentDirections.actionViewPagerFragmentToDetailsScreen(
                data.title,
                data.url,
                data.explanation,
                data.date
            )
            holder.cardView.findNavController().navigate(action)
        }

        holder.btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(context)

            builder.setTitle("Delete Record")
            builder.setMessage("Are you sure you want delete ${data.title}")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            builder.setPositiveButton("Yes") { dialogInterface, i ->
                val dbHelper = DBHelper(context)

                val status = dbHelper.deleteData(data)
                if (status > -1) {
                    Toast.makeText(context, "Deleted record", Toast.LENGTH_LONG).show()
                    notifyDataSetChanged()
                }
                dialogInterface.dismiss()
            }
            builder.setNegativeButton("No") { dialogInterface, i ->
                dialogInterface.dismiss()
            }

            val alertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}