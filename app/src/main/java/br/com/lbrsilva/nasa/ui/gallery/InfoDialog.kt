package br.com.lbrsilva.nasa.ui.gallery

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.lbrsilva.nasa.R
import br.com.lbrsilva.nasa.data.model.Media
import br.com.lbrsilva.nasa.databinding.DialogInfoBinding
import br.com.lbrsilva.nasa.helper.transformer.DateTransformer

object InfoDialog {
    fun show(activity: Activity, media: Media) {
        val builder = AlertDialog.Builder(activity)
        val binding = DialogInfoBinding.inflate(LayoutInflater.from(activity))

        binding.explanation.text = media.explanation ?: "--"
        binding.author.text = media.copyright ?: "--"

        media.date?.let {
            binding.date.text = DateTransformer.parse(it, activity.getString(R.string.date_format))
        } ?: run {
            binding.date.text = "--"
        }

        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            dialog.dismiss()
        }
        builder.setView(binding.root)

        val dialog = builder.create()
        dialog.show()

        dialog.window?.setBackgroundDrawableResource(android.R.color.white)
    }
}