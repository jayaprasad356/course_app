package com.app.courseapp.Fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.courseapp.Adapter.HomeCourseListAdapter
import com.app.courseapp.Adapter.MyLearningAdapter
import com.app.courseapp.Model.HomeCourseList
import com.app.courseapp.Model.Mylearning
import com.app.courseapp.R
import com.app.courseapp.databinding.FragmentHomeBinding
import com.app.courseapp.databinding.FragmentMyLearningBinding
import com.app.courseapp.helper.ApiConfig
import com.app.courseapp.helper.Constant
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject


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

//        val mylearning = ArrayList<Mylearning>()
//
//        mylearning.add(Mylearning("1","The  Complete Microsoft word","Denis Panjuta, Tutorials.eu by Denis pann","₹ 450"))
//        mylearning.add(Mylearning("2","The  Complete Microsoft word","Denis Panjuta, Tutorials.eu by Denis pann","₹ 450"))
//        mylearning.add(Mylearning("3","The  Complete Microsoft word","Denis Panjuta, Tutorials.eu by Denis pann","₹ 450"))
//        mylearning.add(Mylearning("4","The  Complete Microsoft word","Denis Panjuta, Tutorials.eu by Denis pann","₹ 450"))
//
//        val adapter = MyLearningAdapter(activity, mylearning )
//        binding.rvMyLearning.adapter = adapter



        val params: MutableMap<String, String> = HashMap()
//        params[Constant.USER_ID] = "11"
        ApiConfig.RequestToVolley({ result, response ->
            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        val `object` = JSONObject(response)
                        val jsonArray = `object`.getJSONArray(Constant.DATA)
                        val g = Gson()
                        val mylearning: java.util.ArrayList<Mylearning> =
                            java.util.ArrayList<Mylearning>()
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject1 = jsonArray.getJSONObject(i)
                            if (jsonObject1 != null) {
                                val group: Mylearning =
                                    g.fromJson(jsonObject1.toString(), Mylearning::class.java)
                                mylearning.add(group)
                            } else {
                                break
                            }
                        }
                        val adapter = MyLearningAdapter(activity, mylearning )
                        binding.rvMyLearning.adapter = adapter
                    } else {
                        Toast.makeText(
                            activity,
                            "" + jsonObject.getString(Constant.MESSAGE),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }, activity, Constant.MY_COURSE_LIST, params, true, 1)

    }


}