package c.kaitlyn.chainmaillecalculations;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import c.kaitlyn.chainmaillecalculations.fragments.calc_tab;
import c.kaitlyn.chainmaillecalculations.fragments.instruction_tab;
import c.kaitlyn.chainmaillecalculations.fragments.instruction_tab;
import c.kaitlyn.chainmaillecalculations.fragments.inventory_tab;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return new calc_tab();
            case 1:
                return new instruction_tab();
            case 2:
                return new inventory_tab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Calculator";
            case 1:
                return "Instruction";
            case 2:
                return "Inventory";
        }
        return null;
    }
}
