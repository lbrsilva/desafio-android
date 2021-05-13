package br.com.lbrsilva.nasa.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.lbrsilva.nasa.R
import br.com.lbrsilva.nasa.databinding.ActivityMainBinding
import br.com.lbrsilva.nasa.helper.extension.format
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CarouselActivity : AppCompatActivity() {
    private val viewModel: CarouselViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setupUi()

        this.viewModel.loadPictures(Date().format("yyyy-MM-dd"))
        this.viewModel.picturesLiveData.observe(this) {
            it?.let { list ->
                this.binding.carousel.adapter?.let { adapter ->
                    (adapter as CarouselAdapter).add(list)
                } ?: run {
                    this.binding.carousel.adapter = CarouselAdapter(this.supportFragmentManager, it)
                }
            }
        }
    }

    private fun setupUi() {
        this.binding = ActivityMainBinding.inflate(this.layoutInflater)

        this.setContentView(this.binding.root)
    }
}