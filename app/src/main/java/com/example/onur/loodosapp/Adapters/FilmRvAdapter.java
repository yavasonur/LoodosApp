package com.example.onur.loodosapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.onur.loodosapp.Model.Film;
import com.example.onur.loodosapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmRvAdapter extends RecyclerView.Adapter<FilmRvAdapter.MyViewHolder>{

    private Context context;
    private List<Film> films;

    public FilmRvAdapter(Context c, List list){
        this.context = c;
        this.films = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.film_item_row,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try{
            Glide.with(context).load(films.get(position).getPoster()).into(holder.filmImageView);
            holder.txtFilmTitle.setText(films.get(position).getTitle());
            holder.txtFilmType.setText("Film Type : " + films.get(position).getType());
            holder.txtFilmYear.setText("Year : " + films.get(position).getYear());

        }catch (Exception e){
        }
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cardView) CardView cardView;
        @BindView(R.id.filmImageView) ImageView filmImageView;
        @BindView(R.id.txtFilmTitle) TextView txtFilmTitle;
        @BindView(R.id.txtFilmType) TextView txtFilmType;
        @BindView(R.id.txtFilmYear) TextView txtFilmYear;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void setFilter(List<Film> newList){
        films = new ArrayList<>();
        films.addAll(newList);
        notifyDataSetChanged();
    }
}
