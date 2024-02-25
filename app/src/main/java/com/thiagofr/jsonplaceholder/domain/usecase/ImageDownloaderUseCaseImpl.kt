package com.thiagofr.jsonplaceholder.domain.usecase

import android.app.Application
import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import com.thiagofr.jsonplaceholder.domain.Result
import java.io.OutputStream

class ImageDownloaderUseCaseImpl(application: Application): ImageDownloaderUseCase {

    private val contentResolver by lazy {
        application.contentResolver
    }

    override fun invoke(imageUrl: String, fileName: String): Result<Uri?> {
        val bitmap = downloadImage(imageUrl)
        return if (bitmap != null) {
            val uri = saveImageToMediaStore(bitmap, fileName)
            Result.Success(uri)
        } else {
            Result.Error(Exception())
        }
    }

    private fun downloadImage(imageUrl: String): Bitmap? {
        return try {
            val requestCreator: RequestCreator = Picasso.get().load(imageUrl)
            val bitmap: Bitmap = requestCreator.get()
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun saveImageToMediaStore(bitmap: Bitmap, fileName: String): Uri? {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        }

        val imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        imageUri?.let {
            try {
                val outputStream: OutputStream? = contentResolver.openOutputStream(it)
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                }
                outputStream?.close()

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    contentValues.clear()
                    contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
                    contentResolver.update(imageUri, contentValues, null, null)
                }
                return imageUri
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return null
    }
}
