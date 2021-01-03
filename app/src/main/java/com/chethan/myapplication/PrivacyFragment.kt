package com.chethan.myapplication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.ui.tooling.preview.Preview


/**
 * Created by Chethan on 5/3/2019.
 */


class PrivacyFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                PrivacyFragment()
            }
        }
    }


    @Composable
    @Preview(showBackground = true)
    fun PrivacyFragment() {
        Text(text = "PrivacyFragment")
    }

}



