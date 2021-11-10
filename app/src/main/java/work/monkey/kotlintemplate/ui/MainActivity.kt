package work.monkey.kotlintemplate.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import work.monkey.kotlintemplate.R
import work.monkey.kotlintemplate.common.viewBinding
import work.monkey.kotlintemplate.databinding.ActivityMainBinding
import work.monkey.kotlintemplate.init.InitApp
import work.monkey.kotlintemplate.state.AppSession
import javax.inject.Inject
import work.monkey.kotlintemplate.api.RepoProvider
import work.monkey.kotlintemplate.api.model.InitAppDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.logging.Logger
import androidx.core.app.ActivityCompat
import androidx.navigation.ui.setupWithNavController
import work.monkey.kotlintemplate.common.SimpleAlertDialog
import work.monkey.kotlintemplate.common.SimpleAlertDialogType

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val repo =  RepoProvider.provideRepository()
    private val disposable = CompositeDisposable()
    private lateinit var mainHandler: Handler

    @Inject
    lateinit var session: AppSession

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment).navController
    }

    companion object {
        private val LOG = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        inject()
        loadDefaultSession()
        requestWriteStoragePermission()

        mainHandler = Handler(Looper.getMainLooper())

        initApp(
            token = session.supervisor.id,
            country = session.supervisor.country
        )

        if (savedInstanceState == null) {
            val graphInflater = navController.navInflater
            val navGraph = graphInflater.inflate(R.navigation.main_navigation)

            navGraph.startDestination = R.id.mainMenuFragment
            navController.graph = navGraph
        }

        navController.addOnDestinationChangedListener(listener)
        binding.bottomNavigationView.setupWithNavController(navController)

        AppBarConfiguration(
            setOf(
                R.id.mainMenuFragment, R.id.MonkeyLogFragment
            )
        )

//        setupActionBarWithNavController(navController, appBarConfiguration)

        setContentView(binding.root)
    }

    fun hideBottomNavigation() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    fun showBottomNavigation() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun inject() {
        (applicationContext as InitApp)
            .appComp().inject(this)
    }

    private fun loadDefaultSession() {
        session.createMockSupervisor()
        session.createMockMonkey()
    }

    private val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
        when(destination.id) {
            R.id.MonkeyLogFragment -> {
                setStatusBarColor(R.color.black)
            }
            R.id.mainMenuFragment -> {
                setStatusBarColor(R.color.black)
            }
            R.id.activatedFragment -> {
                setStatusBarColor(R.color.light_blue)
            }
        }
    }

    private fun setStatusBarColor(@ColorRes id: Int) {
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor = ContextCompat.getColor(this, id)
    }

    private fun initApp(token: String, country: String) {
        val request = InitAppDTO(
            token = token,
            country = country
        )
        disposable.add(
            repo.initApp(request = request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    { result ->
                        if (session.isDebug) {
                            val alert = SimpleAlertDialog(
                                context = this,
                                type = SimpleAlertDialogType.INFO,
                                title = "Token",
                                message = token
                            )
                            alert.show {}
                        }
                    },
                    { error ->
                        val message = error.message ?: "Unknown error"
                        val alert = SimpleAlertDialog(
                            context = this,
                            type = SimpleAlertDialogType.ERROR,
                            title = "Something went wrong",
                            message = message
                        )
                        alert.show {}
                    }
                )
        )
    }

    @Suppress("unused")
    private fun requestWriteStoragePermission(): Boolean {
        if (!session.isDebug) return false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return true
        }
        
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                112
            )
            return false
        }
        return true
    }
}

