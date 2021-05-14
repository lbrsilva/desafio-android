package br.com.lbrsilva.nasa.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.lbrsilva.nasa.R
import br.com.lbrsilva.nasa.databinding.ActivityGalleryBinding
import br.com.lbrsilva.nasa.helper.extension.format
import br.com.lbrsilva.nasa.helper.transformer.DateTransformer
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {
    private val viewModel: GalleryViewModel by viewModels()
    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setupUi()

        this.viewModel.loadMedias(Date().format("yyyy-MM-dd"))
        this.viewModel.mediasLiveData.observe(this) {
            this.binding.load.visibility = View.GONE

            it?.let { list ->
                this.binding.gallery.adapter?.let { adapter ->
                    (adapter as GalleryAdapter).add(list)

                    if (list.size == 0) {
                        this.binding.gallery.removeLoadMore()
                    }
                } ?: run {
                    this.binding.gallery.adapter = GalleryAdapter(this.supportFragmentManager, it)
                    this.binding.info.visibility = View.VISIBLE
                }
            }
        }
        this.viewModel.errorLiveData.observe(this) {
            this.binding.load.visibility = View.GONE

            Snackbar.make(
                this.binding.root,
                it ?: this.getString(R.string.nasa_error),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun setupUi() {
        this.binding = ActivityGalleryBinding.inflate(this.layoutInflater)

        this.setContentView(this.binding.root)

        this.binding.gallery.addLoadMore {
            this.binding.gallery.adapter?.let { adapter ->
                (adapter as GalleryAdapter).list[adapter.count - 1].date?.let { date ->
                    this.viewModel.loadMedias(DateTransformer.calculateDays(date, -1))
                }
            }
        }
    }

    fun clickInfo(view: View) {
        val currentPage = this.binding.gallery.currentPage()

        this.binding.gallery.adapter?.let { adapter ->
            val media = (adapter as GalleryAdapter).list[currentPage]

            InfoDialog.show(this, media)
        }
    }
}