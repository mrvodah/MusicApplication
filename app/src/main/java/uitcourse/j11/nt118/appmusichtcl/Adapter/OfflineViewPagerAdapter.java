package uitcourse.j11.nt118.appmusichtcl.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class OfflineViewPagerAdapter extends FragmentPagerAdapter {

    // Danh sách các fragment để đưa vào ViewPager
    private ArrayList<Fragment> arrayFragment = new ArrayList<>();

    // Danh sách title của fragment
    private ArrayList<String> arraytitle = new ArrayList<>();

    public OfflineViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayFragment.size();
    }


    // Them fragment
    public void addFragment(Fragment fragment, String title)
    {
        arrayFragment.add(fragment);
        arraytitle.add(title);
    }

    public CharSequence getPageTitle(int position) {
        return arraytitle.get(position);
    }


}
