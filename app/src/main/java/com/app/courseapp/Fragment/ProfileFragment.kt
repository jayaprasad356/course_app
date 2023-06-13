package com.app.courseapp.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.courseapp.Activity.EditProfileActivity
import com.app.courseapp.R
import com.app.courseapp.databinding.FragmentMyLearningBinding
import com.app.courseapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var activity: Activity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)


        binding.tvEdit.setOnClickListener {

            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)


        }


        activity = getActivity() as Activity



        return binding.root
    }




}