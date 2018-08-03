package com.perfomatix.namelistapp.adapter;

import android.databinding.ViewDataBinding;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.perfomatix.namelistapp.BR;
import com.perfomatix.namelistapp.R;
import com.perfomatix.namelistapp.databinding.ItemRowPersonBinding;
import com.perfomatix.namelistapp.model.Person;
import com.perfomatix.namelistapp.utility.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter to get person details from the list and
 * bind the person details to recycler UI
 */
public class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.NameListViewHolder>{

    List<Person> personList = new ArrayList<>();

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public NameListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRowPersonBinding itemRowPersonBinding = ItemRowPersonBinding.inflate(layoutInflater, parent, false);
        return new NameListViewHolder(itemRowPersonBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NameListViewHolder holder, int position) {
        holder.bind(personList.get(position));

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    /**
     * ViewHolder class to bind each details to viewholder item
     */
    class NameListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_user_layout)
        FrameLayout circleLayout;

        private final ViewDataBinding dataBinding;

        public NameListViewHolder(ViewDataBinding dataBinding) {
            super(dataBinding.getRoot());
            ButterKnife.bind(this, dataBinding.getRoot());
            this.dataBinding = dataBinding;
        }

        public void bind(Person person){
            dataBinding.setVariable(BR.person, person);
            dataBinding.executePendingBindings();
            Utils.customView(circleLayout, Utils.getColor(itemView.getContext(), R.array.avatarColors));
        }
    }

}
