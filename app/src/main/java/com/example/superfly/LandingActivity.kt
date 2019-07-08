package com.example.superfly

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.content_landing.*
import kotlinx.android.synthetic.main.spinner_item.view.*
import java.io.IOException
import java.util.*

import kotlin.math.ln

class LandingActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    override fun onMarkerClick(p0: Marker?) = false

    //    private var mMap?: GoogleMap? = null
    private lateinit var mMap: GoogleMap

    data class LatitudeLongitude(val lat: Double, val lng: Double )

    var lat:Double? = null

    var lng: Double? = null

    private lateinit var lastLocation: Location

    val personNames = arrayOf("Ikeja", "Maryland", "Victoria Island", "Ajah", "Surulere", "Berger", "Ketu")

    val times = arrayOf("7:00 AM - 7:45 AM", "12:00 PM - 12:45 PM", "5:00AM - 5:45 PM")

    val ikeja = LatLng(6.5801382,3.3415718)
    val maryland = LatLng(6.5766924,3.3611775)
    val vicIsland = LatLng(6.4299843,3.4141327)
    val ajah = LatLng(6.4686055,3.5634609)
    val surulere = LatLng(6.489769,3.330016)
    val berger = LatLng(6.6408374,3.3710828)
    val ketu = LatLng(6.596402,3.3813)

    var spinner: Spinner? = null





    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        navView.setNavigationItemSelectedListener(this)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)









        spinner = findViewById<Spinner>(R.id.spinner)

        if (timeSpinner != null){

            val arraayAdap = ArrayAdapter(this, android.R.layout.simple_spinner_item, times)

            timeSpinner!!.adapter = arraayAdap
            timeSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {


                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }


            }

        }
        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, personNames)
//            val arrayAdapter = ArrayAdapter(this, R.layout.spinner_item, personNames)

            spinner!!.adapter = arrayAdapter

            spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                    Toast.makeText(this@LandingActivity, getString(R.string.selected_item) + " " + personNames[position], Toast.LENGTH_SHORT).show()

//                    Log.e("First letter???", personNames[position][0].toString())
                    if (personNames[position][0].equals("I")){
                        placeMarkerOnMap(ikeja)
                    }else if (personNames[position][0].equals("M")){
                        placeMarkerOnMap(maryland)
                    }else if (personNames[position][0].equals("V")){
                        placeMarkerOnMap(vicIsland)
                    }else if (personNames[position][0].equals("A")){
                        placeMarkerOnMap(ajah)
                    }else if (personNames[position][0].equals("S")){
                        placeMarkerOnMap(surulere)
                    }else if (personNames[position][0].equals("B")){
                        placeMarkerOnMap(berger)
                    }else if (personNames[position][0].equals("K")){
                        placeMarkerOnMap(ketu)
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }



    }

    fun cont(view: View){

        val intent = Intent(this, BookingActivity::class.java)
        startActivity(intent)
    }
    fun prof(view: View){

        val intent = Intent(this, ProfilePilotActivity::class.java)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDataPicker(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in Toast
            //Toast.makeText(this, """$dayOfMonth - ${monthOfYear + 1} - $year""", Toast.LENGTH_LONG).show()

            selectDate.text = """$dayOfMonth - ${monthOfYear + 1} - $year"""

        }, year, month, day)
        dpd.show()
    }



    private fun placeMarkerOnMap(location: LatLng) {
        // 1
        val markerOptions = MarkerOptions().position(location)
        // 2
        val titleStr = getAddress(location)  // add these two lines
        markerOptions.title(titleStr).position(location)

        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 8f))
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        mMap.isMyLocationEnabled = true


        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN



        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                //placeMarkerOnMap(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
    }

    private fun getAddress(latLng: LatLng): String {
        // 1
        val geocoder = Geocoder(this)
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            // 2
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            // 3
            if (null != addresses && !addresses.isEmpty()) {
                address = addresses[0]
                for (i in 0 until address.maxAddressLineIndex) {
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
                }
            }
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }

        return addressText
    }


    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!

        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)


//        val your_location = LatLng(this!!.lat!!, this!!.lng!!)
//
//        mMap.addMarker(MarkerOptions().position(your_location).title("Your location"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(your_location, 12.0f))


        setUpMap()

        // 1


            mMap.isMyLocationEnabled = true

// 2
            fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
                // Got last known location. In some rare situations this can be null.
                // 3
                if (location != null) {
                    lastLocation = location
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                }
            }

        mMap!!.addMarker(MarkerOptions().position(ajah).title("Ajah"))
        mMap!!.addMarker(MarkerOptions().position(ikeja).title("Ikaja"))
        mMap!!.addMarker(MarkerOptions().position(maryland).title("Maryland"))
        mMap!!.addMarker(MarkerOptions().position(surulere).title("Surulere"))
        mMap!!.addMarker(MarkerOptions().position(berger).title("Berger"))
        mMap!!.addMarker(MarkerOptions().position(ketu).title("Ketu"))


        spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@LandingActivity, getString(R.string.selected_item) + " " + personNames[position], Toast.LENGTH_SHORT).show()

                    Log.e("First letter???", personNames[position][0].toString())
                    if (personNames[position][0].equals("I")){
//                    placeMarkerOnMap(ikeja)


                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ikeja, 8f))
                    }else if (personNames[position][0].equals("M")){
                        placeMarkerOnMap(maryland)

                        Log.e("Tapped??", "Maryland")
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(maryland, 8f))


                    }else if (personNames[position][0].equals("V")){
                        placeMarkerOnMap(vicIsland)

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vicIsland, 8f))


                    }else if (personNames[position][0].equals("A")){
                        placeMarkerOnMap(ajah)

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ajah, 8f))

                    }else if (personNames[position][0].equals("S")){
                        placeMarkerOnMap(surulere)
                    }else if (personNames[position][0].equals("B")){
                        placeMarkerOnMap(berger)
                    }else if (personNames[position][0].equals("K")){
                        placeMarkerOnMap(ketu)
                    }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }




    }
    fun getLatt(lat: Double) {

        this.lat = lat

    }
    fun getLng(lng: Double){

        this.lng = lng
    }

    fun getLocation() {

        var locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        var locationListener = object : LocationListener {

            override fun onLocationChanged(location: Location?) {

                var latitute = location!!.latitude
                var longitute = location!!.longitude

                Log.i("test", "Latitute: $latitute ; Longitute: $longitute")


                getLatt(latitute)
                getLng(longitute)


            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }



        }

        try {
            locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        } catch (ex:SecurityException) {
//            Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
        }
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)

    }




//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//
//        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> getLocation()
                PackageManager.PERMISSION_DENIED -> Toast.makeText(applicationContext, "Grant Location permission", Toast.LENGTH_SHORT).show() //Tell to user the need of grant permission
            }
        }
    }



    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.landing, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_booking -> {

            }
            R.id.nav_flights -> {

            }
            R.id.nav_view -> {

            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

     fun loadPlacePicker(view: View) {

         val builder = PlacePicker.IntentBuilder()

        try {
            startActivityForResult(builder.build(this@LandingActivity), PLACE_PICKER_REQUEST)
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
//                locationUpdateState = true
//                startLocationUpdates()
            }
        }

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                val place = PlacePicker.getPlace(this, data)
                var addressText = place.name.toString()
                addressText += "\n" + place.address.toString()

                placeMarkerOnMap(place.latLng)
            }
        }

    }


    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100

        private const val LOCATION_PERMISSION_REQUEST_CODE = 1

        // 3
        private const val REQUEST_CHECK_SETTINGS = 2

        private const val PLACE_PICKER_REQUEST = 3

    }
}
