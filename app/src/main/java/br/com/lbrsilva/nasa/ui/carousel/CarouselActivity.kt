package br.com.lbrsilva.nasa.ui.carousel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.lbrsilva.nasa.R
import br.com.lbrsilva.nasa.data.model.Media
import br.com.lbrsilva.nasa.databinding.ActivityMainBinding
import br.com.lbrsilva.nasa.databinding.DialogInfoBinding
import br.com.lbrsilva.nasa.helper.extension.format
import br.com.lbrsilva.nasa.helper.transformer.DateTransformer
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CarouselActivity : AppCompatActivity() {
    private val viewModel: CarouselViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setupUi()

        this.viewModel.loadMedias(Date().format("yyyy-MM-dd"))
        this.viewModel.mediasLiveData.observe(this) {
            it?.let { list ->
                this.binding.carousel.adapter?.let { adapter ->
                    (adapter as CarouselAdapter).add(list)

                    if (list.size == 0) {
                        this.binding.carousel.removeLoadMore()
                    }
                } ?: run {
                    this.binding.carousel.adapter = CarouselAdapter(this.supportFragmentManager, it)
                    this.binding.info.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupUi() {
        this.binding = ActivityMainBinding.inflate(this.layoutInflater)

        this.setContentView(this.binding.root)

        this.binding.carousel.addLoadMore {
            this.binding.carousel.adapter?.let { adapter ->
                (adapter as CarouselAdapter).list[adapter.count - 1].date?.let { date ->
                    this.viewModel.loadMedias(DateTransformer.calculateDays(date, -1))
                }
            }
        }
    }

    fun clickInfo(view: View) {
        val currentPage = this.binding.carousel.currentPage()

        this.binding.carousel.adapter?.let { adapter ->
            val media = (adapter as CarouselAdapter).list[currentPage]

            this.dialogInfo(media)
        }
    }

    private fun dialogInfo(media: Media) {
        val builder = AlertDialog.Builder(this)
        val binding = DialogInfoBinding.inflate(LayoutInflater.from(this))

        binding.explanation.text = media.explanation ?: "--"
        binding.author.text = media.copyright ?: "--"
        
        media.date?.let {
            binding.date.text = DateTransformer.parse(it, this.getString(R.string.date_format))
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