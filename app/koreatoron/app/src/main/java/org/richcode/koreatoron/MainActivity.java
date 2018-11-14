package org.richcode.koreatoron;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.ag.floatingactionmenu.OptionsFabLayout;

import org.richcode.koreatoron.Dialog.MyDialogListener;
import org.richcode.koreatoron.Dialog.ExitDialog;

public class MainActivity extends AppCompatActivity {

    ViewPager ViewPage;

    LinearLayout TabLayout;

    Button ToronButton;
    Button BestToronButton;
    Button WriteButton;

    OptionsFabLayout optionsFabLayout;


    private void UIgenerate(){
        ViewPage = (ViewPager)findViewById(R.id.viewpager);

        TabLayout = (LinearLayout)findViewById(R.id.main_title_bar);

        ToronButton = (Button)findViewById(R.id.toron_button);
        BestToronButton = (Button)findViewById(R.id.best_toron_button);
        WriteButton = (Button)findViewById(R.id.write_button);

        optionsFabLayout = (OptionsFabLayout)findViewById(R.id.fab_chat_options);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIgenerate();

        ViewPage.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        ViewPage.setOffscreenPageLimit(2);
        ViewPage.setCurrentItem(0);

        ToronButton.setOnClickListener(movePageListener);
        ToronButton.setTag(0);
        BestToronButton.setOnClickListener(movePageListener);
        BestToronButton.setTag(1);

        ToronButton.setSelected(true);

        WriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String href = "https://www1.president.go.kr/forums/new";

                Intent intent = new Intent(getApplicationContext(),WebBroswerActivity.class);
                intent.putExtra("link",href);

                startActivity(intent);
            }
        });

        optionsFabLayout.setMiniFabsColors(
                R.color.colorPrimary
        );

        optionsFabLayout.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        optionsFabLayout.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {
                switch (fabItem.getItemId()){
                    case R.id.fab_open_link:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.kakao.com/o/gWm74xU")));
                        break;
                }
            }
        });

        ViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                int i = 0;
                while(i<2)
                {
                    if(position==i)
                    {
                        TabLayout.findViewWithTag(i).setSelected(true);
                    }
                    else
                    {
                        TabLayout.findViewWithTag(i).setSelected(false);
                    }
                    i++;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

    }

    View.OnClickListener movePageListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int tag = (int) v.getTag();

            int i = 0;

            while(i<2){
                if(tag==i){
                    TabLayout.findViewWithTag(i).setSelected(true);
                }
                else{
                    TabLayout.findViewWithTag(i).setSelected(false);
                }
                i++;
            }
            ViewPage.setCurrentItem(tag);
        }
    };

    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new FragmentToron();
                case 1:
                    return new FragmentToronBest();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 2;
        }
    }
}
