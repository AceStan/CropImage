package com.example.cropimagelayout

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.cropimagelayout.cropLayout.OnCropListener
import com.example.cropimagelayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var pickMediaLauncher: ActivityResultLauncher<PickVisualMediaRequest>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)
        initMediaLauncher()
    }

    private fun initMediaLauncher() {
        pickMediaLauncher =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null) {
                    println(uri)
                    binding.cropLayout.setUri(uri)
                } else {
                    println("No media selected")
                }
            }
    }

    private fun cropImage() {
        binding.cropLayout.crop()
    }


    private fun initView(binding: ActivityMainBinding) {
        with(binding) {
            btnSelectImage.setOnClickListener {
                selectImageFromGallery()
            }
            btnSetCroppedImage.setOnClickListener {
                cropImage()
            }
            cropLayout.addOnCropListener(object : OnCropListener {
                override fun onSuccess(bitmap: Bitmap) {
                    ivCroppedImage.setImageBitmap(bitmap)
                }

                override fun onFailure(e: Exception) {
                    // do error handling.
                }
            })
            cropLayout.isOffFrame()
        }
    }

    private fun selectImageFromGallery() {
        pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }


}