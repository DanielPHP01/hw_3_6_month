package com.example.hw_3_6_month.viewmodel

import SendActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hw_3_6_month.R
import com.example.hw_3_6_month.databinding.ActivityMainBinding
import com.example.hw_3_6_month.viewmodel.adapter.AdapterGallery
import java.lang.reflect.Constructor

class MainActivity() : BaseActivity<ActivityMainBinding>(), AdapterGallery.Listener {

    private val adapter by lazy { AdapterGallery(this) }
    private val imageList = mutableListOf<Uri>()


    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.data?.let { uri ->
                adapter.addImage(uri)
                imageList.add(uri)
                updateToolbarText()
            }
        }
    }


    override fun inflateVB(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initView() {
        binding.recycler.layoutManager = GridLayoutManager(this, 3)
        binding.recycler.adapter = adapter
    }

    override fun initListener() {
        binding.floatingActionButton.setOnClickListener {
            pickImage()
        }

        binding.tvToolBar.setOnClickListener {
            openSendActivity()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEY_IMG, ArrayList(imageList))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getParcelableArrayList<Uri>(KEY_IMG)?.let { uris ->
            imageList.addAll(uris)
            updateToolbarText()
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        pickImageLauncher.launch(intent)
    }

    private fun openSendActivity() {
        if (imageList.isNotEmpty()) {
            val intent = Intent(this, SendActivity::class.java)
            intent.putExtra(KEY_IMG, ArrayList(imageList))
            startActivity(intent)
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun updateToolbarText() {
        val count = imageList.size
        binding.tvToolBar.text = getString(
            R.string.selected_image,
            count
        )
    }

    override fun onClick(image: Uri) {
        imageList.add(image)
        updateToolbarText()
    }

    override fun deleteClick(image: Uri) {
        imageList.remove(image)
        updateToolbarText()
    }

    companion object {
        const val KEY_IMG = "img"
    }
}
