package com.demo.arraypargeradapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nakama.arraypageradapter.ArrayViewPagerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nakama on 2016/03/31.
 */
public class ViewPagerAdapterActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.control_view)
    ControlView controlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);
        MyPagerAdapter adapter = new MyPagerAdapter(new String[]{"1", "2", "3"});
        viewPager.setAdapter(adapter);
        controlView.setAdapter(adapter);
    }

    private class MyPagerAdapter extends ArrayViewPagerAdapter<String> {
        public MyPagerAdapter(String[] data) {
            super(data);
        }

        @Override
        public View getView(LayoutInflater inflater, ViewGroup container, String item, int position) {
            View v = inflater.inflate(R.layout.item_text_page, container, false);
            ((TextView) v.findViewById(R.id.item_txt)).setText(item);
            return v;
        }

    }
}
