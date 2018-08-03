package com.perfomatix.namelistapp.view.listing;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.perfomatix.namelistapp.R;
import com.perfomatix.namelistapp.adapter.NameListAdapter;
import com.perfomatix.namelistapp.view.BaseActivity;
import com.perfomatix.namelistapp.viewmodel.listing.NameListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This is the Main Activity of the app which contains the list of
 * contacts parsed from the provided JSON
 */
public class NameListActivity extends BaseActivity {

    @BindView(R.id.recyclerview_nameslist)
    RecyclerView recyclerViewNameList;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.text_no_content)
    TextView textNoContent;

    private NameListViewModel nameListViewModel;
    private NameListAdapter nameListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names_list);
        ButterKnife.bind(this);

        nameListViewModel = ViewModelProviders.of(this).get(NameListViewModel.class);
        nameListAdapter = new NameListAdapter();

        recyclerViewNameList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNameList.setHasFixedSize(true);
        recyclerViewNameList.setAdapter(nameListAdapter);

        nameListViewModel.getPersons().observe(this, listResource -> {
            switch (listResource.status){
                case SUCCESS:
                    nameListAdapter.setPersonList(listResource.data);
                    nameListAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);
                    textNoContent.setVisibility(View.INVISIBLE);
                    break;
                case ERROR:
                    textNoContent.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    break;
                case LOADING:
                    progressBar.setVisibility(View.VISIBLE);
                    textNoContent.setVisibility(View.INVISIBLE);
                    break;
            }
        });

    }
}
