package tporto.com.message.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import tporto.com.message.fragment.ContatosFragment;
import tporto.com.message.fragment.ConversasFragment;

/**
 * Created by thiago on 11/09/16.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    private String[] tituloAba = {"CONVERSAS","CONTATOS"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new ConversasFragment();
                break;
            case 1:
                fragment = new ContatosFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tituloAba.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tituloAba[position];
    }
}
