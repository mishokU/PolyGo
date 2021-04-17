package com.mishok.core_ar_impl.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.ar.core.ArCoreApk
import com.google.ar.sceneform.ux.ArFragment
import com.mishok.core_ar_impl.R


class BaseArFragment : ArFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_ar, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Enable AR-related functionality on ARCore supported devices only.
        maybeEnableArButton()
    }

    private fun maybeEnableArButton() {
        val availability = ArCoreApk.getInstance().checkAvailability(requireContext())
        if (availability.isTransient) {
            // Continue to query availability at 5Hz while compatibility is checked in the background.
            Handler().postDelayed({
                maybeEnableArButton()
            }, 200)
        }
        if (availability.isSupported) {
            //mArButton.visibility = View.VISIBLE
            //mArButton.isEnabled = true
        } else { // The device is unsupported or unknown.
            //mArButton.visibility = View.INVISIBLE
            //mArButton.isEnabled = false
        }
    }
}