package com.example.cropimagelayout.cropLayout

import android.graphics.Bitmap

/**
 * Listener to return an cropped image.
 */
interface OnCropListener {

    /**
     * called when cropping is successful
     *
     * @param bitmap result bitmap
     */
    fun onSuccess(bitmap: Bitmap)

    /**
     * called when cropping is failed
     */
    fun onFailure(e: Exception)
}