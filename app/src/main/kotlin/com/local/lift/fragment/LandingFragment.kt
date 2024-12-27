package com.local.lift.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.local.locallift.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LandingFragment : Fragment(R.layout.landing_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //navigate to next page after 3 sec - using Coroutine

//        lifecycleScope.launch {
//            delay(3000)
//            findNavController().navigate(R.id.action_)
//        }
    }
}