package work.monkey.kotlintemplate.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import work.monkey.kotlintemplate.R
import work.monkey.kotlintemplate.databinding.ActivityAppSettingsBinding
import work.monkey.kotlintemplate.init.InitApp
import work.monkey.kotlintemplate.state.AppSession
import javax.inject.Inject


class AppSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppSettingsBinding

    @Inject
    lateinit var session: AppSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as InitApp)
            .appComp().inject(this)

        supportActionBar?.hide()

        binding = ActivityAppSettingsBinding.inflate(layoutInflater)

        binding.apply {

            countryTextView.setText(
                session.supervisor.country,
                TextView.BufferType.EDITABLE
            )

            continueButton.setText(R.string.button_done)
            continueButton.setOnClickListener {
                finish()
            }
        }

        setContentView(binding.root)
    }
}