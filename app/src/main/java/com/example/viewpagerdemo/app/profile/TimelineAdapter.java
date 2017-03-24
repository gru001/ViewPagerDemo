package com.example.viewpagerdemo.app.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.viewpagerdemo.app.R;
import com.example.viewpagerdemo.app.ViewPagerDemoApplication;
import com.example.viewpagerdemo.app.profile.models.Reaction;
import com.example.viewpagerdemo.app.utils.CropCircleTransformation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder> {
    public static final String TAG = TimelineAdapter.class.getSimpleName();
    private List<Reaction> mUsersList;
    private ItemClickListener itemClickListener;

    public TimelineAdapter(ItemClickListener itemClickListener) {
        mUsersList = new ArrayList<>();
        this.itemClickListener = itemClickListener;
    }

    public void setUsers(List<Reaction> usersList) {
        Log.i(TAG,"getItemCount()--> "+usersList.size());
        this.mUsersList.clear();
        this.mUsersList.addAll(usersList);
        notifyDataSetChanged();
    }

    public List<Reaction> getmUsersList() {
        return mUsersList;
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline, parent, false);
        return new TimelineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TimelineViewHolder holder, int position) {
        final Reaction mUsers = mUsersList.get(position);
        Log.i(TAG, "onBindViewHolder: "+mUsers.getUserProfilePicUrl());
        final Context context = holder.imgProfilePic.getContext();
        Glide.with(ViewPagerDemoApplication.getInstance()).load(mUsers.getUserProfilePicUrl())
                .bitmapTransform(new CropCircleTransformation(ViewPagerDemoApplication.getInstance()))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgProfilePic);
    }

    @Override
    public int getItemCount() {
        Log.i(TAG,"getItemCount()--> "+mUsersList.size());
        return mUsersList.size();
    }

    class TimelineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.img_profile_pic) ImageView imgProfilePic;

        public TimelineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(int pos);
    }
}