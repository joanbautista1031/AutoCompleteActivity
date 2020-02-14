package com.jlbautista1031.autocompleteactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode

class MainActivity : AppCompatActivity() {

    companion object{

        const val AUTOCOMPLETE_REQUEST_ID = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializePlaces()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == MainActivity.AUTOCOMPLETE_REQUEST_ID) {

            if(resultCode == Activity.RESULT_OK){

                val place = Autocomplete.getPlaceFromIntent(data!!)

                onSelectedPlace(place)
            }
            else
                onNoSelectedPlace()

        }

    }

    /*
    * Initializes the Places object.
    * */
    private fun initializePlaces(){

        val apiKey = BuildConfig.GOOGLE_API_KEY

        if(!Places.isInitialized())
            // Make sure to initialize Places before making any call to methods or properties related to Places object, such as the autocomplete search function.
            Places.initialize(this, apiKey)

    }

    fun onSearchPlace(view : View){

        showSearch()
    }

    private fun showSearch(){

        //Here you can specify the fields of the Place object you want to include in the search results
        val fields = mutableListOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this)

        //This will launch the AutoCompleteActivity. The request code AUTOCOMPLETE_REQUEST_ID identifies the intent and is used to handle the result from the AutoComplete search
        startActivityForResult(intent, MainActivity.AUTOCOMPLETE_REQUEST_ID)
    }

    private fun onSelectedPlace(place: Place){

        setResultView(true)
        setResultViewValues(place)
    }

    private fun onNoSelectedPlace(){

        setResultView(false)
    }

    private fun setResultView(hasSelectedPlace : Boolean){

        val resultPanel = findViewById<Group>(R.id.view_group)
        val messageView = findViewById<TextView>(R.id.result_message)

        resultPanel.isVisible = hasSelectedPlace
        val message = getString(if(hasSelectedPlace) R.string.you_selected else R.string.you_did_not_select)
        messageView.setText(message)

    }


    private fun setResultViewValues(place : Place){

        val name = findViewById<TextView>(R.id.name)
        val address = findViewById<TextView>(R.id.address)
        val id = findViewById<TextView>(R.id.id)
        val latitude = findViewById<TextView>(R.id.latitude)
        val longitude = findViewById<TextView>(R.id.longitude)


        name.text = place.name
        address.text = place.address
        id.text = place.id
        latitude.text = String.format("%.6f",place.latLng?.latitude)
        longitude.text = String.format("%.6f",place.latLng?.longitude)
    }

}
