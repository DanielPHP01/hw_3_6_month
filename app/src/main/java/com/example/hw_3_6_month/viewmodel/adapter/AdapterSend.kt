package com.example.hw_3_6_month.viewmodel.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_3_6_month.databinding.ItemSendBinding

class AdapterSend : RecyclerView.Adapter<AdapterSend.ImageHolder>() {


    private val selectedImages = mutableListOf<Uri>()


    class ImageHolder(private val binding: ItemSendBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageUri: Uri) {
            binding.imageSelectedRecycler.setImageURI(imageUri)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSendBinding.inflate(inflater, parent, false)
        return ImageHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(selectedImages[position])
    }

    override fun getItemCount(): Int = selectedImages.size


    fun addImages(images: List<Uri>) {
        selectedImages.addAll(images)
        notifyDataSetChanged()
    }

}