package com.app.courseapp.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.courseapp.Activity.EditProfileActivity
import com.app.courseapp.Activity.ReferEarnActivity
import com.app.courseapp.databinding.FragmentProfileBinding
import com.app.courseapp.helper.Constant
import com.app.courseapp.helper.Session


class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var activity: Activity
    lateinit var session: Session


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        activity = getActivity() as Activity

        session = Session(activity)


        binding.tvName.setText(session.getData(Constant.NAME))
        binding.tvEmail.setText(session.getData(Constant.EMAIL))


        binding.tvEdit.setOnClickListener {

            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)


        }

        binding.tvLogout.setOnClickListener {

            session.logoutUser(activity)

        }

        binding.tvReferEarn.setOnClickListener {

            val intent = Intent(activity, ReferEarnActivity::class.java)
            startActivity(intent)

        }


        activity = getActivity() as Activity



        return binding.root
    }




}