package work.monkey.kotlintemplate.ui.auth

import android.os.Bundle
import android.view.*
import work.monkey.kotlintemplate.R
import work.monkey.kotlintemplate.common.*
import work.monkey.kotlintemplate.databinding.FragmentAuthActivatedBinding
import work.monkey.kotlintemplate.ui.MainActivity
import work.monkey.kotlintemplate.ui.common.AppFragment
import work.monkey.kotlintemplate.state.Event

class ActivatedFragment : AppFragment(R.layout.fragment_auth_activated) {

    private val binding by viewBinding(FragmentAuthActivatedBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideActionBar()
        (requireActivity() as? MainActivity)?.run {
            hideBottomNavigation()
        }

        binding.apply {
            continueButton.setOnClickListener {
                navigateToHome()
            }
        }
    }

    private fun navigateToHome() {
        session.appState.transition(Event.OnHome)
        val action = ActivatedFragmentDirections
                .actionActivatedToHome()
        findSafeNavController().navigate(action)
    }
}