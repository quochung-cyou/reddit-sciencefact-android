package com.quochung.redditapi_sciencefact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.quochung.redditapi_sciencefact.R;
import com.quochung.redditapi_sciencefact.model.PostModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private Context context;
    private List<PostModel> postlist;

    public PostAdapter(Context context , List<PostModel> post){
        this.context = context;
        postlist = post;
    }
    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
        return new PostHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        PostModel post = postlist.get(position);
        holder.title.setText(post.getTitle().toString());
        holder.author.setText(post.getAuthor().toString());


        try {
            Glide.with(context).load(post.getThumbnail()).placeholder(R.drawable.testimage).into(holder.thumbnail);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        try {
            Glide.with(context).load(post.getAvatar()).placeholder(R.drawable.avatar).into(holder.avatar);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return postlist.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        CircleImageView avatar;
        TextView author, title;
        RoundedImageView thumbnail;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.profile_image);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
