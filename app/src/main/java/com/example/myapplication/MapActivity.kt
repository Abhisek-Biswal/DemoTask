package com.example.myapplication

import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.*

class MapActivity : AppCompatActivity() , OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    var currentMarker : Marker? = null
    private lateinit var googleMap: GoogleMap
    var fusedLocationProviderClient: FusedLocationProviderClient?= null
    var currentLocation: Location?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Get the SupportMapFragment and request notification when the map is ready to be used.
//        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
//        mapFragment?.getMapAsync(this)


        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)
        fetchLocation()


    }
    private fun fetchLocation(){
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf( android.Manifest.permission.ACCESS_FINE_LOCATION),1000)
            return
        }
        val task = fusedLocationProviderClient?.lastLocation
        task?.addOnSuccessListener { location ->
            if(location!= null){
                this.currentLocation= location
                val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
                mapFragment?.getMapAsync(this)
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1000 -> if(grantResults.size>0  && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                fetchLocation()
        }
    }

    //Use the onMapReady callback method to get a handle to the GoogleMapobject
    override fun onMapReady(map1: GoogleMap?) {
        googleMap = map1!!
        // Set the map coordinates to Kyoto Japan.
//        val sydney = LatLng(-33.852, 151.211)
//        // Add a marker on the map coordinates.
//        googleMap.addMarker(
//            MarkerOptions()
//                .position(sydney)
//                .title("Marker in Sydney")
//        )
//        // Move the camera to the map coordinates and zoom in closer.
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f)) //zoom level from 0 to 21
//        googleMap.mapType= GoogleMap.MAP_TYPE_NORMAL//setting map type
//        googleMap.setOnMarkerClickListener(this)
//        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.map_style))
//        googleMap.setTrafficEnabled(true);

        val latLong = LatLng(currentLocation?.latitude!!,currentLocation?.longitude!!)
        drawMarker(latLong)

        googleMap.setOnMarkerDragListener(object: GoogleMap.OnMarkerDragListener)
        fun onMarkerDrag(p0: Marker?) {

        }
        override fun onMarkerDragEnd(p0: Marker?) {

            if(currentMarker!=null){
                currentMarker?.remove()

                val newLatLng=LatLng(p0?.position!!.latitude,p0?.position!!.latitude)
                drawMarker(newLatLng)
            }
        }
         fun onMarkerDragStart(m1: Marker?) {

        }

    }
    private fun drawMarker(latLong: LatLng){
        val markerOptions=MarkerOptions().position(latLong).title("I am Abhisek")
            .snippet.(getAddress(latLong.latitude, latLong.latitude)).draggable(true)

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLong))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLong,15f))
        currentMarker=googleMap.addMarker(markerOptions)
        currentMarker.showInfoWindow()

    }
    private fun getAddress(lat: Double,lon: Double): String?{
        val geocoder = Geocoder(this, Locale.getDefault())
        val address = geocoder.getFromLocation(lat,lon,1)
        return address[0].getAddressLine(0).toString()
    }



    //method to draw a circle on the map
//    override fun onMarkerClick(marker: Marker?): Boolean {
//        val circleOptions = CircleOptions()
//            .center(marker?.position)
//            .radius(100000000000000000.0)
//            .strokeColor(Color.DKGRAY)
//            .fillColor(Color.RED)
//
//        googleMap.addCircle(circleOptions)
//
//        return false
//    }

}
