package work.monkey.kotlintemplate.ui.common

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import work.monkey.kotlintemplate.R
import work.monkey.kotlintemplate.init.InitApp
import work.monkey.kotlintemplate.state.AppSession
import work.monkey.kotlintemplate.state.State
import javax.inject.Inject

open class AppFragment : Fragment {

    constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    @Inject
    lateinit var session: AppSession

    override fun onAttach(context: Context) {
        (context.applicationContext as InitApp)
                .appComp().inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            val prevState = session.appState.stateMachine.prevState
            if (!noPopToStates.contains(prevState)) {
                session.appState.stateMachine.popUp()
                findNavController().navigateUp()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_close -> {
                popToHome()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun popToHome() {
        findNavController().popBackStack(R.id.mainMenuFragment, false)
    }

    companion object {
        val noPopToStates = linkedSetOf(
                State.OperatorActivated,
        )
    }
}