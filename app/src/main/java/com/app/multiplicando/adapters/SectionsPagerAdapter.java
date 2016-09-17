package com.app.multiplicando.adapters;

/**
 * Created by Miguel on 11/09/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.app.multiplicando.AndroidVideoPlayer;
import com.app.multiplicando.PlaceholderFragment;
import com.app.multiplicando.interfaces.IComunication;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter implements IComunication
{

    private PlaceholderFragment actual;

    public SectionsPagerAdapter( FragmentManager fm )
    {
        super( fm );
    }

    @Override
    public Fragment getItem(int position )
    {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class
        // below).
        Log.i( "posicion", position+"" );
        switch ( position )
        {
            case 1:
                return new AndroidVideoPlayer( );
            default:
                actual = PlaceholderFragment.newInstance(  );
                return actual;
        }

    }

    @Override
    public int getCount( )
    {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle( int position )
    {
        //	Locale l = Locale.getDefault( );
        switch ( position )
        {
            case 0:
                return "Juegos";
            case 1:
                return "Videos";
        }
        return null;
    }

    @Override
    public void comunicate() {
        actual.cambiarPantallaPrincipal(true);
    }

    @Override
    public boolean isInHome() {
        boolean home = actual == null ? true : actual.isInHome();
        return home;
    }
}
