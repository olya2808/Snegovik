package com.kinotech.kinotechappv1.ui.profile

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PermissionChecker
import com.kinotech.kinotechappv1.R
import com.kinotech.kinotechappv1.databinding.ChangeProfileBinding

class ChangeProfileFragment : Fragment() {

    companion object {
        private const val REQUEST_CODE = 1
        private const val PERMISSION_CODE = 2
    }

    //    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: ChangeProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        profileViewModel =
//            ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding = ChangeProfileBinding.inflate(inflater, container, false)

        binding.changePhotoButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context?.let { it1 ->
                        PermissionChecker.checkSelfPermission(
                            it1,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    } ==
                    PackageManager.PERMISSION_DENIED) {

                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    openGallery()
                }
            } else {
                openGallery()
            }
        }
        var buttonsv = binding.root.findViewById<Button>(R.id.save_button)
        buttonsv.setOnClickListener{
            loadfragment()
        }
        val buttonch: ImageButton = binding.root.findViewById(R.id.backBtn_ch)
        buttonch.setOnClickListener{
            loadfragment()
        }
        return binding.root
    }
    private fun loadfragment() {
        val transaction = activity?.getSupportFragmentManager()?.beginTransaction()
        if (transaction != null) {
            transaction.replace(R.id.container, ProfileFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    openGallery()
                } else {
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            binding.changePhoto.setImageURI(data?.data)
            ProfileFragment().photoAcc.setImageURI(data?.data)
        }
    }
}
