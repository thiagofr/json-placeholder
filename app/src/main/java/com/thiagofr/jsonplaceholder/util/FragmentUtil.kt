package com.thiagofr.jsonplaceholder.util

import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.checkExternalStoragePermission(permissions: Array<String>): Boolean {

    val permissionsDeny = permissions.filter {
        ContextCompat.checkSelfPermission(requireContext(), it) != PackageManager.PERMISSION_GRANTED
    }

    return permissionsDeny.isEmpty()

}