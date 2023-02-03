package com.example.nasaapp.screens

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.nasaapp.R
import com.example.nasaapp.database.DBHelper
import com.example.nasaapp.models.DBModel
import com.squareup.picasso.Picasso


class DetailsScreen : Fragment() {
    private val args: DetailsScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_details_screen, container, false)

        Picasso.get().load(Uri.parse(args.url))
            .into(view.findViewById<ImageView>(R.id.imageView))

        view.findViewById<TextView>(R.id.tvDate).text = args.date
        view.findViewById<TextView>(R.id.tvTitle).text = args.title
        view.findViewById<TextView>(R.id.tvExplanation).text = args.explanation

        view.findViewById<ImageView>(R.id.btnFavorites).setOnClickListener {
            val dbHelper = DBHelper(view.context)

            val status = dbHelper.addData(DBModel(0, args.title, args.url, args.explanation, args.date))
            if (status > -1) {
                Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_LONG).show()

            }else {
                Toast.makeText(requireContext(), "Error acquired!", Toast.LENGTH_LONG).show()
            }
        }


        return view
    }
}