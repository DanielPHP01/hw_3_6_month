package com.example.hw_3_6_month.viewmodel.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_3_6_month.databinding.ItemGalleryBinding

class AdapterGallery(private val listener: Listener) :
    RecyclerView.Adapter<AdapterGallery.ViewHolder>() {

    private val imageList = arrayListOf<Uri>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGalleryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount() = imageList.size

    fun addImage(image: Uri) {
        imageList.add(image)
        notifyItemInserted(imageList.lastIndex)
    }

    inner class ViewHolder(private val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val image = imageList[adapterPosition]
                if (binding.imageShadow.alpha == 0f) {
                    listener.deleteClick(image)
                    binding.imageShadow.animate().alpha(1f).start()
                } else {
                    listener.onClick(image)
                    binding.imageShadow.animate().alpha(0f).start()
                }
            }
        }

        fun bind(image: Uri) {
            binding.imageShadow.animate().alpha(0f).start()
            binding.image.setImageURI(image)
        }
    }

    interface Listener {
        fun onClick(image: Uri)
        fun deleteClick(image: Uri)
    }
}
