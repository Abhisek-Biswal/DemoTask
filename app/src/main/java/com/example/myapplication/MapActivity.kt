package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.R.id
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.*

class MapActivity : AppCompatActivity() , OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private lateinit var marker : Marker
    private  val locationPermissionRequestCode = 1
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var location : Location
    private lateinit var geocoder : Geocoder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment =
            supportFragmentManager.findFragmentById(id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapClick(latlng: LatLng) {
        marker.remove()
        val geocoder = Geocoder(this)
        val address : List<Address> = geocoder.getFromLocation(latlng.latitude,latlng.longitude,1) as List<Address>
        marker = mMap.addMarker(MarkerOptions().position(latlng).title(address[0].getAddressLine(0)))!!
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,10f))
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap.mapType = MAP_TYPE_NORMAL
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
            getCurrentLocation()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionRequestCode)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            id.default_type -> mMap.mapType = MAP_TYPE_NORMAL
            id.satellite_type -> mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            id.terrain_type -> mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            id.hybrid_type -> mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

            id.current_map -> {
                if(item.isChecked) {
                    mMap.uiSettings.isMyLocationButtonEnabled  = true
                    item.isChecked = false
                }else{
                    mMap.uiSettings.isMyLocationButtonEnabled  = false
                    item.isChecked = true
                }


                Toast.makeText(this, item.isChecked.toString(), Toast.LENGTH_SHORT).show()
            }
            id.current_pointer -> {
                if (item.isChecked){
                    getCurrentLocation()
                    item.isChecked = false
                }else{
                    marker.remove()
                    item.isChecked = true
                }
            }
            id.zoom_btn -> {
                if(item.isChecked) {
                    mMap.uiSettings.isZoomControlsEnabled = true
                    item.isChecked = false
                }else{
                    mMap.uiSettings.isZoomControlsEnabled = false
                    item.isChecked = true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun bitMapFromVector(context: Context, vectorResId:Int): BitmapDescriptor? {
        val vectorDrawable: Drawable = ContextCompat.getDrawable(context,vectorResId)!!
        vectorDrawable.setBounds(0,0,vectorDrawable.intrinsicWidth,vectorDrawable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth,vectorDrawable.intrinsicHeight,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if(isLocationEnabled()){
            fusedLocationClient.lastLocation.addOnCompleteListener(this){
                    task-> location = task.result!!
                geocoder = Geocoder(this, Locale.getDefault())

                val list : List<Address>? = geocoder.getFromLocation(location.latitude,
                    location.longitude,1)
                val address : Address? = list?.get(0)

                marker = mMap.addMarker(MarkerOptions().position(
                    LatLng(location.latitude,
                        location.longitude)
                ).title(address?.getAddressLine(0)).icon(bitMapFromVector(applicationContext, R.drawable.ic_bike)))!!

                mMap.animateCamera(CameraUpdateFactory.
                newLatLngZoom(LatLng(location.latitude,
                    location.longitude),11f))
            }
        }else{
            Toast.makeText(this, "Please turn on Location", Toast.LENGTH_SHORT).show()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(
                        this,android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED
                ) {
                    mMap.isMyLocationEnabled = true
                    getCurrentLocation()
                }
            }
        }
    }
}



