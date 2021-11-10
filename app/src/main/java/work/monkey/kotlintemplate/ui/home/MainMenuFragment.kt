package work.monkey.kotlintemplate.ui.home

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import work.monkey.kotlintemplate.R
import work.monkey.kotlintemplate.common.*
import work.monkey.kotlintemplate.common.dp
import work.monkey.kotlintemplate.databinding.FragmentMainMenuBinding
import work.monkey.kotlintemplate.init.InitApp
import work.monkey.kotlintemplate.state.AppSession
import work.monkey.kotlintemplate.ui.MainActivity
import java.util.*
import javax.inject.Inject

class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {

    private val binding by viewBinding(FragmentMainMenuBinding::bind)

    @Inject
    lateinit var session: AppSession

    override fun onAttach(context: Context) {
        (context.applicationContext as InitApp)
            .appComp().inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            // disable back navigation
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(R.string.app_name)
        showActionBar()

        val items = MainMenuType.values()
        val mainAdapter = MainMenuAdapter()

        val menuItems: List<MainMenuType> = ArrayList(
            EnumSet.allOf(
                MainMenuType::class.java
            )
        )
        mainAdapter.submitList(menuItems)
        binding.menuList.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                binding.menuList,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        when (items[+position]) {
                            MainMenuType.MONKEY_TYPES -> { }
                            MainMenuType.SAPIENS -> { }
                        }
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        // do whatever
                    }
                })
        )

        val manager = GridLayoutManager(activity, 1)

        // This is necessary so that the binding can observe LiveData updates.
        binding.lifecycleOwner =this

        binding.apply {
            menuList.adapter = mainAdapter
            menuList.layoutManager = manager

            context?.let {
                val spacing = it.dp(DimensionType.MEDIUM_LARGE.dimen).toInt()
                menuList.addItemDecoration(VerticalSpaceItemDecoration(spacing))
            }
        }

        setHasOptionsMenu(true)
        showActionBar()
        (requireActivity() as? MainActivity)?.run {
            showBottomNavigation()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.options_menu_home -> {
                popToHome()
                return true
            }
            R.id.options_menu_settings -> {
                popToHome()
                navigateToAppSettingsActivity()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToAppSettingsActivity() {
        val action = MainMenuFragmentDirections
                .actionHomeToAppSettingsActivity()
        findNavController().navigate(action)
    }

    private fun popToHome() {
        findNavController().popBackStack(R.id.mainMenuFragment, false)
    }
}