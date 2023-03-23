package com.example.thigk.fragments.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.thigk.Model.User
import com.example.thigk.R
import com.example.thigk.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class fragment_add : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btn_dangky.setOnClickListener {
            insertDataToDatabase()
            view.add_email.setText("")
            view.add_tendangnhap.setText("")
            view.add_matkhau.setText("")
        }

        return view
    }

    private fun insertDataToDatabase() {
        val email = add_email.text.toString()
        val name = add_tendangnhap.text.toString()
        val pass = add_matkhau.text.toString()

        if(inputCheck(email, name, pass)){
            // Create User Object
            val user = User(0, email, name, pass)
            // Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully registerd!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(email: String, name: String, pass: String): Boolean{
        return !(TextUtils.isEmpty(email) && TextUtils.isEmpty(name) && TextUtils.isEmpty(pass))
    }
}