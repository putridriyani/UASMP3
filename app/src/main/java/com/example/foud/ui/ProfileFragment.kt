package com.example.foud.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.foud.R
import com.example.foud.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var profileName = "Your Name"
    private var profileBio = "Tell everyone about you!"

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    private val PICK_IMAGE_REQUEST = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set initial profile information
        updateProfile()

        // Handle click event on the edit button
        binding.editButton.setOnClickListener {
            showEditDialog()
        }

        // Handle click event on the change photo button
        binding.changePhotoButton.setOnClickListener {
            openImageChooser()
        }
    }

    private fun showEditDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edit_profile, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.nameEditText)
        val bioEditText = dialogView.findViewById<EditText>(R.id.bioEditText)

        // Pre-fill the dialog with existing values
        nameEditText.setText(profileName)
        bioEditText.setText(profileBio)

        AlertDialog.Builder(requireContext())
            .setTitle("Edit Profile")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                // Save the edited values
                profileName = nameEditText.text.toString()
                profileBio = bioEditText.text.toString()

                // Update the profile view after editing
                updateProfile()

                // Notify the system that the options menu has changed
                requireActivity().invalidateOptionsMenu()

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun updateProfile() {
        // Update the view with the new profile information
        binding.nameTextView.text = profileName
        binding.bioTextView.text = profileBio
    }

    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri = data.data!!

            // Remove any tint that might be applied to the ImageView
            binding.profileImage.setColorFilter(null)

            // Update the profile photo with the selected image
            binding.profileImage.setImageURI(selectedImageUri)

            Toast.makeText(requireContext(), "Selected Image: $selectedImageUri", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
