package com.app.courseapp.Fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.courseapp.Adapter.HomeCourseListAdapter
import com.app.courseapp.Adapter.MyLearningAdapter
import com.app.courseapp.Model.HomeCourseList
import com.app.courseapp.Model.Mylearning
import com.app.courseapp.R
import com.app.courseapp.databinding.FragmentHomeBinding
import com.app.courseapp.databinding.FragmentMyLearningBinding


class MyLearningFragment : Fragment() {
    lateinit var binding: FragmentMyLearningBinding
    lateinit var activity: Activity




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyLearningBinding.inflate(inflater, container, false)

        activity = getActivity() as Activity


        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvMyLearning.layoutManager = linearLayoutManager
        myLearningList()



        return binding.root



    }

    private fun myLearningList() {

        val mylearning = ArrayList<Mylearning>()

        mylearning.add(Mylearning("1","The  Complete Microsoft word","Denis Panjuta, Tutorials.eu by Denis pann","₹ 450"))
        mylearning.add(Mylearning("2","The  Complete Microsoft word","Denis Panjuta, Tutorials.eu by Denis pann","₹ 450"))
        mylearning.add(Mylearning("3","The  Complete Microsoft word","Denis Panjuta, Tutorials.eu by Denis pann","₹ 450"))
        mylearning.add(Mylearning("4","The  Complete Microsoft word","Denis Panjuta, Tutorials.eu by Denis pann","₹ 450"))

        val adapter = MyLearningAdapter(activity, mylearning )
        binding.rvMyLearning.adapter = adapter
    }


}