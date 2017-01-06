package com.example.pedro.projecto_opvc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapController;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;


public class menu extends Activity {
    private MapView myOpenMapView;
    private MapController myMapController;
    LocationManager locationManager;

    ArrayList<OverlayItem> overlayItemArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        myOpenMapView = (MapView)findViewById(R.id.mapView);
        myOpenMapView.setBuiltInZoomControls(false);
        myOpenMapView.setMultiTouchControls(true);
        myOpenMapView.setMinZoomLevel(11);
        myOpenMapView.setMaxZoomLevel(20);
        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setZoom(12);

        overlayItemArray = new ArrayList<OverlayItem>();

        DefaultResourceProxyImpl defaultResourceProxyImpl = new DefaultResourceProxyImpl(this);
        MyItemizedIconOverlay myItemizedIconOverlay = new MyItemizedIconOverlay(overlayItemArray, null, defaultResourceProxyImpl);
        myOpenMapView.getOverlays().add(myItemizedIconOverlay);


        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastLocation != null){
            updateLoc(lastLocation);
        }

        //Add Scale Bar
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
        myOpenMapView.getOverlays().add(myScaleBarOverlay);
    }

    @Override
    protected void onResume() {

        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, myLocationListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(myLocationListener);
    }

    private void updateLoc(Location loc){
        GeoPoint locGeoPoint = new GeoPoint(loc.getLatitude(), loc.getLongitude());
        myMapController.setCenter(locGeoPoint);

        setOverlayLoc(loc);

        myOpenMapView.invalidate();
    }

    private void setOverlayLoc(Location overlayloc){
        GeoPoint overlocGeoPoint = new GeoPoint(overlayloc);
        //---
        overlayItemArray.clear();

        OverlayItem newMyLocationItem = new OverlayItem(
                "My Location", "My Location", overlocGeoPoint);
        overlayItemArray.add(newMyLocationItem);
        //---
    }

    private LocationListener myLocationListener = new LocationListener(){

        @Override
        public void onLocationChanged(Location location) {

            updateLoc(location);
        }

        @Override
        public void onProviderDisabled(String provider) {


        }

        @Override
        public void onProviderEnabled(String provider) {


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

    };
    private class MyItemizedIconOverlay extends ItemizedIconOverlay<OverlayItem>{

        public MyItemizedIconOverlay(ArrayList<OverlayItem> pList, OnItemGestureListener<OverlayItem> pOnItemGestureListener,DefaultResourceProxyImpl pResourceProxy) {
            super(pList, pOnItemGestureListener, pResourceProxy);
        }

        @Override
        public void draw(Canvas canvas, MapView mapview, boolean arg2) {
            super.draw(canvas, mapview, arg2);

            if(!overlayItemArray.isEmpty()){
                GeoPoint in = overlayItemArray.get(0).getPoint();

                Point out = new Point();
                mapview.getProjection().toPixels(in, out);

                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_location);
                canvas.drawBitmap(bm,out.x-bm.getWidth()/2,out.y-bm.getHeight()/2, null);
            }
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e, MapView mapView) {

            File map = Environment.getExternalStorageDirectory();
            Projection proj = mapView.getProjection();
            IGeoPoint p = proj.fromPixels((int) e.getY(), (int) e.getX());
            String longitude = Double.toString(((double) p.getLongitudeE6()) / 1000000);
            String latitude = Double.toString(((double) p.getLatitudeE6()) / 1000000);
            Toast toast = Toast.makeText(getApplicationContext(), "Longitude: " + longitude + " Latitude: " + latitude, Toast.LENGTH_LONG);
            toast.show();
            Intent i = new Intent(menu.this, InserirRua.class);
            i.putExtra("longitude",longitude);
            i.putExtra("latitude",latitude);
            startActivity(i);
            return true;
        }
        public boolean onSingleTapUp(MotionEvent event, MapView mapView) {

            return true;
        }
        public boolean onLongPress(MotionEvent event, MapView mapView){
            return true;
        }
    }
}