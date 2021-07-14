package com.techshiv.collegeapp;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.firestore.auth.User;

public class AdapterLetestNews extends FirebaseRecyclerAdapter<NewsDataModelClass,AdapterLetestNews.newsDataHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public AdapterLetestNews(@NonNull FirebaseRecyclerOptions<NewsDataModelClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull newsDataHolder holder, int position, @NonNull NewsDataModelClass model) {
    holder.newsTitle.setText(model.getNewsTitle());
    holder.newsDesc.setText(model.getNews());
    holder.newsImage.setImageURI(Uri.parse(model.PostImage));
    Glide.with(holder.newsImage.getContext()).load(model.PostImage).into(holder.newsImage);
    holder.timedatenewsstud.setText(model.getNewsTimeDate());
    Student_news_Fragment.progressDialog.dismiss();

    }

    @NonNull
    @Override
    public newsDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.newscard,parent,false);
        return new newsDataHolder(view);
    }
    @Override
    public NewsDataModelClass getItem(int position) {
        return super.getItem(getItemCount() - 1 - position);
    }
    public class newsDataHolder extends RecyclerView.ViewHolder{
        TextView newsDesc,newsTitle,timedatenewsstud;
        ImageView newsImage;
        public newsDataHolder(@NonNull View itemView) {
            super(itemView);
            newsDesc=itemView.findViewById(R.id.newsDesc);
            newsTitle=itemView.findViewById(R.id.newsTitle);
            timedatenewsstud=itemView.findViewById(R.id.timedatenewsstud);
            newsImage=itemView.findViewById(R.id.newsImage);
        }
    }
}
