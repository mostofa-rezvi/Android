import android.app.AlertDialog
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.ImageView

private const val REQUEST_IMAGE_CAPTURE = 1
private const val REQUEST_IMAGE_PICK = 2

class ImagePicker(private val activity: Activity) {

    private var selectedImage: Bitmap? = null

    fun showPickerDialog() {
        val options = arrayOf("Take Photo", "Choose from Gallery")

        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Select an option")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> openCamera()
                1 -> openGallery()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        activity.startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?, imageView: ImageView) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val image = data?.extras?.get("data") as Bitmap?
                    selectedImage = image
                    imageView.setImageBitmap(image)
                }
                REQUEST_IMAGE_PICK -> {
                    val imageUri = data?.data
                    val image = MediaStore.Images.Media.getBitmap(activity.contentResolver, imageUri)
                    selectedImage = image
                    imageView.setImageBitmap(image)
                }
            }
        }
    }

    fun getSelectedImage(): Bitmap? {
        return selectedImage
    }
}
