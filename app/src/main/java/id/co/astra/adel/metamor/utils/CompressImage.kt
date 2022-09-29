package id.co.astra.adel.metamor.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import java.io.File
import java.io.FileOutputStream

fun compressImage(filePath: String, maxFile: Double) {
    var sizeString = ""
    var image: Bitmap = BitmapFactory.decodeFile(filePath)
    val exif = ExifInterface(filePath)
    val exifOrientation: Int = exif.getAttributeInt(
        ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL
    )
    val exifDegree: Int = exifOrientationToDegrees(exifOrientation)
    image = rotateImage(image, exifDegree.toFloat())

    try {
        val fileSizeInMB = getFileSizeInMB(filePath)
        sizeString += "size before ${String.format("%.2f", fileSizeInMB)}"
        var quality = 100
        if(fileSizeInMB > maxFile) {
            quality = ((maxFile / fileSizeInMB) * 100).toInt()
        }

        val fileOutputStream = FileOutputStream(filePath)
        image.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream)
        fileOutputStream.close()
        sizeString += "size after: ${String.format("%.2f", getFileSizeInMB(filePath))}"
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun getFileSizeInMB(filePath: String): Double {
    val file = File(filePath)
    val length = file.length()

    val fileSizeInKB = (length / 1024).toString().toDouble()
    return (fileSizeInKB / 1024).toString().toDouble()
}

private fun exifOrientationToDegrees(exifOrientation: Int): Int {
    return when (exifOrientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> 90
        ExifInterface.ORIENTATION_ROTATE_180 -> 180
        ExifInterface.ORIENTATION_ROTATE_270 -> 270
        else -> 0
    }
}

private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
}