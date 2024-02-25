package com.thiagofr.jsonplaceholder.domain.usecase

import android.net.Uri
import com.thiagofr.jsonplaceholder.domain.Result

interface ImageDownloaderUseCase {
    operator fun invoke(imageUrl: String, fileName: String): Result<Uri?>
}