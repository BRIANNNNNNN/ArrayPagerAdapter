package com.demo.arraypargeradapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nakama.arraypageradapter.ArrayFragmentStatePagerAdapter;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nakama on 2016/03/31.
 */
public class FragmentStatePagerAdapterActivity extends AppCompatActivity {

    @BindView(R.id.control_view)
    ControlView controlView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);
        ArrayList<Dog> dogs = new ArrayList<>();
        ControlView.DogItemCreator creator = new ControlView.DogItemCreator();
        for (int i = 0; i < 4; i++) {
            dogs.add(creator.createItem());
        }
        MyStatePagerAdapter adapter = new MyStatePagerAdapter(getSupportFragmentManager(), dogs);
        viewPager.setAdapter(adapter);
        controlView.setAdapter(adapter);
        controlView.setItemCreator(creator);
    }

    public static class MyFragment extends Fragment {
        static MyFragment newInstance(Dog dog) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", dog);
            MyFragment f = new MyFragment();
            f.setArguments(bundle);
            return f;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View v = inflater.inflate(R.layout.item_dog_page, container, false);
            Dog dog = getArguments().getParcelable("data");

            ((TextView) v.findViewById(R.id.name_txt)).setText(dog.getName());
            ((TextView) v.findViewById(R.id.favorite_txt)).setText(dog.getFavoriteFood());
            ((TextView) v.findViewById(R.id.age_txt)).setText(String.valueOf(dog.getAge()));

            return v;
        }
    }

    private class MyStatePagerAdapter extends ArrayFragmentStatePagerAdapter<Dog> {

        public MyStatePagerAdapter(FragmentManager fm, ArrayList<Dog> dogs) {
            super(fm, dogs);
        }

        @Override
        public Fragment getFragment(Dog item, int position) {
            return MyFragment.newInstance(item);
        }
    }

}
