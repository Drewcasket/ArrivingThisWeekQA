package detroitlabs.arrivingthisweekqa;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import detroitlabs.arrivingthisweekqa.formatters.ComixologyDateFormatter;
import detroitlabs.arrivingthisweekqa.formatters.DateFormatter;


public class ComicReleaseListActivity extends FragmentActivity implements ActionBar.TabListener {

    public static final String COMPANY_LOGO = "compLogo";
    public static final String COMPANY_TITLE = "compTitle";
    ViewPager viewPager=null;
    ActionBar actionBar;
    private String companyTitle;
    private int companyLogo;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_main);
        companyLogo = getIntent().getIntExtra(COMPANY_LOGO, 0);
        companyTitle = getIntent().getStringExtra(COMPANY_TITLE);
        viewPager= (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ComicReleaseListAdapter(getSupportFragmentManager()));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                actionBar.setSelectedNavigationItem(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        actionBar=getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setTitle(companyTitle);
        actionBar.setIcon(companyLogo);

        ActionBar.Tab lastWeekTab=actionBar.newTab();
        lastWeekTab.setText("Last Week");
        lastWeekTab.setTabListener(this);

        ActionBar.Tab currentWeekTab=actionBar.newTab();
        currentWeekTab.setText("Current Week");
        currentWeekTab.setTabListener(this);

        ActionBar.Tab nextWeekTab=actionBar.newTab();
        nextWeekTab.setText("Next Week");
        nextWeekTab.setTabListener(this);

        actionBar.addTab(lastWeekTab);
        actionBar.addTab(currentWeekTab);
        actionBar.addTab(nextWeekTab);
        actionBar.selectTab(currentWeekTab);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());


    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    class ComicReleaseListAdapter extends FragmentStatePagerAdapter {
        DateFormatter dateFormatter;

        public ComicReleaseListAdapter(FragmentManager fm) {
            super(fm);
            dateFormatter = new ComixologyDateFormatter(companyTitle);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment=null;
            if(i==0) {
                fragment = new CurrentWeekFragment();

            }
            if(i==1) {
                fragment=new CurrentWeekFragment();
            }
            if(i==2) {
                fragment=new CurrentWeekFragment();

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        String title=new String();
//        if(position==0) {
//            return "Publishers";
//        }
//        if(position==1) {
//            return "Favorites";
//        }
//        if(position==2) {
//            return "Search";
//        }
//        return super.getPageTitle(position);
//    }

    }
}